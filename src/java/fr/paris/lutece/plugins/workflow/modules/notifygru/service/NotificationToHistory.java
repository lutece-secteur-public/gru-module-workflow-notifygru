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
        oSMSHistory.setLevelNotificationSMS( config.getLevelNotificationSMS(  ) );

        return oSMSHistory;
    }

    /**
     * Populate email.
     *
     * @param config the config
     * @param strMessageEmail the str message email
     * @return the email history
     */
    public static EmailHistory populateEmail( TaskNotifyGruConfig config, String strMessageEmail )
    {
        EmailHistory oEmailHistory = new EmailHistory(  );

        oEmailHistory.setLevelNotificationEmail( config.getLevelNotificationEmail(  ) );
        oEmailHistory.setMessageEmail( strMessageEmail );
        oEmailHistory.setRecipientsCcEmail( config.getRecipientsCcEmail(  ) );
        oEmailHistory.setRecipientsCciEmail( config.getRecipientsCciEmail(  ) );
        oEmailHistory.setSenderNameEmail( config.getSenderNameEmail(  ) );
        oEmailHistory.setSubjectEmail( config.getSubjectEmail(  ) );

        return oEmailHistory;
    }

    /**
     * Populate broadcast.
     *
     * @param config the config
     * @param strMessageBroadcast the str message broadcast
     * @return the broadcast history
     */
    public static BroadcastHistory populateBroadcast( TaskNotifyGruConfig config, String strMessageBroadcast )
    {
        BroadcastHistory oBroadcastHistory = new BroadcastHistory(  );

        oBroadcastHistory.setIdMailingListBroadcast( config.getIdMailingListBroadcast(  ) );
        oBroadcastHistory.setLevelNotificationBroadcast( config.getLevelNotificationBroadcast(  ) );
        oBroadcastHistory.setMessageBroadcast( strMessageBroadcast );
        oBroadcastHistory.setRecipientsCcBroadcast( config.getRecipientsCcBroadcast(  ) );
        oBroadcastHistory.setRecipientsCciBroadcast( config.getRecipientsCciBroadcast(  ) );
        oBroadcastHistory.setSenderNameBroadcast( config.getSenderNameBroadcast(  ) );
        oBroadcastHistory.setSubjectBroadcast( config.getSubjectBroadcast(  ) );

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
        oAgentHistory.setLevelNotificationAgent( config.getLevelNotificationAgent(  ) );

        return oAgentHistory;
    }

    /**
     * Populate guichet.
     *
     * @param config the config
     * @param strMessageGuichet the str message guichet
     * @return the guichet history
     */
    public static GuichetHistory populateGuichet( TaskNotifyGruConfig config, String strMessageGuichet )
    {
        GuichetHistory oGuichetHistory = new GuichetHistory(  );

        oGuichetHistory.setLevelNotificationGuichet( config.getLevelNotificationGuichet(  ) );
        oGuichetHistory.setMessageGuichet( strMessageGuichet );
        oGuichetHistory.setSenderNameGuichet( config.getSenderNameGuichet(  ) );
        oGuichetHistory.setStatustextGuichet( config.getStatustextGuichet(  ) );
        oGuichetHistory.setSubjectGuichet( config.getSubjectGuichet(  ) );
        oGuichetHistory.setDemandMaxStepGuichet( config.getDemandMaxStepGuichet(  ) );
        oGuichetHistory.setDemandStateGuichet( config.getDemandStateGuichet(  ) );
        oGuichetHistory.setDemandUserCurrentStepGuichet( config.getDemandUserCurrentStepGuichet(  ) );

        return oGuichetHistory;
    }
}
