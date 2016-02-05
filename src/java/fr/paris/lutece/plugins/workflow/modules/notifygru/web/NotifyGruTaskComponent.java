/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotifyGruHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.AbstractServiceProvider;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.INotifyGruService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruHistoryService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ServiceConfigTaskForm;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.TaskNotifyGruConfigService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.Validator;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.service.security.IWorkflowUserAttributesManager;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflow.web.task.NoFormTaskComponent;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
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
    private static final String TEMPLATE_TASK_NOTIFY_INFORMATION = "admin/plugins/workflow/modules/notifygru/task_notify_information.html";

    // MARKS
    private static final String MARK_CONFIG = "config";
    private static final String MARK_NOTIFY_HISTORY = "information_history";

    // SERVICES
    @Inject
    @Named(TaskNotifyGruConfigService.BEAN_SERVICE)
    private ITaskConfigService _taskNotifyGruConfigService;
    @Inject
    private INotifyGruService _notifyGRUService;
    @Inject
    private IWorkflowUserAttributesManager _userAttributesManager;
    private AbstractServiceProvider _providerService;
    @Inject
    @Named(NotifyGruHistoryService.BEAN_SERVICE)
    private INotifyGruHistoryService _taskNotifyGruHistoryService;

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
        String strApply = request.getParameter(Constants.PARAMETER_APPY);
        String strOngletActive = request.getParameter(Constants.PARAMETER_ONGLET);
        String strProvider = request.getParameter(Constants.PARAMETER_SELECT_PROVIDER);

        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());

        Boolean bActiveOngletGuichet = ServiceConfigTaskForm.setConfigOnglet(strApply, Constants.MARK_ONGLET_GUICHET,
                strOngletActive, config.isActiveOngletGuichet(),
                Constants.PARAMETER_BUTTON_REMOVE_GUICHET);
        Boolean bActiveOngletAgent = ServiceConfigTaskForm.setConfigOnglet(strApply, Constants.MARK_ONGLET_AGENT,
                strOngletActive, config.isActiveOngletAgent(),
                Constants.PARAMETER_BUTTON_REMOVE_AGENT);
        Boolean bActiveOngletEmail = ServiceConfigTaskForm.setConfigOnglet(strApply, Constants.MARK_ONGLET_EMAIL,
                strOngletActive, config.isActiveOngletEmail(),
                Constants.PARAMETER_BUTTON_REMOVE_EMAIL);
        Boolean bActiveOngletSMS = ServiceConfigTaskForm.setConfigOnglet(strApply, Constants.MARK_ONGLET_SMS,
                strOngletActive, config.isActiveOngletSMS(),
                Constants.PARAMETER_BUTTON_REMOVE_SMS);
        Boolean bActiveOngletBROADCAST = ServiceConfigTaskForm.setConfigOnglet(strApply, Constants.MARK_ONGLET_LIST,
                strOngletActive, config.isActiveOngletBroadcast(),
                Constants.PARAMETER_BUTTON_REMOVE_LISTE);

        //set the active onglet
        config.setSetOnglet(ServiceConfigTaskForm.getNumberOblet(strOngletActive));

        Boolean bRedirector = false;
        String strUrlRedirector = "";

        /*if is the first time we register provider*/
        if ((strProvider != null) && ServiceConfigTaskForm.isBeanExiste(strProvider)) 
        {
            config.setIdSpringProvider(strProvider);
            config.setKeyProvider(strProvider); // à faire
        } /*if the provider is already register*/
        else if (config.getIdSpringProvider() == null) 
        {
            Object[] tabRequiredFields
                    = {
                        I18nService.getLocalizedString(Constants.MESSAGE_MANDATORY_PROVIDER, locale),};
            bRedirector = true;
            strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_MANDATORY_PROVIDER,
                    tabRequiredFields, AdminMessage.TYPE_STOP);
        }
        else if (ServiceConfigTaskForm.isBeanExiste(config.getIdSpringProvider())) 
        {
            _providerService = SpringContextService.getBean(config.getIdSpringProvider());
        }

        /*if we are in started config of task. the provider is already register*/
        if (strProvider == null && !bRedirector && (strApply == null) && !bActiveOngletAgent && !bActiveOngletBROADCAST
                && !bActiveOngletEmail && !bActiveOngletGuichet && !bActiveOngletSMS) {
            Object[] tabRequiredFields = {I18nService.getLocalizedString(Constants.MESSAGE_MANDATORY_ONGLET, locale),};

            bRedirector = true;
            strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_MANDATORY_ONGLET,
                    tabRequiredFields, AdminMessage.TYPE_STOP);
        }

        if ( !bRedirector && ( bActiveOngletGuichet || (( strApply != null ) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_GUICHET)))) 
           {
            ArrayList<String> errors = new ArrayList<String>();
            String strMessageGuichet = request.getParameter(Constants.PARAMETER_MESSAGE_GUICHET);
            String strStatusTextGuichet = request.getParameter(Constants.PARAMETER_STATUS_TEXT_GUICHET);
            String strSenderNameGuichet = request.getParameter(Constants.PARAMETER_SENDER_NAME_GUICHET);
            String strSubjectGuichet = request.getParameter(Constants.PARAMETER_SUBJECT_GUICHET);
            
            //optional
            String strDemandMaxStepGuichet = request.getParameter(Constants.PARAMETER_DEMAND_MAX_STEP_GUICHET);         
            int nDemandMaxStepGuichet = ServiceConfigTaskForm.getNumbertoString(strDemandMaxStepGuichet);
            String strDemandUserCurrentStepGuichet = request.getParameter( Constants.PARAMETER_DEMAND_USER_CURRENT_STEP_GUICHET );
             int nDemandUserCurrentStepGuichet = ServiceConfigTaskForm.getNumbertoString( strDemandUserCurrentStepGuichet );
              
            
           
         

            if (StringUtils.isBlank(strApply)) {
                
                  if(StringUtils.isNotBlank(Validator.mandotoryParams(strMessageGuichet, Constants.MESSAGE_MANDATORY_GUICHET_MESSAGE_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strMessageGuichet, Constants.MESSAGE_MANDATORY_GUICHET_MESSAGE_FIELD, locale));
                }
                  if(StringUtils.isNotBlank(Validator.mandotoryParams(strStatusTextGuichet, Constants.MESSAGE_MANDATORY_GUICHET_STATUS_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strStatusTextGuichet, Constants.MESSAGE_MANDATORY_GUICHET_STATUS_FIELD, locale));
                }
                  if(StringUtils.isNotBlank(Validator.mandotoryParams(strSenderNameGuichet, Constants.MESSAGE_MANDATORY_GUICHET_SENDER_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strSenderNameGuichet, Constants.MESSAGE_MANDATORY_GUICHET_SENDER_FIELD, locale));
                }
                  if(StringUtils.isNotBlank(Validator.mandotoryParams(strSubjectGuichet, Constants.MESSAGE_MANDATORY_GUICHET_OBJECT_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strSubjectGuichet, Constants.MESSAGE_MANDATORY_GUICHET_OBJECT_FIELD, locale));
                }           
              
                  if (!Validator.isFreemarkerValid(strMessageGuichet, locale, _providerService.getInfos(-1)))
                  {
                    Object[] tabRequiredFields  = { I18nService.getLocalizedString(Constants.MESSAGE_ERROR_FREEMARKER, locale),};
                    bRedirector = true;
                    strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_ERROR_FREEMARKER,
                            tabRequiredFields, AdminMessage.TYPE_STOP);
                }
            }

            if (!errors.isEmpty()) {
                return ServiceConfigTaskForm.displayErrorMessage(errors, request);
            }

            config.setMessageGuichet(strMessageGuichet);
            config.setActiveOngletGuichet(bActiveOngletGuichet);
            config.setStatustextGuichet(strStatusTextGuichet);
            config.setSenderNameGuichet(strSenderNameGuichet);
            config.setSubjectGuichet(strSubjectGuichet);
            config.setDemandMaxStepGuichet(nDemandMaxStepGuichet);
            config.setDemandUserCurrentStepGuichet(nDemandUserCurrentStepGuichet);
           

            /*fin guichet*/
        }

        if (!bRedirector && (bActiveOngletAgent || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_AGENT))))
        {
            /*Agent*/
            ArrayList<String> errors = new ArrayList<String>();
            String strMessageAgent = request.getParameter(Constants.PARAMETER_STATUS_MESSAGE_AGENT);

            if (StringUtils.isBlank(strApply)) {              
                
                 if(StringUtils.isNotBlank(Validator.mandotoryParams(strMessageAgent, Constants.MESSAGE_AGENT_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strMessageAgent, Constants.MESSAGE_AGENT_FIELD, locale));
                }

                if (!Validator.isFreemarkerValid(strMessageAgent, locale, _providerService.getInfos(-1))) {
                    Object[] tabRequiredFields = { I18nService.getLocalizedString(Constants.MESSAGE_ERROR_FREEMARKER, locale),};
                    bRedirector = true;
                    strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_ERROR_FREEMARKER,
                            tabRequiredFields, AdminMessage.TYPE_STOP);
                }
            }

            if (!errors.isEmpty()) {
                return ServiceConfigTaskForm.displayErrorMessage(errors, request);
            }

            config.setMessageAgent(strMessageAgent);
            config.setActiveOngletAgent(bActiveOngletAgent);

            /*Fin Agent*/
        }

        if (!bRedirector  && (bActiveOngletEmail || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_EMAIL)))) 
        {
            /*email*/
            ArrayList<String> errors = new ArrayList<String>();

            String strSubjectEmail = request.getParameter(Constants.PARAMETER_SUBJECT_EMAIL);
            String strMessageEmail = request.getParameter(Constants.PARAMETER_MESSAGE_EMAIL);
            String strSenderNameEmail = request.getParameter(Constants.PARAMETER_SENDER_NAME_EMAIL);
            String strRecipientsCcEmail = request.getParameter(Constants.PARAMETER_RECIPIENT_CC_EMAIL);
            String strRecipientsCciEmail = request.getParameter(Constants.PARAMETER_RECIPIENT_CCI_EMAIL);

            if (StringUtils.isBlank(strApply)) {
                
                 if(StringUtils.isNotBlank(Validator.mandotoryParams(strSubjectEmail, Constants.MESSAGE_EMAIL_SUBJECT_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strSubjectEmail, Constants.MESSAGE_EMAIL_SUBJECT_FIELD, locale));
                }
                 
                 if(StringUtils.isNotBlank(Validator.mandotoryParams(strSenderNameEmail, Constants.MESSAGE_EMAIL_SENDER_NAME_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strSenderNameEmail, Constants.MESSAGE_EMAIL_SENDER_NAME_FIELD, locale));
                }
                 if(StringUtils.isNotBlank(Validator.mandotoryParams(strMessageEmail, Constants.MESSAGE_EMAIL_MESSAGE_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strMessageEmail, Constants.MESSAGE_EMAIL_MESSAGE_FIELD, locale));
                }
                 
               
                if (!Validator.isFreemarkerValid(strMessageEmail, locale, _providerService.getInfos(-1))) {
                    Object[] tabRequiredFields   = {  I18nService.getLocalizedString(Constants.MESSAGE_ERROR_FREEMARKER, locale),};
                    bRedirector = true;
                    strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_ERROR_FREEMARKER,
                            tabRequiredFields, AdminMessage.TYPE_STOP);
                }
            }

            if (!errors.isEmpty()) {
                return ServiceConfigTaskForm.displayErrorMessage(errors, request);
            }

            config.setSubjectEmail(strSubjectEmail);
            config.setMessageEmail(strMessageEmail);
            config.setSenderNameEmail(strSenderNameEmail);
            config.setRecipientsCcEmail(strRecipientsCcEmail);
            config.setRecipientsCciEmail(strRecipientsCciEmail);
            config.setActiveOngletEmail(bActiveOngletEmail);

            /*fin email*/
        }

        if (!bRedirector && (bActiveOngletSMS  || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_SMS))))
        {
            /*sms*/
            ArrayList<String> errors = new ArrayList<String>();
            String strMessageSMS = request.getParameter(Constants.PARAMETER_MESSAGE_SMS);
            String strLevelNotificationSMS = request.getParameter(Constants.PARAMETER_LEVEL_NOTIFICATION_SMS);

            if (StringUtils.isBlank(strApply)) {
               
                
                 if(StringUtils.isNotBlank(Validator.mandotoryParams(strMessageSMS, Constants.MESSAGE_SMS_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strMessageSMS, Constants.MESSAGE_SMS_FIELD, locale));
                }

                if (!Validator.isFreemarkerValid(strMessageSMS, locale, _providerService.getInfos(-1))) {
                    Object[] tabRequiredFields  = {  I18nService.getLocalizedString(Constants.MESSAGE_ERROR_FREEMARKER, locale),};
                    bRedirector = true;
                    strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_ERROR_FREEMARKER,
                            tabRequiredFields, AdminMessage.TYPE_STOP);
                }
            }

            if (!errors.isEmpty()) {
                return ServiceConfigTaskForm.displayErrorMessage(errors, request);
            }

            config.setMessageSMS(strMessageSMS);
            config.setLevelNotificationSMS(strLevelNotificationSMS);
            config.setActiveOngletSMS(bActiveOngletSMS);

            /*fin sms*/
        }

        if (!bRedirector  && (bActiveOngletBROADCAST  || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_LISTE))))
        {
            ArrayList<String> errors = new ArrayList<String>();
            String strIdMailingListBroadcast = request.getParameter(Constants.PARAMETER_ID_MAILING_LIST);
            int nIdMailingListBroadcast = (strIdMailingListBroadcast == null) ? WorkflowUtils.CONSTANT_ID_NULL
                    : Integer.parseInt(strIdMailingListBroadcast);

            String strsenderNameBroadcast = request.getParameter(Constants.PARAMETER_SENDER_NAME_BROADCAST);
            String strsubjectBroadcast = request.getParameter(Constants.PARAMETER_SUBJECT_BROADCAST);
            String strmessageBroadcast = request.getParameter(Constants.PARAMETER_MESSAGE_BROADCAST);
            String strrecipientsCcBroadcast = request.getParameter(Constants.PARAMETER_RECIPIENT_CC_BROADCAST);
            String strrecipientsCciBroadcast = request.getParameter(Constants.PARAMETER_RECIPIENT_CCI_BROADCAST);

            if (StringUtils.isBlank(strApply)) {
                
                
                   if(StringUtils.isNotBlank(Validator.mandotoryParams(strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale));
                }                   
               
                   if(StringUtils.isNotBlank(Validator.mandotoryParams(strsenderNameBroadcast, Constants.MESSAGE_LIST_SENDER_NAME_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strsenderNameBroadcast, Constants.MESSAGE_LIST_SENDER_NAME_FIELD, locale));
                }
                   if(StringUtils.isNotBlank(Validator.mandotoryParams(strsubjectBroadcast, Constants.MESSAGE_LIST_SUBJECT_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strsubjectBroadcast, Constants.MESSAGE_LIST_SUBJECT_FIELD, locale));
                }
                   if(StringUtils.isNotBlank(Validator.mandotoryParams(strmessageBroadcast, Constants.MESSAGE_LIST_MESSAGE_FIELD, locale))) 
                {
                  errors.add(Validator.mandotoryParams(strmessageBroadcast, Constants.MESSAGE_LIST_MESSAGE_FIELD, locale));
                }

                   if (!Validator.isFreemarkerValid(strmessageBroadcast, locale, _providerService.getInfos(-1))) {
                    Object[] tabRequiredFields = { I18nService.getLocalizedString(Constants.MESSAGE_ERROR_FREEMARKER, locale),};
                    bRedirector = true;
                    strUrlRedirector = AdminMessageService.getMessageUrl(request, Constants.MESSAGE_ERROR_FREEMARKER,
                            tabRequiredFields, AdminMessage.TYPE_STOP);
                }
            }

            if (!errors.isEmpty()) {
                return ServiceConfigTaskForm.displayErrorMessage(errors, request);
            }

            /* fin liste diffusion*/
            config.setIdMailingListBroadcast(nIdMailingListBroadcast);
            config.setSenderNameBroadcast(strsenderNameBroadcast);
            config.setSubjectBroadcast(strsubjectBroadcast);
            config.setMessageBroadcast(strmessageBroadcast);
            config.setRecipientsCcBroadcast(strrecipientsCcBroadcast);
            config.setRecipientsCciBroadcast(strrecipientsCciBroadcast);
            config.setActiveOngletBroadcast(bActiveOngletBROADCAST);
        }

        if (bRedirector) {
            return strUrlRedirector;
        }

        if (bActiveOngletAgent || bActiveOngletBROADCAST || bActiveOngletEmail || bActiveOngletGuichet
                || bActiveOngletSMS
                || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_GUICHET))
                || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_AGENT))
                || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_EMAIL))
                || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_SMS))
                || ((strApply != null) && strApply.equals(Constants.PARAMETER_BUTTON_REMOVE_LISTE))
                || (strProvider != null)) {
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
     *
     * @param request
     * @param locale
     * @param task
     * @return
     */
    @Override
    public String getDisplayConfigForm(HttpServletRequest request, Locale locale, ITask task) {
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());

        String strDefaultSenderName = AppPropertiesService.getProperty(Constants.PROPERTY_NOTIFY_MAIL_DEFAULT_SENDER_NAME);

        Map<String, Object> model = new HashMap<String, Object>();

        model.put(Constants.MARK_CONFIG, config);
        model.put(Constants.MARK_DEFAULT_SENDER_NAME, strDefaultSenderName);

        if (config.getIdSpringProvider() == null) {
            model.put(Constants.MARK_SELECT_PROVIDER, ServiceConfigTaskForm.getListProvider());
        }

        ReferenceList listeOnglet = ServiceConfigTaskForm.getListOnglet(config, locale);

        if (listeOnglet.size() > 0) {
            model.put(Constants.MARK_LIST_ONGLET, listeOnglet);
        }

        ReferenceList levelNotification = ServiceConfigTaskForm.getListNotification(locale);
        model.put(Constants.MARK_LEVEL_NOTIFICATION_GUICHET, levelNotification);
        model.put(Constants.MARK_LEVEL_NOTIFICATION_AGENT, levelNotification);
        model.put(Constants.MARK_LEVEL_NOTIFICATION_EMAIL, levelNotification);
        model.put(Constants.MARK_LEVEL_NOTIFICATION_SMS, levelNotification);
        model.put(Constants.MARK_LEVEL_NOTIFICATION_BROADCAST, levelNotification);

        model.put(Constants.MARK_MAILING_LIST, _notifyGRUService.getMailingList(request));

        model.put(Constants.MARK_LOCALE, request.getLocale());
        model.put(Constants.MARK_WEBAPP_URL, AppPathService.getBaseUrl(request));

        if ((config.getIdSpringProvider() != null)
                && ServiceConfigTaskForm.isBeanExiste(config.getIdSpringProvider())) {
            _providerService = SpringContextService.getBean(config.getIdSpringProvider());

            String strTemplateProvider = (_providerService == null) ? "" : _providerService.getInfosHelp(locale);

            model.put(Constants.MARK_HELPER_PROVIDER, strTemplateProvider);
        }

        HtmlTemplate template = AppTemplateService.getTemplate(TEMPLATE_TASK_NOTIFY_GRU_CONFIG, locale, model);

        return template.getHtml();
    }

    /**
     * {@inheritDoc}
     *
     * @param nIdHistory
     * @param request
     * @param locale
     * @param task
     * @return html template of history
     */
    @Override
    public String getDisplayTaskInformation(int nIdHistory, HttpServletRequest request, Locale locale, ITask task) {
        NotifyGruHistory notifyGruTaskHistory = _taskNotifyGruHistoryService.findByPrimaryKey(nIdHistory,
                task.getId(), WorkflowUtils.getPlugin());

        Map<String, Object> model = new HashMap<String, Object>();
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(task.getId());
        model.put(MARK_CONFIG, config);
        model.put(MARK_NOTIFY_HISTORY, notifyGruTaskHistory);
        HtmlTemplate template = AppTemplateService.getTemplate(TEMPLATE_TASK_NOTIFY_INFORMATION, locale, model);

        return template.getHtml();
    }

    /**
     * {@inheritDoc}
     *
     * @param nIdHistory
     * @param request
     * @param locale
     * @param task
     * @return
     */
    @Override
    public String getTaskInformationXml(int nIdHistory, HttpServletRequest request, Locale locale, ITask task) {
        // TODO Auto-generated method stub
        return null;
    }
}
