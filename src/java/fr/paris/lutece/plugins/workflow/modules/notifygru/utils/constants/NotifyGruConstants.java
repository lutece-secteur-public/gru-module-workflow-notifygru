package fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants;

/**
 *
 * NotifyGruConstants
 *
 */
public final class NotifyGruConstants {

    // CONSTANTS
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String OPEN_BRACKET = "(";
    public static final String CLOSED_BRACKET = ")";
    public static final String HYPHEN = "-";
    public static final String SLASH = "/";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USER_AUTO = "auto";
    public static final String TASK_NOTIFY_GRU_KEY = "taskNotifyGru";

    // FIELDS
    public static final String FIELD_NOTIFY = "module.workflow.notifygru.task_notify_gru_config.label_notify_by";
    public static final String FIELD_SUBJECT = "module.workflow.notifygru.task_notify_gru_config.label_subject";
    public static final String FIELD_MESSAGE = "module.workflow.notifygru.task_notify_gru_config.label_message";
    public static final String FIELD_SENDER_NAME = "module.workflow.notifygru.task_notify_gru_config.label_sender_name";
    public static final String FIELD_TASK_RESSOURCE_GRU = "module.workflow.notifygru.task_notify_gru_config.label_task_ressource";
    public static final String FIELD_TASK_ENTRY_GRU_SMS = "module.workflow.notifygru.task_notify_gru_config.label_task_entry_gru_sms";
    public static final String FIELD_TASK_ENTRY_GRU_EMAIL = "module.workflow.notifygru.task_notify_gru_config.label_task_entry_gru_email";
    public static final String FIELD_TASK_ENTRY_GRU_USER_GUID = "module.workflow.notifygru.task_notify_gru_config.label_task_entry_gru_user_guid";
    public static final String FIELD_MAILING_LIST = "module.workflow.notifygru.task_notify_gru_config.label_mailing_list";
    public static final String FIELD_STATE = "module.workflow.notifygru.task_notify_gru_config.label_state";
    public static final String FIELD_MESSAGE_VALIDATION = "module.workflow.notifygru.task_notify_gru_config.label_message_validation";
    public static final String FIELD_LABEL_LINK = "module.workflow.notifygru.task_notify_gru_config.label_label_link";
    public static final String FIELD_LABEL_LINK_VIEW_RECORD = "module.workflow.notifygru.task_notify_gru_config.label_label_link_view_record";
    public static final String FIELD_LABEL_PERIOD_VALIDITY = "module.workflow.notifygru.task_notify_gru_config.label_period_validity";

    //VIEW
    public static final String VIEW_GUICHET = "vue guichet";
    public static final String VIEW_AGENT = "vue agent";
    public static final String VIEW_EMAIL = "vue_email";
    public static final String VIEW_SMS = "vue sms";
    public static final String VIEW_BROADCAST_LIST="liste de diffusion";
    
    //VISIBILITY
    public static final String VISIBILITY_ALL = "visible par tout le monde";
    public static final String VISIBILITY_DOMAIN = "visible par domaine";
    public static final String VISIBILITY_ADMIN = "visible par admin";
    
    // MESSAGES
    public static final String MESSAGE_MANDATORY_FIELD = "module.workflow.notifygru.message.mandatory.field";
    public static final String MESSAGE_MANDATORY_ONGLET = "module.workflow.notifygru.message.mandatory.onglet";
    public static final String MESSAGE_EQUAL_FIELD = "module.workflow.notifygru.message.equal.field";
    public static final String MESSAGE_ERROR_VALIDATION = "module.workflow.notifygru.message.error_validation";

    // PROPERTIES
    public static final String PROPERTY_ACCEPTED_GRU_ENTRY_TYPE_EMAIL_SMS = "workflow-notifygru.acceptedDirectoryEntryTypesEmailSMS";
    public static final String PROPERTY_ACCEPTED_GRU_ENTRY_TYPE_USER_GUID = "workflow-notifygru.acceptedDirectoryEntryTypesUserGuid";
    public static final String PROPERTY_REFUSED_GRU_ENTRY_TYPE_USER_GUID = "workflow-notifygru.refusedDirectoryEntryTypes";
    public static final String PROPERTY_ACCEPTED_GRU_ENTRY_TYPE_FILE = "workflow-notifygru.acceptedDirectoryEntryTypesFile";
    public static final String PROPERTY_NOTIFY_MAIL_DEFAULT_SENDER_NAME = "workflow-notifygru.notification_mail.default_sender_name";
    public static final String PROPERTY_SERVER_SMS = "workflow-notifygru.email_server_sms";
    public static final String PROPERTY_XPAGE_PAGETITLE = "module.workflow.notifygru.xpage.pagetitle";
    public static final String PROPERTY_XPAGE_PATHLABEL = "module.workflow.notifygru.xpage.pathlabel";
    public static final String PROPERTY_LUTECE_ADMIN_PROD_URL = "lutece.admin.prod.url";
    public static final String PROPERTY_LUTECE_BASE_URL = "lutece.base.url";
    public static final String PROPERTY_LUTECE_PROD_URL = "lutece.prod.url";

