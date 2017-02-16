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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.grubusiness.business.notification.BackofficeNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.BroadcastNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.EmailAddress;
import fr.paris.lutece.plugins.grubusiness.business.notification.EmailNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.MyDashboardNotification;
import fr.paris.lutece.plugins.grubusiness.business.notification.SMSNotification;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.AgentHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.BroadcastHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.EmailHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.GuichetHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.SMSHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;

/**
 * The Class NotificationToHistory.
 */
public final class NotificationToHistory
{
    /**
     * Instantiates a new notification to history.
     */
    private NotificationToHistory( )
    {
    }

    /**
     * Populate sms.
     *
     * @param config
     *            the config
     * @param smsNotification
     *            the SMS notification
     * @return the SMS history
     */
    public static SMSHistory populateSMS( TaskNotifyGruConfig config, SMSNotification smsNotification )
    {
        SMSHistory oSMSHistory = new SMSHistory( );

        oSMSHistory.setActiveOngletSMS( config.isActiveOngletSMS( ) );

        if ( smsNotification != null )
        {
            oSMSHistory.setMessageSMS( smsNotification.getMessage( ) );
        }
        else
        {
            oSMSHistory.setMessageSMS( StringUtils.EMPTY );
        }

        return oSMSHistory;
    }

    /**
     * Populate email.
     *
     * @param config
     *            the config
     * @param emailNotification
     *            the email notification
     * @return the email history
     */
    public static EmailHistory populateEmail( TaskNotifyGruConfig config, EmailNotification emailNotification )
    {
        EmailHistory oEmailHistory = new EmailHistory( );

        oEmailHistory.setRecipientsCcEmail( config.getRecipientsCcEmail( ) );
        oEmailHistory.setRecipientsCciEmail( config.getRecipientsCciEmail( ) );
        oEmailHistory.setSenderNameEmail( config.getSenderNameEmail( ) );
        oEmailHistory.setActiveOngletEmail( config.isActiveOngletEmail( ) );

        if ( emailNotification != null )
        {
            oEmailHistory.setMessageEmail( emailNotification.getMessage( ) );
            oEmailHistory.setSubjectEmail( emailNotification.getSubject( ) );
        }
        else
        {
            oEmailHistory.setMessageEmail( StringUtils.EMPTY );
            oEmailHistory.setSubjectEmail( StringUtils.EMPTY );
        }

        return oEmailHistory;
    }

    /**
     * Populate broadcast.
     *
     * @param config
     *            the config
     * @param broadcastNotification
     *            the broadcast notification
     * @return the broadcast history
     */
    public static BroadcastHistory populateBroadcast( TaskNotifyGruConfig config, BroadcastNotification broadcastNotification )
    {
        BroadcastHistory oBroadcastHistory = new BroadcastHistory( );

        oBroadcastHistory.setIdMailingListBroadcast( config.getIdMailingListBroadcast( ) );
        oBroadcastHistory.setRecipientsCcBroadcast( config.getRecipientsCcBroadcast( ) );
        oBroadcastHistory.setRecipientsCciBroadcast( config.getRecipientsCciBroadcast( ) );
        oBroadcastHistory.setSenderNameBroadcast( config.getSenderNameBroadcast( ) );
        oBroadcastHistory.setActiveOngletBroadcast( config.isActiveOngletBroadcast( ) );

        if ( broadcastNotification != null )
        {
            oBroadcastHistory.setMessageBroadcast( broadcastNotification.getMessage( ) );
            oBroadcastHistory.setSubjectBroadcast( broadcastNotification.getSubject( ) );

            StringBuilder sbEmailAdresses = new StringBuilder( );
            List<EmailAddress> listEmailAddresses = broadcastNotification.getRecipient( );

            if ( ( listEmailAddresses != null ) && !listEmailAddresses.isEmpty( ) )
            {
                for ( EmailAddress emailAddress : listEmailAddresses )
                {
                    if ( sbEmailAdresses.length( ) > 0 )
                    {
                        sbEmailAdresses.append( Constants.SEMICOLON );
                    }

                    sbEmailAdresses.append( emailAddress.getAddress( ) );
                }
            }

            oBroadcastHistory.setEmailBroadcast( sbEmailAdresses.toString( ) );
        }
        else
        {
            oBroadcastHistory.setEmailBroadcast( StringUtils.EMPTY );
            oBroadcastHistory.setMessageBroadcast( StringUtils.EMPTY );
            oBroadcastHistory.setSubjectBroadcast( StringUtils.EMPTY );
        }

        return oBroadcastHistory;
    }

    /**
     * Populate agent.
     *
     * @param config
     *            the config
     * @param backofficeNotification
     *            the backoffice notification
     * @return the agent history
     */
    public static AgentHistory populateAgent( TaskNotifyGruConfig config, BackofficeNotification backofficeNotification )
    {
        AgentHistory oAgentHistory = new AgentHistory( );

        oAgentHistory.setStatustextAgent( config.getStatustextAgent( ) );
        oAgentHistory.setActiveOngletAgent( config.isActiveOngletAgent( ) );

        if ( backofficeNotification != null )
        {
            oAgentHistory.setMessageAgent( backofficeNotification.getMessage( ) );
        }
        else
        {
            oAgentHistory.setMessageAgent( StringUtils.EMPTY );
        }

        return oAgentHistory;
    }

    /**
     * Populate guichet.
     *
     * @param config
     *            the config
     * @param myDashboardNotification
     *            the MyDashboard notification
     * @return the guichet history
     */
    public static GuichetHistory populateGuichet( TaskNotifyGruConfig config, MyDashboardNotification myDashboardNotification )
    {
        GuichetHistory oGuichetHistory = new GuichetHistory( );

        oGuichetHistory.setSenderNameGuichet( config.getSenderNameGuichet( ) );
        oGuichetHistory.setStatustextGuichet( config.getStatustextGuichet( ) );
        oGuichetHistory.setDemandMaxStepGuichet( config.getDemandMaxStepGuichet( ) );
        oGuichetHistory.setDemandUserCurrentStepGuichet( config.getDemandUserCurrentStepGuichet( ) );
        oGuichetHistory.setActiveOngletGuichet( config.isActiveOngletGuichet( ) );

        if ( myDashboardNotification != null )
        {
            oGuichetHistory.setMessageGuichet( myDashboardNotification.getMessage( ) );
            oGuichetHistory.setSubjectGuichet( myDashboardNotification.getSubject( ) );
        }
        else
        {
            oGuichetHistory.setMessageGuichet( StringUtils.EMPTY );
            oGuichetHistory.setSubjectGuichet( StringUtils.EMPTY );
        }

        return oGuichetHistory;
    }
}
