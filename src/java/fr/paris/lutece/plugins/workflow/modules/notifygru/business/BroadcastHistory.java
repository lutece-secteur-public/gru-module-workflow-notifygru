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


/**
 * This is the business class for the object BroadcastHistory
 */
public class BroadcastHistory
{
    // Variables declarations 
    private int _nIdMailingListBroadcast;
    private String _strEmailBroadcast;
    private String _strSenderNameBroadcast;
    private String _strSubjectBroadcast;
    private String _strMessageBroadcast;
    private String _strRecipientsCcBroadcast;
    private String _strRecipientsCciBroadcast;
    private boolean _bActiveOngletBroadcast;

    /**
     * Returns the IdMailingListBroadcast
     * @return The IdMailingListBroadcast
     */
    public int getIdMailingListBroadcast(  )
    {
        return _nIdMailingListBroadcast;
    }

    /**
     * Sets the IdMailingListBroadcast
     * @param nIdMailingListBroadcast The IdMailingListBroadcast
     */
    public void setIdMailingListBroadcast( int nIdMailingListBroadcast )
    {
        _nIdMailingListBroadcast = nIdMailingListBroadcast;
    }

    /**
         * @return the EmailBroadcast
         */
    public String getEmailBroadcast(  )
    {
        return _strEmailBroadcast;
    }

    /**
     * @param strEmailBroadcast the EmailBroadcast to set
     */
    public void setEmailBroadcast( String strEmailBroadcast )
    {
        this._strEmailBroadcast = strEmailBroadcast;
    }

    /**
    * Returns the SenderNameBroadcast
    * @return The SenderNameBroadcast
    */
    public String getSenderNameBroadcast(  )
    {
        return _strSenderNameBroadcast;
    }

    /**
     * Sets the SenderNameBroadcast
     * @param strSenderNameBroadcast The SenderNameBroadcast
     */
    public void setSenderNameBroadcast( String strSenderNameBroadcast )
    {
        _strSenderNameBroadcast = strSenderNameBroadcast;
    }

    /**
     * Returns the SubjectBroadcast
     * @return The SubjectBroadcast
     */
    public String getSubjectBroadcast(  )
    {
        return _strSubjectBroadcast;
    }

    /**
     * Sets the SubjectBroadcast
     * @param strSubjectBroadcast The SubjectBroadcast
     */
    public void setSubjectBroadcast( String strSubjectBroadcast )
    {
        _strSubjectBroadcast = strSubjectBroadcast;
    }

    /**
     * Returns the MessageBroadcast
     * @return The MessageBroadcast
     */
    public String getMessageBroadcast(  )
    {
        return _strMessageBroadcast;
    }

    /**
     * Sets the MessageBroadcast
     * @param strMessageBroadcast The MessageBroadcast
     */
    public void setMessageBroadcast( String strMessageBroadcast )
    {
        _strMessageBroadcast = strMessageBroadcast;
    }

    /**
     * Returns the RecipientsCcBroadcast
     * @return The RecipientsCcBroadcast
     */
    public String getRecipientsCcBroadcast(  )
    {
        return _strRecipientsCcBroadcast;
    }

    /**
     * Sets the RecipientsCcBroadcast
     * @param strRecipientsCcBroadcast The RecipientsCcBroadcast
     */
    public void setRecipientsCcBroadcast( String strRecipientsCcBroadcast )
    {
        _strRecipientsCcBroadcast = strRecipientsCcBroadcast;
    }

    /**
     * Returns the RecipientsCciBroadcast
     * @return The RecipientsCciBroadcast
     */
    public String getRecipientsCciBroadcast(  )
    {
        return _strRecipientsCciBroadcast;
    }

    /**
     * Sets the RecipientsCciBroadcast
     * @param strRecipientsCciBroadcast The RecipientsCciBroadcast
     */
    public void setRecipientsCciBroadcast( String strRecipientsCciBroadcast )
    {
        _strRecipientsCciBroadcast = strRecipientsCciBroadcast;
    }

    /**
     * Returns the ActiveOngletBroadcast
     * @return The ActiveOngletBroadcast
     */
    public boolean isActiveOngletBroadcast(  )
    {
        return _bActiveOngletBroadcast;
    }

    /**
     * Sets the ActiveOngletBroadcast
     * @param activeOngletBroadcast The ActiveOngletBroadcast
     */
    public void setActiveOngletBroadcast( boolean activeOngletBroadcast )
    {
        _bActiveOngletBroadcast = activeOngletBroadcast;
    }
}
