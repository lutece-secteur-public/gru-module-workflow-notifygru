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


/**
 *
 * NotifyGruHistory
 *
 */
public class NotifyGruHistory
{
    private int _nIdResourceHistory;
    private int _nIdTask;
    private BroadcastHistory _oBroadCast;
    private EmailHistory _oEmail;
    private GuichetHistory _oGuichet;
    private SMSHistory _oSMS;
    private AgentHistory _oAgent;

    /**
     * @return
     */
    public AgentHistory getAgent(  )
    {
        return _oAgent;
    }

    /**
        * @param oAgent
        */
    public void setAgent( AgentHistory oAgent )
    {
        this._oAgent = oAgent;
    }

    /**
     *
     * @return the resource history id
     */
    public int getIdResourceHistory(  )
    {
        return _nIdResourceHistory;
    }

    /**
     * the resource history id
     * @param id the resource history id
     */
    public void setIdResourceHistory( int id )
    {
        _nIdResourceHistory = id;
    }

    /**
     *
     * @return the task id
     */
    public int getIdTask(  )
    {
        return _nIdTask;
    }

    /**
     * the task id
     * @param idTask the task id
     */
    public void setIdTask( int idTask )
    {
        _nIdTask = idTask;
    }

    /**
     * @return _oBroadCast oject of BroadcastHistory
     */
    public BroadcastHistory getBroadCast(  )
    {
        return _oBroadCast;
    }

    /**
    * @param oBroadCast to set _oBroadCast
    */
    public void setBroadCast( BroadcastHistory oBroadCast )
    {
        this._oBroadCast = oBroadCast;
    }

    /**
    * @return _oEmail object of EmailHistory
    */
    public EmailHistory getEmail(  )
    {
        return _oEmail;
    }

    /**
    * @param oEmail to set _oEmail
    */
    public void setEmail( EmailHistory oEmail )
    {
        this._oEmail = oEmail;
    }

    /**
     * @return  _oGuichet object of GuichetHistory
     */
    public GuichetHistory getGuichet(  )
    {
        return _oGuichet;
    }

    /**
         * @param oGuichet to set _oGuichet
     */
    public void setGuichet( GuichetHistory oGuichet )
    {
        this._oGuichet = oGuichet;
    }

    /**
    * @return _oSMS object of SMSHistory
    */
    public SMSHistory getSMS(  )
    {
        return _oSMS;
    }

    /**
    * @param oSMS to set _oSMS
    */
    public void setSMS( SMSHistory oSMS )
    {
        this._oSMS = oSMS;
    }
}
