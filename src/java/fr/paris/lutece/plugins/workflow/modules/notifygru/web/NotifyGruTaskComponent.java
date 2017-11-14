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
package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotifyGruHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ServiceConfigTaskForm;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.TaskNotifyGruConfigService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.Validator;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.cache.NotifyGruCacheService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.AbstractProviderManager;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.NotifyGruMarker;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.ProviderManagerUtil;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflow.web.task.NoFormTaskComponent;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.utils.MVCMessage;
import fr.paris.lutece.util.ErrorMessage;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * INotifyGruTaskComponent
 *
 */
public class NotifyGruTaskComponent extends NoFormTaskComponent
{
    // TEMPLATES
    private static final String TEMPLATE_TASK_NOTIFY_GRU_CONFIG = "admin/plugins/workflow/modules/notifygru/task_notify_gru_config.html";
    private static final String TEMPLATE_TASK_NOTIFY_INFORMATION = "admin/plugins/workflow/modules/notifygru/task_notify_information.html";

    // MARKS
    private static final String MARK_CONFIG = "config";
    private static final String MARK_NOTIFY_HISTORY = "information_history";

    // SERVICES
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;
    @Inject
    @Named( NotifyGruService.BEAN_SERVICE )
    private INotifyGruService _notifyGRUService;
    @Inject
    @Named( NotifyGruHistoryService.BEAN_SERVICE )
    private INotifyGruHistoryService _taskNotifyGruHistoryService;

