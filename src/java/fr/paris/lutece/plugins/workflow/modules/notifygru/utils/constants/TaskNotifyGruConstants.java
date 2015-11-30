package fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants;

public class TaskNotifyGruConstants {

	public static final String BEAN_MOOC1 = "workflow-notifygru.mooc1";
	
	//MARKERS NOTIFICATION
	public static final String MARK_NOTIFICATION="notification";
	public static final String MARK_RESOURCE = "resource";
	public static final String MARK_USER_GUID= "user_guid";
	public static final String MARK_USER_EMAIL= "user_email";
	public static final String MARK_NOTIFICATION_ID= "notification_id";
	public static final String MARK_NOTIFICATION_DATE= "notification_date";
	public static final String MARK_NOTIFICATION_TYPE= "notification_type";
	public static final String MARK_ID_DEMAND= "id_demand";
	public static final String MARK_ID_DEMAND_TYPE= "id_demand_type";
	public static final String MARK_DEMAND_MAX_STEP= "demand_max_step";
	public static final String MARK_DEMAND_USER_CURRENT_STEP= "demand_user_current_step";
	
	//MARKERS USERDASHBOARD
	public static final String MARK_STATUS_TEXT_USERDASHBOARD = "status_text";
	public static final String MARK_ID_STATUS_CRM_USERDASHBOARD = "id_status_crm";
	public static final String MARK_MESSAGE_USERDASHBOARD = "message";
	public static final String MARK_USER_DASHBOARD = "user_dashboard";
	
	//MARKERS USER_EMAIL
	public static final String MARK_SENDER_NAME = "sender_name";
	public static final String MARK_SENDER_EMAIL = "sender_email";
	public static final String MARK_RECIPIENT = "recipient";
	public static final String MARK_SUBJECT = "subject";
	public static final String MARK_MESSAGE_EMAIL = "message";
	public static final String MARK_CC = "cc";
	public static final String MARK_CCI = "cci";
	public static final String MARK_TAB_USER_MAIL = "tab_user_mail";
	
	//MARKERS SMS
	public static final String MARK_PHONE_NUMBER = "phone number";
	public static final String MARK_MESSAGE_SMS = "message";
	public static final String MARK_USER_SMS = "user_sms";
	
	//MARKERS BACK OFFICE LOGGING
	public static final String MARK_MESSAGE_BACK_OFFICE_LOGGING = "message";
	public static final String MARK_STATUS_TEXT_BACK_OFFICE_LOGGING = "status_text";
	public static final String MARK_ID_STATUS_CRM_BACK_OFFICE_LOGGING = "id_status_crm";
	public static final String MARK_NOTIFIED_ON_DASHBOARD = "notified_on_dashboard";
	public static final String MARK_DISPLAY_LEVEL_DASHBOARD_NOTIFICATION = "display_level_dashboard_notification";
	public static final String MARK_VIEW_DASHBOARD_NOTIFICATION = "view_dashboard_notification";
	public static final String MARK_DISPLAY_MESSAGE = "Message:";
	public static final String MARK_NOTIFIED_BY_EMAIL = "notified_by_email";
	public static final String MARK_DISPLAY_LEVEL_EMAIL_NOTIFICATION = "display_level_email_notification";
	public static final String MARK_VIEW_EMAIL_NOTIFICATION = "view_email_notification";
	public static final String MARK_NOTIFIED_BY_SMS="notified by sms";
	public static final String MARK_DISPLAY_LEVEL_SMS_NOTIFICATION="display_level_sms_notification";
	public static final String MARK_VIEW_SMS_NOTIFICATION="view_sms_notification";
	public static final String MARK_BACK_OFFICE_LOGGING="backoffice_logging";
	
	//MESSAGES
	public static final String MESSAGE_DISPLAY_EMAIL="Email envoyé à l'adresse : ";
	public static final String MESSAGE_DISPLAY_OBJECT="_Objet :";
	public static final String MESSAGE_DISPLAY_MESSAGE_EMAIL=" _ Message : ";
	public static final String MESSAGE_DISPLAY_SMS="SMS envoyé au numéro : ";
	public static final String MESSAGE_DISPLAY_MESSAGE_SMS=" _ Message : ";
	
	//URL
	public static final String URL_ESB= "http://localhost:8080";
	
	//CONTENT FORMAT
	public static final String CONTENT_FORMAT= "application/json";
	
	//HTTP ERROR MESSAGE
	public static final String ERROR_MESSAGE="Failed : HTTP error code : ";
}
