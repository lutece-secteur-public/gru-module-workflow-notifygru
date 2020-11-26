/*
 * Copyright (c) 2002-2020, City of Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ServiceConfigTaskForm;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.TaskNotifyGruConfigService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl.BillingAccountBasedSMSNotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl.AgentNotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl.BroadcastNotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl.EmailNotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl.GuichetNotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl.SMSNotificationConfig;
import fr.paris.lutece.plugins.workflow.service.provider.MarkerProviderService;
import fr.paris.lutece.plugins.workflow.service.provider.ProviderManagerUtil;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.provider.AbstractProviderManager;
import fr.paris.lutece.plugins.workflowcore.service.provider.IMarkerProvider;
import fr.paris.lutece.plugins.workflowcore.service.provider.InfoMarker;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.utils.MVCMessage;
import fr.paris.lutece.util.ErrorMessage;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;

/**
 * This class represents a controller for the Notify GRU task configuration
 *
 */
public class NotifyGruTaskConfigController
{
    // Templates
    private static final String TEMPLATE_FIRST_STEP = "admin/plugins/workflow/modules/notifygru/task_notify_gru_config_first_step.html";
    private static final String TEMPLATE_SECOND_STEP = "admin/plugins/workflow/modules/notifygru/task_notify_gru_config_second_step.html";

    // Marks
    private static final String MARK_SELECT_PROVIDER = "list_provider";
    private static final String MARK_LIST_MARKER_PROVIDER = "list_marker_provider";
    private static final String MARK_LIST_NOTIFICATION_CONFIG = "list_notification_config";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_WEBAPP_URL = "webapp_url";

    // Parameters
    private static final String PARAMETER_APPLY = "apply";

    // Actions
    private static final String ACTION_FIRST_STEP_SAVE = "saveFirstStep";
    private static final String ACTION_SECOND_STEP_SAVE = "saveSecondStep";
    private static final String ACTION_ADVANCED_CONFIG_SAVE = "saveAdvancedConfig";
    private static final String ACTION_ADVANCED_CONFIG_CANCEL = "cancelAdvancedConfig";
    private static final String ACTION_NOTIFICATION_CONFIG_ADD = "AddNotificationConfig";
    private static final String ACTION_NOTIFICATION_CONFIG_REMOVE_PREFIX = "RemoveNotificationConfig_";

    // Services
    private static final ITaskConfigService _taskNotifyGruConfigService = SpringContextService.getBean( TaskNotifyGruConfigService.BEAN_SERVICE );
    private static final INotifyGruService _notifyGRUService = SpringContextService.getBean( NotifyGruService.BEAN_SERVICE );

    private final ITask _task;
    private TaskNotifyGruConfig _config;

    /**
     * Constructor
     * 
     * @param task
     *            the task associated to the configuration
     */
    public NotifyGruTaskConfigController( ITask task )
    {
        _task = task;

        findConfig( );
    }

    /**
     * Finds the configuration
     */
    private void findConfig( )
    {
        _config = _taskNotifyGruConfigService.findByPrimaryKey( _task.getId( ) );

        if ( _config == null )
        {
            // no config stored yet for this task, setting a empty one
            _config = new TaskNotifyGruConfig( );
        }
    }

    /**
     * Initializes the notification configurations
     * 
     * @param request
     *            the request used to initialize the configurations
     * @return the list of configurations
     */
    private List<INotificationConfig> initNotificationConfigs( HttpServletRequest request )
    {
        List<INotificationConfig> listNotificationConfig = new ArrayList<>( );

        listNotificationConfig.add( new AgentNotificationConfig( request, _config ) );
        listNotificationConfig.add( new BroadcastNotificationConfig( request, _config ) );
        listNotificationConfig.add( new EmailNotificationConfig( request, _config ) );
        listNotificationConfig.add( new GuichetNotificationConfig( request, _config ) );
        if ( _config.isBillingAccountBasedSmsNotification( ) )
        {
            listNotificationConfig.add( new BillingAccountBasedSMSNotificationConfig( request, _config ) );
        }
        else
        {
            listNotificationConfig.add( new SMSNotificationConfig( request, _config ) );
        }

        return listNotificationConfig;
    }

