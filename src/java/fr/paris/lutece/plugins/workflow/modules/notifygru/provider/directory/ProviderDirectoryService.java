package fr.paris.lutece.plugins.workflow.modules.notifygru.provider.directory;

import fr.paris.lutece.plugins.directory.business.Directory;
import fr.paris.lutece.plugins.directory.business.DirectoryHome;
import fr.paris.lutece.plugins.directory.business.EntryFilter;
import fr.paris.lutece.plugins.directory.business.EntryHome;
import fr.paris.lutece.plugins.directory.business.EntryTypeGeolocation;
import fr.paris.lutece.plugins.directory.business.File;
import fr.paris.lutece.plugins.directory.business.IEntry;
import fr.paris.lutece.plugins.directory.business.PhysicalFileHome;
import fr.paris.lutece.plugins.directory.business.Record;
import fr.paris.lutece.plugins.directory.business.RecordField;
import fr.paris.lutece.plugins.directory.business.RecordFieldFilter;
import fr.paris.lutece.plugins.directory.business.RecordFieldHome;
import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.plugins.directory.utils.DirectoryUtils;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.AbstractServiceProvider;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.TaskNotifyGruConfigService;


import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.plugins.workflow.service.security.IWorkflowUserAttributesManager;
import fr.paris.lutece.plugins.workflow.service.taskinfo.ITaskInfoProvider;
import fr.paris.lutece.plugins.workflow.service.taskinfo.TaskInfoManager;
import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.business.state.State;
import fr.paris.lutece.plugins.workflowcore.business.state.StateFilter;
import fr.paris.lutece.plugins.workflowcore.service.action.IActionService;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.state.IStateService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.plugins.workflowcore.service.task.ITaskService;
import fr.paris.lutece.portal.business.mailinglist.Recipient;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.mailinglist.AdminMailingListService;


import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.service.workflow.WorkflowService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.mail.FileAttachment;
import fr.paris.lutece.util.url.UrlItem;
import fr.paris.lutece.util.xml.XmlUtil;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * NotifyDirectoryService
 *
 */
public final class ProviderDirectoryService implements IProviderDirectoryService
{
    /** The Constant BEAN_SERVICE. */
    public static final String BEAN_SERVICE = "workflow-notifydirectory.notifyDirectoryService";

    // SERVICES
    @Inject
    private IActionService _actionService;
    @Inject
    private IStateService _stateService;
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyDirectoryService;
    @Inject
    private IWorkflowUserAttributesManager _userAttributesManager;
    @Inject
    private ITaskService _taskService;
     private ProviderDirectory _notifyGruService;
     
   
    
//    @Inject
//    private IResourceKeyService _resourceKeyService;
    private List<Integer> _listAcceptedEntryTypesEmailSMS;
    private List<Integer> _listAcceptedEntryTypesUserGuid;
    private List<Integer> _listRefusedEntryTypes;
    private List<Integer> _listAcceptedEntryTypesFile;
    
     /**
     * Private constructor
     */
    private ProviderDirectoryService(  )
    {
        // Init list accepted entry types for email
        _listAcceptedEntryTypesEmailSMS = fillListEntryTypes( ProviderDirectoryConstants.PROPERTY_ACCEPTED_DIRECTORY_ENTRY_TYPE_EMAIL_SMS );

        // Init list accepted entry types for user guid
        _listAcceptedEntryTypesUserGuid = fillListEntryTypes( ProviderDirectoryConstants.PROPERTY_ACCEPTED_DIRECTORY_ENTRY_TYPE_USER_GUID );

        // Init list accepted entry types for file
        _listAcceptedEntryTypesFile = fillListEntryTypes( ProviderDirectoryConstants.PROPERTY_ACCEPTED_DIRECTORY_ENTRY_TYPE_FILE );

        // Init list refused entry types
        _listRefusedEntryTypes = fillListEntryTypes( ProviderDirectoryConstants.PROPERTY_REFUSED_DIRECTORY_ENTRY_TYPE_USER_GUID );
    }
    
