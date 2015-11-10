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
public class TaskNotifyGruConfig extends TaskConfig
{
    /*for task directory*/
    @NotNull
    @Min( 1 )
    private int _nIdDirectoryOngle1;
    private int _nPositionEntryDirectoryEmail;
    private int _nPositionEntryDirectorySms;
    private int _nPositionEntryDirectoryUserGuidOngle1;
    private String _strSubjectOngle1;
    private String _strMessageOngle1;
    private String _strSenderNameOngle1;
    
    private boolean _bIsNotifyByEmail;
    private boolean _bIsNotifyBySms;
    private boolean _bIsNotifyByMailingList;
    private boolean _bIsNotifyByUserGuid;   
    private boolean _bEmailValidation;
    private int _nIdStateAfterValidation;
    private String _strLabelLink;
    private String _strMessageValidation;
    private int _nPeriodValidity;
    private String _strRecipientsCc;
    private String _strRecipientsBcc;
    private int _nIdMailingList;
    private boolean _bViewRecord;
    private String _strLabelLinkViewRecord;
    private List<Integer> _listPositionEntryFile;
    
     /*for task crm*/
    @NotNull
    @Min( 1 )
    private int _nIdDirectoryOngle3;
    @NotNull
    @Min( 1 )
    private int _nPositionEntryDirectoryIdDemand;
    private int _nPositionEntryDirectoryUserGuidOngle3;
    private int _nPositionEntryDirectoryCrmWebAppCode;
    
      private String _strSubjectOngle3;
    private String _strMessageOngle3;
    private String _strSenderNameOngle3;
     
    private boolean _bSendNotification;
    @NotNull
    private String _strStatusText;
  
    
    
     /**********************************************
       * Getter and setter for task directory
       ************************************/
       /**
    *
    * @return id directory
    */
    public int getIdDirectoryOngle1(  )
    {
        return _nIdDirectoryOngle1;
    }

    /**
     * Set id directory
     * @param idDirectory id directory
     */
    public void setIdDirectoryOngle1( int idDirectory )
    {
        _nIdDirectoryOngle1 = idDirectory;
    }
    
     /**
     * Get the position of the entry directory form email
     * @return position Entry directory Email
     */
    public int getPositionEntryDirectoryEmail(  )
    {
        return _nPositionEntryDirectoryEmail;
    }

    /**
     * Set position Entry directory Email
     * @param nPositionEntryDirectoryEmail position of Entry directory Email
     */
    public void setPositionEntryDirectoryEmail( int nPositionEntryDirectoryEmail )
    {
        _nPositionEntryDirectoryEmail = nPositionEntryDirectoryEmail;
    }

    /**
     * Get the position of the entry directory for sms
     * @return position Entry directory Sms
     */
    public int getPositionEntryDirectorySms(  )
    {
        return _nPositionEntryDirectorySms;
    }

    /**
     * Set position of Entry directory Sms
     * @param nPositionEntryDirectorySms position of Entry directory Sms
     */
    public void setPositionEntryDirectorySms( int nPositionEntryDirectorySms )
    {
        _nPositionEntryDirectorySms = nPositionEntryDirectorySms;
    }

    /**
     * Get the position of the entry directory associated to the user guid
     * @return position Entry directory user guid
     */
    public int getPositionEntryDirectoryUserGuidOngle1(  )
    {
        return _nPositionEntryDirectoryUserGuidOngle1;
    }

    /**
     * Set position Entry directory user guid
     * @param nPositionEntryDirectoryUserGuid position of Entry directory user guid
     */
    public void setPositionEntryDirectoryUserGuidOngle1( int nPositionEntryDirectoryUserGuid )
    {
        _nPositionEntryDirectoryUserGuidOngle1 = nPositionEntryDirectoryUserGuid;
    }
    
     /**
     * Get the subject
     * @return the subject of the message
     */
    public String getSubjectOngle1(  )
    {
        return _strSubjectOngle1;
    }
    
     
     /**
     * Get the subject
     * @return the subject of the message
     */
    public String getSubjectOngle3(  )
    {
        return _strSubjectOngle3;
    }

    /**
     * Set the subject of the message
     * @param subject the subject of the message
     */
    public void setSubjectOngle1( String subject )
    {
        _strSubjectOngle1 = subject;
    }

    
    /**
     * Set the subject of the message
     * @param subject the subject of the message
     */
    public void setSubjectOngle3( String subject )
    {
        _strSubjectOngle3 = subject;
    }
    
    /**
     * Get the message
     * @return the message of the notification
     */
    public String getMessageOngle1(  )
    {
        return _strMessageOngle1;
    }
    
     /**
     * Get the message
     * @return the message of the notification
     */
    public String getMessageOngle3(  )
    {
        return _strMessageOngle3;
    }

