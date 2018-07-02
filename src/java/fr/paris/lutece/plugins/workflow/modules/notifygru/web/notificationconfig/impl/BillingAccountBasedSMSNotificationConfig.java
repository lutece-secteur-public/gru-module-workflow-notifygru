/*
 * Copyright (c) 2002-2018, Mairie de Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import javax.servlet.http.HttpServletRequest;

/**
 * This class represents the config for SMS notification with billing account handling
 */
public class BillingAccountBasedSMSNotificationConfig extends SMSNotificationConfig
{
    
    /* The billing account */
    String _strBillingAccount;

    /* The billing group */
    String _strBillingGroup;

    /**
     * Constructor
     * @param request The HttpServletRequest
     * @param config the config of the task
     */
    public BillingAccountBasedSMSNotificationConfig( HttpServletRequest request, TaskNotifyGruConfig config )
    {
        super( request, config );
        _strBillingAccount = request.getParameter( Constants.PARAMETER_BILLING_ACCOUNT_SMS );
        _strBillingGroup = request.getParameter( Constants.PARAMETER_BILLING_GROUP_SMS );
    }

    /**
     * Get the billing account
     * @return the billing account
     */
    public String getBillingAccount() 
    {
        return _strBillingAccount;
    }

    /**
     * Sets the billing account
     * @param strBillingAccount the billing account 
     */
    public void setBillingAccount( String strBillingAccount ) 
    {
        _strBillingAccount = strBillingAccount;
    }
    
    /**
     * Get the billing group
     * 
     * @return the billing group
     */
    public String getBillingGroup( )
    {
        return _strBillingAccount;
    }

    /**
     * Sets the billing group
     * 
     * @param strBillingGroup
     *            the billing group
     */
    public void setBillingGroup( String strBillingGroup )
    {
        _strBillingGroup = strBillingGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addConfig( )
    {
        super.addConfig( );
        _config.setBillingAccountSMS( _strBillingAccount );
        _config.setBillingGroupSMS( _strBillingGroup );
    }
    
}
