package fr.paris.lutece.plugins.workflow.modules.notifygru.service;



import fr.paris.lutece.plugins.workflow.modules.notifygru.business.BackofficeLogging;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotificationFlux;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.UserDashboard;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.UserEmail;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.UserSms;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;


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
    private static final String TEMPLATE_TASK_NOTIFY_DESK = "admin/plugins/workflow/modules/notifygru/task_notify_gru_desk.html";
    private static final String TEMPLATE_TASK_NOTIFY_AGENT = "admin/plugins/workflow/modules/notifygru/task_notify_gru_agent.html";
    private static final String TEMPLATE_TASK_NOTIFY_MAIL = "admin/plugins/workflow/modules/notifygru/task_notify_gru_mail.html";
    private static final String TEMPLATE_TASK_NOTIFY_SMS = "admin/plugins/workflow/modules/notifygru/task_notify_gru_sms.html";

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
        ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey(nIdResourceHistory);
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey(this.getId());
        
        if ( ( config != null ) )
        {
        	Map<String, Object> modelMessageContent = _notifyGruService.fillModelMoke( );
            
            Map<String, Object> model = new HashMap<String, Object>();

            model.put(NotifyGruConstants.MARK_CONFIG, config);
            
        	NotificationFlux notificationFlux = new NotificationFlux();
        	
        	notificationFlux.setUserGuid(_notifyGruService.getUserGuid(config, 0, 0));
        	notificationFlux.setLabelUserEmail(_notifyGruService.getEmail( config, 0,0 ));
        	
         	boolean bIsNotifyByDesk = config.isActiveOngletGuichet() ;
         	boolean bIsNotifyByViewAgent = config.isActiveOngletAgent() ;
            boolean bIsNotifyByEmail = config.isActiveOngletEmail() ;
            boolean bIsNotifyBySms = config.isActiveOngletSMS() ;
            if(bIsNotifyByDesk)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(), locale, modelMessageContent );
            	UserDashboard userDashboard = new UserDashboard();
            	userDashboard.setStatus(config.getStatusTextGuichet());
            	userDashboard.setMessage(t.getHtml());
            	notificationFlux.setUserDashboard(userDashboard);
            }
            if(bIsNotifyByViewAgent)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageAgent(), locale, modelMessageContent );
            	BackofficeLogging backofficeLogging = new BackofficeLogging();
            	backofficeLogging.setMessage(t.getHtml());
            	notificationFlux.setBackofficeLogging(backofficeLogging);
            }
            if(bIsNotifyByEmail)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(), locale, modelMessageContent );
            	UserEmail userEmail = new UserEmail();
            	userEmail.setSenderName(config.getSenderNameEmail());
            	userEmail.setSenderEmail(MailService.getNoReplyEmail(  ));
            	userEmail.setSubject(config.getSubjectEmail());
            	userEmail.setMessage(t.getHtml());
            	notificationFlux.setUserEmail(userEmail);
            }
            if(bIsNotifyBySms)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(), locale, modelMessageContent );
            	UserSms userSms = new UserSms();
            	userSms.setPhoneNumber(_notifyGruService.getSMSPhoneNumber( config, 0,0 ));
            	userSms.setMessage(t.getHtml());
            	notificationFlux.setUserSms(userSms);
            }
        }  

        JSONObject fluxJson = new JSONObject();

        //user_dashboard
        JSONObject userDashBoardJson = new JSONObject();
        userDashBoardJson.accumulate("status_text", "En attente de validation");
        userDashBoardJson.accumulate("id_status_crm", 1);
        userDashBoardJson.accumulate("data", "");

        //user_email
        JSONObject userEmailJson = new JSONObject();
        userEmailJson.accumulate("sender_name", "Mairie de Paris");
        userEmailJson.accumulate("sender_email", "no_reply@paris.fr");
        userEmailJson.accumulate("recipient", "john.doe@somewhere.com");
        userEmailJson.accumulate("subject", "[Mairie de Paris] Demande de carte de stationnement");
        userEmailJson.accumulate("message", "Bonjour Monsieur John Doe, Votre demande de carte de stationnement "
                + "est en attente de validation. ...");
        userEmailJson.accumulate("cc", "");
        userEmailJson.accumulate("cci", "");

        //user_email
        JSONObject smsJson = new JSONObject();
        smsJson.accumulate("phone_number", "0680125345");
        smsJson.accumulate("message", "Votre demande de carte de stationnement est en attente de validation");

        //backoffice_logging
        JSONObject backOfficeLogginJson = new JSONObject();
        backOfficeLogginJson.accumulate("message", "raitement de la demande en cours par le service de la DVD");
        backOfficeLogginJson.accumulate("status_text", "En attente de validation");
        backOfficeLogginJson.accumulate("id_status_crm", 1);
        backOfficeLogginJson.accumulate("notified_on_dashboard", 1);
        backOfficeLogginJson.accumulate("display_level_dashboard_notification", 2);
        backOfficeLogginJson.accumulate("view_dashboard_notification", "");
        backOfficeLogginJson.accumulate("notified_by_email", 1);
        backOfficeLogginJson.accumulate("display_level_email_notification", 2);
        backOfficeLogginJson.accumulate("view_email_notification", "Email envoyé à l'adresse : john.doe@somewhere.com – "
                + "Objet : ... _ Message : ..");
        backOfficeLogginJson.accumulate("notified_by_sms", 1);
        backOfficeLogginJson.accumulate("display_level_sms_notification", 1);
        backOfficeLogginJson.accumulate("view_sms_notification", "SMS envoyé au numéro 0680125345 _ Message : "
                + "Votre demande de carte de stationnement est en attente de validation.");

        //notification
        JSONObject notificationJson = new JSONObject();
        notificationJson.accumulate("user_guid", 12312);
        notificationJson.accumulate("user_email", "john.doe@somewhere.com");
        notificationJson.accumulate("notification_id", 56454);
        notificationJson.accumulate("notification_date", 312123212);
        notificationJson.accumulate("notification_type", "TYPE");
        notificationJson.accumulate("id_demand", 1108);
        notificationJson.accumulate("id_demand_type", 14);
        notificationJson.accumulate("demand_max_step", 5);
        notificationJson.accumulate("demand_user_current_step", 3);
        notificationJson.accumulate("user_dashboard", userDashBoardJson);
        notificationJson.accumulate("user_email", userEmailJson);
        notificationJson.accumulate("user_sms", smsJson);
        notificationJson.accumulate("backoffice_logging", backOfficeLogginJson);

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
