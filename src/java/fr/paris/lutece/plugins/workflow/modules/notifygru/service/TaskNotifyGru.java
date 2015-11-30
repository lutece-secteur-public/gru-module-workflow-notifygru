package fr.paris.lutece.plugins.workflow.modules.notifygru.service;



import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.TaskNotifyGruConstants;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

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
    @Inject
    private INotifyGruService _notifyGruService;
    
    

    /**
     * {@inheritDoc}
     */
    @Override

    public void processTask(int nIdResourceHistory, HttpServletRequest request, Locale locale) {
        //ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey(nIdResourceHistory);
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(this.getId());
        
        
        if ( ( config != null ) )
        {
        	AbstractServiceProvider _mokeProviderService=SpringContextService.getBean(TaskNotifyGruConstants.BEAN_MOOC1);
        	Resource resource = (Resource) _mokeProviderService.getInfos(0);
            Map<String, Object> modelMessageContent = new HashMap<String, Object>(  );
            modelMessageContent.put( TaskNotifyGruConstants.MARK_RESOURCE, resource);
            
            Map<String, Object> model = new HashMap<String, Object>();

            model.put(NotifyGruConstants.MARK_CONFIG, config);
            
        	JSONObject fluxJson = new JSONObject();
        	JSONObject notificationJson = new JSONObject();
        	
        	notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_GUID, resource.getIdUser());
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_EMAIL, resource.getEmail());
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFICATION_ID, "");
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFICATION_DATE, "");
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFICATION_TYPE, resource.getNotificationType());
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_ID_DEMAND, resource.getIdDemand());
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_ID_DEMAND_TYPE, resource.getIdDemandType());
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_DEMAND_MAX_STEP, resource.getDemandMaxStep());
	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_DEMAND_USER_CURRENT_STEP, resource.getDemandUserCurrentStep());
        	
         	boolean bIsNotifyByDesk = config.isActiveOngletGuichet() ;
         	boolean bIsNotifyByViewAgent = config.isActiveOngletAgent() ;
            boolean bIsNotifyByEmail = config.isActiveOngletEmail() ;
            boolean bIsNotifyBySms = config.isActiveOngletSMS() ;
            
            if(bIsNotifyByDesk)
            {
            	//user_dashboard
    	        JSONObject userDashBoardJson = new JSONObject();
    	        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(), locale, modelMessageContent );
    	        userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_STATUS_TEXT_USERDASHBOARD,  resource.getStatusText());
    	        userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_ID_STATUS_CRM_USERDASHBOARD, resource.getIdStatusCrm());
    	        userDashBoardJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_USERDASHBOARD, t.getHtml());
    	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_DASHBOARD, userDashBoardJson);
            }
            
            if(bIsNotifyByEmail)
            {
            	//user_email
    	        JSONObject userEmailJson = new JSONObject();
    	        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(), locale, modelMessageContent );
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_SENDER_NAME, config.getSenderNameEmail());
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_SENDER_EMAIL, MailService.getNoReplyEmail(  ));
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_RECIPIENT, resource.getEmail());
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_SUBJECT, config.getSubjectEmail());
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_EMAIL, t.getHtml());
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_CC, "");
    	        userEmailJson.accumulate(TaskNotifyGruConstants.MARK_CCI, "");
    	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_TAB_USER_MAIL, userEmailJson);
            	
            }
            if(bIsNotifyBySms)
            {
            	//user_sms
    	        JSONObject smsJson = new JSONObject();
    	        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(), locale, modelMessageContent );
    	        smsJson.accumulate(TaskNotifyGruConstants.MARK_PHONE_NUMBER, resource.getPhoneNumber());
    	        smsJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_SMS, t.getHtml());
    	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_USER_SMS, smsJson);
            }
            if(bIsNotifyByViewAgent)
            {
            	//backoffice_logging
    	        JSONObject backOfficeLogginJson = new JSONObject();
    	        HtmlTemplate tViewAgent = AppTemplateService.getTemplateFromStringFtl( config.getMessageAgent(), locale, modelMessageContent );
    	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_MESSAGE_BACK_OFFICE_LOGGING, tViewAgent.getHtml());
    	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_STATUS_TEXT_BACK_OFFICE_LOGGING, resource.getStatusText());
    	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_ID_STATUS_CRM_BACK_OFFICE_LOGGING, resource.getIdStatusCrm());
    	        
    	        if(bIsNotifyByDesk)
    	        {
    	        	HtmlTemplate tDashboard = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(), locale, modelMessageContent );
    	        	backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFIED_ON_DASHBOARD, 1);
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_DASHBOARD_NOTIFICATION, 2);
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_VIEW_DASHBOARD_NOTIFICATION, TaskNotifyGruConstants.MARK_DISPLAY_MESSAGE + tDashboard.getHtml());
    	        }
    	        if(bIsNotifyByEmail)
                {
    	        	HtmlTemplate tEmail = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(), locale, modelMessageContent );
    	        	backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFIED_BY_EMAIL, 1);
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_EMAIL_NOTIFICATION, 2);
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_VIEW_EMAIL_NOTIFICATION, TaskNotifyGruConstants.MESSAGE_DISPLAY_EMAIL + resource.getEmail() +
        	        		TaskNotifyGruConstants.MESSAGE_DISPLAY_OBJECT+ config.getSubjectEmail() +TaskNotifyGruConstants.MESSAGE_DISPLAY_MESSAGE_EMAIL + tEmail.getHtml());
                }
    	        if(bIsNotifyBySms)
    	        {
    	        	HtmlTemplate tSms = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(), locale, modelMessageContent );
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFIED_BY_SMS, 1);
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_SMS_NOTIFICATION, 1);
        	        backOfficeLogginJson.accumulate(TaskNotifyGruConstants.MARK_VIEW_SMS_NOTIFICATION, TaskNotifyGruConstants.MESSAGE_DISPLAY_SMS + resource.getPhoneNumber() +
        	        		TaskNotifyGruConstants.MESSAGE_DISPLAY_MESSAGE_SMS + tSms.getHtml());
    	        }
    	        
    	        notificationJson.accumulate(TaskNotifyGruConstants.MARK_BACK_OFFICE_LOGGING, backOfficeLogginJson);
            	
            }
            //notification
	       
	        fluxJson.accumulate(TaskNotifyGruConstants.MARK_NOTIFICATION, notificationJson);
	
	        String strJsontoESB = fluxJson.toString(2);
	
	        try {
	
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost postRequest = new HttpPost(
	                   TaskNotifyGruConstants.URL_ESB);
	
	            StringEntity input = new StringEntity(strJsontoESB);
	            input.setContentType(TaskNotifyGruConstants.CONTENT_FORMAT);
	            postRequest.setEntity(input);
	
	            HttpResponse response = httpClient.execute(postRequest);
	
	            if (response.getStatusLine().getStatusCode() != 201) {
	                throw new RuntimeException(TaskNotifyGruConstants.ERROR_MESSAGE
	                        + response.getStatusLine().getStatusCode());
	            }
	
	            BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));
	
	            httpClient.getConnectionManager().shutdown();
	
	        } catch (MalformedURLException e) {
	
	            e.printStackTrace();
	
	        } catch (IOException e) {
	
	            e.printStackTrace();
	
	        }
        }

        /*      try {
         HttpAccess Client = new HttpAccess();
         Map<String, String> data = new HashMap<String, String>();

         data.put("flux", strJsontoESB);

         Map<String, String> params = new HashMap<String, String>();
         params.put("Content-Type", "application/json");
         params.put("Accept", "application/json");
         params.put("Authorization", "Basic (with a username and password)");

         String strResponse = Client.doPost("http://localhost:8080", data, null, null, params, null);

         //                     doPost( String strUrl, Map<String, String> params, RequestAuthenticator authenticator,
         //        List<String> listElements, Map<String, String> headersRequest,Map<String, String> headersResponse )
         System.out.println(strJsontoESB);
         System.out.println(strResponse);

         } catch (HttpAccessException e) {

         }
         */
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

            return new String(NotifyGruConstants.TITLE_NOTIFY);
        }

        return new String(NotifyGruConstants.TITLE_NOTIFY);
    }
}
