package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * TaskNotifyDirectoryConfig
 *
 */
public class TaskNotifyGruConfigRefactoring extends TaskConfig {

    /**
     * user dashboard : guichet
     */
   
     @NotNull
     @Min( 1 )     
    private int _nIdRessourceGuichet;
    private int _nIdDemandGuichet;
    private int _nUserGuidGuichet;   
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
    private int _nRessourceRecordEmail;
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
    private int _nRessourceSMS;
    private int _nRessourceRecordSMS;
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
    /**
     * end user sms : vue sms
     */
}
