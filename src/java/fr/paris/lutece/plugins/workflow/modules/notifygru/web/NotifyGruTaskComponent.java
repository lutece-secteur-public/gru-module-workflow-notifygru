package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.AbstractServiceProvider;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ServiceConfigTaskForm;
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
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
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

    AbstractServiceProvider _mokeProviderService;
    // AbstractServiceProvider _mokeProviderService =  new Mook1ProviderService();

    /**
     * {@inheritDoc}
     *
     * @param request
     * @param locale
     * @param task
     * @return string
     */
    @Override
    public String doSaveConfig(HttpServletRequest request, Locale locale, ITask task) {
        String strApply = request.getParameter(NotifyGruConstants.PARAMETER_APPY);
        String strOngletActive = request.getParameter(NotifyGruConstants.PARAMETER_ONGLET);
        String strProvider = request.getParameter(NotifyGruConstants.PARAMETER_SELECT_PROVIDER);
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());

        Boolean bActiveOngletGuichet = ServiceConfigTaskForm.setConfigOnglet(strApply, NotifyGruConstants.MARK_ONGLET_GUICHET, strOngletActive, config.isActiveOngletGuichet(), NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET);
        Boolean bActiveOngletAgent = ServiceConfigTaskForm.setConfigOnglet(strApply, NotifyGruConstants.MARK_ONGLET_AGENT, strOngletActive, config.isActiveOngletAgent(), NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT);
        Boolean bActiveOngletEmail = ServiceConfigTaskForm.setConfigOnglet(strApply, NotifyGruConstants.MARK_ONGLET_EMAIL, strOngletActive, config.isActiveOngletEmail(), NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL);
        Boolean bActiveOngletSMS = ServiceConfigTaskForm.setConfigOnglet(strApply, NotifyGruConstants.MARK_ONGLET_SMS, strOngletActive, config.isActiveOngletSMS(), NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS);
        Boolean bActiveOngletBROADCAST = ServiceConfigTaskForm.setConfigOnglet(strApply, NotifyGruConstants.MARK_ONGLET_LIST, strOngletActive, config.isActiveOngletBroadcast(), NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE);

        config.setSetOnglet(ServiceConfigTaskForm.getNumberOblet(strOngletActive));

        if (strProvider != null && ServiceConfigTaskForm.isBeanExiste(strProvider)) {

            config.setIdSpringProvider(strProvider);
            config.setKeyProvider(strProvider); // à faire

        } else if (config.getIdSpringProvider() == null) {
            Object[] tabRequiredFields = {I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_MANDATORY_PROVIDER, locale)};
            return AdminMessageService.getMessageUrl(request, NotifyGruConstants.MESSAGE_MANDATORY_PROVIDER,
                    tabRequiredFields, AdminMessage.TYPE_STOP);
        }

        if (strApply == null && !bActiveOngletAgent && !bActiveOngletBROADCAST
                && !bActiveOngletEmail && !bActiveOngletGuichet && !bActiveOngletSMS) {

            Object[] tabRequiredFields = {I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_MANDATORY_ONGLET, locale)};

            return AdminMessageService.getMessageUrl(request, NotifyGruConstants.MESSAGE_MANDATORY_ONGLET,
                    tabRequiredFields, AdminMessage.TYPE_STOP);
        }

        if (bActiveOngletGuichet || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET))) {
            /*général*/
            ArrayList<String> _errors = new ArrayList<String>();
            String strMessageGuichet = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_GUICHET);
            String strLevelNotificationGuichet = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_GUICHET);
            if (StringUtils.isBlank(strApply)) {
                if (strMessageGuichet == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_GUICHET_MESSAGE_FIELD, locale));
                }
            }
            if (_errors.size() != 0) {
                return ServiceConfigTaskForm.displayErrorMessage(_errors, request);
            }
            config.setMessageGuichet(strMessageGuichet);
            config.setLevelNotificationGuichet(strLevelNotificationGuichet);
            config.setActiveOngletGuichet(bActiveOngletGuichet);
            /*fin guichet*/
        }

        if (bActiveOngletAgent || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT))) {

            /*Agent*/
            ArrayList<String> _errors = new ArrayList<String>();
            String strMessageAgent = request.getParameter(NotifyGruConstants.PARAMETER_STATUS_MESSAGE_AGENT);
            String strLevelNotificationAgent = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_AGENT);
            if (StringUtils.isBlank(strApply)) {
                if (strMessageAgent == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_AGENT_FIELD, locale));
                }
            }
            if (_errors.size() != 0) {
                return ServiceConfigTaskForm.displayErrorMessage(_errors, request);
            }

            config.setMessageAgent(strMessageAgent);
            config.setLevelNotificationAgent(strLevelNotificationAgent);
            config.setActiveOngletAgent(bActiveOngletAgent);

            /*Fin Agent*/
        }

        if (bActiveOngletEmail || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL))) {
            /*email*/
            ArrayList<String> _errors = new ArrayList<String>();

            String strSubjectEmail = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_EMAIL);
            String strMessageEmail = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_EMAIL);
            String strSenderNameEmail = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_EMAIL);
            String strRecipientsCcEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CC_EMAIL);
            String strRecipientsCciEmail = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CCI_EMAIL);
            String strLevelNotificationEmail = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_EMAIL);

            if (StringUtils.isBlank(strApply)) {
                if (strSenderNameEmail == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_EMAIL_SENDER_NAME_FIELD, locale));
                }
                if (strSubjectEmail == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_EMAIL_SUBJECT_FIELD, locale));
                }
                if (strMessageEmail == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_EMAIL_MESSAGE_FIELD, locale));
                }

            }
            if (_errors.size() != 0) {
                return ServiceConfigTaskForm.displayErrorMessage(_errors, request);
            }

            config.setSubjectEmail(strSubjectEmail);

            config.setMessageEmail(strMessageEmail);
            config.setSenderNameEmail(strSenderNameEmail);
            config.setRecipientsCcEmail(strRecipientsCcEmail);
            config.setRecipientsCciEmail(strRecipientsCciEmail);
            config.setLevelNotificationEmail(strLevelNotificationEmail);
            config.setActiveOngletEmail(bActiveOngletEmail);

            /*fin email*/
        }

        if (bActiveOngletSMS || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS))) {
            /*sms*/
            ArrayList<String> _errors = new ArrayList<String>();
            String strMessageSMS = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_SMS);
            String strLevelNotificationSMS = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_SMS);
            if (StringUtils.isBlank(strApply)) {

                if (strMessageSMS == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_SMS_FIELD, locale));
                }
            }
            if (_errors.size() != 0) {
                return ServiceConfigTaskForm.displayErrorMessage(_errors, request);
            }
            config.setMessageSMS(strMessageSMS);
            config.setLevelNotificationSMS(strLevelNotificationSMS);
            config.setActiveOngletSMS(bActiveOngletSMS);

            /*fin sms*/
        }

        if (bActiveOngletBROADCAST || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE))) {
            ArrayList<String> _errors = new ArrayList<String>();
            String strIdMailingListBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_ID_MAILING_LIST);
            int nIdMailingListBroadcast = (strIdMailingListBroadcast == null) ? WorkflowUtils.CONSTANT_ID_NULL : Integer.parseInt(strIdMailingListBroadcast);

            String strsenderNameBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_SENDER_NAME_BROADCAST);
            String strsubjectBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_SUBJECT_BROADCAST);
            String strmessageBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_MESSAGE_BROADCAST);
            String strrecipientsCcBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CC_BROADCAST);
            String strrecipientsCciBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_RECIPIENT_CCI_BROADCAST);
            String strLevelNotificationBroadcast = request.getParameter(NotifyGruConstants.PARAMETER_LEVEL_NOTIFICATION_BROADCAST);

            if (StringUtils.isBlank(strApply)) {

                if (strIdMailingListBroadcast == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_LIST, locale));
                }
                if (strsenderNameBroadcast == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_LIST_SENDER_NAME_FIELD, locale));
                }
                if (strsubjectBroadcast == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_LIST_SUBJECT_FIELD, locale));
                }
                if (strmessageBroadcast == WorkflowUtils.EMPTY_STRING) {
                    _errors.add(I18nService.getLocalizedString(NotifyGruConstants.MESSAGE_LIST_MESSAGE_FIELD, locale));
                }
            }
            if (_errors.size() != 0) {
                return ServiceConfigTaskForm.displayErrorMessage(_errors, request);
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

        if (bActiveOngletAgent || bActiveOngletBROADCAST
                || bActiveOngletEmail || bActiveOngletGuichet
                || bActiveOngletSMS
                || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET))
                || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT))
                || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL))
                || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS))
                || (strApply != null && strApply.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE))
                || strProvider != null) {
            Boolean bCreate = false;
            if (config.getIdTask() == 0) {
                config.setIdTask(task.getId());
                bCreate = true;
            }

            if (bCreate) {
                _taskNotifyGruConfigService.create(config);
            } else {
                _taskNotifyGruConfigService.update(config);
            }

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


        Map<String, Object> model = new HashMap<String, Object>();

        model.put(NotifyGruConstants.MARK_CONFIG, config);
        model.put(NotifyGruConstants.MARK_DEFAULT_SENDER_NAME, strDefaultSenderName);

        if (config.getIdSpringProvider() == null) {
            model.put(NotifyGruConstants.MARK_SELECT_PROVIDER, ServiceConfigTaskForm.getListProvider());
        }

        ReferenceList listeOnglet = ServiceConfigTaskForm.getListOnglet(config);

        if (listeOnglet.size() > 0) {
            model.put(NotifyGruConstants.MARK_LIST_ONGLET, listeOnglet);
        }

        ReferenceList levelNotification = ServiceConfigTaskForm.getListNotification(locale);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_GUICHET, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_AGENT, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_EMAIL, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_SMS, levelNotification);
        model.put(NotifyGruConstants.MARK_LEVEL_NOTIFICATION_BROADCAST, levelNotification);

        model.put(NotifyGruConstants.MARK_MAILING_LIST, _notifyGRUService.getMailingList(request));

        model.put(NotifyGruConstants.MARK_LOCALE, request.getLocale());
        model.put(NotifyGruConstants.MARK_WEBAPP_URL, AppPathService.getBaseUrl(request));
     


        if (config.getIdSpringProvider() != null && ServiceConfigTaskForm.isBeanExiste(config.getIdSpringProvider())) {
            _mokeProviderService = SpringContextService.getBean(config.getIdSpringProvider());
            String strTemplateProvider = (_mokeProviderService == null) ? "" : _mokeProviderService.getInfosHelp(request, model);

            model.put(NotifyGruConstants.MARK_HELPER_PROVIDER, strTemplateProvider);
        }
        
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

}