    /**
     * {@inheritDoc}
     *
     * @param request
     * @param locale
     * @param task
     * @return string
     */
    @Override
    public String doSaveConfig( HttpServletRequest request, Locale locale, ITask task )
    {
        String strApply = request.getParameter( Constants.PARAMETER_APPY );
        String strOngletActive = request.getParameter( Constants.PARAMETER_ONGLET );

        TaskNotifyGruConfig config = NotifyGruCacheService.getInstance( ).getNotifyGruConfigFromCache( _taskNotifyGruConfigService, task.getId( ) );

        // set the active onglet
        int nOngletActive = ServiceConfigTaskForm.getNumberOblet( strOngletActive );
        config.setSetOnglet( nOngletActive );

        if ( config.getIdSpringProvider( ) == null )
        {
            config.setIdSpringProvider( request.getParameter( Constants.PARAMETER_SELECT_PROVIDER ) );
        }

        String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( config.getIdSpringProvider( ) );
        AbstractProviderManager providerManager = ProviderManagerUtil.fetchProviderManager( strProviderManagerId );

        if ( providerManager == null )
        {
            return AdminMessageService.getMessageUrl( request, Constants.MESSAGE_MANDATORY_PROVIDER, AdminMessage.TYPE_STOP );
        }

        Boolean bActiveOngletGuichet = ServiceConfigTaskForm.setConfigOnglet( strApply, Constants.MARK_ONGLET_GUICHET, strOngletActive,
                config.isActiveOngletGuichet( ), Constants.PARAMETER_BUTTON_REMOVE_GUICHET );
        Boolean bActiveOngletAgent = ServiceConfigTaskForm.setConfigOnglet( strApply, Constants.MARK_ONGLET_AGENT, strOngletActive,
                config.isActiveOngletAgent( ), Constants.PARAMETER_BUTTON_REMOVE_AGENT );
        Boolean bActiveOngletEmail = ServiceConfigTaskForm.setConfigOnglet( strApply, Constants.MARK_ONGLET_EMAIL, strOngletActive,
                config.isActiveOngletEmail( ), Constants.PARAMETER_BUTTON_REMOVE_EMAIL );
        Boolean bActiveOngletSMS = ServiceConfigTaskForm.setConfigOnglet( strApply, Constants.MARK_ONGLET_SMS, strOngletActive, config.isActiveOngletSMS( ),
                Constants.PARAMETER_BUTTON_REMOVE_SMS );
        Boolean bActiveOngletBROADCAST = ServiceConfigTaskForm.setConfigOnglet( strApply, Constants.MARK_ONGLET_LIST, strOngletActive,
                config.isActiveOngletBroadcast( ), Constants.PARAMETER_BUTTON_REMOVE_LISTE );

        if ( ( strApply == null ) && !bActiveOngletAgent && !bActiveOngletBROADCAST && !bActiveOngletEmail && !bActiveOngletGuichet && !bActiveOngletSMS )
        {
            return AdminMessageService.getMessageUrl( request, Constants.MESSAGE_MANDATORY_ONGLET, AdminMessage.TYPE_STOP );
        }

        String strProviderId = ProviderManagerUtil.fetchProviderId( config.getIdSpringProvider( ) );

        Map<String, Object> model = markersToModel( providerManager.getProviderDescription( strProviderId ).getMarkerDescriptions( ) );

        /* set demand statut params */

        String strCrmStatusId = request.getParameter( Constants.PARAMETER_CRM_STATUS_ID );
        int nCrmStatusId = ( ( StringUtils.equals( strCrmStatusId, "1" ) ) || ( StringUtils.equals( strCrmStatusId, "0" ) ) ) ? Integer
                .parseInt( strCrmStatusId ) : 1;

        int nDemandStatus = ( Validator.VALUE_CHECKBOX.equals( request.getParameter( Constants.PARAMETER_DEMAND_STATUS ) ) ) ? 1 : 0;
        config.setDemandStatus( nDemandStatus );
        config.setCrmStatusId( nCrmStatusId );

        Boolean bRedirector = false;
        String strUrlRedirector = null;

        /* validate and build guichet */
        if ( !bRedirector && ( bActiveOngletGuichet || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_GUICHET ) ) ) )
        {
            strUrlRedirector = Validator.isValidBuildGuichet( request, config, model, locale, strApply );
            config.setActiveOngletGuichet( bActiveOngletGuichet );

            bRedirector = StringUtils.isNotBlank( strUrlRedirector );
        }

        /* validate and build agent */
        if ( !bRedirector && ( bActiveOngletAgent || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_AGENT ) ) ) )
        {
            strUrlRedirector = Validator.isValidBuildAgent( request, config, model, locale, strApply );
            config.setActiveOngletAgent( bActiveOngletAgent );

            bRedirector = StringUtils.isNotBlank( strUrlRedirector );
        }

        if ( !bRedirector && ( bActiveOngletEmail || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_EMAIL ) ) ) )
        {
            strUrlRedirector = Validator.isValidBuildEmail( request, config, model, locale, strApply );
            config.setActiveOngletEmail( bActiveOngletEmail );

            bRedirector = StringUtils.isNotBlank( strUrlRedirector );
        }

        if ( !bRedirector && ( bActiveOngletSMS || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_SMS ) ) ) )
        {
            config.setActiveOngletSMS( bActiveOngletSMS );
            strUrlRedirector = Validator.isValidBuildSMS( request, config, model, locale, strApply );

            bRedirector = StringUtils.isNotBlank( strUrlRedirector );
        }

        if ( !bRedirector && ( bActiveOngletBROADCAST || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_LISTE ) ) ) )
        {
            config.setActiveOngletBroadcast( bActiveOngletBROADCAST );
            strUrlRedirector = Validator.isValidBuildBroadcast( request, config, model, locale, strApply );

            bRedirector = StringUtils.isNotBlank( strUrlRedirector );
        }

        if ( bRedirector )
        {
            return strUrlRedirector;
        }

        if ( bActiveOngletAgent || bActiveOngletBROADCAST || bActiveOngletEmail || bActiveOngletGuichet || bActiveOngletSMS
                || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_GUICHET ) )
                || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_AGENT ) )
                || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_EMAIL ) )
                || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_SMS ) )
                || ( ( strApply != null ) && strApply.equals( Constants.PARAMETER_BUTTON_REMOVE_LISTE ) ) || ( config.getIdSpringProvider( ) != null ) )
        {
            Boolean bCreate = false;

            if ( config.getIdTask( ) == 0 )
            {
                config.setIdTask( task.getId( ) );
                bCreate = true;
            }

            if ( bCreate )
            {
                _taskNotifyGruConfigService.create( config );
            }
            else
            {
                _taskNotifyGruConfigService.update( config );
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param request
     * @param locale
     * @param task
     * @return
     */
    @Override
    public String getDisplayConfigForm( HttpServletRequest request, Locale locale, ITask task )
    {
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( task.getId( ) );

        if ( config == null )
        {
            // no config stored yet for this task, setting a empty one
            config = new TaskNotifyGruConfig( );
        }

        String strDefaultSenderName = AppPropertiesService.getProperty( Constants.PROPERTY_NOTIFY_MAIL_DEFAULT_SENDER_NAME );

        Map<String, Object> model = new HashMap<String, Object>( );

        model.put( Constants.MARK_CONFIG, config );
        model.put( Constants.MARK_DEFAULT_SENDER_NAME, strDefaultSenderName );

        if ( config.getIdSpringProvider( ) == null )
        {
            model.put( Constants.MARK_SELECT_PROVIDER, ServiceConfigTaskForm.getProviderReferenceList( task ) );
        }
        else
        {
            String strProviderManagerId = ProviderManagerUtil.fetchProviderManagerId( config.getIdSpringProvider( ) );
            String strProviderId = ProviderManagerUtil.fetchProviderId( config.getIdSpringProvider( ) );
            AbstractProviderManager providerManager = ProviderManagerUtil.fetchProviderManager( strProviderManagerId );

            if ( providerManager != null )
            {
                model.put( Constants.MARK_PROVIDER_MARKERS, providerManager.getProviderDescription( strProviderId ).getMarkerDescriptions( ) );
            }
            else
            {
                List<ErrorMessage> listErrorMessages = new ArrayList<>( );
                listErrorMessages.add( new MVCMessage( I18nService.getLocalizedString( Constants.MESSAGE_ERROR_PROVIDER_NOT_FOUND, locale ) ) );
                model.put( Constants.MARK_MESSAGES_ERROR, listErrorMessages );
            }
        }

        ReferenceList listeOnglet = ServiceConfigTaskForm.getListOnglet( config, locale );

        if ( listeOnglet.size( ) > 0 )
        {
            model.put( Constants.MARK_LIST_ONGLET, listeOnglet );
        }

        model.put( Constants.MARK_MAILING_LIST, _notifyGRUService.getMailingList( request ) );

        model.put( Constants.MARK_LOCALE, request.getLocale( ) );
        model.put( Constants.MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_NOTIFY_GRU_CONFIG, locale, model );

        return template.getHtml( );
    }

    /**
     * {@inheritDoc}
     *
     * @param nIdHistory
     * @param request
     * @param locale
     * @param task
     * @return html template of history
     */
    @Override
    public String getDisplayTaskInformation( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        NotifyGruHistory notifyGruTaskHistory = _taskNotifyGruHistoryService.findByPrimaryKey( nIdHistory, task.getId( ), WorkflowUtils.getPlugin( ) );

        Map<String, Object> model = new HashMap<String, Object>( );
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( task.getId( ) );
        model.put( MARK_CONFIG, config );
        model.put( MARK_NOTIFY_HISTORY, notifyGruTaskHistory );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_NOTIFY_INFORMATION, locale, model );

        return template.getHtml( );
    }

    /**
     * {@inheritDoc}
     *
     * @param nIdHistory
     * @param request
     * @param locale
     * @param task
     * @return
     */
    @Override
    public String getTaskInformationXml( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        return null;
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
            model.put( notifyGruMarker.getMarker( ), notifyGruMarker.getDescription( ) );
        }

        return model;
    }
}
