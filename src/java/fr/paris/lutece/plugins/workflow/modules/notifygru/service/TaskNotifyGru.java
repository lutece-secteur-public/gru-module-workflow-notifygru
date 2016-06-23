/*
 * Copyright (c) 2002-2015, Mairie de Paris
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

import fr.paris.lutece.plugins.librarynotifygru.business.notifygru.NotifyGruAgentNotification;
import fr.paris.lutece.plugins.librarynotifygru.business.notifygru.NotifyGruEmailNotification;
import fr.paris.lutece.plugins.librarynotifygru.business.notifygru.NotifyGruGlobalNotification;
import fr.paris.lutece.plugins.librarynotifygru.business.notifygru.NotifyGruGuichetNotification;
import fr.paris.lutece.plugins.librarynotifygru.business.notifygru.NotifyGruSMSNotification;
import fr.paris.lutece.plugins.librarynotifygru.services.IsendNotificationAsJson;
import fr.paris.lutece.plugins.librarynotifygru.services.SendNotificationAsJson;
import fr.paris.lutece.plugins.workflow.business.action.ActionDAO;
import fr.paris.lutece.plugins.workflow.business.task.TaskDAO;
import fr.paris.lutece.plugins.workflow.business.workflow.WorkflowDAO;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotifyGruHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.cache.NotifyGruCacheService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.workflow.Workflow;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;



import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;


/**
 * TaskNotifyGru.
 */
public class TaskNotifyGru extends SimpleTask
{
    /** The Constant REMOVE_TAGS. */
    private static final Pattern REMOVE_TAGS = Pattern.compile( "<.+?>" );

    /** The Constant _DEFAULT_VALUE_JSON. */
    private static final String _DEFAULT_VALUE_JSON = "";

    /** The Constant BEAN_CLIENT_NOTIFICATION. */
    private static final String BEAN_CLIENT_NOTIFICATION = "library-notifygru.clientNotification";

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

    /** The _task notify gru history service. */
    @Inject
    @Named( BEAN_CLIENT_NOTIFICATION )
    private IsendNotificationAsJson _clientNotification;

    /** The _task doa. */
    @Inject
    private TaskDAO _taskDOA;

    /** The _workflow doa. */
    @Inject
    private WorkflowDAO _workflowDOA;

    /** The _action dao. */
    @Inject
    private ActionDAO _actionDAO;
    private IsendNotificationAsJson _senderEndPoint;

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
        /*Task Config form cache*/
        TaskNotifyGruConfig config = NotifyGruCacheService.getInstance(  )
                                                          .getNotifyGruConfigFromCache( _taskNotifyGruConfigService,
                this.getId(  ) );

        //    TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );
        NotifyGruHistory notifyGruHistory = new NotifyGruHistory(  );
        notifyGruHistory.setIdTask( this.getId(  ) );
        notifyGruHistory.setIdResourceHistory( nIdResourceHistory );

        ITask task = ( config != null ) ? _taskDOA.load( config.getIdTask(  ), locale ) : null;

        /*process if Task config not null and valide provider*/
        if ( ( config != null ) && ServiceConfigTaskForm.isBeanExiste( config.getIdSpringProvider(  ), task ) )
        {
            Action action = ( task != null ) ? _actionDAO.load( task.getAction(  ).getId(  ) ) : null;
            Workflow wf = ( action != null ) ? _workflowDOA.load( action.getWorkflow(  ).getId(  ) ) : null;

            //get provider
            _notifyGruService = ServiceConfigTaskForm.getCostumizeBean( config.getIdSpringProvider(  ), task );

            NotifyGruGlobalNotification notificationObject = buildGlobalNotification( config, nIdResourceHistory, locale );

            //if active config for Mail : user_email
            String strMessageEmail = _DEFAULT_VALUE_JSON;
            String strSubjectEmail = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletEmail(  ) )
            {
                NotifyGruEmailNotification userEmail = buildEmailNotification( config, nIdResourceHistory, locale );
                notificationObject.setUserEmail( userEmail );
                strMessageEmail = userEmail.getMessage(  );
                strSubjectEmail = userEmail.getSubject(  );
            }

