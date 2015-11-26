package fr.paris.lutece.plugins.workflow.modules.notifygru.service;



import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;


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
        	Map<String, Object> modelMessageContent = _notifyGruService.fillModelMoke( );
            
            Map<String, Object> model = new HashMap<String, Object>();

            model.put(NotifyGruConstants.MARK_CONFIG, config);
            
        	JSONObject fluxJson = new JSONObject();
        	JSONObject notificationJson = new JSONObject();
        	
        	notificationJson.accumulate("user_guid", "12312");
	        notificationJson.accumulate("user_email", _notifyGruService.getEmail( config, 0,0 ));
	        notificationJson.accumulate("notification_id", 56454);
	        notificationJson.accumulate("notification_date", 312123212);
	        notificationJson.accumulate("notification_type", "TYPE");
	        notificationJson.accumulate("id_demand", 1108);
	        notificationJson.accumulate("id_demand_type", 14);
	        notificationJson.accumulate("demand_max_step", 5);
	        notificationJson.accumulate("demand_user_current_step", 3);
        	
         	boolean bIsNotifyByDesk = config.isActiveOngletGuichet() ;
         	boolean bIsNotifyByViewAgent = config.isActiveOngletAgent() ;
            boolean bIsNotifyByEmail = config.isActiveOngletEmail() ;
            boolean bIsNotifyBySms = config.isActiveOngletSMS() ;
            if(bIsNotifyByDesk)
            {
            	//user_dashboard
    	        JSONObject userDashBoardJson = new JSONObject();
    	        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(), locale, modelMessageContent );
    	        userDashBoardJson.accumulate("status_text",  "En attente de validation");
    	        userDashBoardJson.accumulate("id_status_crm", 1);
    	        userDashBoardJson.accumulate("message", t.getHtml());
    	        notificationJson.accumulate("user_dashboard", userDashBoardJson);
            }
            
            if(bIsNotifyByEmail)
            {
            	//user_email
    	        JSONObject userEmailJson = new JSONObject();
    	        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(), locale, modelMessageContent );
    	        userEmailJson.accumulate("sender_name", "Mairie de Paris");
    	        userEmailJson.accumulate("sender_email", MailService.getNoReplyEmail(  ));
    	        userEmailJson.accumulate("recipient", _notifyGruService.getEmail( config, 0,0 ));
    	        userEmailJson.accumulate("subject", config.getSubjectEmail());
    	        userEmailJson.accumulate("message", t.getHtml());
    	        userEmailJson.accumulate("cc", "");
    	        userEmailJson.accumulate("cci", "");
    	        notificationJson.accumulate("tab_user_email", userEmailJson);
            	
            }
            if(bIsNotifyBySms)
            {
            	//user_sms
    	        JSONObject smsJson = new JSONObject();
    	        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(), locale, modelMessageContent );
    	        smsJson.accumulate("phone_number", _notifyGruService.getSMSPhoneNumber( config, 0,0 ));
    	        smsJson.accumulate("message", t.getHtml());
    	        notificationJson.accumulate("user_sms", smsJson);
            }
            if(bIsNotifyByViewAgent)
            {
            	//backoffice_logging
    	        JSONObject backOfficeLogginJson = new JSONObject();
    	        HtmlTemplate tViewAgent = AppTemplateService.getTemplateFromStringFtl( config.getMessageAgent(), locale, modelMessageContent );
    	        backOfficeLogginJson.accumulate("message", tViewAgent.getHtml());
    	        backOfficeLogginJson.accumulate("status_text", "En attente de validation");
    	        backOfficeLogginJson.accumulate("id_status_crm", 1);
    	        
    	        if(bIsNotifyByDesk)
    	        {
    	        	HtmlTemplate tDashboard = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(), locale, modelMessageContent );
    	        	backOfficeLogginJson.accumulate("notified_on_dashboard", 1);
        	        backOfficeLogginJson.accumulate("display_level_dashboard_notification", 2);
        	        backOfficeLogginJson.accumulate("view_dashboard_notification", "Message : " + tDashboard.getHtml());
    	        }
    	        if(bIsNotifyByEmail)
                {
    	        	HtmlTemplate tEmail = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(), locale, modelMessageContent );
    	        	backOfficeLogginJson.accumulate("notified_by_email", 1);
        	        backOfficeLogginJson.accumulate("display_level_email_notification", 2);
        	        backOfficeLogginJson.accumulate("view_email_notification", "Email envoyé à l'adresse : " + _notifyGruService.getEmail( config, 0,0 ) +
        	        		" – Objet : "+ config.getSubjectEmail() +" _ Message : " + tEmail.getHtml());
                }
    	        if(bIsNotifyBySms)
    	        {
    	        	HtmlTemplate tSms = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(), locale, modelMessageContent );
        	        backOfficeLogginJson.accumulate("notified_by_sms", 1);
        	        backOfficeLogginJson.accumulate("display_level_sms_notification", 1);
        	        backOfficeLogginJson.accumulate("view_sms_notification", "SMS envoyé au numéro : " + _notifyGruService.getSMSPhoneNumber( config, 0,0 ) +
        	        		" _ Message : " + tSms.getHtml());
    	        }
    	        
    	        notificationJson.accumulate("backoffice_logging", backOfficeLogginJson);
            	
            }
            //notification
	       
	        fluxJson.accumulate("notification", notificationJson);
	
	        String strJsontoESB = fluxJson.toString(2);
	
	        try {
	
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost postRequest = new HttpPost(
	                    "http://localhost:8080");
	
	            StringEntity input = new StringEntity(strJsontoESB);
	            input.setContentType("application/json");
	            postRequest.setEntity(input);
	
	            HttpResponse response = httpClient.execute(postRequest);
	
	            if (response.getStatusLine().getStatusCode() != 201) {
	                throw new RuntimeException("Failed : HTTP error code : "
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

            return new String("TACHE GRU CONFIGURER");
        }

        return new String("TACHE GRU CONFIGURER");
    }
}