    // MARKS GUICHET
    public static final String MARK_ONGLET_GUICHET = "guichet";
    public static final String MARK_LIST_ID_RESOURCE_GUICHET = "list_id_resource_guichet";
    public static final String MARK_ID_RESOURCE_GUICHET = "id_resource_guichet";
    public static final String MARK_LIST_ID_DEMAND_GUICHET = "list_id_demand_guichet";
    public static final String MARK_ID_DEMAND_GUICHET = "id_demand_guichet";
    public static final String MARK_LIST_USER_GUID_GUICHET = "list_user_guid_guichet";
    public static final String MARK_USER_GUID_GUICHET = "user_guid_guichet";
    public static final String MARK_LIST_CRM_WEBAPP_CODE_GUICHET = "list_crm_web_app_code_guichet";
    public static final String MARK_CRM_WEBAPP_CODE_GUICHET = "crm_web_app_code_guichet";
    public static final String MARK_SEND_NOTIFICATION_GUICHET = "send_notification_guichet";
    public static final String MARK_STATUS_TEXT_GUICHET = "status_text_guichet";
    public static final String MARK_SUBJECT_GUICHET = "subject_guichet";
    public static final String MARK_MESSAGE_GUICHET = "message_guichet";
    public static final String MARK_SENDER_NAME_GUICHET = "sender_name_guichet";
    public static final String MARK_LEVEL_NOTIFICATION_GUICHET = "level_notification_guichet";
    public static final String MARK_IS_ACTIVE_ONGLET_GUICHET = "is_active_onglet_guichet";
    
    //MARKS AGENT
    public static final String MARK_ONGLET_AGENT = "agent";
    
    
    // MARKS EMAIL
    public static final String MARK_ONGLET_EMAIL = "email";
    public static final String MARK_LIST_ID_RESOURCE_EMAIL = "list_id_resource_email";
    public static final String MARK_ID_RESOURCE_EMAIL = "id_resource_email";
    public static final String MARK_LIST_ID_DEMAND_EMAIL = "list_id_record_email";
    public static final String MARK_ID_DEMAND_EMAIL = "id_record_email";
    public static final String MARK_LIST_USER_GUID_EMAIL = "list_record_user_guid_email";
    public static final String MARK_USER_GUID_EMAIL = "record_user_guid_email";
   
    public static final String MARK_SUBJECT_EMAIL = "subject_email";
    public static final String MARK_ENTRY_EMAIL = "entry_email";
    public static final String MARK_MESSAGE_EMAIL = "message_email";
    public static final String MARK_SENDER_NAME_EMAIL = "sender_name_email";
    public static final String MARK_RECIPIENT_EMAIL = "recipients_email";
    public static final String MARK_RECIPIENT_CC_EMAIL = "recipients_cc_email";
    public static final String MARK_RECIPIENT_CCI_EMAIL = "recipients_cci_email";
    public static final String MARK_LEVEL_NOTIFICATION_EMAIL = "level_notification_email";
    public static final String MARK_IS_ACTIVE_ONGLET_EMAIL = "is_active_onglet_email";
    