            //if active config for Desk : user_dashboard
            String strMessageGuichet = _DEFAULT_VALUE_JSON;
            String strSubjectGuichet = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletGuichet(  ) )
            {
                NotifyGruGuichetNotification userDashBoard = buildGuichetNotification( config, nIdResourceHistory,
                        locale );
                notificationObject.setUserGuichet( userDashBoard );
                strMessageGuichet = userDashBoard.getMessage(  );
                strSubjectGuichet = userDashBoard.getSubject(  );
            }

            //if active config for SMS : user_sms
            String strMessageSMS = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletSMS(  ) &&
                    StringUtils.isNotBlank( _notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory ) ) )
            {
                NotifyGruSMSNotification userSMS = buildSMSNotification( config, nIdResourceHistory, locale );
                notificationObject.setUserSMS( userSMS );
                strMessageSMS = userSMS.getMessage(  );
            }

            //if active config for Agent : backoffice_logging
            String strMessageAgent = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletAgent(  ) )
            {
                NotifyGruAgentNotification userAgent = buildAgenttNotification( config, nIdResourceHistory, locale );
                notificationObject.setUserAgent( userAgent );

                strMessageAgent = userAgent.getMessage(  );
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
            notifyGruHistory.setBroadCast( NotificationToHistory.populateBroadcast( config, strSubjectGuichet,
                    strMessageGuichet ) );

            //populate Broadcast data for history
            notifyGruHistory.setAgent( NotificationToHistory.populateAgent( config, strMessageAgent ) );

            _senderEndPoint = SendNotificationAsJson.instance(  );

            String strNotifyGruCredential = AppPropertiesService.getProperty( Constants.CREDENTIAL_CLIENT_API_MANAGER,
                    "" );
            String strToken = _senderEndPoint.getToken( strNotifyGruCredential );
            String strSender = ( wf != null ) ? wf.getName(  ) : "";
            Map<String, String> headers = new HashMap<String, String>(  );

            headers.put( Constants.NOTIFICATION_SENDER,
                AppPropertiesService.getProperty( Constants.PARAMS_NOTIFICATION_SENDER ) + ":" + strSender );

            AppLogService.info( 
                "\n************************************************ CLIENT NOTIFY GRU SENDER ********************************************************************\n" );
            _senderEndPoint.send( notificationObject, strToken, headers );

            //    _clientNotification.sendJsonFlux( strJson, strSender, AppPropertiesService.getProperty( Constants.TOKEN, "" ) );
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
    private NotifyGruGlobalNotification buildGlobalNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        NotifyGruGlobalNotification notification = new NotifyGruGlobalNotification(  );
        notification.setGuid( _notifyGruService.getUserGuid( nIdResourceHistory ) );
        notification.setEmail( _notifyGruService.getUserEmail( nIdResourceHistory ) );
        notification.setCrmStatusId( config.getCrmStatusId(  ) );
        notification.setNotificationType( _DEFAULT_VALUE_JSON );

        /*Le champs permettra de valorisé le champs demand_status du flux notification V1.
        La valeur est à 0 (veut dire « en cours ») ou à 1 (veut dire « clôturée ». On est dans le cas où le checkbox est coché).
            Ce champs est « destiné » à la vue 360° (colonne statut dans la liste des demandes).*/
        notification.setDemandStatus( config.getDemandStatus(  ) );
        notification.setDemandReference( _notifyGruService.getDemandReference( nIdResourceHistory ) );

        String strCid = _notifyGruService.getCustomerId( nIdResourceHistory );
        int ncid = -1;

        try
        {
            ncid = Integer.parseInt( strCid );
        }
        catch ( NumberFormatException e )
        {
            AppLogService.error( "Invalide Customer ID" );
        }

        notification.setCustomerId( ncid );

        java.util.Date dateSendNotificationJson = new java.util.Date(  );
        notification.setNotificationDate( dateSendNotificationJson.getTime(  ) );
        notification.setDemandId( _notifyGruService.getOptionalDemandId( nIdResourceHistory ) );
        notification.setRemoteDemandId( _notifyGruService.getOptionalDemandId( nIdResourceHistory ) );
        notification.setDemandTypeId( _notifyGruService.getOptionalDemandIdType( nIdResourceHistory ) );
        notification.setDemandMaxStep( config.getDemandMaxStepGuichet(  ) );
        notification.setDemandUserCurrentStep( config.getDemandUserCurrentStepGuichet(  ) );

        return notification;
    }

    /**
     * Builds the guichet notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru guichet notification
     */
    private NotifyGruGuichetNotification buildGuichetNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        NotifyGruGuichetNotification userDashBoard = new NotifyGruGuichetNotification(  );

        String strMessageUserDashboard = giveMeTexteWithValueOfMarker( config.getMessageGuichet(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        String strSubjectUserDashboard = giveMeTexteWithValueOfMarker( config.getSubjectGuichet(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        String strStatustextDashboard = giveMeTexteWithValueOfMarker( config.getStatustextGuichet(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        userDashBoard.setStatusText( getHTMLEntities( strStatustextDashboard ) );
        userDashBoard.setSenderName( config.getSenderNameGuichet(  ) );
        userDashBoard.setSubject( getHTMLEntities( strSubjectUserDashboard ) );
        userDashBoard.setMessage( getHTMLEntities( strMessageUserDashboard ) );
        userDashBoard.setData( _DEFAULT_VALUE_JSON );

        return userDashBoard;
    }

    /**
     * Builds the agentt notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru agent notification
     */
    private NotifyGruAgentNotification buildAgenttNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        NotifyGruAgentNotification userAgent = new NotifyGruAgentNotification(  );

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
        String strmessageFinal = this.getHTMLEntities( template.getHtml(  ) );

        return strmessageFinal;
    }

   
    /**
     * Builds the sms notification.
     *
     * @param config the config
     * @param nIdResourceHistory the n id resource history
     * @param locale the locale
     * @return the notify gru sms notification
     */
    private NotifyGruSMSNotification buildSMSNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        NotifyGruSMSNotification userSMS = new NotifyGruSMSNotification(  );

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
    private NotifyGruEmailNotification buildEmailNotification( TaskNotifyGruConfig config, int nIdResourceHistory,
        Locale locale )
    {
        NotifyGruEmailNotification userEmailNotification = new NotifyGruEmailNotification(  );

        String strMessageEmail = giveMeTexteWithValueOfMarker( config.getMessageEmail(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );
        String strSubjectEmail = giveMeTexteWithValueOfMarker( config.getSubjectEmail(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );

        userEmailNotification.setSenderName( config.getSenderNameEmail(  ) );
        userEmailNotification.setSenderEmail( MailService.getNoReplyEmail(  ) );
        userEmailNotification.setRecipient( _notifyGruService.getUserEmail( nIdResourceHistory ) );
        userEmailNotification.setSubject( getHTMLEntities( strSubjectEmail ) );
        userEmailNotification.setMessage( getHTMLEntities( strMessageEmail ) );
        userEmailNotification.setCc( config.getRecipientsCcEmail(  ) );
        userEmailNotification.setCci( config.getRecipientsCcEmail(  ) );

        return userEmailNotification;
    }

    /**
     * Gets the HTML entities.
     *
     * @param htmlData the html data
     * @return the HTML entities
     */
    private String getHTMLEntities( String htmlData )
    {
        //        htmlData = StringEscapeUtils.unescapeHtml4(htmlData);
        //        AppLogService.info("Apres unescapeHtml4 \n" + htmlData);
        //        htmlData = removeTags(htmlData);
        return htmlData;
    }

    /**
     * Removes the tags.
     *
     * @param string the string
     * @return the string
     */
    public static String removeTags( String string )
    {
        if ( ( string == null ) || ( string.length(  ) == 0 ) )
        {
            return string;
        }

        Matcher m = REMOVE_TAGS.matcher( string );

        return m.replaceAll( "" );
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
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );

        if ( config != null )
        {
            return I18nService.getLocalizedString( Constants.TITLE_NOTIFY, locale );
        }

        return I18nService.getLocalizedString( Constants.TITLE_NOTIFY, locale );
    }
}