    /**
     * Set the message of the notification
     * @param message the message of the notifictaion
     */
    public void setMessageOngle1( String message )
    {
        _strMessageOngle1 = message;
    }

    
     /**
     * Set the message of the notification
     * @param message the message of the notifictaion
     */
    public void setMessageOngle3( String message )
    {
        _strMessageOngle3 = message;
    }

    
    /**
     * Get the sender name
     * @return the sender name
     */
    public String getSenderNameOngle1(  )
    {
        return _strSenderNameOngle1;
    }

    
      
    /**
     * Get the sender name
     * @return the sender name
     */
    public String getSenderNameOngle3(  )
    {
        return _strSenderNameOngle3;
    }
    
    /**
     * Set the sender name
     * @param senderName the sender name
     */
    public void setSenderNameOngle1( String senderName )
    {
        _strSenderNameOngle1 = senderName;
    }

     /**
     * Set the sender name
     * @param senderName the sender name
     */
    public void setSenderNameOngle3( String senderName )
    {
        _strSenderNameOngle3 = senderName;
    }
    
    
    /**
     * Check if it is notify by mail
     * @return true if notify by Email
     */
    public boolean isNotifyByEmail(  )
    {
        return _bIsNotifyByEmail;
    }

    /**
     * Set true if notify by Email
     * @param bIsNotifyByEmail true if notify by Email
     */
    public void setNotifyByEmail( boolean bIsNotifyByEmail )
    {
        _bIsNotifyByEmail = bIsNotifyByEmail;
    }

    /**
     * Check if it is notify by sms
     * @return true if notify by Sms
     */
    public boolean isNotifyBySms(  )
    {
        return _bIsNotifyBySms;
    }

    /**
     * Set true if notify by Sms
     * @param bIsNotifyBySms enable true if notify by Sms
     */
    public void setNotifyBySms( boolean bIsNotifyBySms )
    {
        _bIsNotifyBySms = bIsNotifyBySms;
    }

    /**
     * Check if it is notify by MailingList
     * @return true if notify by MailingList
     */
    public boolean isNotifyByMailingList(  )
    {
        return _bIsNotifyByMailingList;
    }

    /**
     * Set true if notify by MailingList
     * @param bIsNotifyByMailingList enable true if notify by Sms
     */
    public void setNotifyByMailingList( boolean bIsNotifyByMailingList )
    {
        _bIsNotifyByMailingList = bIsNotifyByMailingList;
    }

    /**
     * Set true if notify by user guid
     * @param bIsNotifyByUserGuid true if notify by user guid
     */
    public void setNotifyByUserGuid( boolean bIsNotifyByUserGuid )
    {
        _bIsNotifyByUserGuid = bIsNotifyByUserGuid;
    }

    /**
     * Check if notify by user guid
     * @return true if notify by user guid
     */
    public boolean isNotifyByUserGuid(  )
    {
        return _bIsNotifyByUserGuid;
    }

    /**
     * Check if it must have an email validation
     * @return true if the email is email validation
     */
    public boolean isEmailValidation(  )
    {
        return _bEmailValidation;
    }

    /**
     * Set true if the email is email validation
     * @param bEmailValidation enable true if the email is email validation
     */
    public void setEmailValidation( boolean bEmailValidation )
    {
        _bEmailValidation = bEmailValidation;
    }

    /**
     * Get the id state after validation
     * @return id Entry directory
     */
    public int getIdStateAfterValidation(  )
    {
        return _nIdStateAfterValidation;
    }

    /**
     * Set id of State Workflow after validation
     * @param nIdStateAfterValidation the id state after validation
     */
    public void setIdStateAfterValidation( int nIdStateAfterValidation )
    {
        _nIdStateAfterValidation = nIdStateAfterValidation;
    }

    /**
     * Get the label link
     * @return the label link
     */
    public String getLabelLink(  )
    {
        return _strLabelLink;
    }

    /**
     * Set the label link
     * @param strLabelLink the label link
     */
    public void setLabelLink( String strLabelLink )
    {
        _strLabelLink = strLabelLink;
    }

    /**
     * Get the message validation
     * @return the message of validation
     */
    public String getMessageValidation(  )
    {
        return _strMessageValidation;
    }

    /**
     * Set the message of the validation
     * @param messageValidation the message of the validation
     */
    public void setMessageValidation( String messageValidation )
    {
        _strMessageValidation = messageValidation;
    }

    /**
     * Get the period validity
     * @return nPeriodValidity
     */
    public int getPeriodValidity(  )
    {
        return _nPeriodValidity;
    }

    /**
     * Set nPeriodValidity
     * @param nPeriodValidity period of validity
     */
    public void setPeriodValidity( int nPeriodValidity )
    {
        _nPeriodValidity = nPeriodValidity;
    }

    /**
     * Returns the Recipient
     * @return The Recipient
     */
    public String getRecipientsCc(  )
    {
        return _strRecipientsCc;
    }

    /**
     * Sets the Recipient
     * @param strRecipient The Recipient
     */
    public void setRecipientsCc( String strRecipient )
    {
        _strRecipientsCc = strRecipient;
    }

    /**
     * Returns the Recipient
     * @return The Recipient
     */
    public String getRecipientsBcc(  )
    {
        return _strRecipientsBcc;
    }