    // MARKS SMS
    public static final String MARK_ONGLET_SMS = "sms";
    public static final String MARK_LIST_ID_RESOURCE_SMS = "list_id_resource_sms";
    public static final String MARK_ID_RESOURCE_SMS = "id_resource_sms";
    public static final String MARK_LIST_ID_DEMAND_SMS = "list_id_record_sms";
    public static final String MARK_ID_DEMAND_SMS = "id_record_sms";
    public static final String MARK_LIST_USER_GUID_SMS = "list_record_user_guid_sms";
    public static final String MARK_USER_GUID_SMS = "record_user_guid_sms";
    public static final String MARK_IS_NOTIFIED_BY_SMS = "is_notified_by_sms";
    public static final String MARK_SUBJECT_SMS = "subject_sms";
    public static final String MARK_ENTRY_SMS = "entry_sms";
    public static final String MARK_MESSAGE_SMS = "message_sms";
    public static final String MARK_SENDER_NAME_SMS = "sender_name_sms";
    public static final String MARK_RECIPIENT_SMS = "recipient_sms";
    public static final String MARK_RECIPIENT_CC_SMS = "recipient_cc_sms";
    public static final String MARK_RECIPIENT_CCI_SMS = "recipient_cci_sms";
    public static final String MARK_LEVEL_NOTIFICATION_SMS = "level_notification_sms";
    public static final String MARK_IS_ACTIVE_ONGLET_SMS = "is_active_onglet_sms";
    
    //MARKS MAILING LIST
   public static final String MARK_ONGLET_LIST="liste";
    
     public static final String MARK_LEVEL_NOTIFICATION_AGENT = "level_notification_agent";
    public static final String MARK_IS_ACTIVE_ONGLET_AGENT = "is_active_onglet_agent";
    
      public static final String MARK_LEVEL_NOTIFICATION_BROADCAST = "level_notification_broadcast";

      //BUTTON ADD & REMOVE
      public static final String PARAMETER_BUTTON_ADD = "AddOnglet";
      public static final String PARAMETER_BUTTON_REMOVE = "RemoveOnglet";
      
    // PARAMETERS GUICHET
    public static final String PARAMETER_ID_RESOURCE = "id_ressource";
    public static final String PARAMETER_ID_USER_GUID = "id_user_guid";
    public static final String PARAMETER_APPY= "apply";
    
       

    public static final String PARAMETER_ID_DEMAND_GUICHET = "ressource_id_demand_guichet";
    public static final String PARAMETER_CRM_WEBAPP_CODE_GUICHET = "crm_web_app_code_guichet";
    public static final String PARAMETER_SEND_NOTIFICATION_GUICHET = "send_notification_guichet";
    public static final String PARAMETER_STATUS_TEXT_GUICHET = "status_text_guichet";
    public static final String PARAMETER_SUBJECT_GUICHET = "subject_guichet";
    public static final String PARAMETER_MESSAGE_GUICHET = "message_guichet";
    public static final String PARAMETER_SENDER_NAME_GUICHET = "sender_name_guichet";
    public static final String PARAMETER_LEVEL_NOTIFICATION_GUICHET = "level_notification_guichet";
    public static final String PARAMETER_ACTIVE_ONGLET_GUICHET = "active_onglet_guichet";
    // PARAMETERS AGENT
    public static final String PARAMETER_STATUS_TEXT_AGENT = "status_text_agent";
    public static final String PARAMETER_STATUS_MESSAGE_AGENT = "message_agent";
    public static final String PARAMETER_LEVEL_NOTIFICATION_AGENT = "level_notification_agent";
    public static final String PARAMETER_ACTIVE_ONGLET_AGENT = "active_onglet_agent";
    // PARAMETERS EMAIL
    public static final String PARAMETER_RESOURCE_RECORD_EMAIL = "resource_record_email";
    public static final String PARAMETER_SUBJECT_EMAIL = "subject_email";
    public static final String PARAMETER_ENTRY_EMAIL = "entry_email";
    public static final String PARAMETER_MESSAGE_EMAIL = "message_email";
    public static final String PARAMETER_SENDER_NAME_EMAIL = "sender_name_email";

    public static final String PARAMETER_RECIPIENT_CC_EMAIL = "recipients_cc_email";
    public static final String PARAMETER_RECIPIENT_CCI_EMAIL = "recipients_cci_email";  

    public static final String PARAMETER_LEVEL_NOTIFICATION_EMAIL = "level_notification_email";
    public static final String PARAMETER_ACTIVE_ONGLET_EMAIL = "active_onglet_email";
    // PARAMETERS SMS
    public static final String PARAMETER_RESOURCE_RECORD_SMS = "resource_record_sms";
    public static final String PARAMETER_PHONE_SMS = "phone_sms";
    public static final String PARAMETER_MESSAGE_SMS = "message_sms";
    public static final String PARAMETER_SENDER_NAME_SMS = "sender_name_sms";
    public static final String PARAMETER_RECIPIENT_SMS = "recipient_sms";
    public static final String PARAMETER_RECIPIENT_CC_SMS = "recipient_cc_sms";
    public static final String PARAMETER_RECIPIENT_CCI_SMS = "recipient_cci_sms";
    public static final String PARAMETER_LEVEL_NOTIFICATION_SMS = "level_notification_sms";
    public static final String PARAMETER_ACTIVE_ONGLET_SMS = "active_onglet_sms";
    
