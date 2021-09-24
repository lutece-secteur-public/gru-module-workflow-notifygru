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

/**
 * This is the business class for the object NotifyGruHistory
 */
public class NotifyGruHistory
{
    // Variables declarations
    private int _nIdTask;
    private int _nIdResourceHistory;

    /** The _ncrm status id. */
    private int _nCrmStatusId;
    private BroadcastHistory _oBroadCast;
    private EmailHistory _oEmail;
    private GuichetHistory _oGuichet;
    private SMSHistory _oSMS;
    private AgentHistory _oAgent;
    private EventHistory _oEvent;

    /**
     * Simple Constructor
     */
    public NotifyGruHistory( )
    {
        _oBroadCast = new BroadcastHistory( );
        _oEmail = new EmailHistory( );
        _oGuichet = new GuichetHistory( );
        _oSMS = new SMSHistory( );
        _oAgent = new AgentHistory( );
        _oEvent = new EventHistory( );
    }

    /**
     * Gets the crm status id.
     *
     * @return the _ncrmStatusId
     */
    public int getCrmStatusId( )
    {
        return _nCrmStatusId;
    }

    /**
     * Sets the crm status id.
     *
     * @param crmStatusId
     *            the new crm status id
     */
    public void setCrmStatusId( int crmStatusId )
    {
        this._nCrmStatusId = crmStatusId;
    }

    /**
     * Returns the IdTask
     * 
     * @return The IdTask
     */
    public int getIdTask( )
    {
        return _nIdTask;
    }

    /**
     * Sets the IdTask
     * 
     * @param nIdTask
     *            The IdTask
     */
    public void setIdTask( int nIdTask )
    {
        _nIdTask = nIdTask;
    }

    /**
     * Returns the IdResourceHistory
     * 
     * @return The IdResourceHistory
     */
    public int getIdResourceHistory( )
    {
        return _nIdResourceHistory;
    }

    /**
     * Sets the IdResourceHistory
     * 
     * @param nIdResourceHistory
     *            The IdResourceHistory
     */
    public void setIdResourceHistory( int nIdResourceHistory )
    {
        _nIdResourceHistory = nIdResourceHistory;
    }

    /**
     * Returns the oBroadCast
     * 
     * @return The oBroadCast
     */
    public BroadcastHistory getBroadCast( )
    {
        return _oBroadCast;
    }

    /**
     * Sets the oBroadCast
     * 
     * @param oBroadCast
     *            The oBroadCast
     */
    public void setBroadCast( BroadcastHistory oBroadCast )
    {
        _oBroadCast = oBroadCast;
    }

    /**
     * Returns the oEmail
     * 
     * @return The oEmail
     */
    public EmailHistory getEmail( )
    {
        return _oEmail;
    }

    /**
     * Sets the oEmail
     * 
     * @param oEmail
     *            The oEmail
     */
    public void setEmail( EmailHistory oEmail )
    {
        _oEmail = oEmail;
    }

    /**
     * Returns the oGuichet
     * 
     * @return The oGuichet
     */
    public GuichetHistory getGuichet( )
    {
        return _oGuichet;
    }

    /**
     * Sets the oGuichet
     * 
     * @param oGuichet
     *            The oGuichet
     */
    public void setGuichet( GuichetHistory oGuichet )
    {
        _oGuichet = oGuichet;
    }

    /**
     * Returns the oSMS
     * 
     * @return The oSMS
     */
    public SMSHistory getSMS( )
    {
        return _oSMS;
    }

    /**
     * Sets the oSMS
     * 
     * @param oSMS
     *            The oSMS
     */
    public void setSMS( SMSHistory oSMS )
    {
        _oSMS = oSMS;
    }

    /**
     * Returns the oAgent
     * 
     * @return The oAgent
     */
    public AgentHistory getAgent( )
    {
        return _oAgent;
    }

    /**
     * Sets the oAgent
     * 
     * @param oAgent
     *            The oAgent
     */
    public void setAgent( AgentHistory oAgent )
    {
        _oAgent = oAgent;
    }

    /**
     * get event
     * 
     * @return the event
     */
    public EventHistory getEvent( )
    {
        return _oEvent;
    }

    /**
     * set event
     * 
     * @param event
     */
    public void setEvent( EventHistory event )
    {
        this._oEvent = event;
    }

}
