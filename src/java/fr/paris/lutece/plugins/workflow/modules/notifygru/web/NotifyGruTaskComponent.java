/*
 * Copyright (c) 2002-2026, City of Paris
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

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.AgentHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.BroadcastHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.EmailHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.GuichetHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotifyGruHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.TaskNotifyGruConfigService;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflow.web.task.NoFormTaskComponent;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.html.XSSSanitizerException;
import fr.paris.lutece.portal.service.html.XSSSanitizerService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

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
    private static final String TEMPLATE_TASK_NOTIFY_INFORMATION = "admin/plugins/workflow/modules/notifygru/task_notify_information.html";

    // MARKS
    private static final String MARK_CONFIG = "config";
    private static final String MARK_NOTIFY_HISTORY = "information_history";

    // SERVICES
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;
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
        return new NotifyGruTaskConfigController( task ).performAction( request );
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
        HtmlTemplate template = new NotifyGruTaskConfigController( task ).buildView( request );

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

        sanitizeHistoryContent( notifyGruTaskHistory );

        Map<String, Object> model = new HashMap<>( );
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( task.getId( ) );
        model.put( MARK_CONFIG, config );
        model.put( MARK_NOTIFY_HISTORY, notifyGruTaskHistory );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_NOTIFY_INFORMATION, locale, model );

        return template.getHtml( );
    }

    /**
     * Sanitizes all notification messages in the history to prevent XSS when displayed. This is necessary because the bypassXssFilter mechanism stores raw HTML
     * content in the database, so we must sanitize before rendering in the task information template.
     *
     * @param history
     *            the notification history to sanitize
     */
    private void sanitizeHistoryContent( NotifyGruHistory history )
    {
        if ( history == null )
        {
            return;
        }
        
        try
        {
            sanitizeField( history.getEmail( ), EmailHistory::getMessageEmail, EmailHistory::setMessageEmail );
            sanitizeField( history.getGuichet( ), GuichetHistory::getMessageGuichet, GuichetHistory::setMessageGuichet );
            sanitizeField( history.getAgent( ), AgentHistory::getMessageAgent, AgentHistory::setMessageAgent );
            sanitizeField( history.getBroadCast( ), BroadcastHistory::getMessageBroadcast, BroadcastHistory::setMessageBroadcast );
        }
        catch( XSSSanitizerException e )
        {
            AppLogService.error( "Error sanitizing notification content for history id {}, task id {}", history.getIdResourceHistory( ),
                    history.getIdTask( ), e );
        }
    }

    /**
     * Sanitizes a single message field if the object and its value are not null.
     *
     * @param <T>
     *            the type of the object containing the message
     * @param object
     *            the object to sanitize
     * @param getter
     *            the function to retrieve the message value
     * @param setter
     *            the consumer to set the sanitized message value
     * @throws XSSSanitizerException
     *             if the sanitization fails
     */
    private static <T> void sanitizeField( T object, Function<T, String> getter, BiConsumer<T, String> setter ) throws XSSSanitizerException
    {
        if ( object != null && getter.apply( object ) != null )
        {
            setter.accept( object, XSSSanitizerService.sanitize( getter.apply( object ) ) );
        }
    }
}
