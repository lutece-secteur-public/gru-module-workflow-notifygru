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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.workflow.modules.comment.business.CommentValue;
import fr.paris.lutece.plugins.workflow.modules.comment.service.ICommentValueService;
import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.provider.IMarkerProvider;
import fr.paris.lutece.plugins.workflowcore.service.provider.InfoMarker;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.plugins.workflowcore.service.task.ITaskService;
import fr.paris.lutece.portal.service.plugin.PluginService;

/**
 * This class represents a NotifyGru marker provider for the Comment task
 *
 */
public class CommentMarkerProvider implements IMarkerProvider
{
    private static final String ID = "workflow-notifygru.commentMarkerProvider";

    // Messages
    private static final String MESSAGE_TITLE = "module.workflow.notifygru.marker.provider.comment.title";
    private static final String MESSAGE_MARKER_COMMENT = "module.workflow.notifygru.marker.provider.comment.description";

    // Markers
    private static final String MARK_COMMENT = "comments";

    // Services
    @Inject
    private ICommentValueService _commentService;
    @Inject
    private ITaskService _taskService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId( )
    {
        return ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitleI18nKey( )
    {
        return MESSAGE_TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<InfoMarker> provideMarkerDescriptions( )
    {
        List<InfoMarker> listMarkers = new ArrayList<>( );

        InfoMarker notifyMarker = new InfoMarker( MARK_COMMENT );
        notifyMarker.setDescription( MESSAGE_MARKER_COMMENT );
        listMarkers.add( notifyMarker );

        return listMarkers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<InfoMarker> provideMarkerValues( ResourceHistory resourceHistory, ITask task, HttpServletRequest request )
    {
        List<InfoMarker> listMarkers = new ArrayList<>( );

        StringBuilder sbComments = new StringBuilder( );

        // Retrieves all TaskComment of the action for the 'comments'
        // Only TaskComment declared BEFORE the TaskNotifyGru are set with value !
        for ( ITask taskOther : _taskService.getListTaskByIdAction( resourceHistory.getAction( ).getId( ), request.getLocale( ) ) )
        {
            CommentValue commentValue = _commentService.findByPrimaryKey( resourceHistory.getId( ), taskOther.getId( ),
                    PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME ) );

            if ( commentValue != null )
            {
                sbComments.append( commentValue.getValue( ) );
            }
        }

        InfoMarker notifyGruMarker = new InfoMarker( MARK_COMMENT );
        notifyGruMarker.setValue( sbComments.toString( ) );
        listMarkers.add( notifyGruMarker );

        return listMarkers;
    }

}
