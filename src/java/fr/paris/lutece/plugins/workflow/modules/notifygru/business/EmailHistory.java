/*
 * Copyright (c) 2002-2020, City of Paris
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
 * This is the business class for the object EmailHistory
 */
public class EmailHistory
{
    // Variables declarations
    private String _strSubjectEmail;
    private String _strMessageEmail;
    private String _strSenderNameEmail;
    private String _strRecipientsCcEmail;
    private String _strRecipientsCciEmail;
    private boolean _bActiveOngletEmail;

    /**
     * Returns the SubjectEmail
     * 
     * @return The SubjectEmail
     */
    public String getSubjectEmail( )
    {
        return _strSubjectEmail;
    }

    /**
     * Sets the SubjectEmail
     * 
     * @param strSubjectEmail
     *            The SubjectEmail
     */
    public void setSubjectEmail( String strSubjectEmail )
    {
        _strSubjectEmail = strSubjectEmail;
    }

    /**
     * Returns the MessageEmail
     * 
     * @return The MessageEmail
     */
    public String getMessageEmail( )
    {
        return _strMessageEmail;
    }

    /**
     * Sets the MessageEmail
     * 
     * @param strMessageEmail
     *            The MessageEmail
     */
    public void setMessageEmail( String strMessageEmail )
    {
        _strMessageEmail = strMessageEmail;
    }

    /**
     * Returns the SenderNameEmail
     * 
     * @return The SenderNameEmail
     */
    public String getSenderNameEmail( )
    {
        return _strSenderNameEmail;
    }

    /**
     * Sets the SenderNameEmail
     * 
     * @param strSenderNameEmail
     *            The SenderNameEmail
     */
    public void setSenderNameEmail( String strSenderNameEmail )
    {
        _strSenderNameEmail = strSenderNameEmail;
    }

    /**
     * Returns the RecipientsCcEmail
     * 
     * @return The RecipientsCcEmail
     */
    public String getRecipientsCcEmail( )
    {
        return _strRecipientsCcEmail;
    }

    /**
     * Sets the RecipientsCcEmail
     * 
     * @param strRecipientsCcEmail
     *            The RecipientsCcEmail
     */
    public void setRecipientsCcEmail( String strRecipientsCcEmail )
    {
        _strRecipientsCcEmail = strRecipientsCcEmail;
    }

    /**
     * Returns the RecipientsCciEmail
     * 
     * @return The RecipientsCciEmail
     */
    public String getRecipientsCciEmail( )
    {
        return _strRecipientsCciEmail;
    }

    /**
     * Sets the RecipientsCciEmail
     * 
     * @param strRecipientsCciEmail
     *            The RecipientsCciEmail
     */
    public void setRecipientsCciEmail( String strRecipientsCciEmail )
    {
        _strRecipientsCciEmail = strRecipientsCciEmail;
    }

    /**
     * Returns the ActiveOngletEmail
     * 
     * @return The ActiveOngletEmail
     */
    public boolean isActiveOngletEmail( )
    {
        return _bActiveOngletEmail;
    }

    /**
     * Sets the ActiveOngletEmail
     * 
     * @param activeOngletEmail
     *            The ActiveOngletEmail
     */
    public void setActiveOngletEmail( boolean activeOngletEmail )
    {
        _bActiveOngletEmail = activeOngletEmail;
    }
}