     // PARAMETERS Liste Diffusion
    public static final String PARAMETER_ID_MAILING_LIST= "id_mailing_list_broadcast";
      public static final String PARAMETER_SUBJECT_BROADCAST = "subject_broadcast";    
    public static final String PARAMETER_MESSAGE_BROADCAST = "message_broadcast";
    public static final String PARAMETER_SENDER_NAME_BROADCAST= "sender_name_broadcast"; 
    public static final String PARAMETER_RECIPIENT_CC_BROADCAST = "recipients_cc_broadcast";
    public static final String PARAMETER_RECIPIENT_CCI_BROADCAST = "recipients_cci_broadcast";   
    public static final String PARAMETER_ACTIVE_ONGLET_BROADCAST = "active_onglet_broadcast";
     public static final String PARAMETER_LEVEL_NOTIFICATION_BROADCAST = "level_notification_broadcast";
    
    
       // PARAMETERS ONGLET
     public static final String PARAMETER_ONGLET = "active_onglet";
     public static final String PARAMETER_ONGLE_ADD = "add_onglet";
     public static final String PARAMETER_ONGLE_REMOVE = "remove_onglet";

    // AUTHER MARKS
    public static final String MARK_POSITION = "position_";
    public static final String MARK_GRU_TITLE = "directory_title";
    public static final String MARK_GRU_DESCRIPTION = "directory_description";
    public static final String MARK_LINK = "link";
    public static final String MARK_DEFAULT_SENDER_NAME = "default_sender_name";
    public static final String MARK_LIST_ENTRIES_EMAIL_SMS = "list_entries_email_sms";
    public static final String MARK_LIST_ONGLET = "list_onglet";
    public static final String MARK_GRU_LIST = "list_ressources";    
    public static final String MARK_GRU_LIST_RESSSOURCE_DEMANDES = "list_entries_id_demand";
    public static final String MARK_GRU_LIST_RESSSOURCE_EMAIL = "list_entries_email";
    public static final String MARK_GRU_LIST_CRM_WEBAPP = "list_entries_crm_web_app_code";
    public static final String MARK_CONFIG = "config";
    public static final String MARK_STATE_LIST = "list_state";
    public static final String MARK_LIST_ENTRIES_FREEMARKER = "list_entries_freemarker";
    public static final String MARK_WEBAPP_URL = "webapp_url";
    public static final String MARK_LOCALE = "locale";
    public static final String MARK_IS_USER_ATTRIBUTE_WS_ACTIVE = "is_user_attribute_ws_active";
    public static final String MARK_LIST_ENTRIES_USER_GUID = "list_entries_user_guid";
    public static final String MARK_MESSAGE_VALIDATION = "message_validation_success";
    public static final String MARK_MAILING_LIST = "mailing_list";
    public static final String MARK_PLUGIN_WORKFLOW = "plugin_workflow";
    public static final String MARK_TASKS_LIST = "tasks_list";
    public static final String MARK_TASK = "task_";
    public static final String MARK_FIRST_NAME = "first_name";
    public static final String MARK_LAST_NAME = "last_name";
    public static final String MARK_EMAIL = "email";
    public static final String MARK_PHONE_NUMBER = "phone_number";
    public static final String MARK_LINK_VIEW_RECORD = "link_view_record";
    public static final String MARK_LIST_POSITION_ENTRY_FILE_CHECKED = "list_position_entry_file_checked";
    public static final String MARK_LIST_ENTRIES_FILE = "list_entries_file";

   



    // TAGS
    public static final String TAG_A = "a";

    // ATTRIBUTES
    public static final String ATTRIBUTE_HREF = "href";

    // JSP
    public static final String JSP_DO_VISUALISATION_RECORD = "jsp/admin/plugins/directory/DoVisualisationRecord.jsp";

    /**
     * Private constructor
     */
    private NotifyGruConstants() {
    }
}
