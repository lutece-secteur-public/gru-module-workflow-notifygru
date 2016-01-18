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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.TaskNotifyGruConstants;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.html.HtmlTemplate;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import net.sf.json.JSONSerializer;

/**
 *
 * TaskNotifyGru
 *
 */
public class TaskNotifyGru extends SimpleTask {
    // TEMPLATES
    /* private static final String TEMPLATE_TASK_NOTIFY_DESK = "admin/plugins/workflow/modules/notifygru/task_notify_gru_desk.html";
     private static final String TEMPLATE_TASK_NOTIFY_AGENT = "admin/plugins/workflow/modules/notifygru/task_notify_gru_agent.html";
     private static final String TEMPLATE_TASK_NOTIFY_MAIL = "admin/plugins/workflow/modules/notifygru/task_notify_gru_mail.html";
     private static final String TEMPLATE_TASK_NOTIFY_SMS = "admin/plugins/workflow/modules/notifygru/task_notify_gru_sms.html";*/

    // SERVICES
    @Inject
    private IResourceHistoryService _resourceHistoryService;
    @Inject
    @Named(TaskNotifyGruConfigService.BEAN_SERVICE)
    private ITaskConfigService _taskNotifyGruConfigService;
    private AbstractServiceProvider _notifyGruService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void processTask(int nIdResourceHistory, HttpServletRequest request, Locale locale) {
        //ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey(nIdResourceHistory);
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(this.getId());

        if ((config != null) && ServiceConfigTaskForm.isBeanExiste(config.getIdSpringProvider())) {
            _notifyGruService = SpringContextService.getBean(config.getIdSpringProvider());

           

            boolean bIsNotifyByDesk = config.isActiveOngletGuichet();
            boolean bIsNotifyByViewAgent = config.isActiveOngletAgent();
            boolean bIsNotifyByEmail = config.isActiveOngletEmail();
            boolean bIsNotifyBySms = config.isActiveOngletSMS();

            JSONObject fluxJson = new JSONObject();
            JSONObject notificationJson = new JSONObject();

            notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_GUID, _notifyGruService.getUserGuid(nIdResourceHistory));
            notificationJson.accumulate(TaskNotifyGruConstants.MARK_EMAIL, _notifyGruService.getUserEmail(nIdResourceHistory));
            notificationJson.accumulate(TaskNotifyGruConstants.MARK_CRM_STATUS_ID, config.getCrmStatusIdCommune());
            notificationJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFICATION_TYPE, "UNDEFINED SOURCE");

