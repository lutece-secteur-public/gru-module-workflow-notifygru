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

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;



/**
 *
 * TaskNotifyDirectoryConfig
 *
 */
public class TaskNotifyGruConfig extends TaskConfig
{
    /**
     * provider
     */
    private String _strIdSpringProvider;
    private String _strKeyProvider;

    /**
    * end provider
    */

    /**
      * user dashboard : guichet
      */
    private String _strMessageGuichet;
    private String _strStatustextGuichet;
    private String _strSenderNameGuichet;
    private String _strSubjectGuichet;
    private int _nDemandMaxStepGuichet;
    private int _nDemandUserCurrentStepGuichet;
    private int _nDemandStateGuichet;
    private String _strLevelNotificationGuichet;
    private boolean _bActiveOngletGuichet;

    /**
     * fin user dashboard : guichet
     */

    /**
     * user agent : agent
     */

    /**
     * fin user agent : agent     */
    private String _strMessageAgent;
    private String _strLevelNotificationAgent;
    private boolean _bActiveOngletAgent;

    /**
     * user email : vue send email
     */
    private String _strSubjectEmail;
    private String _strMessageEmail;
    private String _strSenderNameEmail;
    private String _strRecipientsCcEmail;
    private String _strRecipientsCciEmail;
    private String _strLevelNotificationEmail;
    private boolean _bActiveOngletEmail;

    /**
     * fin user email : vue send email
     */

     /**
     * configuration commune
     */
      private int _nCrmStatusIdCommune;
        /**
     * fin configuration commune
     */

    /**
     * fin configuration commune
     *
     */
      
       /**
     * user sms : vue sms
     */
    private String _strMessageSMS;
    private String _strLevelNotificationSMS;
    private boolean _bActiveOngletSMS;
    private int _nIdMailingListBroadcast;
    private String _strSenderNameBroadcast;
    private String _strSubjectBroadcast;
    private String _strMessageBroadcast;
    private String _strRecipientsCcBroadcast;
    private String _strRecipientsCciBroadcast;
    private String _strLevelNotificationBroadcast;
    private boolean _bActiveOngletBroadcast;
    private int _nSetOnglet;
      
    public String getStatustextGuichet() {
        return _strStatustextGuichet;
    }

    public void setStatustextGuichet(String _strStatustextGuichet) {
        this._strStatustextGuichet = _strStatustextGuichet;
    }

    public String getSenderNameGuichet() {
        return _strSenderNameGuichet;
    }

    public void setSenderNameGuichet(String _strSenderNameGuichet) {
        this._strSenderNameGuichet = _strSenderNameGuichet;
    }

    public String getSubjectGuichet() {
        return _strSubjectGuichet;
    }

    public void setSubjectGuichet(String _strSubjectGuichet) {
        this._strSubjectGuichet = _strSubjectGuichet;
    }

    public int getDemandMaxStepGuichet() {
        return _nDemandMaxStepGuichet;
    }

    public void setDemandMaxStepGuichet(int _nDemandMaxStepGuichet) {
        this._nDemandMaxStepGuichet = _nDemandMaxStepGuichet;
    }

    public int getDemandUserCurrentStepGuichet() {
        return _nDemandUserCurrentStepGuichet;
    }

    public void setDemandUserCurrentStepGuichet(int _nDemandUserCurrentStepGuichet) {
        this._nDemandUserCurrentStepGuichet = _nDemandUserCurrentStepGuichet;
    }

    public int getDemandStateGuichet() {
        return _nDemandStateGuichet;
    }

    public void setDemandStateGuichet(int _nDemandStateGuichet) {
        this._nDemandStateGuichet = _nDemandStateGuichet;
    }

    public int getCrmStatusIdCommune() {
        return _nCrmStatusIdCommune;
    }