    /**
     * Builds the view depending on the specified request and locale
     * 
     * @param request
     *            the request the locale
     * @return the {@link HtmlTemplate} representing the view
     */
    public HtmlTemplate buildView( HttpServletRequest request )
    {
        HtmlTemplate template = null;
        View view = new View( request );

        if ( _config.getIdSpringProvider( ) == null )
        {
            template = view.buildFirstStep( );
        }
        else
        {
            template = view.buildSecondStep( );
        }

        return template;
    }

    /**
     * Performs an action triggered by the user
     * 
     * @param request
     *            the request containing the action
     * @return the URL of the error page if there is an error during the action, {@code null} otherwise
     */
    public String performAction( HttpServletRequest request )
    {
        String strErrorUrl = null;
        String strAction = findAction( request );
        Action action = new Action( request, strAction );

        if ( ACTION_FIRST_STEP_SAVE.equals( strAction ) )
        {
            strErrorUrl = action.saveFirstStep( );
        }

        if ( ACTION_SECOND_STEP_SAVE.equals( strAction ) )
        {
            strErrorUrl = action.saveSecondStep( );
        }

        if ( strAction.startsWith( ACTION_NOTIFICATION_CONFIG_REMOVE_PREFIX ) )
        {
            strErrorUrl = action.removeNotificationConf( );
        }

        if ( ACTION_NOTIFICATION_CONFIG_ADD.equals( strAction ) )
        {
            strErrorUrl = action.addNotificationConf( );
        }

        if ( ACTION_ADVANCED_CONFIG_CANCEL.equals( strAction ) )
        {
            strErrorUrl = action.cancel( );
        }

        if ( ACTION_ADVANCED_CONFIG_SAVE.equals( strAction ) )
        {
            strErrorUrl = action.saveAdvancedConfig( );
        }

        return strErrorUrl;
    }

    /**
     * Finds the action contained in the specified request
     * 
     * @param request
     *            the request
     * @return the action
     */
    private String findAction( HttpServletRequest request )
    {
        String strAction = request.getParameter( PARAMETER_APPLY );

        if ( StringUtils.isBlank( strAction ) )
        {
            strAction = ACTION_SECOND_STEP_SAVE;
        }

        return strAction;
    }

    /**
     * This class represents a view of the task configuration
     *
     */
    private final class View
    {
        private final HttpServletRequest _request;
        private final List<INotificationConfig> _listNotificationConfig;
        private final Map<String, Object> _model;

        /**
         * Constructor
         * 
         * @param request
         *            the request used by the view
         */
        private View( HttpServletRequest request )
        {
            _request = request;
            _model = new HashMap<>( );

            _listNotificationConfig = initNotificationConfigs( request );
        }

        /**
         * Builds the first step of the configuration
         * 
         * @return the {@link HtmlTemplate} representing the view
         */
        private HtmlTemplate buildFirstStep( )
        {
            fillModelWithConfig( );
            fillModelWithGlobalConfig( );

            return AppTemplateService.getTemplate( TEMPLATE_FIRST_STEP, _request.getLocale( ), _model );
        }

        /**
         * Builds the second step of the configuration
         * 
         * @return the {@link HtmlTemplate} representing the view
         */
        private HtmlTemplate buildSecondStep( )
        {
            fillModelWithConfig( );
            fillModelWithRichTextEditorConfig( );
            fillModelWithNotificationConfigs( );
            fillModelWithMailSenderName( );
            fillModelWithMailingList( );
            fillModelWithGlobalConfig( );

            manageNotifyGruMarkersInModel( );

            return AppTemplateService.getTemplate( TEMPLATE_SECOND_STEP, _request.getLocale( ), _model );
        }

        /**
         * Fills the model with the task configuration
         */
        private void fillModelWithConfig( )
        {
            _model.put( Constants.MARK_CONFIG, _config );
        }

        /**
         * Fills the model with the configuration of the rich text editor
         */
        private void fillModelWithRichTextEditorConfig( )
        {
            _model.put( MARK_LOCALE, _request.getLocale( ) );
            _model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( _request ) );
        }