            int nIdDemand = _notifyGruService.getOptionalDemandId(nIdResourceHistory);
            if (nIdDemand != TaskNotifyGruConstants.OPTIONAL_INT_VALUE) {
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_ID_DEMAND, _notifyGruService.getOptionalDemandId(nIdResourceHistory));
            }

            int nIdDemandType = _notifyGruService.getOptionalDemandIdType(nIdResourceHistory);
            if (nIdDemandType != TaskNotifyGruConstants.OPTIONAL_INT_VALUE) {
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_ID_DEMAND_TYPE, 1);
            }

            if (config.getDemandMaxStepGuichet() >= 0) {
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_DEMAND_MAX_STEP, config.getDemandMaxStepGuichet());
            }
            if (config.getDemandUserCurrentStepGuichet() >= 0) {
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_DEMAND_USER_CURRENT_STEP, config.getDemandUserCurrentStepGuichet());
            }
            if (config.getDemandStateGuichet() >= 0) {
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_DEMAND_STATE, config.getDemandStateGuichet());
            }

            String strMessageEmail = "";
            if (bIsNotifyByEmail) {
                //user_email
                JSONObject userEmailJson = new JSONObject();
                HtmlTemplate tMessageEmail = AppTemplateService.getTemplateFromStringFtl(config.getMessageEmail(), locale,
                        _notifyGruService.getInfos(nIdResourceHistory));
                strMessageEmail = tMessageEmail.getHtml();
                userEmailJson.accumulate(TaskNotifyGruConstants.MARK_SENDER_NAME, config.getSenderNameEmail());
                userEmailJson.accumulate(TaskNotifyGruConstants.MARK_SENDER_EMAIL, MailService.getNoReplyEmail());
                userEmailJson.accumulate(TaskNotifyGruConstants.MARK_RECIPIENT, _notifyGruService.getUserEmail(nIdResourceHistory));
                userEmailJson.accumulate(TaskNotifyGruConstants.MARK_SUBJECT, config.getSubjectEmail());
                userEmailJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_EMAIL, strMessageEmail);

                if (config.getRecipientsCcEmail() !=null && !config.getRecipientsCcEmail().equals("")) {
                    userEmailJson.accumulate(TaskNotifyGruConstants.MARK_CC, config.getRecipientsCcEmail());
                }
                if (config.getRecipientsCciEmail() !=null && !config.getRecipientsCciEmail().equals("")) {
                    userEmailJson.accumulate(TaskNotifyGruConstants.MARK_CCI, config.getRecipientsCciEmail());
                }

                notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_MAIL, userEmailJson);
            }

            String strMessageGuichet = "";
            if (bIsNotifyByDesk) {
//                //user_dashboard
                JSONObject userDashBoardJson = new JSONObject();
                HtmlTemplate tMessageUserDashboard = AppTemplateService.getTemplateFromStringFtl(config.getMessageGuichet(), locale,
                        _notifyGruService.getInfos(nIdResourceHistory));
                strMessageGuichet = tMessageUserDashboard.getHtml();
                userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_STATUS_TEXT_USERDASHBOARD, config.getStatustextGuichet());
                userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_SENDER_NAME_USERDASHBOARD, config.getSenderNameGuichet());
                userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_SUBJECT_USERDASHBOARD, config.getSubjectGuichet());
                userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_USERDASHBOARD, strMessageGuichet);
                userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_DATA_USERDASHBOARD, "UNDEFINED SOURCE");
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_DASHBOARD, userDashBoardJson);
            }

            String strMessageSMS = "";
            if (bIsNotifyBySms && !_notifyGruService.getOptionalMobilePhoneNumber(nIdResourceHistory).equals(TaskNotifyGruConstants.OPTIONAL_STRING_VALUE)) {
                //user_sms
                JSONObject smsJson = new JSONObject();
                HtmlTemplate tMessageSMS = AppTemplateService.getTemplateFromStringFtl(config.getMessageSMS(), locale,
                        _notifyGruService.getInfos(nIdResourceHistory));
                strMessageSMS = tMessageSMS.getHtml();
                smsJson.accumulate(TaskNotifyGruConstants.MARK_PHONE_NUMBER, _notifyGruService.getOptionalMobilePhoneNumber(nIdResourceHistory));
                smsJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_SMS, strMessageSMS);
                notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_SMS, smsJson);
            }

            if (bIsNotifyByViewAgent) {
                //backoffice_logging
                JSONObject backOfficeLogginJson = new JSONObject();
                backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_STATUS_TEXT_BACK_OFFICE_LOGGING, config.getStatustextGuichet());
                HtmlTemplate tMessageAgent = AppTemplateService.getTemplateFromStringFtl(config.getMessageAgent(), locale,
                        _notifyGruService.getInfos(nIdResourceHistory));
                String strMessageAgent = tMessageAgent.getHtml();
                backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_BACK_OFFICE_LOGGING, strMessageAgent);
                backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFIED_ON_DASHBOARD, (bIsNotifyByDesk) ? 1 : 0);
                backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFIED_BY_EMAIL, (bIsNotifyByEmail) ? 1 : 0);
                backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFIED_BY_SMS, (bIsNotifyBySms) ? 1 : 0);

                if (bIsNotifyByDesk) {
                    backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_DASHBOARD_NOTIFICATION, config.getLevelNotificationGuichet());
                    backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_VIEW_DASHBOARD_NOTIFICATION,
                            TaskNotifyGruConstants.MARK_DISPLAY_MESSAGE + " " + strMessageGuichet);
                }

                if (bIsNotifyByEmail) {
                    backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_EMAIL_NOTIFICATION, config.getLevelNotificationEmail());
                    backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_VIEW_EMAIL_NOTIFICATION,
                            TaskNotifyGruConstants.MESSAGE_DISPLAY_EMAIL + " " + _notifyGruService.getUserEmail(nIdResourceHistory)
                            + TaskNotifyGruConstants.MESSAGE_DISPLAY_OBJECT + " " + config.getSubjectEmail()
                            + TaskNotifyGruConstants.MESSAGE_DISPLAY_MESSAGE_EMAIL + " " + strMessageEmail);
                }

                if (bIsNotifyBySms && !_notifyGruService.getOptionalMobilePhoneNumber(nIdResourceHistory).equals(TaskNotifyGruConstants.OPTIONAL_STRING_VALUE)) {
                    backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_SMS_NOTIFICATION, config.getLevelNotificationSMS());
                    backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_VIEW_SMS_NOTIFICATION,
                            TaskNotifyGruConstants.MESSAGE_DISPLAY_SMS + " " + _notifyGruService.getOptionalMobilePhoneNumber(nIdResourceHistory)
                            + TaskNotifyGruConstants.MESSAGE_DISPLAY_MESSAGE_SMS + " " + strMessageSMS);
                }

                notificationJson.accumulate(TaskNotifyGruConstants.MARK_BACK_OFFICE_LOGGING, backOfficeLogginJson);
            }

            //notification
            fluxJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFICATION, notificationJson);

            String strJsontoESB = fluxJson.toString(2);
            
            //recupération du token
            
