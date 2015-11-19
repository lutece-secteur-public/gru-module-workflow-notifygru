package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

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
public class NotifyGruTaskComponent extends NoFormTaskComponent {

    // TEMPLATES
    private static final String TEMPLATE_TASK_NOTIFY_GRU_CONFIG = "admin/plugins/workflow/modules/notifygru/task_notify_gru_config.html";

    // SERVICES
    @Inject
    @Named(TaskNotifyGruConfigService.BEAN_SERVICE)
    private ITaskConfigService _taskNotifyGruConfigService;
    @Inject
    private INotifyGruService _notifyGRUService;
    @Inject
    private IWorkflowUserAttributesManager _userAttributesManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public String doSaveConfig(HttpServletRequest request, Locale locale, ITask task) {

        String strOnglet = request.getParameter(NotifyGruConstants.PARAMETER_ONGLE);

        String strAddOnglet = request.getParameter(NotifyGruConstants.PARAMETER_ONGLE_ADD);

        //non present dans le formulaire : ActiveOngletGuichet
        String strActiveOngletGuichet = request.getParameter(NotifyGruConstants.PARAMETER_ACTIVE_ONGLET_GUICHET);
        Boolean bActiveOngletGuichet = Boolean.parseBoolean(strActiveOngletGuichet);

        //non present dans le formulaire : ActiveOngletAgent
        String strActiveOngletAgent = request.getParameter(NotifyGruConstants.PARAMETER_ACTIVE_ONGLET_AGENT);
        Boolean bActiveOngletAgent = Boolean.parseBoolean(strActiveOngletAgent);

        //non present dans le formulaire : ActiveOngletAgent
        String strActiveOngletEmail = request.getParameter(NotifyGruConstants.PARAMETER_ACTIVE_ONGLET_EMAIL);
        Boolean bActiveOngletEmail = Boolean.parseBoolean(strActiveOngletEmail);

        //non present dans le formulaire : ActiveOngletSMS
        String strActiveOngletSMS = request.getParameter(NotifyGruConstants.PARAMETER_ACTIVE_ONGLET_SMS);
        Boolean bActiveOngletSMS = Boolean.parseBoolean(strActiveOngletSMS);

        if (strAddOnglet == null) {
            //ajout onglet
        }

        String strRemoveOnglet = request.getParameter(NotifyGruConstants.PARAMETER_ONGLE_REMOVE);

        if (strRemoveOnglet == null) {

        }

        /*général*/
        String strIdResource = request.getParameter(NotifyGruConstants.PARAMETER_ID_RESOURCE);
        int nIdResource = (strIdResource == null) ? -1 : Integer.parseInt(strIdResource);
        String stridUserGuid = request.getParameter(NotifyGruConstants.PARAMETER_ID_USER_GUID);
        int nstridUserGuid = (stridUserGuid == null) ? -1 : Integer.parseInt(stridUserGuid);
        /*fin général*/

        /*guichet*/
        String stridDemandGuichet = request.getParameter(NotifyGruConstants.PARAMETER_ID_DEMAND_GUICHET);
        int nidDemandGuichet = (stridDemandGuichet == null) ? -1 : Integer.parseInt(stridDemandGuichet);

        String strCrmWebAppCodeGuichet = request.getParameter(NotifyGruConstants.PARAMETER_CRM_WEBAPP_CODE_GUICHET);
        int nCrmWebAppCodeGuichet = (strCrmWebAppCodeGuichet == null) ? -1 - 1 : Integer.parseInt(strCrmWebAppCodeGuichet);

        String strSendNotificationGuichet = request.getParameter(NotifyGruConstants.PARAMETER_SEND_NOTIFICATION_GUICHET);
        Boolean bSendNotificationGuichet = Boolean.parseBoolean(strSendNotificationGuichet);

        String strStatusTextGuichet = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_TEXT_GUICHET);
        String strSubjectGuichet = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_GUICHET);
        String strMessageGuichet = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_GUICHET);
        String strSenderNameGuichet = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_GUICHET);
        String strLevelNotificationGuichet = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_GUICHET);

        /*fin guichet*/
        /*Agent*/
        String strStatusTextAgent = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_TEXT_AGENT);
        String strMessageAgent = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_MESSAGE_AGENT);
        String strLevelNotificationAgent = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_AGENT);


        /*Fin Agent*/
        /*email*/
        String strRessourceRecordEmail = request.getParameter(NotifyGruConstants.PARAMETER_RESOURCE_RECORD_EMAIL);
        String strSubjectEmail = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_EMAIL);
        String strEntryEmail = request.getParameter(NotifyGruConstants.PARAMETER_ENTRY_EMAIL);
        String strMessageEmail = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_EMAIL);
        String strSenderNameEmail = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_EMAIL);
        String strRecipientsEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_EMAIL);
        String strRecipientsCcEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CC_EMAIL);
        String strRecipientsCciEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CCI_EMAIL);
        String strLevelNotificationEmail = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_EMAIL);


        /*fin email*/
        /*sms*/
        //non present dans le formulaire : IsNotifyBySMS
        String strRessourceRecordSMS = request.getParameter(NotifyGruConstants.PARAMETER_RESOURCE_RECORD_SMS);
        String strPhoneSMS = request.getParameter(NotifyGruConstants.PARAMETER_PHONE_SMS);
        String strMessageSMS = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_SMS);
        String strLevelNotificationSMS = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_SMS);


        /*fin sms*/
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());
        Boolean bCreate = false;

        if (config.getIdTask() == 0) {
            config = new TaskNotifyGruConfig();
            config.setIdTask(task.getId());
            bCreate = true;
        }

        config.setIdRessource(nIdResource);
        config.setIdUserGuid(nstridUserGuid);
        config.setIdDemandGuichet(nidDemandGuichet);
        config.setCrmWebAppCodeGuichet(nCrmWebAppCodeGuichet);
        config.setSendNotificationGuichet(bSendNotificationGuichet);
        config.setStatusTextGuichet(strStatusTextGuichet);
        config.setSubjectGuichet(strSubjectGuichet);
        config.setMessageGuichet(strMessageGuichet);
        config.setSenderNameGuichet(strSenderNameGuichet);
        config.setLevelNotificationGuichet(strLevelNotificationGuichet);
        config.setActiveOngletGuichet(bActiveOngletGuichet);

        config.setStatusTextAgent(strStatusTextAgent);
        config.setMessageAgent(strMessageAgent);
        config.setLevelNotificationAgent(strLevelNotificationAgent);
        config.setActiveOngletAgent(bActiveOngletAgent);

        config.setRessourceRecordEmail(strRessourceRecordEmail);
        config.setSubjectEmail(strSubjectEmail);
        config.setEntryEmail(strEntryEmail);
        config.setMessageEmail(strMessageEmail);
        config.setSenderNameEmail(strSenderNameEmail);
        config.setRecipientsEmail(strRecipientsEmail);
        config.setRecipientsCcEmail(strRecipientsCcEmail);
        config.setRecipientsCciEmail(strRecipientsCciEmail);

        config.setLevelNotificationEmail(strLevelNotificationEmail);
        config.setActiveOngletEmail(bActiveOngletEmail);

        config.setRessourceRecordSMS(strRessourceRecordSMS);
        config.setPhoneSMS(strPhoneSMS);
        config.setMessageSMS(strMessageSMS);

        config.setLevelNotificationSMS(strLevelNotificationSMS);
        config.setActiveOngletSMS(bActiveOngletSMS);

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
//    
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
    public String getDisplayConfigForm(HttpServletRequest request, Locale locale, ITask task) {
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());

        String strDefaultSenderName = AppPropertiesService.getProperty(NotifyGruConstants.PROPERTY_NOTIFY_MAIL_DEFAULT_SENDER_NAME);
        Plugin pluginWorkflow = PluginService.getPlugin(WorkflowPlugin.PLUGIN_NAME);

        Map<String, Object> model = new HashMap<String, Object>();

        model.put(NotifyGruConstants.MARK_CONFIG, config);
        model.put(NotifyGruConstants.MARK_DEFAULT_SENDER_NAME, strDefaultSenderName);

        ReferenceList listeOnglet = this.getListOnglet();
        model.put(NotifyGruConstants.MARK_LIST_ONGLE, listeOnglet);

        ReferenceList levelNotification = this.getListNotification();
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_GUICHET, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_AGENT, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_EMAIL, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_SMS, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_BROADCAST, levelNotification);

        model.put(NotifyGruConstants.MARK_GRU_LIST, this.getListRessources());
        model.put(NotifyGruConstants.MARK_GRU_LIST_CRM_WEBAPP, this.getListCrmWebApp());
        model.put(NotifyGruConstants.MARK_GRU_LIST_RESSSOURCE_DEMANDES, this.getListRessourcesDemande());
        model.put(NotifyGruConstants.MARK_GRU_LIST_RESSSOURCE_DEMANDES, this.getListRessourceEmail());
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
        model.put(NotifyGruConstants.MARK_MAILING_LIST, _notifyGRUService.getMailingList(request));
