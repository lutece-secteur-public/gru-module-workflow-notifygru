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
package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruPlugin;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfigDAO;
import fr.paris.lutece.util.sql.DAOUtil;


/**
 *
 * TaskNotifyDirectoryConfigDAO
 *
 */
public class TaskNotifyGruConfigDAO implements ITaskConfigDAO<TaskNotifyGruConfig>
{
    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = "SELECT id_task, id_spring_provider,key_provider" +
        ",message_guichet,level_notification_guichet,is_active_onglet_guichet," +
        "message_agent,level_notification_agent,is_active_onglet_agent," +
        "subject_email,message_email,sender_name_email,recipients_cc_email," +
        "recipients_cci_email,level_notification_email,is_active_onglet_email," +
        "message_sms,level_notification_sms,is_active_onglet_sms," +
        "id_mailing_list_broadcast,subject_broadcast,message_broadcast," +
        "sender_name_broadcast,recipients_cc_broadcast,recipients_cci_broadcast," +
        "level_notification_broadcast,is_active_onglet_broadcast,set_onglet" +
        " FROM workflow_task_notify_gru_cf  WHERE id_task = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO workflow_task_notify_gru_cf( " +
        "id_task,id_spring_provider,key_provider," + "message_guichet,level_notification_guichet," +
        "is_active_onglet_guichet," + "message_agent,level_notification_agent,is_active_onglet_agent," +
        "subject_email,message_email," + "sender_name_email,recipients_cc_email,recipients_cci_email," +
        "level_notification_email,is_active_onglet_email," +
        "message_sms,level_notification_sms,is_active_onglet_sms," +
        "id_mailing_list_broadcast,subject_broadcast,message_broadcast," +
        "sender_name_broadcast,recipients_cc_broadcast,recipients_cci_broadcast," +
        "level_notification_broadcast,is_active_onglet_broadcast,set_onglet" + ")" +
        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_QUERY_UPDATE = "UPDATE workflow_task_notify_gru_cf " +
        " SET id_task = ?, id_spring_provider = ?, key_provider = ?," +
        "message_guichet = ?, level_notification_guichet = ?, is_active_onglet_guichet= ?, " +
        "message_agent = ? ,level_notification_agent = ? ,is_active_onglet_agent = ? , " +
        " subject_email = ?, message_email = ?, sender_name_email = ?," +
        "recipients_cc_email = ?, recipients_cci_email = ?, " +
        "level_notification_email = ?, is_active_onglet_email= ?," + "message_sms = ?, " +
        " level_notification_sms = ?," + "is_active_onglet_sms = ?,  " +
        "id_mailing_list_broadcast = ?, subject_broadcast = ?, message_broadcast = ?," +
        "sender_name_broadcast = ?, recipients_cc_broadcast = ?,recipients_cci_broadcast = ?, " +
        "level_notification_broadcast = ?, is_active_onglet_broadcast = ?, set_onglet = ?" + " WHERE id_task = ? ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM workflow_task_notify_gru_cf WHERE id_task = ? ";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( TaskNotifyGruConfig config )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, NotifyGruPlugin.getPlugin(  ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.setString( ++nPos, config.getIdSpringProvider(  ) );
        daoUtil.setString( ++nPos, config.getKeyProvider(  ) );

        daoUtil.setString( ++nPos, config.getMessageGuichet(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationGuichet(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletGuichet(  ) );

        daoUtil.setString( ++nPos, config.getMessageAgent(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationAgent(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletAgent(  ) );

        daoUtil.setString( ++nPos, config.getSubjectEmail(  ) );
        daoUtil.setString( ++nPos, config.getMessageEmail(  ) );
        daoUtil.setString( ++nPos, config.getSenderNameEmail(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcEmail(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciEmail(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationEmail(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletEmail(  ) );

        daoUtil.setString( ++nPos, config.getMessageSMS(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationSMS(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletSMS(  ) );

        daoUtil.setInt( ++nPos, config.getIdMailingListBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getSubjectBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getMessageBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getSenderNameBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationBroadcast(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletBroadcast(  ) );

        daoUtil.setInt( ++nPos, config.getSetOnglet(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( TaskNotifyGruConfig config )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, NotifyGruPlugin.getPlugin(  ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );

        daoUtil.setString( ++nPos, config.getIdSpringProvider(  ) );
        daoUtil.setString( ++nPos, config.getKeyProvider(  ) );

        daoUtil.setString( ++nPos, config.getMessageGuichet(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationGuichet(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletGuichet(  ) );

        daoUtil.setString( ++nPos, config.getMessageAgent(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationAgent(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletAgent(  ) );

        daoUtil.setString( ++nPos, config.getSubjectEmail(  ) );
        daoUtil.setString( ++nPos, config.getMessageEmail(  ) );
        daoUtil.setString( ++nPos, config.getSenderNameEmail(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcEmail(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciEmail(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationEmail(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletEmail(  ) );

        daoUtil.setString( ++nPos, config.getMessageSMS(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationSMS(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletSMS(  ) );

        daoUtil.setInt( ++nPos, config.getIdMailingListBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getSubjectBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getMessageBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getSenderNameBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCcBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCciBroadcast(  ) );
        daoUtil.setString( ++nPos, config.getLevelNotificationBroadcast(  ) );
        daoUtil.setBoolean( ++nPos, config.isActiveOngletBroadcast(  ) );

        daoUtil.setInt( ++nPos, config.getSetOnglet(  ) );

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskNotifyGruConfig load( int nIdTask )
    {
        TaskNotifyGruConfig config = new TaskNotifyGruConfig(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_PRIMARY_KEY, NotifyGruPlugin.getPlugin(  ) );

        daoUtil.setInt( 1, nIdTask );

        daoUtil.executeQuery(  );

        int nPos = 0;

        if ( daoUtil.next(  ) )
        {
            config.setIdTask( daoUtil.getInt( ++nPos ) );

            config.setIdSpringProvider( daoUtil.getString( ++nPos ) );
            config.setKeyProvider( daoUtil.getString( ++nPos ) );

            config.setMessageGuichet( daoUtil.getString( ++nPos ) );
            config.setLevelNotificationGuichet( daoUtil.getString( ++nPos ) );
            config.setActiveOngletGuichet( daoUtil.getBoolean( ++nPos ) );

            config.setMessageAgent( daoUtil.getString( ++nPos ) );
            config.setLevelNotificationAgent( daoUtil.getString( ++nPos ) );
            config.setActiveOngletAgent( daoUtil.getBoolean( ++nPos ) );

            config.setSubjectEmail( daoUtil.getString( ++nPos ) );
            config.setMessageEmail( daoUtil.getString( ++nPos ) );
            config.setSenderNameEmail( daoUtil.getString( ++nPos ) );
            config.setRecipientsCcEmail( daoUtil.getString( ++nPos ) );
            config.setRecipientsCciEmail( daoUtil.getString( ++nPos ) );
            config.setLevelNotificationEmail( daoUtil.getString( ++nPos ) );
            config.setActiveOngletEmail( daoUtil.getBoolean( ++nPos ) );

            config.setMessageSMS( daoUtil.getString( ++nPos ) );
            config.setLevelNotificationSMS( daoUtil.getString( ++nPos ) );
            config.setActiveOngletSMS( daoUtil.getBoolean( ++nPos ) );

            config.setIdMailingListBroadcast( daoUtil.getInt( ++nPos ) );
            config.setSubjectBroadcast( daoUtil.getString( ++nPos ) );
            config.setMessageBroadcast( daoUtil.getString( ++nPos ) );
            config.setSenderNameBroadcast( daoUtil.getString( ++nPos ) );
            config.setRecipientsCcBroadcast( daoUtil.getString( ++nPos ) );
            config.setRecipientsCciBroadcast( daoUtil.getString( ++nPos ) );
            config.setLevelNotificationBroadcast( daoUtil.getString( ++nPos ) );
            config.setActiveOngletBroadcast( daoUtil.getBoolean( ++nPos ) );

            config.setSetOnglet( daoUtil.getInt( ++nPos ) );
        }

        daoUtil.free(  );

        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdState )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, NotifyGruPlugin.getPlugin(  ) );

        daoUtil.setInt( 1, nIdState );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