        /**
         * Fills the model the notification configurations (as reference list)
         */
        private void fillModelWithNotificationConfigs( )
        {
            ReferenceList listNotificationConfig = ServiceConfigTaskForm.buildReferenceListOfInactiveNotificationConfigs( _listNotificationConfig,
                    _request.getLocale( ) );

            _model.put( MARK_LIST_NOTIFICATION_CONFIG, listNotificationConfig );
        }

        /**
         * Fills the model with the global configuration of the task
         */
        private void fillModelWithGlobalConfig( )
        {
            fillModelWithProviders( );
            fillModelWithMarkerProviders( );
        }

        /**
         * Fills the model with the providers
         */
        private void fillModelWithProviders( )
        {
            _model.put( MARK_SELECT_PROVIDER, ServiceConfigTaskForm.getProviderReferenceList( _task ) );
        }

        /**
         * Fills the model with the marker providers
         */
        private void fillModelWithMarkerProviders( )
        {
            _model.put( MARK_LIST_MARKER_PROVIDER, MarkerProviderService.getInstance( ).getMarkerProviders( ) );
        }

        /**
         * Fills the model with the mailing list
         */
        private void fillModelWithMailingList( )
        {
            _model.put( Constants.MARK_MAILING_LIST, _notifyGRUService.getMailingList( _request ) );
        }

        /**
         * Fills the model with the mail sender name
         */
        private void fillModelWithMailSenderName( )
        {
            String strDefaultSenderName = AppPropertiesService.getProperty( Constants.PROPERTY_NOTIFY_MAIL_DEFAULT_SENDER_NAME );

            _model.put( Constants.MARK_DEFAULT_SENDER_NAME, strDefaultSenderName );
        }

        /**
         * Manages the NotifyGru markers in the model
         */
        private void manageNotifyGruMarkersInModel( )
        {
            String strIdSpringProvider = _config.getIdSpringProvider( );
            String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( strIdSpringProvider );
            String strProviderId = ProviderManagerUtil.fetchProviderId( strIdSpringProvider );
            AbstractProviderManager providerManager = ProviderManagerUtil.fetchProviderManager( strProviderManagerId );

            if ( providerManager != null )
            {
                _model.put( Constants.MARK_NOTIFYGRU_MARKERS, findMarkers( providerManager, strProviderId, _config.getMarkerProviders( ) ) );
            }
            else
            {
                List<ErrorMessage> listErrorMessages = new ArrayList<>( );
                listErrorMessages.add( new MVCMessage( I18nService.getLocalizedString( Constants.MESSAGE_ERROR_PROVIDER_NOT_FOUND, _request.getLocale( ) ) ) );
                _model.put( Constants.MARK_MESSAGES_ERROR, listErrorMessages );
            }
        }

