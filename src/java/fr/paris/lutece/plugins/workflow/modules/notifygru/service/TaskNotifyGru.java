/*
 * Copyright (c) 2002-2016, Mairie de Paris
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
import fr.paris.lutece.plugins.grubusiness.business.notification.BackofficeNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.BroadcastNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.EmailAddress;
import fr.paris.lutece.plugins.grubusiness.business.notification.EmailNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.MyDashboardNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.Notification;
import fr.paris.lutece.plugins.grubusiness.business.notification.SMSNotification;
import fr.paris.lutece.plugins.librarynotifygru.services.NotificationService;
import fr.paris.lutece.plugins.workflow.business.task.TaskDAO;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotifyGruHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.cache.NotifyGruCacheService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.business.mailinglist.Recipient;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.mailinglist.AdminMailingListService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    /** The Constant _DEFAULT_VALUE_JSON. */
    private static final String _DEFAULT_VALUE_JSON = "";

    /** The _task notify gru config service. */
    // SERVICES 
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;

    /** The _notify gru service. */
    private AbstractServiceProvider _notifyGruService;

    /** The _task notify gru history service. */
    @Inject
    @Named( NotifyGruHistoryService.BEAN_SERVICE )
    private INotifyGruHistoryService _taskNotifyGruHistoryService;

    /** Lib-NotifyGru sender service */
    @Inject
    @Named( Constants.BEAN_NOTIFICATION_SENDER )
    private NotificationService _notifyGruSenderService;

    /** The _task doa. */
    @Inject
    private TaskDAO _taskDOA;

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
        /*Task Config form cache, it can't be null due to getNotifyGruConfigFromCache algorithm */
        TaskNotifyGruConfig config = NotifyGruCacheService.getInstance(  )
                                                          .getNotifyGruConfigFromCache( _taskNotifyGruConfigService,
                this.getId(  ) );

        NotifyGruHistory notifyGruHistory = new NotifyGruHistory(  );
        notifyGruHistory.setIdTask( this.getId(  ) );
        notifyGruHistory.setIdResourceHistory( nIdResourceHistory );

        ITask task = _taskDOA.load( config.getIdTask(  ), locale );

        /*process if Task not null and valid provider*/
        if ( ( task != null ) && ServiceConfigTaskForm.isBeanExists( config.getIdSpringProvider(  ), task ) )
        {
            //get provider
            _notifyGruService = ServiceConfigTaskForm.getCustomizedBean( config.getIdSpringProvider(  ), task );

            Notification notificationObject = buildGlobalNotification( config, nIdResourceHistory, locale );

            //if active config for Mail : user_email
            String strMessageEmail = _DEFAULT_VALUE_JSON;
            String strSubjectEmail = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletEmail(  ) )
            {
                EmailNotification userEmail = buildEmailNotification( config, nIdResourceHistory, locale );
                notificationObject.setUserEmail( userEmail );
                strMessageEmail = userEmail.getMessage(  );
                strSubjectEmail = userEmail.getSubject(  );
            }

            //if active config for Desk : user_dashboard
            String strMessageGuichet = _DEFAULT_VALUE_JSON;
            String strSubjectGuichet = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletGuichet(  ) )
            {
                MyDashboardNotification userDashBoard = buildUserDashboardNotification( config, nIdResourceHistory,
                        locale );
                notificationObject.setUserDashboard( userDashBoard );
                strMessageGuichet = userDashBoard.getMessage(  );
                strSubjectGuichet = userDashBoard.getSubject(  );
            }

            //if active config for SMS : user_sms
            String strMessageSMS = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletSMS(  ) &&
                    StringUtils.isNotBlank( _notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory ) ) )
            {
                SMSNotification userSMS = buildSMSNotification( config, nIdResourceHistory, locale );
                notificationObject.setUserSMS( userSMS );
                strMessageSMS = userSMS.getMessage(  );
            }

            //if active config for Agent : backoffice_logging
            String strMessageAgent = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletAgent(  ) )
            {
                BackofficeNotification backoffice = buildAgentNotification( config, nIdResourceHistory, locale );
                notificationObject.setBackofficeLogging( backoffice );

                strMessageAgent = backoffice.getMessage(  );
            }

            //if active config for Broadcast : broadcast
            String strMessageBroadcast = _DEFAULT_VALUE_JSON;
            String strSubjectBroadcast = _DEFAULT_VALUE_JSON;
            String strRecipientBroadcast = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletBroadcast(  ) )
            {
                BroadcastNotification broadcast = buildBroadcastNotification( config, nIdResourceHistory, locale );
                notificationObject.addBroadcastEmail( broadcast );
                strMessageBroadcast = broadcast.getMessage(  );
                strSubjectBroadcast = broadcast.getSubject(  );

                StringBuilder strEmailAdresses = new StringBuilder(  );

                if ( ( broadcast.getRecipient(  ) != null ) && !broadcast.getRecipient(  ).isEmpty(  ) )
                {
                    for ( EmailAddress emailAddress : broadcast.getRecipient(  ) )
                    {
                        if ( strEmailAdresses.length(  ) > 0 )
                        {
                            strEmailAdresses.append( Constants.SEMICOLON );
                        }

                        strEmailAdresses.append( emailAddress.getAddress(  ) );
                    }
                }

                strRecipientBroadcast = strEmailAdresses.toString(  );
            }

            //crm status id
            notifyGruHistory.setCrmStatusId( config.getCrmStatusId(  ) );
            //populate email data for history
            notifyGruHistory.setEmail( NotificationToHistory.populateEmail( config, strSubjectEmail, strMessageEmail ) );

            //populate desk data for history
            notifyGruHistory.setGuichet( NotificationToHistory.populateGuichet( config, strSubjectGuichet,
                    strMessageGuichet ) );

            //populate sms data for history
            notifyGruHistory.setSMS( NotificationToHistory.populateSMS( config, strMessageSMS ) );

            //populate Broadcast data for history
            notifyGruHistory.setBroadCast( NotificationToHistory.populateBroadcast( config, strRecipientBroadcast,
                    strSubjectBroadcast, strMessageBroadcast ) );

            //populate Broadcast data for history
            notifyGruHistory.setAgent( NotificationToHistory.populateAgent( config, strMessageAgent ) );

            _notifyGruSenderService.send( notificationObject );

            _taskNotifyGruHistoryService.create( notifyGruHistory, WorkflowUtils.getPlugin(  ) );
        }
    }

    /**
     * Builds the global notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru global notification
     */
    private Notification buildGlobalNotification( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        Notification notification = new Notification(  );

        Demand demand = new Demand(  );
        /*Le champs permettra de valorisé le champs demand_status du flux notification V1.
        La valeur est à 0 (veut dire « en cours ») ou à 1 (veut dire « clôturée ». On est dans le cas où le checkbox est coché).
            Ce champs est « destiné » à la vue 360° (colonne statut dans la liste des demandes).*/
        demand.setStatusId( config.getDemandStatus(  ) );
        demand.setReference( _notifyGruService.getDemandReference( nIdResourceHistory ) );
        notification.setNotificationDate( System.currentTimeMillis(  ) );
        demand.setId( String.valueOf( _notifyGruService.getOptionalDemandId( nIdResourceHistory ) ) );
        demand.setTypeId( String.valueOf( _notifyGruService.getOptionalDemandIdType( nIdResourceHistory ) ) );
        demand.setMaxSteps( config.getDemandMaxStepGuichet(  ) );
        demand.setCurrentStep( config.getDemandUserCurrentStepGuichet(  ) );

        Customer customer = new Customer(  );
        customer.setId( _notifyGruService.getCustomerId( nIdResourceHistory ) );
        customer.setAccountGuid( _notifyGruService.getUserGuid( nIdResourceHistory ) );
        customer.setEmail( _notifyGruService.getUserEmail( nIdResourceHistory ) );
        demand.setCustomer( customer );
        notification.setDemand( demand );

        return notification;
    }

    /**
     * Builds the userDashboard notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru userDashboard notification
     */
    private MyDashboardNotification buildUserDashboardNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        MyDashboardNotification userDashBoard = new MyDashboardNotification(  );

        String strMessageUserDashboard = giveMeTexteWithValueOfMarker( config.getMessageGuichet(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        String strSubjectUserDashboard = giveMeTexteWithValueOfMarker( config.getSubjectGuichet(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        String strStatustextDashboard = giveMeTexteWithValueOfMarker( config.getStatustextGuichet(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        userDashBoard.setStatusId( config.getCrmStatusId(  ) );
        userDashBoard.setStatusText( strStatustextDashboard );
        userDashBoard.setSenderName( config.getSenderNameGuichet(  ) );
        userDashBoard.setSubject( strSubjectUserDashboard );
        userDashBoard.setMessage( strMessageUserDashboard );
        userDashBoard.setData( _DEFAULT_VALUE_JSON );

        return userDashBoard;
    }

    /**
     * Builds the agent notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru agent notification
     */
    private BackofficeNotification buildAgentNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        BackofficeNotification userAgent = new BackofficeNotification(  );

        String strStatustextAgent = giveMeTexteWithValueOfMarker( config.getStatustextAgent(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        String strMessageAgent = giveMeTexteWithValueOfMarker( config.getMessageAgent(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        userAgent.setStatusText( strStatustextAgent );
        userAgent.setMessage( strMessageAgent );

        return userAgent;
    }

    /**
     * Give me texte with value of marker.
     *
     * @param strMessage the str message
     * @param locale the locale
     * @param model the model
     * @return the string
     */
    private String giveMeTexteWithValueOfMarker( String strMessage, Locale locale, Map<String, Object> model )
    {
        @SuppressWarnings( "deprecation" )
        HtmlTemplate template = AppTemplateService.getTemplateFromStringFtl( strMessage, locale, model );

        return template.getHtml(  );
    }

    /**
     * Builds the sms notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru sms notification
     */
    private SMSNotification buildSMSNotification( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        SMSNotification userSMS = new SMSNotification(  );

        String strMessageSMS = giveMeTexteWithValueOfMarker( config.getMessageSMS(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        userSMS.setMessage( strMessageSMS );
        userSMS.setPhoneNumber( _notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory ) );

        return userSMS;
    }

    /**
     * Builds the email notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru email notification
     */
    private EmailNotification buildEmailNotification( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        EmailNotification userEmailNotification = new EmailNotification(  );

        String strMessageEmail = giveMeTexteWithValueOfMarker( config.getMessageEmail(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );
        String strSubjectEmail = giveMeTexteWithValueOfMarker( config.getSubjectEmail(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        userEmailNotification.setSenderName( config.getSenderNameEmail(  ) );
        userEmailNotification.setSenderEmail( MailService.getNoReplyEmail(  ) );
        userEmailNotification.setRecipient( _notifyGruService.getUserEmail( nIdResourceHistory ) );
        userEmailNotification.setSubject( strSubjectEmail );
        userEmailNotification.setMessage( strMessageEmail );
        userEmailNotification.setCc( config.getRecipientsCcEmail(  ) );
        userEmailNotification.setBcc( config.getRecipientsCciEmail(  ) );

        return userEmailNotification;
    }

    /**
     * Builds the broadcast notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru broadcast notification
     */
    private BroadcastNotification buildBroadcastNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        BroadcastNotification broadcastNotification = new BroadcastNotification(  );
        Map<String, Object> mapInfosNotif = _notifyGruService.getInfos( nIdResourceHistory );
        String strMessageBroadcast = giveMeTexteWithValueOfMarker( config.getMessageBroadcast(  ), locale, mapInfosNotif );
        String strSubjectBroadcast = giveMeTexteWithValueOfMarker( config.getSubjectBroadcast(  ), locale, mapInfosNotif );
        List<String> lstRecipientBroadcast = new ArrayList<String>(  );

        if ( StringUtils.isNotEmpty( config.getEmailBroadcast(  ) ) )
        {
            String strRecipientBroadcast = giveMeTexteWithValueOfMarker( config.getEmailBroadcast(  ), locale,
                    mapInfosNotif );
            lstRecipientBroadcast.addAll( Arrays.asList( strRecipientBroadcast.split( Constants.SEMICOLON ) ) );
        }

        if ( config.getIdMailingListBroadcast(  ) > 0 )
        {
            Collection<Recipient> listRecipients = AdminMailingListService.getRecipients( config.getIdMailingListBroadcast(  ) );

            for ( Recipient recipient : listRecipients )
            {
                lstRecipientBroadcast.add( recipient.getEmail(  ) );
            }
        }

        broadcastNotification.setSenderName( config.getSenderNameBroadcast(  ) );
        broadcastNotification.setSenderEmail( MailService.getNoReplyEmail(  ) );
        //we split a StringBuilder we can
        broadcastNotification.setRecipient( EmailAddress.buildEmailAddresses( lstRecipientBroadcast.toArray( 
                    new String[] {  } ) ) );
        broadcastNotification.setSubject( strSubjectBroadcast );
        broadcastNotification.setMessage( strMessageBroadcast );
        broadcastNotification.setCc( EmailAddress.buildEmailAddresses( config.getRecipientsCcBroadcast(  )
                                                                             .split( Constants.SEMICOLON ) ) );
        broadcastNotification.setBcc( EmailAddress.buildEmailAddresses( config.getRecipientsCciBroadcast(  )
                                                                              .split( Constants.SEMICOLON ) ) );

        return broadcastNotification;
    }

    /* (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask#doRemoveConfig()
     */
    @Override
    public void doRemoveConfig(  )
    {
        _taskNotifyGruConfigService.remove( this.getId(  ) );
        _taskNotifyGruHistoryService.removeByTask( this.getId(  ), WorkflowUtils.getPlugin(  ) );
    }

    /**
    * {@inheritDoc}
    * @param nIdHistory
    */
    @Override
    public void doRemoveTaskInformation( int nIdHistory )
    {
        _taskNotifyGruHistoryService.removeByHistory( nIdHistory, this.getId(  ), WorkflowUtils.getPlugin(  ) );
    }

    /**
     * {@inheritDoc}
     * @param locale
     * @return
     */
    @Override
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( Constants.TITLE_NOTIFY, locale );
    }
}
