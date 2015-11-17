package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

import fr.paris.lutece.plugins.directory.business.DirectoryHome;
import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.plugins.directory.utils.DirectoryUtils;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotificationTypeEnum;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.TaskNotifyGruConfigService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.plugins.workflow.service.security.IWorkflowUserAttributesManager;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflow.web.task.NoFormTaskComponent;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * INotifyGruTaskComponent
 *
 */
public class NotifyGruTaskComponent extends NoFormTaskComponent
{
    // TEMPLATES
    private static final String TEMPLATE_TASK_NOTIFY_GRU_CONFIG = "admin/plugins/workflow/modules/notifygru/task_notify_gru_config.html";

    // SERVICES
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;
    @Inject
    private INotifyGruService _notifyGRUService;
    @Inject
    private IWorkflowUserAttributesManager _userAttributesManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public String doSaveConfig( HttpServletRequest request, Locale locale, ITask task )
    {
//        int nNotify = DirectoryUtils.convertStringToInt( request.getParameter( 
//                    NotifyGruConstants.PARAMETER_NOTIFY ) );
//        
             
//        String strPositionEntryDirectoryUserGuid_ongle1 = request.getParameter( NotifyGruConstants.PARAMETER_POSITION_ENTRY_GRU_USER_GUID_ONGLE1 );
//        int nPositionEntryDirectoryUserGuid_ongle1 = WorkflowUtils.convertStringToInt( strPositionEntryDirectoryUserGuid_ongle1 );
//       
//         String strPositionEntryDirectoryUserGuid_ongle3 = request.getParameter( NotifyGruConstants.PARAMETER_POSITION_ENTRY_GRU_USER_GUID_ONGLE3 );
//        int nPositionEntryDirectoryUserGuid_ongle3 = WorkflowUtils.convertStringToInt( strPositionEntryDirectoryUserGuid_ongle3 );
//       
//        int nIdDirectory_ongle1 = WorkflowUtils.convertStringToInt( strIdDirectory_ongle1 );
//        int nIdDirectory_ongle3 = WorkflowUtils.convertStringToInt( strIdDirectory_ongle3 );
//        int nIdState = DirectoryUtils.convertStringToInt( request.getParameter( 
//                    NotifyGruConstants.PARAMETER_ID_STATE ) );
//        String strEmailValidation = request.getParameter( NotifyGruConstants.PARAMETER_EMAIL_VALIDATION );
//        String strMessageValidation = request.getParameter( NotifyGruConstants.PARAMETER_MESSAGE_VALIDATION );
//        String strLabelLink = request.getParameter( NotifyGruConstants.PARAMETER_LABEL_LINK );
//        int nPeriodValidity = DirectoryUtils.convertStringToInt( request.getParameter( 
//                    NotifyGruConstants.PARAMETER_PERIOD_VALIDTY ) );
//        boolean bIsNotifyByUserGuid = StringUtils.isNotBlank( request.getParameter( 
//                    NotifyGruConstants.PARAMETER_IS_NOTIFY_BY_USER_GUID ) );
//        String strRecipientsCc = request.getParameter( NotifyGruConstants.PARAMETER_RECIPIENTS_CC );
//        String strRecipientsBcc = request.getParameter( NotifyGruConstants.PARAMETER_RECIPIENTS_BCC );
//        int nIdMailingList = DirectoryUtils.convertStringToInt( request.getParameter( 
//                    NotifyGruConstants.PARAMETER_ID_MAILING_LIST ) );
//        String strViewRecord = request.getParameter( NotifyGruConstants.PARAMETER_VIEW_RECORD );
//        String strLabelLinkViewRecord = request.getParameter( NotifyGruConstants.PARAMETER_LABEL_LINK_VIEW_RECORD );
//        String strApply = request.getParameter( NotifyGruConstants.PARAMETER_APPLY );
//        String strError = StringUtils.EMPTY;
//        String[] tabSelectedPositionEntryFile = request.getParameterValues( NotifyGruConstants.PARAMETER_LIST_POSITION_ENTRY_FILE_CHECKED );

        
        
//        if ( StringUtils.isBlank( strApply ) )
//        {
//            if ( nIdDirectory_ongle1 == WorkflowUtils.CONSTANT_ID_NULL )
//            {
//                strError = NotifyGruConstants.FIELD_TASK_DIRECTORY;
//            }
//            else if ( nNotify == DirectoryUtils.CONSTANT_ID_NULL )
//            {
//                strError = NotifyGruConstants.FIELD_NOTIFY;
//            }
//            else if ( ( nPositionEntryDirectorySms == WorkflowUtils.CONSTANT_ID_NULL ) &&
//                    ( ( nNotify == NotificationTypeEnum.SMS.getId(  ) ) ||
//                    ( nNotify == NotificationTypeEnum.EMAIL_SMS.getId(  ) ) ) )
//            {
//                strError = NotifyGruConstants.FIELD_TASK_ENTRY_GRU_SMS;
//            }
//            else if ( ( nPositionEntryDirectoryEmail == WorkflowUtils.CONSTANT_ID_NULL ) && !bIsNotifyByUserGuid &&
//                    ( ( nNotify == NotificationTypeEnum.EMAIL.getId(  ) ) ||
//                    ( nNotify == NotificationTypeEnum.EMAIL_SMS.getId(  ) ) ) )
//            {
//                strError = NotifyGruConstants.FIELD_TASK_ENTRY_GRU_EMAIL;
//            }
//            else if ( StringUtils.isBlank( strSenderName_ongle1 ) )
//            {
//                strError = NotifyGruConstants.FIELD_SENDER_NAME;
//            }
//            else if ( StringUtils.isBlank( strSubject_ongle1 ) )
//            {
//                strError = NotifyGruConstants.FIELD_SUBJECT;
//            }
//            else if ( StringUtils.isBlank( strMessage_ongle1 ) )
//            {
//                strError = NotifyGruConstants.FIELD_MESSAGE;
//            }
//            else if ( ( nPositionEntryDirectoryUserGuid_ongle1 == WorkflowUtils.CONSTANT_ID_NULL ) && bIsNotifyByUserGuid )
//            {
//                strError = NotifyGruConstants.FIELD_TASK_ENTRY_GRU_USER_GUID;
//            }
//            else if ( ( nNotify == NotificationTypeEnum.MAILING_LIST.getId(  ) ) &&
//                    ( nIdMailingList == WorkflowUtils.CONSTANT_ID_NULL ) )
//            {
//                strError = NotifyGruConstants.FIELD_MAILING_LIST;
//            }
//            else if ( StringUtils.isNotBlank( strEmailValidation ) )
//            {
//                if ( nIdState == WorkflowUtils.CONSTANT_ID_NULL )
//                {
//                    strError = NotifyGruConstants.FIELD_STATE;
//                }
//                else if ( StringUtils.isBlank( strMessageValidation ) )
//                {
//                    strError = NotifyGruConstants.FIELD_MESSAGE_VALIDATION;
//                }
//                else if ( StringUtils.isBlank( strLabelLink ) )
//                {
//                    strError = NotifyGruConstants.FIELD_LABEL_LINK;
//                }
//                else if ( nPeriodValidity == WorkflowUtils.CONSTANT_ID_NULL )
//                {
//                    strError = NotifyGruConstants.FIELD_LABEL_PERIOD_VALIDITY;
//                }
//            }
//            else if ( StringUtils.isNotBlank( strViewRecord ) && StringUtils.isBlank( strLabelLinkViewRecord ) )
//            {
//                strError = NotifyGruConstants.FIELD_LABEL_LINK_VIEW_RECORD;
//            }
//        }
//
//        if ( !strError.equals( WorkflowUtils.EMPTY_STRING ) )
//        {
//            Object[] tabRequiredFields = { I18nService.getLocalizedString( strError, locale ) };
//
//            return AdminMessageService.getMessageUrl( request, NotifyGruConstants.MESSAGE_MANDATORY_FIELD,
//                tabRequiredFields, AdminMessage.TYPE_STOP );
//        }

//        if ( StringUtils.isBlank( strApply ) && ( nPositionEntryDirectorySms == nPositionEntryDirectoryEmail ) &&
//                !bIsNotifyByUserGuid && ( nNotify == NotificationTypeEnum.EMAIL_SMS.getId(  ) ) )
//        {
//            Object[] tabRequiredFields = 
//                {
//                    I18nService.getLocalizedString( NotifyGruConstants.FIELD_TASK_ENTRY_GRU_EMAIL, locale ),
//                    I18nService.getLocalizedString( NotifyGruConstants.FIELD_TASK_ENTRY_GRU_SMS, locale ),
//                };
//
//            return AdminMessageService.getMessageUrl( request, NotifyGruConstants.MESSAGE_EQUAL_FIELD,
//                tabRequiredFields, AdminMessage.TYPE_STOP );
//        }

//        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( task.getId(  ) );
//        Boolean bCreate = false;
//
//        if ( config == null )
//        {
//            config = new TaskNotifyGruConfig(  );
//            config.setIdTask( task.getId(  ) );
//            bCreate = true;
//        }

//        config.setIdDirectoryOngle1(DirectoryUtils.convertStringToInt( strIdDirectory_ongle1 ) );
//        config.setIdDirectoryOngle1(DirectoryUtils.convertStringToInt( strIdDirectory_ongle3 ) );
//        config.setPositionEntryDirectorySms( nPositionEntryDirectorySms );
//        config.setPositionEntryDirectoryEmail( nPositionEntryDirectoryEmail );
//        config.setPositionEntryDirectoryUserGuidOngle1(nPositionEntryDirectoryUserGuid_ongle1 );
//        config.setPositionEntryDirectoryUserGuidOngle1(nPositionEntryDirectoryUserGuid_ongle3 );
//        config.setMessageOngle1(strMessage_ongle1 );
//        config.setMessageOngle1(strMessage_ongle3 );
//        config.setSenderNameOngle1(strSenderName_ongle1 );
//        config.setSenderNameOngle1(strSenderName_ongle3 );
//        config.setSubjectOngle1(strSubject_ongle1 );
//        config.setSubjectOngle1(strSubject_ongle3 );
//        config.setEmailValidation( strEmailValidation != null );
//        config.setIdStateAfterValidation( nIdState );
//        config.setLabelLink( strLabelLink );
//        config.setMessageValidation( strMessageValidation );
//        config.setPeriodValidity( nPeriodValidity );
//        config.setRecipientsCc( StringUtils.isNotEmpty( strRecipientsCc ) ? strRecipientsCc : StringUtils.EMPTY );
//        config.setRecipientsBcc( StringUtils.isNotEmpty( strRecipientsBcc ) ? strRecipientsBcc : StringUtils.EMPTY );
//        config.setIdMailingList( nIdMailingList );
//        config.setViewRecord( strViewRecord != null );
//        config.setLabelLinkViewRecord( strLabelLinkViewRecord );
//        config.setStatusText("status texte");
//        config.setSendNotification(true);
//        config.setPositionEntryDirectoryCrmWebAppCode(-1);
//
//        config.setNotifyByUserGuid( bIsNotifyByUserGuid );

//        if ( nNotify == NotificationTypeEnum.EMAIL.getId(  ) )
//        {
//            config.setNotifyByEmail( true );
//            config.setNotifyBySms( false );
//            config.setNotifyByMailingList( false );
//        }
//        else if ( nNotify == NotificationTypeEnum.SMS.getId(  ) )
//        {
//            config.setNotifyByEmail( false );
//            config.setNotifyBySms( true );
//            config.setNotifyByMailingList( false );
//        }
//        else if ( nNotify == NotificationTypeEnum.EMAIL_SMS.getId(  ) )
//        {
//            config.setNotifyByEmail( true );
//            config.setNotifyBySms( true );
//            config.setNotifyByMailingList( false );
//        }
//        else if ( nNotify == NotificationTypeEnum.MAILING_LIST.getId(  ) )
//        {
//            config.setNotifyByEmail( false );
//            config.setNotifyBySms( false );
//            config.setNotifyByMailingList( true );
//        }

//        if ( ( tabSelectedPositionEntryFile != null ) && ( tabSelectedPositionEntryFile.length > 0 ) )
//        {
//            List<Integer> listSelectedPositionEntryFile = new ArrayList<Integer>(  );
//
//            for ( int i = 0; i < tabSelectedPositionEntryFile.length; i++ )
//            {
//                listSelectedPositionEntryFile.add( WorkflowUtils.convertStringToInt( tabSelectedPositionEntryFile[i] ) );
//            }
//
//            config.setListPositionEntryFile( listSelectedPositionEntryFile );
//        }
//        else
//        {
//            config.setListPositionEntryFile( null );
//        }
//
//        if ( bCreate )
//        {
//            _taskNotifyGruConfigService.create( config );
//        }
//        else
//        {
//            _taskNotifyGruConfigService.update( config );
//        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayConfigForm( HttpServletRequest request, Locale locale, ITask task )
    {
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( task.getId(  ) );

        String strDefaultSenderName = AppPropertiesService.getProperty( NotifyGruConstants.PROPERTY_NOTIFY_MAIL_DEFAULT_SENDER_NAME );
        Plugin pluginWorkflow = PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( NotifyGruConstants.MARK_CONFIG, config );
        model.put( NotifyGruConstants.MARK_DEFAULT_SENDER_NAME, strDefaultSenderName );
       
        ReferenceList listeOnglet = new ReferenceList(  );
        listeOnglet.addItem( "guichet", "vue guichet");
        listeOnglet.addItem( "agent", "vue agent");
        listeOnglet.addItem( "email", "vue email");
        listeOnglet.addItem( "sms", "vue sms");
        listeOnglet.addItem( "liste", "liste diffusion");
         
        model.put( NotifyGruConstants.MARK_LIST_ONGLE, listeOnglet );
        
       model.put( NotifyGruConstants.MARK_GRU_LIST, this.getListRessources() );
       model.put( NotifyGruConstants.MARK_GRU_LIST_CRM_WEBAPP, this.getListCrmWebApp() );
       model.put( NotifyGruConstants.MARK_GRU_LIST_RESSSOURCE_DEMANDES, this.getListRessourcesDemande());
//        model.put( NotifyGruConstants.MARK_STATE_LIST,
//            _notifyGRUService.getListStates( task.getAction(  ).getId(  ) ) );
       
     
//        model.put( NotifyGruConstants.MARK_IS_USER_ATTRIBUTE_WS_ACTIVE, _userAttributesManager.isEnabled(  ) );
//       model.put( NotifyGruConstants.MARK_LIST_ENTRIES_USER_GUID,
//           _notifyGRUService.getListEntriesUserGuid( task.getId(  ), locale ) );
//        model.put( NotifyGruConstants.MARK_LIST_ENTRIES_FILE,
//            _notifyGRUService.getListEntriesFile( task.getId(  ), locale ) );

//        if ( config != null )
//        {
//            model.put( NotifyGruConstants.MARK_LIST_POSITION_ENTRY_FILE_CHECKED,
//                config.getListPositionEntryFile(  ) );
//        }

       model.put( NotifyGruConstants.MARK_MAILING_LIST, _notifyGRUService.getMailingList( request ) );
//        model.put( NotifyGruConstants.MARK_PLUGIN_WORKFLOW, pluginWorkflow );
//        model.put( NotifyGruConstants.MARK_TASKS_LIST, _notifyGRUService.getListBelowTasks( task, locale ) );

          model.put( NotifyGruConstants.MARK_LOCALE, request.getLocale(  ) );
         model.put( NotifyGruConstants.MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_NOTIFY_GRU_CONFIG, locale, model );

        return template.getHtml(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayTaskInformation( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskInformationXml( int nIdHistory, HttpServletRequest request, Locale locale, ITask task )
    {
        // TODO Auto-generated method stub
        return null;
    }
    
     public ReferenceList getListRessources(  )
    {
       
        ReferenceList refenreceList = new ReferenceList(  );
        refenreceList.addItem(1, "Ressource 1");
        refenreceList.addItem(2, "Ressource 2");
        refenreceList.addItem(3, "Ressource 3");
        refenreceList.addItem(4, "Ressource 4");
        refenreceList.addItem(5, "Ressource 5");

      

        return refenreceList;
    }
     
     public ReferenceList getListRessourcesDemande(  )
    {
       
        ReferenceList refenreceList = new ReferenceList(  );
        refenreceList.addItem(1, "Demande  1");
        refenreceList.addItem(2, "Demande  2");
        refenreceList.addItem(3, "Demande  3");
        refenreceList.addItem(4, "Demande  4");
        refenreceList.addItem(5, "Demande  5");

      

        return refenreceList;
    }
     
      public ReferenceList getListCrmWebApp(  )
    {
       
        ReferenceList refenreceList = new ReferenceList(  );
        refenreceList.addItem(1, "CRM App 1");
        refenreceList.addItem(2, "CRM App 2");
        refenreceList.addItem(3, "CRM App 3");
        refenreceList.addItem(4, "CRM App 4");
        refenreceList.addItem(5, "CRM App 5");

      

        return refenreceList;
    }
}