    public void setCrmStatusIdCommune(int _nCrmStatusIdCommune) {
        this._nCrmStatusIdCommune = _nCrmStatusIdCommune;
    }
     
   
/**
 * 
 * @return _nSetOnglet
 */
    public int getSetOnglet(  )
    {
        return _nSetOnglet;
    }
/**
 * 
 * @param nsetOnglet to set _nSetOnglet
 */
    public void setSetOnglet( int nsetOnglet )
    {
        this._nSetOnglet = nsetOnglet;
    }
/**
 * 
 * @return _strIdSpringProvider
 */
    public String getIdSpringProvider(  )
    {
        return _strIdSpringProvider;
    }
/**
 * 
 * @param strIdProvider to set _strIdSpringProvider
 */
    public void setIdSpringProvider( String strIdProvider )
    {
        this._strIdSpringProvider = strIdProvider;
    }
/**
 * 
 * @return _strKeyProvide
 */
    public String getKeyProvider(  )
    {
        return _strKeyProvider;
    }
/**
 * 
 * @param strKeyProvider to set _strKeyProvider
 */
    public void setKeyProvider( String strKeyProvider )
    {
        this._strKeyProvider = strKeyProvider;
    }
/**
 * 
 * @return _nIdMailingListBroadcast
 */
    public int getIdMailingListBroadcast(  )
    {
        return _nIdMailingListBroadcast;
    }
/**
 * 
 * @param nIdMailingListBroadcast to set _nIdMailingListBroadcast
 */
    public void setIdMailingListBroadcast( int nIdMailingListBroadcast )
    {
        this._nIdMailingListBroadcast = nIdMailingListBroadcast;
    }
/**
 * 
 * @return _strSenderNameBroadcast
 */
    public String getSenderNameBroadcast(  )
    {
        return _strSenderNameBroadcast;
    }
/**
 * 
 * @param strSenderNameBroadcast to set _strSenderNameBroadcast
 */
    public void setSenderNameBroadcast( String strSenderNameBroadcast )
    {
        this._strSenderNameBroadcast = strSenderNameBroadcast;
    }
/**
 * 
 * @return _strSubjectBroadcast
 */
    public String getSubjectBroadcast(  )
    {
        return _strSubjectBroadcast;
    }
/**
 * 
 * @param strSubjectBroadcast to set _strSubjectBroadcast
 */
    public void setSubjectBroadcast( String strSubjectBroadcast )
    {
        this._strSubjectBroadcast = strSubjectBroadcast;
    }
/**
 * 
 * @return _strMessageBroadcast
 */
    public String getMessageBroadcast(  )
    {
        return _strMessageBroadcast;
    }
/**
 * 
 * @param strMessageBroadcast to set _strMessageBroadcast
 */
    public void setMessageBroadcast( String strMessageBroadcast )
    {
        this._strMessageBroadcast = strMessageBroadcast;
    }
/**
 * 
 * @return _strRecipientsCcBroadcast
 */
    public String getRecipientsCcBroadcast(  )
    {
        return _strRecipientsCcBroadcast;
    }
/**
 * 
 * @param strRecipientsCcBroadcast to set _strRecipientsCcBroadcast
 */
    public void setRecipientsCcBroadcast( String strRecipientsCcBroadcast )
    {
        this._strRecipientsCcBroadcast = strRecipientsCcBroadcast;
    }
/**
 * 
 * @return _strRecipientsCciBroadcast
 */
    public String getRecipientsCciBroadcast(  )
    {
        return _strRecipientsCciBroadcast;
    }
/**
 * 
 * @param strRecipientsCciBroadcast to set _strRecipientsCciBroadcast
 */
    public void setRecipientsCciBroadcast( String strRecipientsCciBroadcast )
    {
        this._strRecipientsCciBroadcast = strRecipientsCciBroadcast;
    }
/**
 * 
 * @return _strLevelNotificationBroadcast
 */
    public String getLevelNotificationBroadcast(  )
    {
        return _strLevelNotificationBroadcast;
    }
/**
 * 
 * @param strLevelNotificationBroadcast to set _strLevelNotificationBroadcast
 */
    public void setLevelNotificationBroadcast( String strLevelNotificationBroadcast )
    {
        this._strLevelNotificationBroadcast = strLevelNotificationBroadcast;
    }
/**
 * 
 * @return _bActiveOngletBroadcast
 */
    public boolean isActiveOngletBroadcast(  )
    {
        return _bActiveOngletBroadcast;
    }
/**
 * 
 * @param bActiveOngletBroadcast to set _bActiveOngletBroadcast
 */
    public void setActiveOngletBroadcast( boolean bActiveOngletBroadcast )
    {
        this._bActiveOngletBroadcast = bActiveOngletBroadcast;
    }
/**
 * 
 * @return _strMessageAgent
 */
    public String getMessageAgent(  )
    {
        return _strMessageAgent;
    }
/**
 * 
 * @param strMessageAgent to set _strMessageAgent
 */
    public void setMessageAgent( String strMessageAgent )
    {
        this._strMessageAgent = strMessageAgent;
    }
/**
 * 
 * @return _strLevelNotificationAgent
 */
    public String getLevelNotificationAgent(  )
    {
        return _strLevelNotificationAgent;
    }
/**
 * 
 * @param strLevelNotificationAgent to set _strLevelNotificationAgent
 */
    public void setLevelNotificationAgent( String strLevelNotificationAgent )
    {
        this._strLevelNotificationAgent = strLevelNotificationAgent;
    }
/**
 * 
 * @return _bActiveOngletAgent
 */
    public boolean isActiveOngletAgent(  )
    {
        return _bActiveOngletAgent;
    }
/**
 * 
 * @param bActiveOngletAgent to set _bActiveOngletAgent
 */
    public void setActiveOngletAgent( boolean bActiveOngletAgent )
    {
        this._bActiveOngletAgent = bActiveOngletAgent;
    }
/**
 * 
 * @return _strMessageGuichet
 */
    public String getMessageGuichet(  )
    {
        return _strMessageGuichet;
    }
/**
 * 
 * @param strMessageGuichet to set _strMessageGuichet
 */
    public void setMessageGuichet( String strMessageGuichet )
    {
        this._strMessageGuichet = strMessageGuichet;
    }
/**
 * 
 * @return _strLevelNotificationGuichet
 */
    public String getLevelNotificationGuichet(  )
    {
        return _strLevelNotificationGuichet;
    }
/**
 * 
 * @param strLevelNotificationGuichet to set _strLevelNotificationGuichet
 */
    public void setLevelNotificationGuichet( String strLevelNotificationGuichet )
    {
        this._strLevelNotificationGuichet = strLevelNotificationGuichet;
    }
/**
 * 
 * @return _bActiveOngletGuichet
 */
    public boolean isActiveOngletGuichet(  )
    {
        return _bActiveOngletGuichet;
    }
/**
 * 
 * @param bActiveOngletGuichet to set _bActiveOngletGuichet
 */
    public void setActiveOngletGuichet( boolean bActiveOngletGuichet )
    {
        this._bActiveOngletGuichet = bActiveOngletGuichet;
    }
/**
 * 
 * @return _strSubjectEmail
 */
    public String getSubjectEmail(  )
    {
        return _strSubjectEmail;
    }
/**
 * 
 * @param strSubjectEmail to set _strSubjectEmail
 */
    public void setSubjectEmail( String strSubjectEmail )
    {
        this._strSubjectEmail = strSubjectEmail;
    }
/**
 * 
 * 
 * @return _strMessageEmail
 */
    public String getMessageEmail(  )
    {
        return _strMessageEmail;
    }
/**
 * 
 * @param strMessageEmail to set _strMessageEmail
 */
    public void setMessageEmail( String strMessageEmail )
    {
        this._strMessageEmail = strMessageEmail;
    }
/**
 * 
 * @return _strSenderNameEmail
 */
    public String getSenderNameEmail(  )
    {
        return _strSenderNameEmail;
    }
/**
 * 
 * @param strSenderNameEmail to set _strSenderNameEmail
 */
    public void setSenderNameEmail( String strSenderNameEmail )
    {
        this._strSenderNameEmail = strSenderNameEmail;
    }
/**
 * 
 * @return _strRecipientsCcEmail
 */
    public String getRecipientsCcEmail(  )
    {
        return _strRecipientsCcEmail;
    }
/**
 * 
 * @param strRecipientsCcEmail to set _strRecipientsCcEmail
 */
    public void setRecipientsCcEmail( String strRecipientsCcEmail )
    {
        this._strRecipientsCcEmail = strRecipientsCcEmail;
    }
/**
 * 
 * @return _strRecipientsCciEmail
 */
    public String getRecipientsCciEmail(  )
    {
        return _strRecipientsCciEmail;
    }
/**
 * 
 * @param strRecipientsCciEmail to set _strRecipientsCciEmail
 */
    public void setRecipientsCciEmail( String strRecipientsCciEmail )
    {
        this._strRecipientsCciEmail = strRecipientsCciEmail;
    }
/**
 * 
 * @return _strLevelNotificationEmail
 */
    public String getLevelNotificationEmail(  )
    {
        return _strLevelNotificationEmail;
    }
/**
 * 
 * @param strLevelNotificationEmail to set _strLevelNotificationEmail
 */
    public void setLevelNotificationEmail( String strLevelNotificationEmail )
    {
        this._strLevelNotificationEmail = strLevelNotificationEmail;
    }
/**
 * 
 * @return _bActiveOngletEmail
 */
    public boolean isActiveOngletEmail(  )
    {
        return _bActiveOngletEmail;
    }
/**
 * 
 * @param bActiveOngletEmail to set _bActiveOngletEmail
 */
    public void setActiveOngletEmail( boolean bActiveOngletEmail )
    {
        this._bActiveOngletEmail = bActiveOngletEmail;
    }
/**
 * 
 * @return _strMessageSMS
 */
    public String getMessageSMS(  )
    {
        return _strMessageSMS;
    }
/**
 * 
 * @param strMessageSMS to set _strMessageSMS
 */
    public void setMessageSMS( String strMessageSMS )
    {
        this._strMessageSMS = strMessageSMS;
    }
/**
 * 
 * @return _strLevelNotificationSMS
 */
    public String getLevelNotificationSMS(  )
    {
        return _strLevelNotificationSMS;
    }
/**
 * 
 * @param strLevelNotificationSMS to set _strLevelNotificationSMS
 */
    public void setLevelNotificationSMS( String strLevelNotificationSMS )
    {
        this._strLevelNotificationSMS = strLevelNotificationSMS;
    }
/**
 * 
 * @return _bActiveOngletSMS
 */
    public boolean isActiveOngletSMS(  )
    {
        return _bActiveOngletSMS;
    }
/**
 * 
 * @param bActiveOngletSMS to set _bActiveOngletSMS
 */
    public void setActiveOngletSMS( boolean bActiveOngletSMS )
    {
        this._bActiveOngletSMS = bActiveOngletSMS;
    }
}
