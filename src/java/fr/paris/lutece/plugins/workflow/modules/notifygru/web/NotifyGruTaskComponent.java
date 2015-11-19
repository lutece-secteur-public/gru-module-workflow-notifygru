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

        String strApply = request.getParameter(NotifyGruConstants.PARAMETER_APPY);
        String strOngletActive = request.getParameter(NotifyGruConstants.PARAMETER_ONGLE);
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());

        Boolean bActiveOngletGuichet = (config.getIdTask() == 0) ? false : config.isActiveOngletGuichet();
        Boolean bActiveOngletAgent = (config.getIdTask() == 0) ? false : config.isActiveOngletAgent();
        Boolean bActiveOngletEmail = (config.getIdTask() == 0) ? false : config.isActiveOngletEmail();
        Boolean bActiveOngletSMS = (config.getIdTask() == 0) ? false : config.isActiveOngletSMS();
        Boolean bActiveOngletBROADCAST = (config.getIdTask() == 0) ? false : config.isActiveOngletBroadcast();

        switch (strApply) {
            case "AddOnglet":
                bActiveOngletGuichet = ("guichet".equals(strOngletActive)) ? true : false;
                bActiveOngletAgent = ("agent".equals(strOngletActive)) ? true : false;
                bActiveOngletEmail = ("email".equals(strOngletActive)) ? true : false;
                bActiveOngletSMS = ("sms".equals(strOngletActive)) ? true : false;
                bActiveOngletBROADCAST = ("liste".equals(strOngletActive)) ? true : false;
                break;
            case "RemoveOnglet":
                bActiveOngletGuichet = ("guichet".equals(strOngletActive)) ? false : config.isActiveOngletGuichet();
                bActiveOngletAgent = ("agent".equals(strOngletActive)) ? false : config.isActiveOngletAgent();
                bActiveOngletEmail = ("email".equals(strOngletActive)) ? false : config.isActiveOngletEmail();
                bActiveOngletSMS = ("sms".equals(strOngletActive)) ? false : config.isActiveOngletSMS();
                bActiveOngletBROADCAST = ("liste".equals(strOngletActive)) ? false : config.isActiveOngletBroadcast();
                break;
        }

        String strError = "";

        if (bActiveOngletGuichet || ( strApply.equals("RemoveOnglet") && "guichet".equals(strOngletActive))) {

            /*général*/
            String strIdResource = request.getParameter(NotifyGruConstants.PARAMETER_ID_RESOURCE);
            int nIdResource = (strIdResource == null) ? -1 : Integer.parseInt(strIdResource);
            String stridUserGuid = request.getParameter(NotifyGruConstants.PARAMETER_ID_USER_GUID);//non
            int nstridUserGuid = (stridUserGuid == null) ? -1 : Integer.parseInt(stridUserGuid);
            /*fin général*/

            if (StringUtils.isBlank(strApply)) {
                if (nIdResource == WorkflowUtils.CONSTANT_ID_NULL) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }
            }

            /*guichet*/
            String stridDemandGuichet = request.getParameter(NotifyGruConstants.PARAMETER_ID_DEMAND_GUICHET);
            int nidDemandGuichet = (stridDemandGuichet == null) ? -1 : Integer.parseInt(stridDemandGuichet);

            if (StringUtils.isBlank(strApply)) {
                if (nidDemandGuichet == WorkflowUtils.CONSTANT_ID_NULL) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

            }

            String strCrmWebAppCodeGuichet = request.getParameter(NotifyGruConstants.PARAMETER_CRM_WEBAPP_CODE_GUICHET);
            int nCrmWebAppCodeGuichet = (strCrmWebAppCodeGuichet == null) ? -1 : Integer.parseInt(strCrmWebAppCodeGuichet);

            if (StringUtils.isBlank(strApply)) {
                if (nCrmWebAppCodeGuichet == WorkflowUtils.CONSTANT_ID_NULL) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

            }

            String strSendNotificationGuichet = request.getParameter(NotifyGruConstants.PARAMETER_SEND_NOTIFICATION_GUICHET);
            Boolean bSendNotificationGuichet = Boolean.parseBoolean(strSendNotificationGuichet);

            String strStatusTextGuichet = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_TEXT_GUICHET);
            String strSubjectGuichet = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_GUICHET);
            String strMessageGuichet = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_GUICHET);
            String strSenderNameGuichet = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_GUICHET);
            String strLevelNotificationGuichet = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_GUICHET);

            if (StringUtils.isBlank(strApply)) {
                if (strSenderNameGuichet == WorkflowUtils.EMPTY_STRING
                        || strMessageGuichet == WorkflowUtils.EMPTY_STRING
                        || strSubjectGuichet == WorkflowUtils.EMPTY_STRING
                        || strLevelNotificationGuichet == WorkflowUtils.EMPTY_STRING
                        || strStatusTextGuichet == WorkflowUtils.EMPTY_STRING) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

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

            /*fin guichet*/
        }

        if (bActiveOngletAgent || ( strApply.equals("RemoveOnglet") && "agent".equals(strOngletActive))) {

            /*Agent*/
            String strStatusTextAgent = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_TEXT_AGENT);
            String strMessageAgent = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_MESSAGE_AGENT);
            String strLevelNotificationAgent = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_AGENT);

            if (StringUtils.isBlank(strApply)) {
                if (strStatusTextAgent == WorkflowUtils.EMPTY_STRING
                        || strMessageAgent == WorkflowUtils.EMPTY_STRING
                        || strLevelNotificationAgent == WorkflowUtils.EMPTY_STRING) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

            }

            config.setStatusTextAgent(strStatusTextAgent);
            config.setMessageAgent(strMessageAgent);
            config.setLevelNotificationAgent(strLevelNotificationAgent);
            config.setActiveOngletAgent(bActiveOngletAgent);

            /*Fin Agent*/
        }

        if (bActiveOngletEmail || ( strApply.equals("RemoveOnglet") && "email".equals(strOngletActive))) {
            /*email*/
            String strRessourceRecordEmail = request.getParameter(NotifyGruConstants.PARAMETER_RESOURCE_RECORD_EMAIL);
            String strSubjectEmail = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_EMAIL);
            String strEntryEmail = request.getParameter(NotifyGruConstants.PARAMETER_ENTRY_EMAIL); //NON  
            String strMessageEmail = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_EMAIL);
            String strSenderNameEmail = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_EMAIL);

            String strRecipientsCcEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CC_EMAIL);
            String strRecipientsCciEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CCI_EMAIL);
            String strLevelNotificationEmail = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_EMAIL);

            if (StringUtils.isBlank(strApply)) {
                if (strRessourceRecordEmail == WorkflowUtils.EMPTY_STRING
                        || strSubjectEmail == WorkflowUtils.EMPTY_STRING
                        || strEntryEmail == WorkflowUtils.EMPTY_STRING
                        || strMessageEmail == WorkflowUtils.EMPTY_STRING
                        || strSenderNameEmail == WorkflowUtils.EMPTY_STRING
                        || strRecipientsCcEmail == WorkflowUtils.EMPTY_STRING
                        || strRecipientsCciEmail == WorkflowUtils.EMPTY_STRING
                        || strLevelNotificationEmail == WorkflowUtils.EMPTY_STRING) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

            }

            config.setRessourceRecordEmail(strRessourceRecordEmail);
            config.setSubjectEmail(strSubjectEmail);
            config.setEntryEmail(strEntryEmail);
            config.setMessageEmail(strMessageEmail);
            config.setSenderNameEmail(strSenderNameEmail);

            config.setRecipientsCcEmail(strRecipientsCcEmail);
            config.setRecipientsCciEmail(strRecipientsCciEmail);

            config.setLevelNotificationEmail(strLevelNotificationEmail);
            config.setActiveOngletEmail(bActiveOngletEmail);

            /*fin email*/
        }

        if (bActiveOngletSMS || ( strApply.equals("RemoveOnglet") && "sms".equals(strOngletActive))) {
            /*sms*/
            //NON pour l'instant
            String strRessourceRecordSMS = request.getParameter(NotifyGruConstants.PARAMETER_RESOURCE_RECORD_SMS);

            String strPhoneSMS = request.getParameter(NotifyGruConstants.PARAMETER_PHONE_SMS);
            String strMessageSMS = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_SMS);
            String strLevelNotificationSMS = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_SMS);

            if (StringUtils.isBlank(strApply)) {
                if (strRessourceRecordSMS == WorkflowUtils.EMPTY_STRING
                        || strPhoneSMS == WorkflowUtils.EMPTY_STRING
                        || strMessageSMS == WorkflowUtils.EMPTY_STRING
                        || strLevelNotificationSMS == WorkflowUtils.EMPTY_STRING) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

            }

            config.setRessourceRecordSMS(strRessourceRecordSMS);
            config.setPhoneSMS(strPhoneSMS);
            config.setMessageSMS(strMessageSMS);

            config.setLevelNotificationSMS(strLevelNotificationSMS);
            config.setActiveOngletSMS(bActiveOngletSMS);

            /*fin sms*/
        }

        if (bActiveOngletBROADCAST || ( strApply.equals("RemoveOnglet") && "liste".equals(strOngletActive))) {
            String strIdMailingListBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_ID_MAILING_LIST);
            int nIdMailingListBroadcast = (strIdMailingListBroadcast == null) ? -1 : Integer.parseInt(strIdMailingListBroadcast);

            String strsenderNameBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_BROADCAST);
            String strsubjectBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_BROADCAST);
            String strmessageBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_BROADCAST);
            String strrecipientsCcBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CC_BROADCAST);
            String strrecipientsCciBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CCI_BROADCAST);
            String strLevelNotificationBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_BROADCAST);

            if (StringUtils.isBlank(strApply)) {
                if (nIdMailingListBroadcast == WorkflowUtils.CONSTANT_ID_NULL
                        || strsenderNameBroadcast == WorkflowUtils.EMPTY_STRING
                        || strsubjectBroadcast == WorkflowUtils.EMPTY_STRING
                        || strrecipientsCciBroadcast == WorkflowUtils.EMPTY_STRING
                        || strrecipientsCcBroadcast == WorkflowUtils.EMPTY_STRING
                        || strLevelNotificationBroadcast == WorkflowUtils.EMPTY_STRING) {
                    strError = NotifyGruConstants.MESSAGE_MANDATORY_FIELD;
                }

            }

            if (!strError.equals(WorkflowUtils.EMPTY_STRING)) {
                Object[] tabRequiredFields = {I18nService.getLocalizedString(strError, locale)};

                return AdminMessageService.getMessageUrl(request, NotifyGruConstants.MESSAGE_MANDATORY_FIELD,
                        tabRequiredFields, AdminMessage.TYPE_STOP);
            }

            /* fin liste diffusion*/
            config.setIdMailingListBroadcast(nIdMailingListBroadcast);
            config.setSenderNameBroadcast(strsenderNameBroadcast);
            config.setSubjectBroadcast(strsubjectBroadcast);
            config.setMessageBroadcast(strmessageBroadcast);
            config.setRecipientsCcBroadcast(strrecipientsCcBroadcast);
            config.setRecipientsCciBroadcast(strrecipientsCciBroadcast);
            config.setLevelNotificationBroadcast(strLevelNotificationBroadcast);
            config.setActiveOngletBroadcast(bActiveOngletBROADCAST);
        }

        if (bActiveOngletAgent || bActiveOngletBROADCAST || bActiveOngletEmail || bActiveOngletGuichet || bActiveOngletSMS) {
            Boolean bCreate = false;
            if (config.getIdTask() == 0) {
                config = new TaskNotifyGruConfig();
                config.setIdTask(task.getId());
                bCreate = true;
            }

            if (bCreate) {
                _taskNotifyGruConfigService.create(config);
            } else {
                _taskNotifyGruConfigService.update(config);
            }

        } else {
            Object[] tabRequiredFields = {I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_MANDATORY_ONGLET, locale)};

            return AdminMessageService.getMessageUrl(request, NotifyGruConstants.MESSAGE_MANDATORY_ONGLET,
                    tabRequiredFields, AdminMessage.TYPE_STOP);

        }

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
        model.put(NotifyGruConstants.MARK_GRU_LIST_RESSSOURCE_EMAIL, this.getListRessourceEmail());
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
