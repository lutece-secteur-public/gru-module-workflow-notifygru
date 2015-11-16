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
    private int _nIdRessourceGuichet;
    private int _nIdDemandGuichet;
    private int _nIdUserGuidGuichet;
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
     * user email : vue send email
     */
    private int _nIdRessourceEmail;
    private String _strRessourceRecordEmail;
    private int _nRessourceRecordUserGuidEmail;
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
    private int _nIdRessourceSMS;
    private String _nRessourceRecordSMS;
    private int _nRessourceRecordUserGuidSMS;
    private String _strSubjectSMS;
    private String _strEntrySMS;
    private String _strMessageSMS;
    private String _strSenderNameSMS;
    private String _strRecipientsSMS;
    private String _strRecipientsCcSMS;
    private String _strRecipientsCciSMS;
    private boolean _bIsNotifyBySMS;
    private String _strLevelNotificationSMS;
    private boolean _bActiveOngletSMS;

    public int getIdRessourceGuichet() {
        return _nIdRessourceGuichet;
    }

    public void setIdRessourceGuichet(int _nIdRessourceGuichet) {
        this._nIdRessourceGuichet = _nIdRessourceGuichet;
    }

    public int getIdDemandGuichet() {
        return _nIdDemandGuichet;
    }

    public void setIdDemandGuichet(int _nIdDemandGuichet) {
        this._nIdDemandGuichet = _nIdDemandGuichet;
    }

    public int getIdUserGuidGuichet() {
        return _nIdUserGuidGuichet;
    }

    public void setIdUserGuidGuichet(int _nIdUserGuidGuichet) {
        this._nIdUserGuidGuichet = _nIdUserGuidGuichet;
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

    public int getIdRessourceEmail() {
        return _nIdRessourceEmail;
    }

    public void setIdRessourceEmail(int _nIdRessourceEmail) {
        this._nIdRessourceEmail = _nIdRessourceEmail;
    }

    public String getRessourceRecordEmail() {
        return _strRessourceRecordEmail;
    }

    public void setRessourceRecordEmail(String _strRessourceRecordEmail) {
        this._strRessourceRecordEmail = _strRessourceRecordEmail;
    }

    public int getRessourceRecordUserGuidEmail() {
        return _nRessourceRecordUserGuidEmail;
    }

    public void setRessourceRecordUserGuidEmail(int _nRessourceRecordUserGuidEmail) {
        this._nRessourceRecordUserGuidEmail = _nRessourceRecordUserGuidEmail;
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

    public int getIdRessourceSMS() {
        return _nIdRessourceSMS;
    }

    public void setIdRessourceSMS(int _nIdRessourceSMS) {
        this._nIdRessourceSMS = _nIdRessourceSMS;
    }

    public String getRessourceRecordSMS() {
        return _nRessourceRecordSMS;
    }

    public void setRessourceRecordSMS(String _nRessourceRecordSMS) {
        this._nRessourceRecordSMS = _nRessourceRecordSMS;
    }

    public int getRessourceRecordUserGuidSMS() {
        return _nRessourceRecordUserGuidSMS;
    }

    public void setRessourceRecordUserGuidSMS(int _nRessourceRecordUserGuidSMS) {
        this._nRessourceRecordUserGuidSMS = _nRessourceRecordUserGuidSMS;
    }

    public String getSubjectSMS() {
        return _strSubjectSMS;
    }

    public void setSubjectSMS(String _strSubjectSMS) {
        this._strSubjectSMS = _strSubjectSMS;
    }

    public String getEntrySMS() {
        return _strEntrySMS;
    }

    public void setEntrySMS(String _strEntrySMS) {
        this._strEntrySMS = _strEntrySMS;
    }

    public String getMessageSMS() {
        return _strMessageSMS;
    }

    public void setMessageSMS(String _strMessageSMS) {
        this._strMessageSMS = _strMessageSMS;
    }

    public String getSenderNameSMS() {
        return _strSenderNameSMS;
    }

    public void setSenderNameSMS(String _strSenderNameSMS) {
        this._strSenderNameSMS = _strSenderNameSMS;
    }

    public String getRecipientsSMS() {
        return _strRecipientsSMS;
    }

    public void setRecipientsSMS(String _strRecipientsSMS) {
        this._strRecipientsSMS = _strRecipientsSMS;
    }

    public String getRecipientsCcSMS() {
        return _strRecipientsCcSMS;
    }

    public void setRecipientsCcSMS(String _strRecipientsCcSMS) {
        this._strRecipientsCcSMS = _strRecipientsCcSMS;
    }

    public String getRecipientsCciSMS() {
        return _strRecipientsCciSMS;
    }

    public void setRecipientsCciSMS(String _strRecipientsCciSMS) {
        this._strRecipientsCciSMS = _strRecipientsCciSMS;
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