        /**
         * Finds the NotifyGru markers
         * 
         * @param providerManager
         *            the provider manager
         * @param strProviderId
         *            the provider id
         * @param listMarkerProviderIds
         *            the list of marker provider ids
         * @return the NotifyGru markers
         */
        private Collection<InfoMarker> findMarkers( AbstractProviderManager providerManager, String strProviderId, List<String> listMarkerProviderIds )
        {
            Collection<InfoMarker> collectionMarkers = providerManager.getProviderDescription( strProviderId ).getMarkerDescriptions( );

            for ( String strMarkerProviderId : listMarkerProviderIds )
            {
                IMarkerProvider markerProvider = MarkerProviderService.getInstance( ).find( strMarkerProviderId );

                if ( markerProvider != null )
                {
                    collectionMarkers.addAll( markerProvider.provideMarkerDescriptions( ) );
                }
            }

            return collectionMarkers;
        }

    }

    /**
     * This class represents an action in the task configuration
     *
     */
    private final class Action
    {
        private static final String VALUE_CHECKBOX = "on";
        private static final String PARAMETER_ADDED_NOTIFICATION_CONFIG = "added_notification_config";
        private static final String PARAMETER_REMOVE_SEPARATOR = "_";
        private static final int PARAMETER_REMOVE_NOTIFICATION_CONFIG_NAME_PART = 1;

        private final HttpServletRequest _request;
        private final List<INotificationConfig> _listNotificationConfig;
        private final AbstractProviderManager _providerManager;
        private final String _strAction;

        /**
         * Constructor
         * 
         * @param request
         *            the request used to perform the action
         * @param strAction
         *            the action to perform
         */
        private Action( HttpServletRequest request, String strAction )
        {
            _request = request;
            _strAction = strAction;

            String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( _config.getIdSpringProvider( ) );
            _providerManager = ProviderManagerUtil.fetchProviderManager( strProviderManagerId );

            _listNotificationConfig = initNotificationConfigs( request );

        }

        /**
         * Saves the first step of the task configuration
         * 
         * @return the URL of the error page if there is an error during the action, {@code null} otherwise
         */
        private String saveFirstStep( )
        {
            String strErrorUrl = validateGlobalConfig( );

            if ( !StringUtils.isEmpty( strErrorUrl ) )
            {
                return strErrorUrl;
            }

            fillConfigWithGlobalConfig( );
            saveConfig( );

            return null;
        }

        /**
         * Saves the second step of the task configuration
         * 
         * @return the URL of the error page if there is an error during the action, {@code null} otherwise
         */
        private String saveSecondStep( )
        {
            String strResult = validateSecondStepConfig( );

            if ( !StringUtils.isEmpty( strResult ) )
            {
                return strResult;
            }

            saveNotificationConfigs( );
            saveConfig( );

            return null;

        }

        /**
         * Saves the advanced configuration of the task
         * 
         * @return the URL of the error page if there is an error during the action, {@code null} otherwise
         */
        private String saveAdvancedConfig( )
        {
            String strErrorUrl = validateGlobalConfig( );

            if ( !StringUtils.isEmpty( strErrorUrl ) )
            {
                return strErrorUrl;
            }

            fillConfigWithGlobalConfig( );
            fillConfigWithCrmStatus( );
            saveConfig( );

            return null;
        }

        /**
         * Cancels the action
         * 
         * @return {@code null}
         */
        private String cancel( )
        {
            return null;
        }

        /**
         * Adds a notification configuration in the task
         * 
         * @return the URL of the error page if there is an error during the action, {@code null} otherwise
         */
        private String addNotificationConf( )
        {
            String strErrorUrl = validateNotificationConfigs( );

            if ( !StringUtils.isEmpty( strErrorUrl ) )
            {
                return strErrorUrl;
            }

            String strNameOfAddedNotificationConfig = findNameOfAddedNotificatioConfig( );
            INotificationConfig notificationConfigAdded = findNotificationConfigByName( strNameOfAddedNotificationConfig );

            notificationConfigAdded.setActive( true );

            fillConfigWithCurrentNotificationConfig( notificationConfigAdded );

            saveNotificationConfigs( );
            saveConfig( );

            return null;
        }

        /**
         * Removes a notification configuration from the task
         * 
         * @return the URL of the error page if there is an error during the action, {@code null} otherwise
         */
        private String removeNotificationConf( )
        {
            String strNameOfRemovedNotificationConfig = findNameOfRemovedNotificatioConfig( );
            INotificationConfig notificationConfigRemoved = findNotificationConfigByName( strNameOfRemovedNotificationConfig );

            notificationConfigRemoved.setActive( false );
            notificationConfigRemoved.removeConfig( );

            saveConfig( );

            return null;
        }

        /**
         * Finds the name of the added notification configuration
         * 
         * @return the name of the added notification configuration
         */
        private String findNameOfAddedNotificatioConfig( )
        {
            return _request.getParameter( PARAMETER_ADDED_NOTIFICATION_CONFIG );
        }

        /**
         * Finds the name of the removed notification configuration
         * 
         * @return the name of the removed notification configuration
         */
        private String findNameOfRemovedNotificatioConfig( )
        {
            return _strAction.split( PARAMETER_REMOVE_SEPARATOR ) [PARAMETER_REMOVE_NOTIFICATION_CONFIG_NAME_PART];
        }

        /**
         * Finds a notification configuration by its name
         * 
         * @param strName
         *            the name of the notification configuration to find
         * @return the found notification configuration, or {@code null} if not found
         */
        private INotificationConfig findNotificationConfigByName( String strName )
        {
            INotificationConfig notificationConfigFound = null;

            for ( INotificationConfig notificationConfig : _listNotificationConfig )
            {
                if ( notificationConfig.getName( ).equals( strName ) )
                {
                    notificationConfigFound = notificationConfig;
                    break;
                }
            }

            return notificationConfigFound;
        }

        /**
         * Validates the global configuration of the task
         * 
         * @return the URL of the error page if there is a validation error, {@code null} otherwise
         */
        private String validateGlobalConfig( )
        {
            return validateProviderManagerIsNotNull( );
        }

        /**
         * Validates that the provider manager is not {@code null}
         * 
         * @return the URL of the error page if there is a validation error, {@code null} otherwise
         */
        private String validateProviderManagerIsNotNull( )
        {
            String strErrorUrl = null;

            if ( StringUtils.isEmpty( _request.getParameter( Constants.PARAMETER_SELECT_PROVIDER ) ) )
            {
                strErrorUrl = buildUrlForMissingProviderManager( );
            }

            return strErrorUrl;
        }

        /**
         * Validates the second step of the task configuration
         * 
         * @return the URL of the error page if there is a validation error, {@code null} otherwise
         */
        private String validateSecondStepConfig( )
        {
            String strErrorUrl = validateOneNotificationConfigIsActive( );

            if ( !StringUtils.isEmpty( strErrorUrl ) )
            {
                return strErrorUrl;
            }

            strErrorUrl = validateNotificationConfigs( );

            if ( !StringUtils.isEmpty( strErrorUrl ) )
            {
                return strErrorUrl;
            }

            return null;
        }

        /**
         * Validates that at least one notification configuration is active
         * 
         * @return the URL of the error page if there is a validation error, {@code null} otherwise
         */
        private String validateOneNotificationConfigIsActive( )
        {
            String strErrorUrl = null;

            if ( filterActiveNotificationConfigs( ).isEmpty( ) )
            {
                strErrorUrl = buildUrlForNoNotificationConfig( );
            }

            return strErrorUrl;
        }

        /**
         * Validates the notification configurations
         * 
         * @return the URL of the error page if there is a validation error, {@code null} otherwise
         */
        private String validateNotificationConfigs( )
        {
            return validateNotificationConfigs( buildModelForValidation( ) );
        }

        /**
         * Validates the notification configurations with the specified model
         * 
         * @param model
         *            the model
         * @return the URL of the error page if there is a validation error, {@code null} otherwise
         */
        private String validateNotificationConfigs( Map<String, Object> model )
        {
            String strErrorUrl = null;

            for ( INotificationConfig notificationConfig : filterActiveNotificationConfigs( ) )
            {
                strErrorUrl = notificationConfig.getValidator( ).validate( model );

                if ( !StringUtils.isEmpty( strErrorUrl ) )
                {
                    break;
                }
            }

            return strErrorUrl;
        }

        /**
         * Filters the active notification configurations
         * 
         * @return the list of active notification configurations
         */
        private List<INotificationConfig> filterActiveNotificationConfigs( )
        {
            List<INotificationConfig> listActiveNotificationConfigs = new ArrayList<>( );

            for ( INotificationConfig notificationConfig : _listNotificationConfig )
            {
                if ( notificationConfig.isActive( ) )
                {
                    listActiveNotificationConfigs.add( notificationConfig );
                }
            }

            return listActiveNotificationConfigs;
        }

        /**
         * Builds the URL for missing provider manager
         * 
         * @return the URL
         */
        private String buildUrlForMissingProviderManager( )
        {
            return AdminMessageService.getMessageUrl( _request, Constants.MESSAGE_MANDATORY_PROVIDER, AdminMessage.TYPE_STOP );
        }

        /**
         * Builds the URL when there is no notification configuration
         * 
         * @return the URL
         */
        private String buildUrlForNoNotificationConfig( )
        {
            return AdminMessageService.getMessageUrl( _request, Constants.MESSAGE_MANDATORY_ONGLET, AdminMessage.TYPE_STOP );
        }

        /**
         * Builds the model used to validate the task
         * 
         * @return the model
         */
        private Map<String, Object> buildModelForValidation( )
        {
            String strProviderId = ProviderManagerUtil.fetchProviderId( _config.getIdSpringProvider( ) );

            return markersToModel( _providerManager.getProviderDescription( strProviderId ).getMarkerDescriptions( ) );
        }

        /**
         * Converts the specified collection of NotifyGru markers into a model
         * 
         * @param collectionNotifyGruMarkers
         *            the collection to convert
         * @return the model
         */
        private Map<String, Object> markersToModel( Collection<InfoMarker> collectionNotifyMarkers )
        {
            Map<String, Object> model = new HashMap<>( );

            for ( InfoMarker notifyMarker : collectionNotifyMarkers )
            {
                model.put( notifyMarker.getMarker( ), notifyMarker.getDescription( ) );
            }

            return model;
        }

        /**
         * Fills the configuration with the global configuration of the task
         */
        private void fillConfigWithGlobalConfig( )
        {
            fillConfigWithProvider( );
            fillConfigWithMarkerProviders( );
            fillConfigWithDemandStatus( );
        }

        /**
         * Fills the configuration with the provider
         */
        private void fillConfigWithProvider( )
        {
            _config.setIdSpringProvider( _request.getParameter( Constants.PARAMETER_SELECT_PROVIDER ) );
        }

        /**
         * Fills the configuration with the marker providers
         */
        private void fillConfigWithMarkerProviders( )
        {
            String [ ] listMarkerProviders = _request.getParameterValues( Constants.PARAMETER_MARKER_PROVIDERS );

            if ( listMarkerProviders != null )
            {
                _config.setMarkerProviders( Arrays.asList( listMarkerProviders ) );
            }
            else
            {
                _config.setMarkerProviders( null );
            }
        }

        /**
         * Fills the configuration with the demand status
         */
        private void fillConfigWithDemandStatus( )
        {
            int nDemandStatus = ( VALUE_CHECKBOX.equals( _request.getParameter( Constants.PARAMETER_DEMAND_STATUS ) ) ) ? 1 : 0;

            _config.setDemandStatus( nDemandStatus );
        }

        /**
         * Fills the configuration with the CRM status
         */
        private void fillConfigWithCrmStatus( )
        {
            String strCrmStatusId = _request.getParameter( Constants.PARAMETER_CRM_STATUS_ID );
            int nCrmStatusId = ( ( StringUtils.equals( strCrmStatusId, "1" ) ) || ( StringUtils.equals( strCrmStatusId, "0" ) ) )
                    ? Integer.parseInt( strCrmStatusId )
                    : 1;

            _config.setCrmStatusId( nCrmStatusId );
        }

        /**
         * Fills the configuration with the current notification configuration
         * 
         * @param notificationConfig
         *            the current notification configuration
         */
        private void fillConfigWithCurrentNotificationConfig( INotificationConfig notificationConfig )
        {
            int nCurrentTabIndex = ServiceConfigTaskForm.getTabIndex( notificationConfig.getName( ) );
            _config.setSetOnglet( nCurrentTabIndex );
        }

        /**
         * Saves the notification configurations
         */
        private void saveNotificationConfigs( )
        {
            for ( INotificationConfig notificationConfig : filterActiveNotificationConfigs( ) )
            {
                notificationConfig.addConfig( );
            }
        }

        /**
         * Saves the configuration of the task
         */
        private void saveConfig( )
        {
            boolean bCreate = false;

            if ( _config.getIdTask( ) == 0 )
            {
                _config.setIdTask( _task.getId( ) );
                bCreate = true;
            }

            if ( bCreate )
            {
                _taskNotifyGruConfigService.create( _config );
            }
            else
            {
                _taskNotifyGruConfigService.update( _config );
            }
        }
    }
}
