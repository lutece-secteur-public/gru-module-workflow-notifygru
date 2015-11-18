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
     * user dashboard : guichet
     */
    @NotNull
    @Min(1)
    private int _nIdRessource;
    private int _nIdUserGuid;

    private int _nIdDemandGuichet;
    private int _nCrmWebAppCodeGuichet;
    private boolean _bSendNotificationGuichet;
    @NotNull
    private String _strStatusTextGuichet;
    private String _strSubjectGuichet;
    private String _strMessageGuichet;
    private String _strSenderNameGuichet;
    private String _strLevelNotificationGuichet;
    private boolean _bActiveOngletGuichet;
    /**
     * fin user dashboard : guichet
     */

    /**
     * user agent : agent
     */
    /**
     * fin user agent : agent
     */
    private String _strStatusTextAgent;
    private String _strMessageAgent;
    private String _strLevelNotificationAgent;
    private boolean _bActiveOngletAgent;
    /**
     * user email : vue send email
     */

    private String _strRessourceRecordEmail;
    private String _strSubjectEmail;
    private String _strEntryEmail;
    private String _strMessageEmail;
    private String _strSenderNameEmail;
    private String _strRecipientsEmail;
    private String _strRecipientsCcEmail;
    private String _strRecipientsCciEmail;
    private boolean _bIsNotifyByEmail;
    private String _strLevelNotificationEmail;
    private boolean _bActiveOngletEmail;

    /**
     * fin user email : vue send email
     */
    /**
     * user sms : vue sms
     */
    private String _nRessourceRecordSMS;
    private String _strPhoneSMS;
    private String _strMessageSMS;
    private boolean _bIsNotifyBySMS;
    private String _strLevelNotificationSMS;
    private boolean _bActiveOngletSMS;

    public String getStatusTextAgent() {
        return _strStatusTextAgent;
    }

    public void setStatusTextAgent(String _strStatusTextAgent) {
        this._strStatusTextAgent = _strStatusTextAgent;
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

    
    
    
    public int getIdRessource() {
        return _nIdRessource;
    }

    public void setIdRessource(int _nIdRessource) {
        this._nIdRessource = _nIdRessource;
    }

    public int getIdDemandGuichet() {
        return _nIdDemandGuichet;
    }

    public void setIdDemandGuichet(int _nIdDemandGuichet) {
        this._nIdDemandGuichet = _nIdDemandGuichet;
    }

    public int getIdUserGuid() {
        return _nIdUserGuid;
    }

    public void setIdUserGuid(int _nIdUserGuid) {
        this._nIdUserGuid = _nIdUserGuid;
    }

    public int getCrmWebAppCodeGuichet() {
        return _nCrmWebAppCodeGuichet;
    }

    public void setCrmWebAppCodeGuichet(int _nCrmWebAppCodeGuichet) {
        this._nCrmWebAppCodeGuichet = _nCrmWebAppCodeGuichet;
    }

    public boolean isSendNotificationGuichet() {
        return _bSendNotificationGuichet;
    }

    public void setSendNotificationGuichet(boolean _bSendNotificationGuichet) {
        this._bSendNotificationGuichet = _bSendNotificationGuichet;
    }

    public String getStatusTextGuichet() {
        return _strStatusTextGuichet;
    }

    public void setStatusTextGuichet(String _strStatusTextGuichet) {
        this._strStatusTextGuichet = _strStatusTextGuichet;
    }

    public String getSubjectGuichet() {
        return _strSubjectGuichet;
    }

    public void setSubjectGuichet(String _strSubjectGuichet) {
        this._strSubjectGuichet = _strSubjectGuichet;
    }

    public String getMessageGuichet() {
        return _strMessageGuichet;
    }

    public void setMessageGuichet(String _strMessageGuichet) {
        this._strMessageGuichet = _strMessageGuichet;
    }

    public String getSenderNameGuichet() {
        return _strSenderNameGuichet;
    }

    public void setSenderNameGuichet(String _strSenderNameGuichet) {
        this._strSenderNameGuichet = _strSenderNameGuichet;
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

    public String getRessourceRecordEmail() {
        return _strRessourceRecordEmail;
    }

    public void setRessourceRecordEmail(String _strRessourceRecordEmail) {
        this._strRessourceRecordEmail = _strRessourceRecordEmail;
    }

    public String getSubjectEmail() {
        return _strSubjectEmail;
    }

    public void setSubjectEmail(String _strSubjectEmail) {
        this._strSubjectEmail = _strSubjectEmail;
    }

    public String getEntryEmail() {
        return _strEntryEmail;
    }

    public void setEntryEmail(String _strEntryEmail) {
        this._strEntryEmail = _strEntryEmail;
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

    public String getRecipientsEmail() {
        return _strRecipientsEmail;
    }

    public void setRecipientsEmail(String _strRecipientsEmail) {
        this._strRecipientsEmail = _strRecipientsEmail;
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

    public boolean isIsNotifyByEmail() {
        return _bIsNotifyByEmail;
    }

    public void setIsNotifyByEmail(boolean _bIsNotifyByEmail) {
        this._bIsNotifyByEmail = _bIsNotifyByEmail;
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

    public String getRessourceRecordSMS() {
        return _nRessourceRecordSMS;
    }

    public void setRessourceRecordSMS(String _nRessourceRecordSMS) {
        this._nRessourceRecordSMS = _nRessourceRecordSMS;
    }

    public String getPhoneSMS() {
        return _strPhoneSMS;
    }

    public void setPhoneSMS(String _strEntrySMS) {
        this._strPhoneSMS = _strEntrySMS;
    }

    public String getMessageSMS() {
        return _strMessageSMS;
    }

    public void setMessageSMS(String _strMessageSMS) {
        this._strMessageSMS = _strMessageSMS;
    }

    public boolean isIsNotifyBySMS() {
        return _bIsNotifyBySMS;
    }

    public void setIsNotifyBySMS(boolean _bIsNotifyBySMS) {
        this._bIsNotifyBySMS = _bIsNotifyBySMS;
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
