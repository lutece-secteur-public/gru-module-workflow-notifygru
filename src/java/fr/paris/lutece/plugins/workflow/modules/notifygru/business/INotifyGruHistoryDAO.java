/*
 * Copyright (c) 2002-2021, City of Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import java.sql.Timestamp;
import java.util.Date;

import fr.paris.lutece.portal.service.plugin.Plugin;

/**
 *
 * INotifyGruHistoryDAO
 *
 */
public interface INotifyGruHistoryDAO
{
    /**
     * insert new history notifygru
     * 
     * @param history
     *            history notifygru
     *
     * @param plugin
     *            the plugin
     */
    void insert( NotifyGruHistory history, Plugin plugin );

    /**
     * Load a record by primary key
     * 
     * @param nIdHistory
     *            the action history id
     * @param nIdTask
     *            the task id
     * @param plugin
     *            the plugin
     * @return NotifyGruHistory Object
     */
    NotifyGruHistory load( int nIdHistory, int nIdTask, Plugin plugin );

    /**
     * delete all notifygrus associated width the task specified in parameter
     * 
     * @param nIdTask
     *            the task id
     * @param plugin
     *            the plugin
     */
    void deleteByTask( int nIdTask, Plugin plugin );

    /**
     * delete all notifygrus associated width the history specified in parameter
     * 
     * @param nIdHistory
     *            the history id
     * @param nIdTask
     *            the task id
     * @param plugin
     *            the plugin
     */
    void deleteByHistory( int nIdHistory, int nIdTask, Plugin plugin );
    
    /**
     * clean all histories content who have a creation date before the tMinCreationDate
     * @param tMinCreationDate the creation date minimal
     * @param plugin the plugin
     */
    
    void cleanHistoryContentByDate( Timestamp tMinCreationDate , Plugin plugin );
    
    /**
     * get nb  histories who have a creation date before the tMinCreationDate
     * @param tMinCreationDate the history creation date
     * @param plugin the plugin
     * @return the nb  nb  histories who have a creation date before the tMinCreationDate
     */
    int getNbHistoryToCleanByDate( Timestamp tMinCreationDate , Plugin plugin );
}