//end recupération du token

            try {
                
                
               JSONObject TokenAuth= this.getToken();
                
//                  String strToken = (String)jsonObject.get("access_token");
//                 String strScope = (String)jsonObject.get("scope");
//                 String strTokenType = (String)jsonObject.get("token_type");
//                 String strExpiresIn = (String)jsonObject.get("expires_in");
                Client client = Client.create();

                WebResource webResource = client.resource(AppPropertiesService.getProperty(
                        TaskNotifyGruConstants.URL_ESB));

                ClientResponse response = webResource.type(TaskNotifyGruConstants.CONTENT_FORMAT)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+ (String)TokenAuth.get("access_token"))
                        .accept(MediaType.APPLICATION_JSON)
                        .post(ClientResponse.class,
                                strJsontoESB);

                AppLogService.info("\n\n"+TaskNotifyGruConstants.CONTENT_FORMAT+"\n\n");
                AppLogService.info("\n\n"+(String)TokenAuth.get("token_type")+" "+ (String)TokenAuth.get("access_token")+"\n\n");
//                AppLogService.info("\n\n"+HttpHeaders.AUTHORIZATION+" : "+"Bearer " + AppPropertiesService.getProperty(
//                                        TaskNotifyGruConstants.TOKEN)+"\n\n");
                AppLogService.info("\n\nFLUX ENVOYER : "+strJsontoESB+"\n\n");
                 AppLogService.info("\n\nREPONSE ESB  1 : "+response+"\n\n");
              
                if (response.getStatus() != 201) {
                     AppLogService.info("\n\nCode De REPONSE "+response.getStatus() +"\n\n");
                    throw new RuntimeException(TaskNotifyGruConstants.ERROR_MESSAGE + response.getStatus());
                }

                String output = response.getEntity(String.class);
                  AppLogService.info("\n\nREPONSE ESB 2: "+output+"\n\n");
                  AppLogService.info("\n\nREPONSE ESB 2: "+response.getHeaders()+"\n\n");
                
               
                
            } catch (Exception e) {
                  AppLogService.info("\n\nEXCEPTION POUR NE PLUS CONTINUER LA TACHE \n\n");
                e.getMessage();
            }

        }
    }
    
    
    private JSONObject getToken(){
        
         Client client = Client.create();

                WebResource webResource = client.resource("http://dev.lutece.paris.fr/poc/api/token");

                
                javax.ws.rs.core.MultivaluedMap<String, String> params =
                new com.sun.jersey.core.util.MultivaluedMapImpl();
        params.add("grant_type", "client_credentials");

                ClientResponse response = webResource.type("application/x-www-form-urlencoded")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer a0xCU3EyeFJHMF9na29jOXZHTGZNTzdVdXZ3YTpmSWd3SjhPRXRzZDV0empJMm9RXzJ4elljQVFh")                       
                        .accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,params);
                       
                
                     String output = response.getEntity(String.class);
                     
               
                  JSONObject jsonObject = (JSONObject) JSONSerializer
					.toJSON(output);
               
                     return jsonObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveConfig() {
        _taskNotifyGruConfigService.remove(this.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle(Locale locale) {
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(this.getId());

        if (config != null) {
            return new String(I18nService.getLocalizedString(NotifyGruConstants.TITLE_NOTIFY, locale));
        }

        return new String(I18nService.getLocalizedString(NotifyGruConstants.TITLE_NOTIFY, locale));
    }
}
