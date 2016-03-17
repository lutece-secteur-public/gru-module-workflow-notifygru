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

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.AgentHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.BroadcastHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.EmailHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.GuichetHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.SMSHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;


/**
 * The Class NotificationToHistory.
 *
 * @author root
 */
public final class NotificationToHistory
{
    /**
     * Instantiates a new notification to history.
     */
    private NotificationToHistory(  )
    {
    }

    /**
     * Populate sms.
     *
     * @param config the config
     * @param strMessageSMS the str message sms
     * @return the SMS history
     */
    public static SMSHistory populateSMS( TaskNotifyGruConfig config, String strMessageSMS )
    {
        SMSHistory oSMSHistory = new SMSHistory(  );

        oSMSHistory.setMessageSMS( strMessageSMS );
        oSMSHistory.setActiveOngletSMS( config.isActiveOngletSMS(  ) );

        return oSMSHistory;
    }

    /**
     * Populate email.
     *
     * @param config the config
     * @param strSubjectEmail the email subject
     * @param strMessageEmail the str message email
     * @return the email history
     */
    public static EmailHistory populateEmail( TaskNotifyGruConfig config, String strSubjectEmail, String strMessageEmail )
    {
        EmailHistory oEmailHistory = new EmailHistory(  );

        oEmailHistory.setMessageEmail( strMessageEmail );
        oEmailHistory.setRecipientsCcEmail( config.getRecipientsCcEmail(  ) );
        oEmailHistory.setRecipientsCciEmail( config.getRecipientsCciEmail(  ) );
        oEmailHistory.setSenderNameEmail( config.getSenderNameEmail(  ) );
        oEmailHistory.setSubjectEmail( strSubjectEmail );
        oEmailHistory.setActiveOngletEmail( config.isActiveOngletEmail(  ) );

        return oEmailHistory;
    }

    /**
     * Populate broadcast.
     *
     * @param config the config
     * @param strSubjectBroadcast the broadcast subject
     * @param strMessageBroadcast the str message broadcast
     * @return the broadcast history
     */
    public static BroadcastHistory populateBroadcast( TaskNotifyGruConfig config, String strSubjectBroadcast,
        String strMessageBroadcast )
    {
        BroadcastHistory oBroadcastHistory = new BroadcastHistory(  );

        oBroadcastHistory.setIdMailingListBroadcast( config.getIdMailingListBroadcast(  ) );
        oBroadcastHistory.setMessageBroadcast( strMessageBroadcast );
        oBroadcastHistory.setRecipientsCcBroadcast( config.getRecipientsCcBroadcast(  ) );
        oBroadcastHistory.setRecipientsCciBroadcast( config.getRecipientsCciBroadcast(  ) );
        oBroadcastHistory.setSenderNameBroadcast( config.getSenderNameBroadcast(  ) );
        oBroadcastHistory.setSubjectBroadcast( strSubjectBroadcast );
        oBroadcastHistory.setActiveOngletBroadcast( config.isActiveOngletBroadcast(  ) );

        return oBroadcastHistory;
    }

    /**
     * Populate agent.
     *
     * @param config the config
     * @param strMessageAgent the str message agent
     * @return the agent history
     */
    public static AgentHistory populateAgent( TaskNotifyGruConfig config, String strMessageAgent )
    {
        AgentHistory oAgentHistory = new AgentHistory(  );

        oAgentHistory.setMessageAgent( strMessageAgent );
        oAgentHistory.setStatustextAgent( config.getStatustextAgent(  ) );
        oAgentHistory.setActiveOngletAgent( config.isActiveOngletAgent(  ) );

        return oAgentHistory;
    }

    /**
     * Populate guichet.
     *
     * @param config the config
     * @param strSubjectGuichet the guichet subject
     * @param strMessageGuichet the str message guichet
     * @return the guichet history
     */
    public static GuichetHistory populateGuichet( TaskNotifyGruConfig config, String strSubjectGuichet,
        String strMessageGuichet )
    {
        GuichetHistory oGuichetHistory = new GuichetHistory(  );

        oGuichetHistory.setMessageGuichet( strMessageGuichet );
        oGuichetHistory.setSenderNameGuichet( config.getSenderNameGuichet(  ) );
        oGuichetHistory.setStatustextGuichet( config.getStatustextGuichet(  ) );
        oGuichetHistory.setSubjectGuichet( strSubjectGuichet );
        oGuichetHistory.setDemandMaxStepGuichet( config.getDemandMaxStepGuichet(  ) );
        oGuichetHistory.setDemandUserCurrentStepGuichet( config.getDemandUserCurrentStepGuichet(  ) );
        oGuichetHistory.setActiveOngletGuichet( config.isActiveOngletGuichet(  ) );

        return oGuichetHistory;
    }
}
