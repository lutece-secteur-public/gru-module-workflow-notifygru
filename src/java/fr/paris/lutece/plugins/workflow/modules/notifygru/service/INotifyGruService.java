
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;


import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.mail.FileAttachment;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 *
 */
public interface INotifyGruService
{
    // CHECKS

    /**
     * Check if the given entry type id is accepted for the email or the sms
     * @param nIdEntryType the id entry type
     * @return true if it is accepted, false otherwise
     */
    boolean isEntryTypeEmailSMSAccepted( int nIdEntryType );

    /**
     * Check if the given entry type id is accepted for the user guid
     * @param nIdEntryType the id entry type
     * @return true if it is accepted, false otherwise
     */
    boolean isEntryTypeUserGuidAccepted( int nIdEntryType );

    /**
     * Check if the given entry type id is accepted for file
     * @param nIdEntryType the id entry type
     * @return true if it is accepted, false otherwise
     */
    boolean isEntryTypeFileAccepted( int nIdEntryType );

    /**
     * Check if the entry is refused (values set in the
     * workflow-notifygru.properties)
     * @param nIdEntryType the id entry type
     * @return true if it is refused, false otherwise
     */
    boolean isEntryTypeRefused( int nIdEntryType );

    // GETS

    /**
     * Get the list of states
     * @param nIdAction  the id action
     * @return a ReferenceList
     */
    ReferenceList getListStates( int nIdAction );

    /**
     * Get the list of directorise
     * @return a ReferenceList
     */
    ReferenceList getListDirectories(  );

    /**
     * Get the mailing list
     * @param request the HTTP request
     * @return a ReferenceList
     */
    ReferenceList getMailingList( HttpServletRequest request );

    /**
     * Get the list of entries from a given id task
     * @param nIdTask the id task
     * @return a list of IEntry
     */
   // List<IEntry> getListEntries( int nIdTask );

    /**
     * Get the list of entries that have the accepted type (which are defined in
     * <b>workflow-notifygru.properties</b>)
     * @param nIdTask the id task
     * @param locale the Locale
     * @return a ReferenceList
     */
    ReferenceList getListEntriesUserGuid( int nIdTask, Locale locale );

    /**
     * Get the list of entries that have the accepted type (which are defined in
     * <b>workflow-notifygru.properties</b>)
     * @param nIdTask the id task
     * @param locale the Locale
     * @return a ReferenceList
     */
    ReferenceList getListEntriesEmailSMS( int nIdTask, Locale locale );

    /**
     * Get the list of entries that have not the refused type (which are defined
     * in the <b>workflow-notifygru.properties</b>). <br />
     * This list will be displayed as a freemarker label that the webmaster can
     * use to write the notifications.
     * @param nIdTask the id task
     * @return a list of {@link IEntry}
     */
   // List<IEntry> getListEntriesFreemarker( int nIdTask );

    /**
     * Get the list of entries that have the accepted type for file)
     * @param nIdTask the id task
     * @param locale the Locale
     * @return a List of entries
     */
   // List<IEntry> getListEntriesFile( int nIdTask, Locale locale );

    /**
     * Get the email from either an entry containing the email, or an entry
     * containing the user guid
     * @param config the config
     * @param nIdRecord the id record
     * @param nIdDirectory the id directory
     * @return the email
     */
    String getEmail( TaskNotifyGruConfig config, int nIdRecord, int nIdDirectory );

    /**
     * Get the sms phone number
     * @param config the config
     * @param nIdRecord the id record
     * @param nIdDirectory the id directory
     * @return the sms phone number
     */
    String getSMSPhoneNumber( TaskNotifyGruConfig config, int nIdRecord, int nIdDirectory );

    /**
     * The files Attachments to insert in the mail
     * @param config the configuration
     * @param nIdRecord the record id
     * @param nIdDirectory the  directory id
     * @return the files Attachments to insert in the mail
     */
    List<FileAttachment> getFilesAttachment( TaskNotifyGruConfig config, int nIdRecord, int nIdDirectory );

    /**
     * Get the user guid
     * @param config the config
     * @param nIdRecord the id record
     * @param nIdDirectory the id directory
     * @return the user guid, an empty string if the position is not set
     */
    String getUserGuid( TaskNotifyGruConfig config, int nIdRecord, int nIdDirectory );

    /**
     * Get the list of tasks that have an order below the given reference task and that
     * have a ITaskProvider .
     * <br />
     * <b>Example</b> : The list of all tasks that has a provider :
     * <ul>
     * <li>Task1</li>
     * <li>Task2</li>
     * <li>Task3</li>
     * </ul>
     * If the reference task is Task1, the it returns nothing.
     * <br />
     * If the reference task is Task2, the it returns only Task1.
     * <br />
     * If the reference task is Task3, the it returns [Task1, Task2].
     * @param task the reference task
     * @param locale the locale
     * @return a list of {@link ITask}
     */
    List<ITask> getListBelowTasks( ITask task, Locale locale );

    // OTHERS

    /**
     * Send the message
     * @param config the config
     * @param strEmail the email
     * @param strSms the sms
     * @param strSenderEmail the sender email
     * @param strSubject the subject
     * @param strEmailContent the email content
     * @param strSMSContent the sms content
     * @param listFileAttachment the list of attachments
     */
    void sendMessage( TaskNotifyGruConfig config, String strEmail, String strSms, String strSenderEmail,
        String strSubject, String strEmailContent, String strSMSContent, List<FileAttachment> listFileAttachment );

    /**
     * Fill the model
     * @param config the config
     * @param resourceHistory the resource history
     * @param record the record
     * @param directory the directory
     * @param request the HTTP request
     * @param nIdAction the id action
     * @param nIdHistory the id history
     * @return the model
     */
//    Map<String, Object> fillModel( TaskNotifyGruConfig config, ResourceHistory resourceHistory, Record record,
//        Directory directory, HttpServletRequest request, int nIdAction, int nIdHistory );

    /**
     * Gets the locale.
     *
     * @param request the request
     * @return the locale
     */
    Locale getLocale( HttpServletRequest request );
    
    Map<String, Object> fillModelMoke(  );
}