//        model.put( NotifyGruConstants.MARK_PLUGIN_WORKFLOW, pluginWorkflow );
//        model.put( NotifyGruConstants.MARK_TASKS_LIST, _notifyGRUService.getListBelowTasks( task, locale ) );

        model.put(NotifyGruConstants.MARK_LOCALE, request.getLocale());
        model.put(NotifyGruConstants.MARK_WEBAPP_URL, AppPathService.getBaseUrl(request));
        HtmlTemplate template = AppTemplateService.getTemplate(TEMPLATE_TASK_NOTIFY_GRU_CONFIG, locale, model);

        return template.getHtml();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayTaskInformation(int nIdHistory, HttpServletRequest request, Locale locale, ITask task) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskInformationXml(int nIdHistory, HttpServletRequest request, Locale locale, ITask task) {
        // TODO Auto-generated method stub
        return null;
    }

    public ReferenceList getListRessources() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem(1, "Ressource 1");
        refenreceList.addItem(2, "Ressource 2");
        refenreceList.addItem(3, "Ressource 3");
        refenreceList.addItem(4, "Ressource 4");
        refenreceList.addItem(5, "Ressource 5");

        return refenreceList;
    }

    public ReferenceList getListRessourcesDemande() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem(1, "Demande  1");
        refenreceList.addItem(2, "Demande  2");
        refenreceList.addItem(3, "Demande  3");
        refenreceList.addItem(4, "Demande  4");
        refenreceList.addItem(5, "Demande  5");

        return refenreceList;
    }

    public ReferenceList getListCrmWebApp() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem(1, "CRM App 1");
        refenreceList.addItem(2, "CRM App 2");
        refenreceList.addItem(3, "CRM App 3");
        refenreceList.addItem(4, "CRM App 4");
        refenreceList.addItem(5, "CRM App 5");

        return refenreceList;
    }

    public ReferenceList getListOnglet() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem("guichet", "vue guichet");
        refenreceList.addItem("agent", "vue agent");
        refenreceList.addItem("email", "vue email");
        refenreceList.addItem("sms", "vue sms");
        refenreceList.addItem("liste", "liste diffusion");

        return refenreceList;
    }

    public ReferenceList getListNotification() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem(0, "Visible par tout le monde");
        refenreceList.addItem(1, "Visible par domaine");
        refenreceList.addItem(2, "Visible par Admin");

        return refenreceList;
    }

    public ReferenceList getListRessourceEmail() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem(0, "email_1@email.com");
        refenreceList.addItem(1, "email_2@email.com");
        refenreceList.addItem(2, "email_3@email.com");

        return refenreceList;
    }
}
