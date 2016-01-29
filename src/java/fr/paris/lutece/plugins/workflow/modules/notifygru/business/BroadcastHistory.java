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
 * BroadcastHistory
 */
public class BroadcastHistory
{
    private int _nIdMailingListBroadcast;
    private String _strSenderNameBroadcast;
    private String _strSubjectBroadcast;
    private String _strMessageBroadcast;
    private String _strRecipientsCcBroadcast;
    private String _strRecipientsCciBroadcast;
    private String _strLevelNotificationBroadcast;

    public int getIdMailingListBroadcast(  )
    {
        return _nIdMailingListBroadcast;
    }

    public void setIdMailingListBroadcast( int nIdMailingListBroadcast )
    {
        this._nIdMailingListBroadcast = nIdMailingListBroadcast;
    }

    public String getSenderNameBroadcast(  )
    {
        return _strSenderNameBroadcast;
    }

    public void setSenderNameBroadcast( String strSenderNameBroadcast )
    {
        this._strSenderNameBroadcast = strSenderNameBroadcast;
    }

    public String getSubjectBroadcast(  )
    {
        return _strSubjectBroadcast;
    }

    public void setSubjectBroadcast( String strSubjectBroadcast )
    {
        this._strSubjectBroadcast = strSubjectBroadcast;
    }

    public String getMessageBroadcast(  )
    {
        return _strMessageBroadcast;
    }

    public void setMessageBroadcast( String strMessageBroadcast )
    {
        this._strMessageBroadcast = strMessageBroadcast;
    }

    public String getRecipientsCcBroadcast(  )
    {
        return _strRecipientsCcBroadcast;
    }

    public void setRecipientsCcBroadcast( String strRecipientsCcBroadcast )
    {
        this._strRecipientsCcBroadcast = strRecipientsCcBroadcast;
    }

    public String getRecipientsCciBroadcast(  )
    {
        return _strRecipientsCciBroadcast;
    }

    public void setRecipientsCciBroadcast( String strRecipientsCciBroadcast )
    {
        this._strRecipientsCciBroadcast = strRecipientsCciBroadcast;
    }

    public String getLevelNotificationBroadcast(  )
    {
        return _strLevelNotificationBroadcast;
    }

    public void setLevelNotificationBroadcast( String strLevelNotificationBroadcast )
    {
        this._strLevelNotificationBroadcast = strLevelNotificationBroadcast;
    }
}