    /**
     * Sets the Recipient
     * @param strRecipient The Recipient
     */
    public void setRecipientsBcc( String strRecipient )
    {
        _strRecipientsBcc = strRecipient;
    }

    /**
     * Get the id mailing list
     * @return the id mailing list
     */
    public int getIdMailingList(  )
    {
        return _nIdMailingList;
    }

    /**
    * Set the id mailing list
    * @param nIdMailingList the id mailing list
    */
    public void setIdMailingList( int nIdMailingList )
    {
        _nIdMailingList = nIdMailingList;
    }

    /**
     * Set the link view record
     * @param bViewRecord true if the email should include the link view record
     */
    public void setViewRecord( boolean bViewRecord )
    {
        _bViewRecord = bViewRecord;
    }

    /**
     * Check if the email should include the link view record
     * @return true if the email should include the link view record
     */
    public boolean isViewRecord(  )
    {
        return _bViewRecord;
    }

    /**
     * Set the label for the link view record
     * @param strLabelLinkViewRecord the label
     */
    public void setLabelLinkViewRecord( String strLabelLinkViewRecord )
    {
        _strLabelLinkViewRecord = strLabelLinkViewRecord;
    }

    /**
     * Get the label for the link view record
     * @return the label for the link view record
     */
    public String getLabelLinkViewRecord(  )
    {
        return _strLabelLinkViewRecord;
    }

    /**
     * set the list of entry file which must be include in mail attachments
     * @param listPositionEntryFile  list of entry file which must be include in mail attachments
     */
    public void setListPositionEntryFile( List<Integer> listPositionEntryFile )
    {
        _listPositionEntryFile = listPositionEntryFile;
    }

    /**
     * get the list of entry file which must be include in mail attachments
     * @return the list of entry file which must be include in mail attachments
     */
    public List<Integer> getListPositionEntryFile(  )
    {
        return _listPositionEntryFile;
    }
    
     /**********************************************
       * end Getter and setter for task directory
       ************************************/
    

      /**********************************************
       * Getter and setter for task crm
       ************************************/
    /**
     * Get the ID directory
     * @return id directory
     */
    public int getIdDirectoryOngle3(  )
    {
        return _nIdDirectoryOngle3;
    }

    /**
     * Set id directory
     * @param idDirectory id directory
     */
    public void setIdDirectoryOngle3( int idDirectory )
    {
        _nIdDirectoryOngle3 = idDirectory;
    }

    /**
     * Get the position of the entry directory id demand
     * @return position Entry directory ID demand
     */
    public int getPositionEntryDirectoryIdDemand(  )
    {
        return _nPositionEntryDirectoryIdDemand;
    }

    /**
     * Set position Entry directory id demand
     * @param nPositionEntryDirectoryIdDemand position of Entry directory id demand
     */
    public void setPositionEntryDirectoryIdDemand( int nPositionEntryDirectoryIdDemand )
    {
        _nPositionEntryDirectoryIdDemand = nPositionEntryDirectoryIdDemand;
    }

    /**
     * Get the position of the entry directory associated to the user guid
     * @return position Entry directory user guid
     */
    public int getPositionEntryDirectoryUserGuidOngle3(  )
    {
        return _nPositionEntryDirectoryUserGuidOngle3;
    }

    /**
     * Set position Entry directory user guid
     * @param nPositionEntryDirectoryUserGuid position of Entry directory user guid
     */
    public void setPositionEntryDirectoryUserGuidOngle3( int nPositionEntryDirectoryUserGuid )
    {
        _nPositionEntryDirectoryUserGuidOngle3 = nPositionEntryDirectoryUserGuid;
    }

    /**
     * Set true if it must send a notification, false otherwise
     * @param bSendNotification true if it must send a notification, false otherwise
     */
    public void setSendNotification( boolean bSendNotification )
    {
        _bSendNotification = bSendNotification;
    }

    /**
     * Return true if it must send a notification, false otherwise
     * @return true if it must send a notification, false otherwise
     */
    public boolean getSendNotification(  )
    {
        return _bSendNotification;
    }

    /**
     * Set the status
     * @param strStatusText the status
     */
    public void setStatusText( String strStatusText )
    {
        _strStatusText = strStatusText;
    }

    /**
     * Get the status
     * @return the status
     */
    public String getStatusText(  )
    {
        return _strStatusText;
    }
   
	/**
	 * 
	 * @return  the position Entry directory crm webapp code
	 */
	public int getPositionEntryDirectoryCrmWebAppCode() {
		return _nPositionEntryDirectoryCrmWebAppCode;
	}
	
	/**
	 * Set position Entry directory crm webapp code
	 * @param positionEntryCrmWebAppCode Set position Entry directory crm webapp code
	 */
	public void setPositionEntryDirectoryCrmWebAppCode(int nPositionEntryCrmWebAppCode) {
		this._nPositionEntryDirectoryCrmWebAppCode = nPositionEntryCrmWebAppCode;
	}
        
         /**********************************************
       * end Getter and setter for task crm
       ************************************/
}
