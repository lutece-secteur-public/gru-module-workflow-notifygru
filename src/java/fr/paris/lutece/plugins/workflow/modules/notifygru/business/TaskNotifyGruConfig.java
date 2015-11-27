package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * TaskNotifyDirectoryConfig
 *
 */
public class TaskNotifyGruConfig extends TaskConfig {

    
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

     
       public int getSetOnglet() {
        return _nSetOnglet;
    }

    public void setSetOnglet(int nsetOnglet) {
        this._nSetOnglet = nsetOnglet;
    }
    
      public String getIdSpringProvider() {
        return _strIdSpringProvider;
    }

    public void setIdSpringProvider(String strIdProvider) {
        this._strIdSpringProvider = strIdProvider;
    }

    public String getKeyProvider() {
        return _strKeyProvider;
    }

    public void setKeyProvider(String _strKeyProvider) {
        this._strKeyProvider = _strKeyProvider;
    }
    
    
    public int getIdMailingListBroadcast() {
        return _nIdMailingListBroadcast;
    }

    public void setIdMailingListBroadcast(int _nIdMailingListBroadcast) {
        this._nIdMailingListBroadcast = _nIdMailingListBroadcast;
    }

    public String getSenderNameBroadcast() {
        return _strSenderNameBroadcast;
    }

    public void setSenderNameBroadcast(String _strSenderNameBroadcast) {
        this._strSenderNameBroadcast = _strSenderNameBroadcast;
    }

    public String getSubjectBroadcast() {
        return _strSubjectBroadcast;
    }

    public void setSubjectBroadcast(String _strSubjectBroadcast) {
        this._strSubjectBroadcast = _strSubjectBroadcast;
    }

    public String getMessageBroadcast() {
        return _strMessageBroadcast;
    }

    public void setMessageBroadcast(String _strMessageBroadcast) {
        this._strMessageBroadcast = _strMessageBroadcast;
    }

    public String getRecipientsCcBroadcast() {
        return _strRecipientsCcBroadcast;
    }

    public void setRecipientsCcBroadcast(String _strRecipientsCcBroadcast) {
        this._strRecipientsCcBroadcast = _strRecipientsCcBroadcast;
    }

    public String getRecipientsCciBroadcast() {
        return _strRecipientsCciBroadcast;
    }

    public void setRecipientsCciBroadcast(String _strRecipientsCciBroadcast) {
        this._strRecipientsCciBroadcast = _strRecipientsCciBroadcast;
    }

    public String getLevelNotificationBroadcast() {
        return _strLevelNotificationBroadcast;
    }

    public void setLevelNotificationBroadcast(String _strLevelNotificationBroadcast) {
        this._strLevelNotificationBroadcast = _strLevelNotificationBroadcast;
    }

    public boolean isActiveOngletBroadcast() {
        return _bActiveOngletBroadcast;
    }

    public void setActiveOngletBroadcast(boolean _bActiveOngletBroadcast) {
        this._bActiveOngletBroadcast = _bActiveOngletBroadcast;
    }
    
    
    

    public String getMessageAgent() {
        return _strMessageAgent;
    }

    public void setMessageAgent(String _strMessageAgent) {
        this._strMessageAgent = _strMessageAgent;
    }

    public String getLevelNotificationAgent() {
        return _strLevelNotificationAgent;
    }

    public void setLevelNotificationAgent(String _strLevelNotificationAgent) {
        this._strLevelNotificationAgent = _strLevelNotificationAgent;
    }

    public boolean isActiveOngletAgent() {
        return _bActiveOngletAgent;
    }

    public void setActiveOngletAgent(boolean _bActiveOngletAgent) {
        this._bActiveOngletAgent = _bActiveOngletAgent;
    }

    

    public String getMessageGuichet() {
        return _strMessageGuichet;
    }

    public void setMessageGuichet(String _strMessageGuichet) {
        this._strMessageGuichet = _strMessageGuichet;
    }

   

    public String getLevelNotificationGuichet() {
        return _strLevelNotificationGuichet;
    }

    public void setLevelNotificationGuichet(String _strLevelNotificationGuichet) {
        this._strLevelNotificationGuichet = _strLevelNotificationGuichet;
    }

    public boolean isActiveOngletGuichet() {
        return _bActiveOngletGuichet;
    }

    public void setActiveOngletGuichet(boolean _bActiveOngletGuichet) {
        this._bActiveOngletGuichet = _bActiveOngletGuichet;
    }

   

    public String getSubjectEmail() {
        return _strSubjectEmail;
    }

    public void setSubjectEmail(String _strSubjectEmail) {
        this._strSubjectEmail = _strSubjectEmail;
    }

  

    public String getMessageEmail() {
        return _strMessageEmail;
    }

    public void setMessageEmail(String _strMessageEmail) {
        this._strMessageEmail = _strMessageEmail;
    }

    public String getSenderNameEmail() {
        return _strSenderNameEmail;
    }

    public void setSenderNameEmail(String _strSenderNameEmail) {
        this._strSenderNameEmail = _strSenderNameEmail;
    }

   

    public String getRecipientsCcEmail() {
        return _strRecipientsCcEmail;
    }

    public void setRecipientsCcEmail(String _strRecipientsCcEmail) {
        this._strRecipientsCcEmail = _strRecipientsCcEmail;
    }

    public String getRecipientsCciEmail() {
        return _strRecipientsCciEmail;
    }

    public void setRecipientsCciEmail(String _strRecipientsCciEmail) {
        this._strRecipientsCciEmail = _strRecipientsCciEmail;
    }

  

    public String getLevelNotificationEmail() {
        return _strLevelNotificationEmail;
    }

    public void setLevelNotificationEmail(String _strLevelNotificationEmail) {
        this._strLevelNotificationEmail = _strLevelNotificationEmail;
    }

    public boolean isActiveOngletEmail() {
        return _bActiveOngletEmail;
    }

    public void setActiveOngletEmail(boolean _bActiveOngletEmail) {
        this._bActiveOngletEmail = _bActiveOngletEmail;
    }

  

    public String getMessageSMS() {
        return _strMessageSMS;
    }

    public void setMessageSMS(String _strMessageSMS) {
        this._strMessageSMS = _strMessageSMS;
    }

   

    public String getLevelNotificationSMS() {
        return _strLevelNotificationSMS;
    }

    public void setLevelNotificationSMS(String _strLevelNotificationSMS) {
        this._strLevelNotificationSMS = _strLevelNotificationSMS;
    }

    public boolean isActiveOngletSMS() {
        return _bActiveOngletSMS;
    }

    public void setActiveOngletSMS(boolean _bActiveOngletSMS) {
        this._bActiveOngletSMS = _bActiveOngletSMS;
    }

}
