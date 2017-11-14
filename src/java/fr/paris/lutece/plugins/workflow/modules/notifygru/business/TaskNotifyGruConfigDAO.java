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
package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruPlugin;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.cache.NotifyGruCacheService;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfigDAO;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 *
 * TaskNotifyDirectoryConfigDAO
 *
 */
public class TaskNotifyGruConfigDAO implements ITaskConfigDAO<TaskNotifyGruConfig>
{
    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = "SELECT id_task, id_spring_provider,demand_status,crm_status_id, set_onglet,"
            + "message_guichet,status_text_guichet,sender_name_guichet,"
            + "subject_guichet,demand_max_step_guichet,demand_user_current_step_guichet,is_active_onglet_guichet,"
            + "status_text_agent,message_agent,is_active_onglet_agent,"
            + "subject_email,message_email,sender_name_email,recipients_cc_email,"
            + "recipients_cci_email,is_active_onglet_email,message_sms,is_active_onglet_sms,"
            + "id_mailing_list_broadcast,email_broadcast,sender_name_broadcast,subject_broadcast,message_broadcast,"
            + "recipients_cc_broadcast,recipients_cci_broadcast,is_active_onglet_broadcast " + " FROM workflow_task_notify_gru_cf  WHERE id_task = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO workflow_task_notify_gru_cf( "
            + "id_task,id_spring_provider,demand_status,crm_status_id,set_onglet,message_guichet,status_text_guichet,sender_name_guichet,"
            + "subject_guichet,demand_max_step_guichet,demand_user_current_step_guichet,is_active_onglet_guichet,"
            + "status_text_agent,message_agent,is_active_onglet_agent,subject_email, message_email,"
            + "sender_name_email,recipients_cc_email,recipients_cci_email,is_active_onglet_email," + "message_sms,is_active_onglet_sms,"
            + "id_mailing_list_broadcast,email_broadcast,sender_name_broadcast,subject_broadcast,message_broadcast,"
            + "recipients_cc_broadcast,recipients_cci_broadcast," + "is_active_onglet_broadcast ) "
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_QUERY_UPDATE = "UPDATE workflow_task_notify_gru_cf "
            + " SET id_task = ?, id_spring_provider = ?, demand_status = ?,crm_status_id = ?, set_onglet = ?,"
            + " message_guichet = ?, status_text_guichet = ?, sender_name_guichet = ?, "
            + "subject_guichet = ? ,demand_max_step_guichet = ? ,demand_user_current_step_guichet = ? ," + " is_active_onglet_guichet = ? ,"
            + "status_text_agent =? , message_agent = ? ,is_active_onglet_agent = ? , " + " subject_email = ?, message_email = ?, sender_name_email = ?,"
            + "recipients_cc_email = ?, recipients_cci_email = ?, " + " is_active_onglet_email= ?," + "message_sms = ?, " + "is_active_onglet_sms = ?,  "
            + "id_mailing_list_broadcast = ?, email_broadcast = ?, sender_name_broadcast = ?, subject_broadcast = ?, message_broadcast = ?,"
            + " recipients_cc_broadcast = ?,recipients_cci_broadcast = ?, " + " is_active_onglet_broadcast = ? " + " WHERE id_task = ? ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM workflow_task_notify_gru_cf WHERE id_task = ? ";

