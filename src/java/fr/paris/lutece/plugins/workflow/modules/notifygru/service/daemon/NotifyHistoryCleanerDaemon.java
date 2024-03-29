/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service.daemon;


import java.sql.Timestamp;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.portal.service.daemon.Daemon;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
 *
 * Daemon DemandCleanerDaemon
 *
 */
public class NotifyHistoryCleanerDaemon extends Daemon
{
	
	private INotifyGruHistoryService _notifyGruHistoryService=SpringContextService.getBean(NotifyGruHistoryService.BEAN_SERVICE);
	private static final String MESSAGE_DAEMON_NAME = "NotifyHistoryCleanerDaemon - ";
    /**
     * Daemon's treatment method
     */
    public void run( )
    {
    	
    	
    	
    	 StringBuilder sbLogs = new StringBuilder( );
    	 sbLogs.append( MESSAGE_DAEMON_NAME );
    	 
        
         int nExpirationHours = AppPropertiesService.getPropertyInt( Constants.PROPERTY_DAEMON_NB_HOUR_BEFORE_CLEANING_HISTORY_CONTENT, -1 );
        if(nExpirationHours > -1)
        {

        	Calendar calendar = Calendar.getInstance();
        	calendar.add( Calendar.HOUR, -nExpirationHours );
        	Timestamp  minDateCreation=new Timestamp(calendar.getTime().getTime());
        	
        	
        	sbLogs.append( "the number of hours before cleaning the content of notification history is set to ");
        	sbLogs.append( nExpirationHours);
        	sbLogs.append( "\n" );
        	int nbNotificationToClean=_notifyGruHistoryService.getNbHistoryToCleanByDate(minDateCreation ,WorkflowUtils.getPlugin( ));
        	sbLogs.append( "the number of histories will be cleaned is " + nbNotificationToClean);
        	sbLogs.append( "\n" );
        	if(nbNotificationToClean > 0)
        	{
        		sbLogs.append( "start cleaning notification process "  );
        		_notifyGruHistoryService.cleanHistoryContentByDate(minDateCreation ,WorkflowUtils.getPlugin( ));
        		sbLogs.append( "\n" );
        		sbLogs.append( "end cleaning notification process " );
        		sbLogs.append( "\n" );
        	}
 
        }
        else
        {
        	  sbLogs.append( "the number of hours before cleaning the content of notification history is not set" );
        	  sbLogs.append( "\n" );
        	  sbLogs.append( "no notification history will be cleaned" );
        	  
        }
        
        AppLogService.info( "runNotifyHistoryCleanerDaemon: {}", sbLogs );
        
        setLastRunLogs( sbLogs.toString( ) );
        
      
    }
}
