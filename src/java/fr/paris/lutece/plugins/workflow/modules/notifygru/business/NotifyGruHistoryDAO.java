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
package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;


/**
 *
 * class NotifyGruHistoryDAO
 *
 */
public class NotifyGruHistoryDAO implements INotifyGruHistoryDAO
{
    public static final String BEAN = "NotifyGruHistoryDAO";
    private static final String SQL_QUERY_SELECT = "SELECT id_history, id_task, crm_status_id," +
        "message_guichet,status_text_guichet,sender_name_guichet," +
        "subject_guichet,demand_max_step_guichet,demand_user_current_step_guichet,is_active_onglet_guichet," +
        "status_text_agent,message_agent,is_active_onglet_agent," +
        "subject_email,message_email,sender_name_email,recipients_cc_email," +
        "recipients_cci_email,is_active_onglet_email," + "message_sms,is_active_onglet_sms," +
        "id_mailing_list_broadcast,sender_name_broadcast,subject_broadcast,message_broadcast," +
        "recipients_cc_broadcast,recipients_cci_broadcast," + "is_active_onglet_broadcast " +
        " FROM workflow_task_notify_gru_history  WHERE id_task = ? AND  id_history=?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO workflow_task_notify_gru_history( " +
        "id_history,id_task,crm_status_id,message_guichet,status_text_guichet,sender_name_guichet," +
        "subject_guichet,demand_max_step_guichet,demand_user_current_step_guichet,is_active_onglet_guichet," +
        "status_text_agent,message_agent,is_active_onglet_agent," + "subject_email, message_email," +
        "sender_name_email,recipients_cc_email,recipients_cci_email," + "is_active_onglet_email," +
        "message_sms,is_active_onglet_sms," +
        "id_mailing_list_broadcast,sender_name_broadcast,subject_broadcast,message_broadcast," +
        "recipients_cc_broadcast,recipients_cci_broadcast," + "is_active_onglet_broadcast ) " +
        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_QUERY_DELETE_BY_HISTORY = "DELETE FROM workflow_task_notify_gru_history  WHERE id_history=? AND id_task=?";
    private static final String SQL_QUERY_DELETE_BY_TASK = "DELETE FROM workflow_task_notify_gru_history  WHERE  id_task=?";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( NotifyGruHistory history, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        int nPos = 0;

        daoUtil.setInt( ++nPos, history.getIdResourceHistory(  ) );
        daoUtil.setInt( ++nPos, history.getIdTask(  ) );
        daoUtil.setInt( ++nPos, history.getCrmStatusId(  ) );

        daoUtil.setString( ++nPos, history.getGuichet(  ).getMessageGuichet(  ) );
        daoUtil.setString( ++nPos, history.getGuichet(  ).getStatustextGuichet(  ) );
        daoUtil.setString( ++nPos, history.getGuichet(  ).getSenderNameGuichet(  ) );
        daoUtil.setString( ++nPos, history.getGuichet(  ).getSubjectGuichet(  ) );
        daoUtil.setInt( ++nPos, history.getGuichet(  ).getDemandMaxStepGuichet(  ) );
        daoUtil.setInt( ++nPos, history.getGuichet(  ).getDemandUserCurrentStepGuichet(  ) );
        daoUtil.setBoolean( ++nPos, history.getGuichet(  ).isActiveOngletGuichet(  ) );

        daoUtil.setString( ++nPos, history.getAgent(  ).getStatustextAgent(  ) );
        daoUtil.setString( ++nPos, history.getAgent(  ).getMessageAgent(  ) );
        daoUtil.setBoolean( ++nPos, history.getAgent(  ).isActiveOngletAgent(  ) );

        daoUtil.setString( ++nPos, history.getEmail(  ).getSubjectEmail(  ) );
        daoUtil.setString( ++nPos, history.getEmail(  ).getMessageEmail(  ) );
        daoUtil.setString( ++nPos, history.getEmail(  ).getSenderNameEmail(  ) );
        daoUtil.setString( ++nPos, history.getEmail(  ).getRecipientsCcEmail(  ) );
        daoUtil.setString( ++nPos, history.getEmail(  ).getRecipientsCciEmail(  ) );
        daoUtil.setBoolean( ++nPos, history.getEmail(  ).isActiveOngletEmail(  ) );

        daoUtil.setString( ++nPos, history.getSMS(  ).getMessageSMS(  ) );
        daoUtil.setBoolean( ++nPos, history.getSMS(  ).isActiveOngletSMS(  ) );

        daoUtil.setInt( ++nPos, history.getBroadCast(  ).getIdMailingListBroadcast(  ) );
        daoUtil.setString( ++nPos, history.getBroadCast(  ).getSenderNameBroadcast(  ) );
        daoUtil.setString( ++nPos, history.getBroadCast(  ).getSubjectBroadcast(  ) );
        daoUtil.setString( ++nPos, history.getBroadCast(  ).getMessageBroadcast(  ) );
        daoUtil.setString( ++nPos, history.getBroadCast(  ).getRecipientsCcBroadcast(  ) );
        daoUtil.setString( ++nPos, history.getBroadCast(  ).getRecipientsCciBroadcast(  ) );
        daoUtil.setBoolean( ++nPos, history.getBroadCast(  ).isActiveOngletBroadcast(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyGruHistory load( int nIdHistory, int nIdTask, Plugin plugin )
    {
        NotifyGruHistory oNotifyGru = new NotifyGruHistory(  );
        GuichetHistory oGuichet = new GuichetHistory(  );
        AgentHistory oAgent = new AgentHistory(  );
        EmailHistory oEmail = new EmailHistory(  );
        SMSHistory oSMS = new SMSHistory(  );
        BroadcastHistory oBroadcast = new BroadcastHistory(  );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        int nPos = 0;
        daoUtil.setInt( ++nPos, nIdTask );
        daoUtil.setInt( ++nPos, nIdHistory );

        nPos = 0;

        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            oNotifyGru.setIdResourceHistory( daoUtil.getInt( ++nPos ) );
            oNotifyGru.setIdTask( daoUtil.getInt( ++nPos ) );
            oNotifyGru.setCrmStatusId( daoUtil.getInt( ++nPos ) );

            oGuichet.setMessageGuichet( daoUtil.getString( ++nPos ) );
            oGuichet.setStatustextGuichet( daoUtil.getString( ++nPos ) );
            oGuichet.setSenderNameGuichet( daoUtil.getString( ++nPos ) );
            oGuichet.setSubjectGuichet( daoUtil.getString( ++nPos ) );
            oGuichet.setDemandMaxStepGuichet( daoUtil.getInt( ++nPos ) );
            oGuichet.setDemandUserCurrentStepGuichet( daoUtil.getInt( ++nPos ) );
            oGuichet.setActiveOngletGuichet( daoUtil.getBoolean( ++nPos ) );

            oAgent.setStatustextAgent( daoUtil.getString( ++nPos ) );
            oAgent.setMessageAgent( daoUtil.getString( ++nPos ) );
            oAgent.setActiveOngletAgent( daoUtil.getBoolean( ++nPos ) );

            oEmail.setSubjectEmail( daoUtil.getString( ++nPos ) );
            oEmail.setMessageEmail( daoUtil.getString( ++nPos ) );
            oEmail.setSenderNameEmail( daoUtil.getString( ++nPos ) );
            oEmail.setRecipientsCcEmail( daoUtil.getString( ++nPos ) );
            oEmail.setRecipientsCciEmail( daoUtil.getString( ++nPos ) );
            oEmail.setActiveOngletEmail( daoUtil.getBoolean( ++nPos ) );

            oSMS.setMessageSMS( daoUtil.getString( ++nPos ) );
            oSMS.setActiveOngletSMS( daoUtil.getBoolean( ++nPos ) );

            oBroadcast.setIdMailingListBroadcast( daoUtil.getInt( ++nPos ) );
            oBroadcast.setSenderNameBroadcast( daoUtil.getString( ++nPos ) );
            oBroadcast.setSubjectBroadcast( daoUtil.getString( ++nPos ) );
            oBroadcast.setMessageBroadcast( daoUtil.getString( ++nPos ) );
            oBroadcast.setRecipientsCcBroadcast( daoUtil.getString( ++nPos ) );
            oBroadcast.setRecipientsCciBroadcast( daoUtil.getString( ++nPos ) );
            oBroadcast.setActiveOngletBroadcast( daoUtil.getBoolean( ++nPos ) );
        }

        oNotifyGru.setGuichet( oGuichet );
        oNotifyGru.setAgent( oAgent );
        oNotifyGru.setEmail( oEmail );
        oNotifyGru.setSMS( oSMS );
        oNotifyGru.setBroadCast( oBroadcast );

        daoUtil.free(  );

        return oNotifyGru;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByHistory( int nIdHistory, int nIdTask, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_BY_HISTORY, plugin );
        int nPos = 0;
        daoUtil.setInt( ++nPos, nIdHistory );
        daoUtil.setInt( ++nPos, nIdTask );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByTask( int nIdTask, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_BY_TASK, plugin );
        int nPos = 0;
        daoUtil.setInt( ++nPos, nIdTask );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