    /**
     * {@inheritDoc}
     * 
     * @param config
     */
    @Override
    public synchronized void insert( TaskNotifyGruConfig config )
    {
        // remove cache
        NotifyGruCacheService.getInstance( ).removeGruConfigFromCache( config.getIdTask( ) );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, NotifyGruPlugin.getPlugin( ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask( ) );
        daoUtil.setString( ++nPos, config.getIdSpringProvider( ) );
        daoUtil.setInt( ++nPos, config.getDemandStatus( ) );
        daoUtil.setInt( ++nPos, config.getCrmStatusId( ) );
        daoUtil.setInt( ++nPos, config.getSetOnglet( ) );

        daoUtil.setString( ++nPos, config.getMessageGuichet( ) );
        daoUtil.setString( ++nPos, config.getStatustextGuichet( ) );
        daoUtil.setString( ++nPos, config.getSenderNameGuichet( ) );
        daoUtil.setString( ++nPos, config.getSubjectGuichet( ) );
        daoUtil.setInt( ++nPos, config.getDemandMaxStepGuichet( ) );
        daoUtil.setInt( ++nPos, config.getDemandUserCurrentStepGuichet( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletGuichet( ) );

        daoUtil.setString( ++nPos, config.getStatustextAgent( ) );
        daoUtil.setString( ++nPos, config.getMessageAgent( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletAgent( ) );

        daoUtil.setString( ++nPos, config.getSubjectEmail( ) );
        daoUtil.setString( ++nPos, config.getMessageEmail( ) );
        daoUtil.setString( ++nPos, config.getSenderNameEmail( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcEmail( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciEmail( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletEmail( ) );

        daoUtil.setString( ++nPos, config.getMessageSMS( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletSMS( ) );

        daoUtil.setInt( ++nPos, config.getIdMailingListBroadcast( ) );
        daoUtil.setString( ++nPos, config.getEmailBroadcast( ) );
        daoUtil.setString( ++nPos, config.getSenderNameBroadcast( ) );
        daoUtil.setString( ++nPos, config.getSubjectBroadcast( ) );
        daoUtil.setString( ++nPos, config.getMessageBroadcast( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcBroadcast( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciBroadcast( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletBroadcast( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     * 
     * @param config
     */
    @Override
    public void store( TaskNotifyGruConfig config )
    {
        // remove cache
        NotifyGruCacheService.getInstance( ).removeGruConfigFromCache( config.getIdTask( ) );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, NotifyGruPlugin.getPlugin( ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask( ) );
        daoUtil.setString( ++nPos, config.getIdSpringProvider( ) );
        daoUtil.setInt( ++nPos, config.getDemandStatus( ) );
        daoUtil.setInt( ++nPos, config.getCrmStatusId( ) );
        daoUtil.setInt( ++nPos, config.getSetOnglet( ) );

        daoUtil.setString( ++nPos, config.getMessageGuichet( ) );
        daoUtil.setString( ++nPos, config.getStatustextGuichet( ) );
        daoUtil.setString( ++nPos, config.getSenderNameGuichet( ) );
        daoUtil.setString( ++nPos, config.getSubjectGuichet( ) );
        daoUtil.setInt( ++nPos, config.getDemandMaxStepGuichet( ) );
        daoUtil.setInt( ++nPos, config.getDemandUserCurrentStepGuichet( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletGuichet( ) );

        daoUtil.setString( ++nPos, config.getStatustextAgent( ) );
        daoUtil.setString( ++nPos, config.getMessageAgent( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletAgent( ) );

        daoUtil.setString( ++nPos, config.getSubjectEmail( ) );
        daoUtil.setString( ++nPos, config.getMessageEmail( ) );
        daoUtil.setString( ++nPos, config.getSenderNameEmail( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcEmail( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciEmail( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletEmail( ) );

        daoUtil.setString( ++nPos, config.getMessageSMS( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletSMS( ) );

        daoUtil.setInt( ++nPos, config.getIdMailingListBroadcast( ) );
        daoUtil.setString( ++nPos, config.getEmailBroadcast( ) );
        daoUtil.setString( ++nPos, config.getSenderNameBroadcast( ) );
        daoUtil.setString( ++nPos, config.getSubjectBroadcast( ) );
        daoUtil.setString( ++nPos, config.getMessageBroadcast( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcBroadcast( ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciBroadcast( ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletBroadcast( ) );

        daoUtil.setInt( ++nPos, config.getIdTask( ) );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     * 
     * @param nIdTask
     * @return
     */
    @Override
    public TaskNotifyGruConfig load( int nIdTask )
    {
        TaskNotifyGruConfig config = null;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_PRIMARY_KEY, NotifyGruPlugin.getPlugin( ) );

        daoUtil.setInt( 1, nIdTask );

        daoUtil.executeQuery( );

        int nPos = 0;

        if ( daoUtil.next( ) )
        {
            config = new TaskNotifyGruConfig( );
            config.setIdTask( daoUtil.getInt( ++nPos ) );

            config.setIdSpringProvider( daoUtil.getString( ++nPos ) );
            config.setDemandStatus( daoUtil.getInt( ++nPos ) );
            config.setCrmStatusId( daoUtil.getInt( ++nPos ) );
            config.setSetOnglet( daoUtil.getInt( ++nPos ) );

            config.setMessageGuichet( daoUtil.getString( ++nPos ) );
            config.setStatustextGuichet( daoUtil.getString( ++nPos ) );
            config.setSenderNameGuichet( daoUtil.getString( ++nPos ) );
            config.setSubjectGuichet( daoUtil.getString( ++nPos ) );
            config.setDemandMaxStepGuichet( daoUtil.getInt( ++nPos ) );
            config.setDemandUserCurrentStepGuichet( daoUtil.getInt( ++nPos ) );
            config.setActiveOngletGuichet( daoUtil.getBoolean( ++nPos ) );

            config.setStatustextAgent( daoUtil.getString( ++nPos ) );
            config.setMessageAgent( daoUtil.getString( ++nPos ) );
            config.setActiveOngletAgent( daoUtil.getBoolean( ++nPos ) );

            config.setSubjectEmail( daoUtil.getString( ++nPos ) );
            config.setMessageEmail( daoUtil.getString( ++nPos ) );
            config.setSenderNameEmail( daoUtil.getString( ++nPos ) );
            config.setRecipientsCcEmail( daoUtil.getString( ++nPos ) );
            config.setRecipientsCciEmail( daoUtil.getString( ++nPos ) );
            config.setActiveOngletEmail( daoUtil.getBoolean( ++nPos ) );

            config.setMessageSMS( daoUtil.getString( ++nPos ) );
            config.setActiveOngletSMS( daoUtil.getBoolean( ++nPos ) );

            config.setIdMailingListBroadcast( daoUtil.getInt( ++nPos ) );
            config.setEmailBroadcast( daoUtil.getString( ++nPos ) );
            config.setSenderNameBroadcast( daoUtil.getString( ++nPos ) );
            config.setSubjectBroadcast( daoUtil.getString( ++nPos ) );
            config.setMessageBroadcast( daoUtil.getString( ++nPos ) );
            config.setRecipientsCcBroadcast( daoUtil.getString( ++nPos ) );
            config.setRecipientsCciBroadcast( daoUtil.getString( ++nPos ) );
            config.setActiveOngletBroadcast( daoUtil.getBoolean( ++nPos ) );
        }

        daoUtil.free( );

        return config;
    }

    /**
     * {@inheritDoc}
     * 
     * @param nIdState
     */
    @Override
    public void delete( int nIdState )
    {
        // remove cache
        NotifyGruCacheService.getInstance( ).removeGruConfigFromCache( nIdState );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, NotifyGruPlugin.getPlugin( ) );

        daoUtil.setInt( 1, nIdState );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }
}
