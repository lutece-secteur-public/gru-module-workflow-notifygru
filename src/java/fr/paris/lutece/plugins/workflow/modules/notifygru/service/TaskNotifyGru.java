/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.plugins.grubusiness.business.demand.Demand;
import fr.paris.lutece.plugins.grubusiness.business.notification.BillingAccountBasedSMSNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.BackofficeNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.BroadcastNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.EmailAddress;
import fr.paris.lutece.plugins.grubusiness.business.notification.EmailNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.MyDashboardNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.Notification;
import fr.paris.lutece.plugins.grubusiness.business.notification.SMSNotification;
import fr.paris.lutece.plugins.librarynotifygru.exception.NotifyGruException;
import fr.paris.lutece.plugins.librarynotifygru.services.NotificationService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotifyGruHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.cache.NotifyGruCacheService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.IProvider;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.NotifyGruMarker;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.MarkerProviderService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.AbstractProviderManager;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.IMarkerProvider;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.ProviderManagerUtil;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.business.mailinglist.Recipient;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.mailinglist.AdminMailingListService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppException;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;

/**
 * TaskNotifyGru.
 */
public class TaskNotifyGru extends SimpleTask
{
    /** The _task notify gru config service. */
    // SERVICES
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;

    /** The _task notify gru history service. */
    @Inject
    @Named( NotifyGruHistoryService.BEAN_SERVICE )
    private INotifyGruHistoryService _taskNotifyGruHistoryService;

    /** Lib-NotifyGru sender service */
    @Inject
    @Named( Constants.BEAN_NOTIFICATION_SENDER )
    private NotificationService _notifyGruSenderService;

    @Inject
    private IResourceHistoryService _resourceHistoryService;

    /**
     * {@inheritDoc}
     *
     * @param nIdResourceHistory
     * @param request
     * @param locale
     */
    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
        /* Task Config form cache, it can't be null due to getNotifyGruConfigFromCache algorithm */
        TaskNotifyGruConfig config = NotifyGruCacheService.getInstance( ).getNotifyGruConfigFromCache( _taskNotifyGruConfigService, this.getId( ) );

        String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( config.getIdSpringProvider( ) );
        String strProviderId = ProviderManagerUtil.fetchProviderId( config.getIdSpringProvider( ) );
        AbstractProviderManager providerManager = ProviderManagerUtil.fetchProviderManager( strProviderManagerId );