       private static List<Integer> fillListEntryTypes( String strPropertyEntryTypes )
    {
        List<Integer> listEntryTypes = new ArrayList<Integer>(  );
        String strEntryTypes = AppPropertiesService.getProperty( strPropertyEntryTypes );

        if ( StringUtils.isNotBlank( strEntryTypes ) )
        {
            String[] listAcceptEntryTypesForIdDemand = strEntryTypes.split( ProviderDirectoryConstants.COMMA );

            for ( String strAcceptEntryType : listAcceptEntryTypesForIdDemand )
            {
                if ( StringUtils.isNotBlank( strAcceptEntryType ) && StringUtils.isNumeric( strAcceptEntryType ) )
                {
                    int nAcceptedEntryType = Integer.parseInt( strAcceptEntryType );
                    listEntryTypes.add( nAcceptedEntryType );
                }
            }
        }

        return listEntryTypes;
    }
       


    @Override
    public boolean isEntryTypeEmailSMSAccepted(int nIdEntryType) {
      boolean bIsAccepted = false;

        if ( ( _listAcceptedEntryTypesEmailSMS != null ) && !_listAcceptedEntryTypesEmailSMS.isEmpty(  ) )
        {
            bIsAccepted = _listAcceptedEntryTypesEmailSMS.contains( nIdEntryType );
        }

        return bIsAccepted;
    }


      
    @Override
    public boolean isEntryTypeUserGuidAccepted(int nIdEntryType) {
         boolean bIsAccepted = false;

        if ( ( _listAcceptedEntryTypesUserGuid != null ) && !_listAcceptedEntryTypesUserGuid.isEmpty(  ) )
        {
            bIsAccepted = _listAcceptedEntryTypesUserGuid.contains( nIdEntryType );
        }

        return bIsAccepted;
    }
    
    
    @Override
    public boolean isEntryTypeFileAccepted(int nIdEntryType) {
         boolean bIsAccepted = false;

        if ( ( _listAcceptedEntryTypesFile != null ) && !_listAcceptedEntryTypesFile.isEmpty(  ) )
        {
            bIsAccepted = _listAcceptedEntryTypesFile.contains( nIdEntryType );
        }

        return bIsAccepted;
    }

    
    @Override
    public boolean isEntryTypeRefused(int nIdEntryType) {
         boolean bIsRefused = true;

        if ( ( _listRefusedEntryTypes != null ) && !_listRefusedEntryTypes.isEmpty(  ) )
        {
            bIsRefused = _listRefusedEntryTypes.contains( nIdEntryType );
        }

        return bIsRefused;
    }
    
   

    @Override
    public ReferenceList getListStates(int nIdAction) {
        ReferenceList referenceListStates = new ReferenceList(  );
        Action action = _actionService.findByPrimaryKey( nIdAction );

        if ( ( action != null ) && ( action.getWorkflow(  ) != null ) )
        {
            StateFilter stateFilter = new StateFilter(  );
            stateFilter.setIdWorkflow( action.getWorkflow(  ).getId(  ) );

            List<State> listStates = _stateService.getListStateByFilter( stateFilter );

            referenceListStates.addItem( DirectoryUtils.CONSTANT_ID_NULL, StringUtils.EMPTY );
            referenceListStates.addAll( ReferenceList.convert( listStates, ProviderDirectoryConstants.ID,
                    ProviderDirectoryConstants.NAME, true ) );
        }

        return referenceListStates;
    }


    @Override
    public ReferenceList getListDirectories() {
         Plugin pluginDirectory = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );
        ReferenceList listDirectories = DirectoryHome.getDirectoryList( pluginDirectory );
        ReferenceList refenreceListDirectories = new ReferenceList(  );
        refenreceListDirectories.addItem( DirectoryUtils.CONSTANT_ID_NULL, StringUtils.EMPTY );

        if ( listDirectories != null )
        {
            refenreceListDirectories.addAll( listDirectories );
        }