        if ( providerManager != null )
        {
            ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey( nIdResourceHistory );
            IProvider provider = providerManager.createProvider( strProviderId, resourceHistory, request );

            if ( provider != null )
            {
                NotifyGruHistory notifyGruHistory = new NotifyGruHistory( );
                notifyGruHistory.setIdTask( this.getId( ) );
                notifyGruHistory.setIdResourceHistory( nIdResourceHistory );

                Map<String, Object> model = markersToModel( findMarkers( resourceHistory, provider, config.getMarkerProviders( ), request ) );

                Notification notificationObject = buildNotification( config, provider );

                EmailNotification emailNotification = null;

                boolean bNotifEmpty = true;

                if ( config.isActiveOngletEmail( ) && StringUtils.isNotBlank( provider.provideCustomerEmail( ) ) )
                {
                    emailNotification = buildEmailNotification( config, provider, model );
                    notificationObject.setEmailNotification( emailNotification );
                    notifyGruHistory.setEmail( NotificationToHistory.populateEmail( config, emailNotification ) );
                    bNotifEmpty = false;
                }

                MyDashboardNotification myDashBoardNotification = null;

                if ( config.isActiveOngletGuichet( ) && StringUtils.isNotBlank( provider.provideCustomerConnectionId( ) ) )
                {
                    myDashBoardNotification = buildMyDashboardNotification( config, model );
                    notificationObject.setMyDashboardNotification( myDashBoardNotification );
                    notifyGruHistory.setGuichet( NotificationToHistory.populateGuichet( config, myDashBoardNotification ) );
                    bNotifEmpty = false;
                }

                SMSNotification smsNotification = null;

                if ( config.isActiveOngletSMS( ) && StringUtils.isNotBlank( provider.provideCustomerMobilePhone( ) ) )
                {
                    smsNotification = buildSMSNotification( config, provider, model );
                    notificationObject.setSmsNotification( smsNotification );
                    notifyGruHistory.setSMS( NotificationToHistory.populateSMS( config, smsNotification ) );
                    bNotifEmpty = false;
                }

                BackofficeNotification backofficeNotification = null;

                if ( config.isActiveOngletAgent( ) )
                {
                    backofficeNotification = buildBackofficeNotification( config, model );
                    notificationObject.setBackofficeNotification( backofficeNotification );
                    notifyGruHistory.setAgent( NotificationToHistory.populateAgent( config, backofficeNotification ) );
                    bNotifEmpty = false;
                }

                BroadcastNotification broadcastNotification = null;

                if ( config.isActiveOngletBroadcast( ) )
                {
                    broadcastNotification = buildBroadcastNotification( config, model );

                    if ( !broadcastNotification.getRecipient( ).isEmpty( ) )
                    {
                        notificationObject.addBroadcastEmail( broadcastNotification );
                        notifyGruHistory.setBroadCast( NotificationToHistory.populateBroadcast( config, broadcastNotification ) );
                        bNotifEmpty = false;
                    }
                }

                // crm status id
                notifyGruHistory.setCrmStatusId( config.getCrmStatusId( ) );

                if ( !bNotifEmpty )
                {
                    try
                    {
                        _notifyGruSenderService.send( notificationObject );
                        _taskNotifyGruHistoryService.create( notifyGruHistory, WorkflowUtils.getPlugin( ) );
                    }
                    catch( AppException | NotifyGruException e )
                    {
                        AppLogService.error( "Unable to send the notification" );
                    }
                }
            }
            else
            {
                AppLogService.error( "Task id " + this.getId( ) + " : Unable to retrieve the provider '" + config.getIdSpringProvider( ) + "'" );
            }
        }
        else
        {
            AppLogService.error( "Task id " + this.getId( ) + " : Unable to retrieve the provider manager '" + strProviderManagerId + "'" );
        }
    }

    /**
     * Builds an {@link Notification} object
     *
     * @param config
     *            the task configuration
     * @param provider
     *            the provider
     * @return the {@link Notification} object
     */
    private static Notification buildNotification( TaskNotifyGruConfig config, IProvider provider )
    {
        Notification notification = new Notification( );

        Demand demand = new Demand( );

        demand.setStatusId( config.getDemandStatus( ) );
        demand.setReference( provider.provideDemandReference( ) );
        notification.setDate( System.currentTimeMillis( ) );
        demand.setId( provider.provideDemandId( ) );
        demand.setTypeId( provider.provideDemandTypeId( ) );
        demand.setSubtypeId( provider.provideDemandSubtypeId( ) );
        demand.setMaxSteps( config.getDemandMaxStepGuichet( ) );
        demand.setCurrentStep( config.getDemandUserCurrentStepGuichet( ) );

        Customer customer = new Customer( );
        customer.setId( provider.provideCustomerId( ) );
        customer.setConnectionId( provider.provideCustomerConnectionId( ) );
        customer.setEmail( provider.provideCustomerEmail( ) );
        demand.setCustomer( customer );
        notification.setDemand( demand );

        return notification;
    }

    /**
     * Builds an {@link MyDashboardNotification} object
     *
     * @param config
     *            the task configuration
     * @param model
     *            the model
     * @return the {@link MyDashboardNotification} object
     */
    private MyDashboardNotification buildMyDashboardNotification( TaskNotifyGruConfig config, Map<String, Object> model )
    {
        MyDashboardNotification userDashBoard = new MyDashboardNotification( );

        userDashBoard.setStatusId( config.getCrmStatusId( ) );
        userDashBoard.setStatusText( replaceMarkers( config.getStatustextGuichet( ), model ) );
        userDashBoard.setSenderName( config.getSenderNameGuichet( ) );
        userDashBoard.setSubject( replaceMarkers( config.getSubjectGuichet( ), model ) );
        userDashBoard.setMessage( replaceMarkers( config.getMessageGuichet( ), model ) );
        userDashBoard.setData( StringUtils.EMPTY );

        return userDashBoard;
    }

    /**
     * Builds an {@link BackofficeNotification} object
     *
     * @param config
     *            the task configuration
     * @param model
     *            the model
     * @return the {@link BackofficeNotification} object
     */
    private BackofficeNotification buildBackofficeNotification( TaskNotifyGruConfig config, Map<String, Object> model )
    {
        BackofficeNotification userAgent = new BackofficeNotification( );

        userAgent.setStatusText( replaceMarkers( config.getStatustextAgent( ), model ) );
        userAgent.setMessage( replaceMarkers( config.getMessageAgent( ), model ) );

        return userAgent;
    }

    /**
     * Builds an {@link SMSNotification} object
     *
     * @param config
     *            the task configuration
     * @param provider
     *            the provider
     * @param model
     *            the model
     * @return the {@link SMSNotification} object
     */
    private <T extends SMSNotification> T buildSMSNotification( TaskNotifyGruConfig config, IProvider provider, Map<String, Object> model )
    {
        if ( !config.isBillingAccountBasedSmsNotification( ) )
        {
            SMSNotification userSMS = new BillingAccountBasedSMSNotification( );
            userSMS.setMessage( replaceMarkers( config.getMessageSMS( ), model ) );
            userSMS.setPhoneNumber( provider.provideCustomerMobilePhone( ) );
            userSMS.setSenderName( provider.provideSmsSender( ) );
            return (T) userSMS;
        }
        else
        {
            BillingAccountBasedSMSNotification billingAccountUserSMS = new BillingAccountBasedSMSNotification( );
            billingAccountUserSMS.setMessage( replaceMarkers( config.getMessageSMS( ), model ) );
            billingAccountUserSMS.setPhoneNumber( provider.provideCustomerMobilePhone( ) );
            billingAccountUserSMS.setSenderName( provider.provideSmsSender( ) );
            billingAccountUserSMS.setBillingAccount( config.getBillingAccountSMS( ) );
            billingAccountUserSMS.setBillingGroup( config.getBillingGroupSMS( ) );
            return (T) billingAccountUserSMS;
        }
    }

    /**
     * Builds an {@link EmailNotification} object
     *
     * @param config
     *            the task configuration
     * @param provider
     *            the provider
     * @param model
     *            the model
     * @return the {@link EmailNotification} object
     */
    private EmailNotification buildEmailNotification( TaskNotifyGruConfig config, IProvider provider, Map<String, Object> model )
    {
        EmailNotification userEmailNotification = new EmailNotification( );

        userEmailNotification.setSenderName( config.getSenderNameEmail( ) );
        userEmailNotification.setSenderEmail( MailService.getNoReplyEmail( ) );
        userEmailNotification.setRecipient( provider.provideCustomerEmail( ) );
        userEmailNotification.setSubject( replaceMarkers( config.getSubjectEmail( ), model ) );
        userEmailNotification.setMessage( replaceMarkers( config.getMessageEmail( ), model ) );
        userEmailNotification.setCc( config.getRecipientsCcEmail( ) );
        userEmailNotification.setBcc( config.getRecipientsCciEmail( ) );

        return userEmailNotification;
    }

    /**
     * Builds an {@link BroadcastNotification} object
     *
     * @param config
     *            the task configuration
     * @param model
     *            the model
     * @return the {@link BroadcastNotification} object
     */
    private BroadcastNotification buildBroadcastNotification( TaskNotifyGruConfig config, Map<String, Object> model )
    {
        BroadcastNotification broadcastNotification = new BroadcastNotification( );

        List<String> listRecipientBroadcast = new ArrayList<String>( );

        if ( StringUtils.isNotEmpty( config.getEmailBroadcast( ) ) )
        {
            String strRecipientBroadcast = replaceMarkers( config.getEmailBroadcast( ), model );
            if ( StringUtils.isNotEmpty( strRecipientBroadcast ) )
            {
                listRecipientBroadcast.addAll( Arrays.asList( strRecipientBroadcast.split( Constants.SEMICOLON ) ) );
            }
        }

        if ( config.getIdMailingListBroadcast( ) > 0 )
        {
            Collection<Recipient> listRecipients = AdminMailingListService.getRecipients( config.getIdMailingListBroadcast( ) );

            for ( Recipient recipient : listRecipients )
            {
                listRecipientBroadcast.add( recipient.getEmail( ) );
            }
        }

        String strRecipientCcBroadcast = StringUtils.EMPTY;

        if ( StringUtils.isNotEmpty( config.getRecipientsCcBroadcast( ) ) )
        {
            strRecipientCcBroadcast = replaceMarkers( config.getRecipientsCcBroadcast( ), model );
        }

        broadcastNotification.setSenderName( config.getSenderNameBroadcast( ) );
        broadcastNotification.setSenderEmail( MailService.getNoReplyEmail( ) );
        // we split a StringBuilder we can
        broadcastNotification.setRecipient( EmailAddress.buildEmailAddresses( listRecipientBroadcast.toArray( new String [ ] { } ) ) );
        broadcastNotification.setSubject( replaceMarkers( config.getSubjectBroadcast( ), model ) );
        broadcastNotification.setMessage( replaceMarkers( config.getMessageBroadcast( ), model ) );
        broadcastNotification.setCc( EmailAddress.buildEmailAddresses( strRecipientCcBroadcast.split( Constants.SEMICOLON ) ) );
        broadcastNotification.setBcc( EmailAddress.buildEmailAddresses( config.getRecipientsCciBroadcast( ).split( Constants.SEMICOLON ) ) );

        return broadcastNotification;
    }

    /**
     * Finds the NotifyGru markers
     * 
     * @param resourceHistory
     *            the resource history
     * @param provider
     *            the provider
     * @param listMarkerProviderIds
     *            the list of marker provider ids
     * @param request
     *            the request
     * @return the NotifyGru markers
     */
    private Collection<NotifyGruMarker> findMarkers( ResourceHistory resourceHistory, IProvider provider, List<String> listMarkerProviderIds,
            HttpServletRequest request )
    {
        Collection<NotifyGruMarker> collectionMarkers = provider.provideMarkerValues( );

        for ( String strMarkerProviderId : listMarkerProviderIds )
        {
            IMarkerProvider markerProvider = MarkerProviderService.getInstance( ).find( strMarkerProviderId );

            if ( markerProvider != null )
            {
                collectionMarkers.addAll( markerProvider.provideMarkerValues( resourceHistory, this, request ) );
            }
        }

        return collectionMarkers;
    }

    /**
     * Replaces the markers in the specified message
     *
     * @param strMessage
     *            the message
     * @param model
     *            the model
     * @return the message with markers replaced
     */
    private static String replaceMarkers( String strMessage, Map<String, Object> model )
    {
        @SuppressWarnings( "deprecation" )
        HtmlTemplate template = AppTemplateService.getTemplateFromStringFtl( strMessage, Locale.FRENCH, model );

        return template.getHtml( );
    }

    /**
     * Converts the specified collection of NotifyGru markers into a model
     * 
     * @param collectionNotifyGruMarkers
     *            the collection to convert
     * @return the model
     */
    private Map<String, Object> markersToModel( Collection<NotifyGruMarker> collectionNotifyGruMarkers )
    {
        Map<String, Object> model = new HashMap<>( );

        for ( NotifyGruMarker notifyGruMarker : collectionNotifyGruMarkers )
        {
            model.put( notifyGruMarker.getMarker( ), notifyGruMarker.getValue( ) );
        }

        return model;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask#doRemoveConfig()
     */
    @Override
    public void doRemoveConfig( )
    {
        _taskNotifyGruConfigService.remove( this.getId( ) );
        _taskNotifyGruHistoryService.removeByTask( this.getId( ), WorkflowUtils.getPlugin( ) );
    }

    /**
     * {@inheritDoc}
     * 
     * @param nIdHistory
     */
    @Override
    public void doRemoveTaskInformation( int nIdHistory )
    {
        _taskNotifyGruHistoryService.removeByHistory( nIdHistory, this.getId( ), WorkflowUtils.getPlugin( ) );
    }

    /**
     * {@inheritDoc}
     * 
     * @param locale
     * @return
     */
    @Override
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( Constants.TITLE_NOTIFY, locale );
    }

}