        return refenreceListDirectories;
    }

  
    @Override
    public ReferenceList getMailingList(HttpServletRequest request) {
 ReferenceList refMailingList = new ReferenceList(  );
        refMailingList.addItem( DirectoryUtils.CONSTANT_ID_NULL, StringUtils.EMPTY );
        refMailingList.addAll( AdminMailingListService.getMailingLists( AdminUserService.getAdminUser( request ) ) );

        return refMailingList;
    }


    @Override
    public List<IEntry> getListEntries(int nIdTask) {
         Plugin pluginDirectory = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );

        TaskNotifyGruConfig config = _taskNotifyDirectoryService.findByPrimaryKey( nIdTask );
        
         _notifyGruService = SpringContextService.getBean( config.getIdSpringProvider(  ) );

        List<IEntry> listEntries = new ArrayList<IEntry>(  );

        if ( config != null )
        {
            EntryFilter entryFilter = new EntryFilter(  );
            entryFilter.setIdDirectory( _notifyGruService.getIdDirectory() );

            listEntries = EntryHome.getEntryList( entryFilter, pluginDirectory );
        }

        return listEntries;
    }

 
     
    
    @Override
    public ReferenceList getListEntriesUserGuid(int nIdTask, Locale locale) {
       ReferenceList refenreceListEntries = new ReferenceList(  );
        refenreceListEntries.addItem( DirectoryUtils.CONSTANT_ID_NULL, DirectoryUtils.EMPTY_STRING );

        for ( IEntry entry : getListEntries( nIdTask ) )
        {
            int nIdEntryType = entry.getEntryType(  ).getIdType(  );

            if ( isEntryTypeUserGuidAccepted( nIdEntryType ) )
            {
                refenreceListEntries.addItem( entry.getPosition(  ), buildReferenceEntryToString( entry, locale ) );
            }
        }

        return refenreceListEntries;
    }
    
      private String buildReferenceEntryToString( IEntry entry, Locale locale )
    {
        StringBuilder sbReferenceEntry = new StringBuilder(  );
        sbReferenceEntry.append( entry.getPosition(  ) );
        sbReferenceEntry.append( ProviderDirectoryConstants.SPACE + ProviderDirectoryConstants.OPEN_BRACKET );
        sbReferenceEntry.append( entry.getTitle(  ) );
        sbReferenceEntry.append( ProviderDirectoryConstants.SPACE + ProviderDirectoryConstants.HYPHEN +
            ProviderDirectoryConstants.SPACE );
        sbReferenceEntry.append( I18nService.getLocalizedString( entry.getEntryType(  ).getTitleI18nKey(  ), locale ) );
        sbReferenceEntry.append( ProviderDirectoryConstants.CLOSED_BRACKET );

        return sbReferenceEntry.toString(  );
    }
      


    @Override
    public ReferenceList getListEntriesEmailSMS(int nIdTask, Locale locale) {
       ReferenceList refenreceListEntries = new ReferenceList(  );
        refenreceListEntries.addItem( DirectoryUtils.CONSTANT_ID_NULL, DirectoryUtils.EMPTY_STRING );

        for ( IEntry entry : getListEntries( nIdTask ) )
        {
            int nIdEntryType = entry.getEntryType(  ).getIdType(  );

            if ( isEntryTypeEmailSMSAccepted( nIdEntryType ) )
            {
                refenreceListEntries.addItem( entry.getPosition(  ), buildReferenceEntryToString( entry, locale ) );
            }
        }

        return refenreceListEntries;
    }

    
   
    @Override
    public List<IEntry> getListEntriesFreemarker(int nIdTask) {
        List<IEntry> listEntries = new ArrayList<IEntry>(  );

        for ( IEntry entry : getListEntries( nIdTask ) )
        {
            int nIdEntryType = entry.getEntryType(  ).getIdType(  );

            if ( !isEntryTypeRefused( nIdEntryType ) )
            {
                listEntries.add( entry );
            }
        }

        return listEntries;
    }

  
    @Override
    public List<IEntry> getListEntriesFile(int nIdTask, Locale locale) {
        List<IEntry> listEntries = new ArrayList<IEntry>(  );

        for ( IEntry entry : getListEntries( nIdTask ) )
        {
            int nIdEntryType = entry.getEntryType(  ).getIdType(  );

            if ( isEntryTypeFileAccepted( nIdEntryType ) )
            {
                listEntries.add( entry );
            }
        }

        return listEntries;
    }

  
    @Override
    public String getEmail(ProviderDirectory provider, int nIdRecord, int nIdDirectory) {
        String strEmail = StringUtils.EMPTY;
        
        if(provider!=null)   {
        strEmail = getRecordFieldValue( provider.getPositionEntryDirectoryEmail(), nIdRecord, nIdDirectory );
        }

       //  _notifyGruService = SpringContextService.getBean( config.getIdSpringProvider(  ) );
         
//        if ( config.isActiveOngletEmail() )
//        {
//            if ( config.isNotifyByUserGuid(  ) )
//            {
//                String strUserGuid = getRecordFieldValue( config.getPositionEntryDirectoryUserGuid(  ), nIdRecord,
//                        nIdDirectory );
//                strEmail = _userAttributesManager.getAttribute( strUserGuid, LuteceUser.BUSINESS_INFO_ONLINE_EMAIL );
//            }
//            else
//            {
              
//            }
//        }

        return strEmail;
    }
    
       private String getRecordFieldValue( int nPosition, int nIdRecord, int nIdDirectory )
    {
        String strRecordFieldValue = StringUtils.EMPTY;
        Plugin pluginDirectory = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );

        // RecordField
        EntryFilter entryFilter = new EntryFilter(  );
        entryFilter.setPosition( nPosition );
        entryFilter.setIdDirectory( nIdDirectory );

        List<IEntry> listEntries = EntryHome.getEntryList( entryFilter, pluginDirectory );

        if ( ( listEntries != null ) && !listEntries.isEmpty(  ) )
        {
            IEntry entry = listEntries.get( 0 );
            RecordFieldFilter recordFieldFilterEmail = new RecordFieldFilter(  );
            recordFieldFilterEmail.setIdDirectory( nIdDirectory );
            recordFieldFilterEmail.setIdEntry( entry.getIdEntry(  ) );
            recordFieldFilterEmail.setIdRecord( nIdRecord );

            List<RecordField> listRecordFields = RecordFieldHome.getRecordFieldList( recordFieldFilterEmail,
                    pluginDirectory );

            if ( ( listRecordFields != null ) && !listRecordFields.isEmpty(  ) && ( listRecordFields.get( 0 ) != null ) )
            {
                RecordField recordFieldIdDemand = listRecordFields.get( 0 );
                strRecordFieldValue = recordFieldIdDemand.getValue(  );

                if ( recordFieldIdDemand.getField(  ) != null )
                {
                    strRecordFieldValue = recordFieldIdDemand.getField(  ).getTitle(  );
                }
            }
        }

        return strRecordFieldValue;
    }
        
       

    @Override
    public String getSMSPhoneNumber(ProviderDirectory provider, int nIdRecord, int nIdDirectory) {
      String strSMSPhoneNumber = StringUtils.EMPTY;
      if(provider!=null)   {
    	  strSMSPhoneNumber = getRecordFieldValue( provider.getPositionEntryDirectorySms(), nIdRecord, nIdDirectory );
          }
 /*_notifyGruService = SpringContextService.getBean( config.getIdSpringProvider(  ) );
        if ( config.isActiveOngletSMS() )
        {
            strSMSPhoneNumber = getRecordFieldValue( _notifyGruService.getPositionEntryDirectorySms(  ), nIdRecord, nIdDirectory );
        }*/
        return strSMSPhoneNumber;
    }    
 
       
    @Override
    public List<FileAttachment> getFilesAttachment(TaskNotifyGruConfig config, int nIdRecord, int nIdDirectory) {
        List<FileAttachment> listFileAttachment = null;

   /*     if ( ( config.getListPositionEntryFile(  ) != null ) && ( config.getListPositionEntryFile(  ).size(  ) > 0 ) )
        {
            listFileAttachment = new ArrayList<FileAttachment>(  );

            for ( Integer nPositionEntryFile : config.getListPositionEntryFile(  ) )
            {
                List<File> listFiles = getFiles( nPositionEntryFile, nIdRecord, nIdDirectory );

                if ( ( listFiles != null ) && !listFiles.isEmpty(  ) )
                {
                    for ( File file : listFiles )
                    {
                        if ( ( file != null ) && ( file.getPhysicalFile(  ) != null ) )
                        {
                            FileAttachment fileAttachment = new FileAttachment( file.getTitle(  ),
                                    file.getPhysicalFile(  ).getValue(  ), file.getMimeType(  ) );
                            listFileAttachment.add( fileAttachment );
                        }
                    }
                }
            }
        }
*/
        return listFileAttachment;
    }

  
    @Override
    public String getUserGuid(ProviderDirectory provider, int nIdRecord, int nIdDirectory) {
       String strUserGuid = StringUtils.EMPTY;
       
 /*_notifyGruService = SpringContextService.getBean( config.getIdSpringProvider(  ) );
        if ( _notifyGruService.getPositionEntryDirectoryUserGuid(  ) != DirectoryUtils.CONSTANT_ID_NULL )
        {
            strUserGuid = getRecordFieldValue( _notifyGruService.getPositionEntryDirectoryUserGuid(  ), nIdRecord, nIdDirectory );
        }*/
       if(provider!=null)   {
     	  strUserGuid = getRecordFieldValue( provider.getPositionEntryDirectoryUserGuid(), nIdRecord, nIdDirectory );
           }
       
        return strUserGuid;
    }

   
    @Override
    public List<ITask> getListBelowTasks(ITask task, Locale locale) {
        List<ITask> listTasks = new ArrayList<ITask>(  );

        if ( task != null )
        {
            for ( ITask otherTask : _taskService.getListTaskByIdAction( task.getAction(  ).getId(  ), locale ) )
            {
                // FIXME : When upgrading to workflow v3.0.2, change this condition to :
                // if ( task.getOrder(  ) <= otherTasK.getOrder(  ) )
                // Indeed, in workflow v3.0.1 and inferior, the task are ordered by id task
                if ( task.getId(  ) == otherTask.getId(  ) )
                {
                    break;
                }

                for ( ITaskInfoProvider provider : TaskInfoManager.getProvidersList(  ) )
                {
                    if ( otherTask.getTaskType(  ).getKey(  ).equals( provider.getTaskType(  ).getKey(  ) ) )
                    {
                        listTasks.add( otherTask );

                        break;
                    }
                }
            }
        }

        return listTasks;
    }

    @Override
    public void sendMessage(TaskNotifyGruConfig config, String strEmail, String strSms, String strSenderEmail, String strSubject, String strEmailContent, String strSMSContent, List<FileAttachment> listFileAttachment) {
        
         if ( config.isActiveOngletEmail() && StringUtils.isNotBlank( strEmail ) )
        {
            // Build the mail message
            if ( ( listFileAttachment != null ) && ( listFileAttachment.size(  ) > 0 ) )
            {
                MailService.sendMailMultipartHtml( strEmail, config.getRecipientsCcEmail(), config.getRecipientsCcEmail(),
                    config.getSenderNameEmail(), strSenderEmail, strSubject, strEmailContent, null, listFileAttachment );
            }
            else
            {
                MailService.sendMailHtml( strEmail, config.getRecipientsCcEmail(), config.getRecipientsCcEmail(),
                    config.getSenderNameEmail(), strSenderEmail, strSubject, strEmailContent );
            }
        }

        if ( config.isActiveOngletSMS() && StringUtils.isNotBlank( strSms ) )
        {
            String strServerSms = AppPropertiesService.getProperty( ProviderDirectoryConstants.PROPERTY_SERVER_SMS );
            MailService.sendMailHtml( strSms + strServerSms, config.getSenderNameEmail(), strSenderEmail, strSubject,
                strSMSContent );
        }

        if ( config.isActiveOngletBroadcast())
        {
            Collection<Recipient> listRecipients = AdminMailingListService.getRecipients( config.getIdMailingListBroadcast());

            // Send Mail
            for ( Recipient recipient : listRecipients )
            {
                if ( ( listFileAttachment != null ) && ( listFileAttachment.size(  ) > 0 ) )
                {
                    MailService.sendMailMultipartHtml( recipient.getEmail(  ), config.getRecipientsCcBroadcast(),
                        config.getRecipientsCcEmail(), config.getSenderNameBroadcast(), strSenderEmail, strSubject,
                        strEmailContent, null, listFileAttachment );
                }
                else
                {
                    // Build the mail message
                    MailService.sendMailHtml( recipient.getEmail(  ), config.getSenderNameBroadcast(), strSenderEmail,
                        strSubject, strEmailContent );
                }
            }
        }

        // If the task is not notified by email and the recipients bcc is not an
        // empty string, then send the bcc
     /*   if ( !config.isActiveOngletEmail() &&
                ( StringUtils.isNotBlank( config.getRecipientsBcc(  ) ) ||
                StringUtils.isNotBlank( config.getRecipientsCc(  ) ) ) )
        {
            if ( ( listFileAttachment != null ) && ( listFileAttachment.size(  ) > 0 ) )
            {
                MailService.sendMailMultipartHtml( null, config.getRecipientsCc(  ), config.getRecipientsBcc(  ),
                    config.getSenderName(  ), strSenderEmail, strSubject, strEmailContent, null, listFileAttachment );
            }
            else
            {
                MailService.sendMailHtml( null, config.getRecipientsCc(  ), config.getRecipientsBcc(  ),
                    config.getSenderName(  ), strSenderEmail, strSubject, strEmailContent );
            }
        }  */
        
    }

    @Override
    public Map<String, Object> fillModel(TaskNotifyGruConfig config, ResourceHistory resourceHistory, Record record, Directory directory, HttpServletRequest request, int nIdAction, int nIdHistory) {
      
         Map<String, Object> model = new HashMap<String, Object>(  );

        Locale locale = getLocale( request );
        ITask task = _taskService.findByPrimaryKey( config.getIdTask(  ), locale );
        Plugin pluginDirectory = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );

        model.put( ProviderDirectoryConstants.MARK_MESSAGE, config.getMessageEmail());
        model.put( ProviderDirectoryConstants.MARK_DIRECTORY_TITLE, directory.getTitle(  ) );
        model.put( ProviderDirectoryConstants.MARK_DIRECTORY_DESCRIPTION, directory.getDescription(  ) );

        RecordFieldFilter recordFieldFilter = new RecordFieldFilter(  );
        recordFieldFilter.setIdRecord( record.getIdRecord(  ) );

        List<RecordField> listRecordField = RecordFieldHome.getRecordFieldList( recordFieldFilter, pluginDirectory );

        for ( RecordField recordField : listRecordField )
        {
            String strNewValue = StringUtils.EMPTY;

            if ( isEntryTypeRefused( recordField.getEntry(  ).getEntryType(  ).getIdType(  ) ) )
            {
                continue;
            }
            else if ( recordField.getEntry(  ) instanceof fr.paris.lutece.plugins.directory.business.EntryTypeGeolocation &&
                    ( ( recordField.getField(  ) == null ) ||
                    StringUtils.isBlank( recordField.getField(  ).getTitle(  ) ) ||
                    !EntryTypeGeolocation.CONSTANT_ADDRESS.equals( recordField.getField(  ).getTitle(  ) ) ) )
            {
                continue;
            }
            else if ( ( recordField.getField(  ) != null ) && ( recordField.getField(  ).getTitle(  ) != null ) &&
                    !( recordField.getEntry(  ) instanceof fr.paris.lutece.plugins.directory.business.EntryTypeGeolocation ) )
            {
                strNewValue = recordField.getField(  ).getTitle(  );
            }
            else if ( recordField.getEntry(  ) instanceof fr.paris.lutece.plugins.directory.business.EntryTypeFile &&
                    ( recordField.getFile(  ) != null ) && ( recordField.getFile(  ).getTitle(  ) != null ) )
            {
                strNewValue = recordField.getFile(  ).getTitle(  );
            }
            else
            {
                strNewValue = recordField.getEntry(  ).convertRecordFieldValueToString( recordField, locale, false,
                        false );
            }

            recordField.setEntry( EntryHome.findByPrimaryKey( recordField.getEntry(  ).getIdEntry(  ), pluginDirectory ) );

            String strKey = ProviderDirectoryConstants.MARK_POSITION + recordField.getEntry(  ).getPosition(  );
            String strOldValue = ( (String) model.get( strKey ) );

            if ( StringUtils.isNotBlank( strOldValue ) && StringUtils.isNotBlank( strNewValue ) )
            {
                // Add markers for message
                model.put( strKey, strNewValue + ProviderDirectoryConstants.COMMA + strOldValue );
            }
            else if ( strNewValue != null )
            {
                model.put( strKey, strNewValue );
            }
            else
            {
                model.put( strKey, StringUtils.EMPTY );
            }
        }

        if ( ( directory.getIdWorkflow(  ) != DirectoryUtils.CONSTANT_ID_NULL ) &&
                WorkflowService.getInstance(  ).isAvailable(  ) )
        {
            State state = WorkflowService.getInstance(  )
                                         .getState( record.getIdRecord(  ), Record.WORKFLOW_RESOURCE_TYPE,
                    directory.getIdWorkflow(  ), null );
            model.put( ProviderDirectoryConstants.MARK_STATUS, state.getName(  ) );
        }

        // Link View record
        String strLinkViewRecordHtml = DirectoryUtils.EMPTY_STRING;

    /*    if ( config.isViewRecord(  ) )
        {
            StringBuilder sbBaseUrl = new StringBuilder( getBaseUrl( request ) );

            if ( ( sbBaseUrl.length(  ) > 0 ) && !sbBaseUrl.toString(  ).endsWith( NotifyDirectoryConstants.SLASH ) )
            {
                sbBaseUrl.append( NotifyDirectoryConstants.SLASH );
            }

            sbBaseUrl.append( NotifyDirectoryConstants.JSP_DO_VISUALISATION_RECORD );

            UrlItem url = new UrlItem( sbBaseUrl.toString(  ) );
            url.addParameter( DirectoryUtils.PARAMETER_ID_DIRECTORY, directory.getIdDirectory(  ) );
            url.addParameter( DirectoryUtils.PARAMETER_ID_DIRECTORY_RECORD, record.getIdRecord(  ) );

            StringBuffer sbLinkHtml = new StringBuffer(  );
            Map<String, String> mapParams = new HashMap<String, String>(  );
            mapParams.put( NotifyDirectoryConstants.ATTRIBUTE_HREF, url.getUrl(  ) );
            XmlUtil.beginElement( sbLinkHtml, NotifyDirectoryConstants.TAG_A, mapParams );
            sbLinkHtml.append( config.getLabelLinkViewRecord(  ) );
            XmlUtil.endElement( sbLinkHtml, NotifyDirectoryConstants.TAG_A );

            Map<String, Object> modelTmp = new HashMap<String, Object>(  );
            modelTmp.put( NotifyDirectoryConstants.MARK_LINK_VIEW_RECORD, url.getUrl(  ) );
            strLinkViewRecordHtml = AppTemplateService.getTemplateFromStringFtl( sbLinkHtml.toString(  ), locale,
                    modelTmp ).getHtml(  );
        }  */

        model.put( ProviderDirectoryConstants.MARK_LINK_VIEW_RECORD, strLinkViewRecordHtml );

        // Generate key
        String linkHtml = DirectoryUtils.EMPTY_STRING;

    /*   if ( config.isEmailValidation(  ) )
        {
            ResourceKey resourceKey = new ResourceKey(  );
            UUID key = java.util.UUID.randomUUID(  );
            resourceKey.setKey( key.toString(  ) );
            resourceKey.setIdResource( record.getIdRecord(  ) );
            resourceKey.setResourceType( resourceHistory.getResourceType(  ) );
            resourceKey.setIdTask( config.getIdTask(  ) );

            Calendar calendar = GregorianCalendar.getInstance(  );
            calendar.add( Calendar.DAY_OF_MONTH, config.getPeriodValidity(  ) );
            resourceKey.setDateExpiry( new Timestamp( calendar.getTimeInMillis(  ) ) );
            _resourceKeyService.create( resourceKey, PluginService.getPlugin( NotifyDirectoryPlugin.PLUGIN_NAME ) );

            StringBuilder sbBaseUrl = new StringBuilder( getBaseUrl( request ) );

            if ( ( sbBaseUrl.length(  ) > 0 ) && !sbBaseUrl.toString(  ).endsWith( NotifyDirectoryConstants.SLASH ) )
            {
                sbBaseUrl.append( NotifyDirectoryConstants.SLASH );
            }

            sbBaseUrl.append( AppPathService.getPortalUrl(  ) );

            UrlItem url = new UrlItem( sbBaseUrl.toString(  ) );
            url.addParameter( NotifyDirectoryConstants.PARAMETER_PAGE, WorkflowPlugin.PLUGIN_NAME );
            url.addParameter( NotifyDirectoryConstants.PARAMETER_KEY, key.toString(  ) );

            StringBuffer sbLinkHtml = new StringBuffer(  );
            Map<String, String> mapParams = new HashMap<String, String>(  );
            mapParams.put( NotifyDirectoryConstants.ATTRIBUTE_HREF, url.getUrl(  ) );
            XmlUtil.beginElement( sbLinkHtml, NotifyDirectoryConstants.TAG_A, mapParams );
            sbLinkHtml.append( config.getLabelLink(  ) );
            XmlUtil.endElement( sbLinkHtml, NotifyDirectoryConstants.TAG_A );

            linkHtml = sbLinkHtml.toString(  );

            Map<String, Object> modelTmp = new HashMap<String, Object>(  );
            modelTmp.put( NotifyDirectoryConstants.MARK_LINK, url.getUrl(  ) );
            linkHtml = AppTemplateService.getTemplateFromStringFtl( linkHtml, locale, modelTmp ).getHtml(  );
        }  */

        model.put( ProviderDirectoryConstants.MARK_LINK, linkHtml );

        // Fill user attributes
       // String strUserGuid = getUserGuid( config, record.getIdRecord(  ), directory.getIdDirectory(  ) );
        //fillModelWithUserAttributes( model, strUserGuid );

        // Fill the model with the info of other tasks
        for ( ITask otherTask : getListBelowTasks( task, locale ) )
        {
            model.put( ProviderDirectoryConstants.MARK_TASK + otherTask.getId(  ),
                TaskInfoManager.getTaskResourceInfo( nIdHistory, otherTask.getId(  ), request ) );
        }

        return model;
        
    }

        private void fillModelWithUserAttributes( Map<String, Object> model, String strUserGuid )
    {
        if ( _userAttributesManager.isEnabled(  ) && StringUtils.isNotBlank( strUserGuid ) )
        {
            Map<String, String> mapUserAttributes = _userAttributesManager.getAttributes( strUserGuid );
            String strFirstName = mapUserAttributes.get( LuteceUser.NAME_GIVEN );
            String strLastName = mapUserAttributes.get( LuteceUser.NAME_FAMILY );
            String strEmail = mapUserAttributes.get( LuteceUser.BUSINESS_INFO_ONLINE_EMAIL );
            String strPhoneNumber = mapUserAttributes.get( LuteceUser.BUSINESS_INFO_TELECOM_TELEPHONE_NUMBER );

            model.put( ProviderDirectoryConstants.MARK_FIRST_NAME,
                StringUtils.isNotEmpty( strFirstName ) ? strFirstName : StringUtils.EMPTY );
            model.put( ProviderDirectoryConstants.MARK_LAST_NAME,
                StringUtils.isNotEmpty( strLastName ) ? strLastName : StringUtils.EMPTY );
            model.put( ProviderDirectoryConstants.MARK_EMAIL,
                StringUtils.isNotEmpty( strEmail ) ? strEmail : StringUtils.EMPTY );
            model.put( ProviderDirectoryConstants.MARK_PHONE_NUMBER,
                StringUtils.isNotEmpty( strPhoneNumber ) ? strPhoneNumber : StringUtils.EMPTY );
        }
    } 
        
    @Override
    public Locale getLocale(HttpServletRequest request) {
      
          Locale locale = null;

        if ( request != null )
        {
            locale = request.getLocale(  );
        }
        else
        {
            locale = I18nService.getDefaultLocale(  );
        }

        return locale;
    }

  private String getBaseUrl( HttpServletRequest request )
    {
        String strBaseUrl = StringUtils.EMPTY;

        if ( request != null )
        {
            strBaseUrl = AppPathService.getBaseUrl( request );
        }
        else
        {
            strBaseUrl = AppPropertiesService.getProperty( ProviderDirectoryConstants.PROPERTY_LUTECE_ADMIN_PROD_URL );

            if ( StringUtils.isBlank( strBaseUrl ) )
            {
                strBaseUrl = AppPropertiesService.getProperty( ProviderDirectoryConstants.PROPERTY_LUTECE_BASE_URL );

                if ( StringUtils.isBlank( strBaseUrl ) )
                {
                    strBaseUrl = AppPropertiesService.getProperty( ProviderDirectoryConstants.PROPERTY_LUTECE_PROD_URL );
                }
            }
        }

        return strBaseUrl;
    }

    
}
