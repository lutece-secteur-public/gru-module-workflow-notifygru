/*
 * Copyright (c) 2002-2020, City of Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants;

/**
 *
 */
public final class Constants
{
    // MARKERS NOTIFICATION
    public static final String MARK_NOTIFICATION = "notification";
    public static final String MARK_RESOURCE = "resource";
    public static final String MARK_USER_GUID = "user_guid";
    public static final String MARK_EMAIL = "email";
    public static final String MARK_CRM_STATUS_ID = "crm_status_id";
    public static final String MARK_NOTIFICATION_ID = "notification_id";
    public static final String MARK_NOTIFICATION_DATE = "notification_date";
    public static final String MARK_NOTIFICATION_TYPE = "notification_type";
    public static final String MARK_ID_DEMAND = "demand_id";
    public static final String MARK_REMOTE_ID_DEMAND = "remote_demand_id";
    public static final String MARK_ID_DEMAND_TYPE = "demand_type_id";
    public static final String MARK_DEMAND_MAX_STEP = "demand_max_step";
    public static final String MARK_DEMAND_USER_CURRENT_STEP = "demand_user_current_step";
    public static final String MARK_DEMAND_STATUS = "demand_status";

    // MARKERS USERDASHBOARD
    public static final String MARK_STATUS_TEXT_USERDASHBOARD = "status_text";
    public static final String MARK_SENDER_NAME_USERDASHBOARD = "sender_name";
    public static final String MARK_SUBJECT_USERDASHBOARD = "subject";
    public static final String MARK_MESSAGE_USERDASHBOARD = "message";
    public static final String MARK_DATA_USERDASHBOARD = "data";
    public static final String MARK_USER_DASHBOARD = "user_dashboard";

    // MARKERS USER_EMAIL
    public static final String MARK_SENDER_NAME = "sender_name";
    public static final String MARK_SENDER_EMAIL = "sender_email";
    public static final String MARK_RECIPIENT = "recipient";
    public static final String MARK_SUBJECT = "subject";
    public static final String MARK_MESSAGE_EMAIL = "message";
    public static final String MARK_CC = "cc";
    public static final String MARK_CCI = "cci";
    public static final String MARK_USER_MAIL = "user_email";
    public static final String MARK_REFERENCE_DEMAND = "demand_reference";
    public static final String MARK_DEMAND_DATE_TIMESTAMP = "notification_date";
    public static final String MARK_COSTUMER_ID = "customer_id";

    // MARKERS SMS
    public static final String MARK_MESSAGE_SMS = "message";
    public static final String MARK_USER_SMS = "user_sms";

    // MARKERS BACK OFFICE LOGGING
    public static final String MARK_MESSAGE_BACK_OFFICE_LOGGING = "message";
    public static final String MARK_STATUS_TEXT_BACK_OFFICE_LOGGING = "status_text";
    public static final String MARK_ID_STATUS_CRM_BACK_OFFICE_LOGGING = "crm_status_id";
    public static final String MARK_NOTIFIED_ON_DASHBOARD = "notified_on_dashboard";
    public static final String MARK_DISPLAY_LEVEL_DASHBOARD_NOTIFICATION = "display_level_dashboard_notification";
    public static final String MARK_VIEW_DASHBOARD_NOTIFICATION = "view_dashboard_notification";
    public static final String MARK_DISPLAY_MESSAGE = "Message: ";
    public static final String MARK_NOTIFIED_BY_EMAIL = "notified_by_email";
    public static final String MARK_DISPLAY_LEVEL_EMAIL_NOTIFICATION = "display_level_email_notification";
    public static final String MARK_VIEW_EMAIL_NOTIFICATION = "view_email_notification";
    public static final String MARK_NOTIFIED_BY_SMS = "notified_by_sms";
    public static final String MARK_DISPLAY_LEVEL_SMS_NOTIFICATION = "display_level_sms_notification";
    public static final String MARK_VIEW_SMS_NOTIFICATION = "view_sms_notification";
    public static final String MARK_BACK_OFFICE_LOGGING = "backoffice_logging";

    // MESSAGES
    public static final String MESSAGE_DISPLAY_EMAIL = "Email envoyé à l'adresse : ";
    public static final String MESSAGE_DISPLAY_OBJECT = "_Objet :";
    public static final String MESSAGE_DISPLAY_MESSAGE_EMAIL = " _ Message : ";
    public static final String MESSAGE_DISPLAY_SMS = "SMS envoyé au numéro : ";
    public static final String MESSAGE_DISPLAY_MESSAGE_SMS = " _ Message : ";

    // CONSTANT FOR SENDING JSON FLUX
    public static final String BEAN_NOTIFICATION_SENDER = "workflow-notifygru.lib-notifygru.notificationService";
    public static final String TYPE_AUTHENTIFICATION = "Bearer";
    public static final String PARAMS_ACCES_TOKEN = "access_token";
    public static final String PARAMS_GRANT_TYPE = "grant_type";
    public static final String PARAMS_GRANT_TYPE_VALUE = "client_credentials";

    // CONTENT FORMAT
    public static final String CONTENT_FORMAT = "application/json; charset=utf-8";
    public static final String CONTENT_FORMAT_TOKEN = "application/x-www-form-urlencoded";

    // NUMBER
    public static final int INT_DEFAULT_VALUE = 0;

    // CONSTANTS
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";
    public static final String SPACE = " ";
    public static final String OPEN_BRACKET = "(";
    public static final String CLOSED_BRACKET = ")";
    public static final String HYPHEN = "-";
    public static final String SLASH = "/";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USER_AUTO = "auto";
    public static final String TASK_NOTIFY_GRU_KEY = "taskNotifyGru";

    // TITLE
    public static final String TITLE_NOTIFY = "module.workflow.notifygru.task_notify_title";

    // VIEW
    public static final String VIEW_GUICHET = "module.workflow.notifygru.manage_guichet.title";
    public static final String VIEW_AGENT = "module.workflow.notifygru.manage_agent_view.title";
    public static final String VIEW_EMAIL = "module.workflow.notifygru.manage_email.title";
    public static final String VIEW_SMS = "module.workflow.notifygru.manage_sms.title";
    public static final String VIEW_BROADCAST_LIST = "module.workflow.notifygru.manage_mailing_list.title";

    // VISIBILITY
    public static final String VISIBILITY_ALL = "module.workflow.notifygru.task_notify_gru_config.visibility_all";
    public static final String VISIBILITY_DOMAIN = "module.workflow.notifygru.task_notify_gru_config.visibility_domain";
    public static final String VISIBILITY_ADMIN = "module.workflow.notifygru.task_notify_gru_config.visibility_admin";

    // MESSAGES
    public static final String MESSAGE_MANDATORY_ONGLET = "module.workflow.notifygru.message.mandatory.onglet";
    public static final String MESSAGE_MANDATORY_PROVIDER = "module.workflow.notifygru.message.mandatory.provider";
    public static final String MESSAGE_EQUAL_FIELD = "module.workflow.notifygru.message.equal.field";
    public static final String MESSAGE_ERROR_VALIDATION = "module.workflow.notifygru.message.error_validation";
    public static final String MESSAGE_ERROR_FREEMARKER = "module.workflow.notifygru.message.error_freemarker";
    public static final String MESSAGE_ERROR_PROVIDER_NOT_FOUND = "module.workflow.notifygru.message.provider.notFound";

    // MANDATORY MESSAGE
    public static final String MESSAGE_MANDATORY_ONE_FIELD = "module.workflow.notifygru.message.mandatory.one.field";
    public static final String MESSAGE_MANDATORY_TWO_FIELD = "module.workflow.notifygru.message.mandatory.two.field";
    public static final String MESSAGE_MANDATORY_THREE_FIELD = "module.workflow.notifygru.message.mandatory.three.field";

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
    public static final String PROPERTY_GRU_ONGLET_ACTIVE = "number";

    // MARKS GUICHET
    public static final String MARK_TAB_GUICHET = "guichet";
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

    // MARKS AGENT
    public static final String MARK_TAB_AGENT = "agent";

    // MARKS EMAIL
    public static final String MARK_TAB_EMAIL = "email";
    public static final String MARK_LIST_ID_RESOURCE_EMAIL = "list_id_resource_email";
    public static final String MARK_ID_RESOURCE_EMAIL = "id_resource_email";
    public static final String MARK_LIST_ID_DEMAND_EMAIL = "list_id_record_email";
    public static final String MARK_ID_DEMAND_EMAIL = "id_record_email";
    public static final String MARK_LIST_USER_GUID_EMAIL = "list_record_user_guid_email";
    public static final String MARK_USER_GUID_EMAIL = "record_user_guid_email";
    public static final String MARK_SUBJECT_EMAIL = "subject_email";
    public static final String MARK_ENTRY_EMAIL = "entry_email";
    public static final String MARK_SENDER_NAME_EMAIL = "sender_name_email";
    public static final String MARK_RECIPIENT_EMAIL = "recipients_email";
    public static final String MARK_RECIPIENT_CC_EMAIL = "recipients_cc_email";
    public static final String MARK_RECIPIENT_CCI_EMAIL = "recipients_cci_email";
    public static final String MARK_LEVEL_NOTIFICATION_EMAIL = "level_notification_email";
    public static final String MARK_IS_ACTIVE_ONGLET_EMAIL = "is_active_onglet_email";

    // MARKS SMS
    public static final String MARK_TAB_SMS = "sms";
    public static final String MARK_LIST_ID_RESOURCE_SMS = "list_id_resource_sms";
    public static final String MARK_ID_RESOURCE_SMS = "id_resource_sms";
    public static final String MARK_LIST_ID_DEMAND_SMS = "list_id_record_sms";
    public static final String MARK_ID_DEMAND_SMS = "id_record_sms";
    public static final String MARK_LIST_USER_GUID_SMS = "list_record_user_guid_sms";
    public static final String MARK_USER_GUID_SMS = "record_user_guid_sms";
    public static final String MARK_IS_NOTIFIED_BY_SMS = "is_notified_by_sms";
    public static final String MARK_SUBJECT_SMS = "subject_sms";
    public static final String MARK_ENTRY_SMS = "entry_sms";
    public static final String MARK_SENDER_NAME_SMS = "sender_name_sms";
    public static final String MARK_RECIPIENT_SMS = "recipient_sms";
    public static final String MARK_RECIPIENT_CC_SMS = "recipient_cc_sms";
    public static final String MARK_RECIPIENT_CCI_SMS = "recipient_cci_sms";
    public static final String MARK_LEVEL_NOTIFICATION_SMS = "level_notification_sms";
    public static final String MARK_IS_ACTIVE_ONGLET_SMS = "is_active_onglet_sms";

    // MARKS MAILING LIST
    public static final String MARK_TAB_BROADCAST = "broadcast";
    public static final String MARK_LEVEL_NOTIFICATION_AGENT = "level_notification_agent";
    public static final String MARK_IS_ACTIVE_ONGLET_AGENT = "is_active_onglet_agent";
    public static final String MARK_LEVEL_NOTIFICATION_BROADCAST = "level_notification_broadcast";

    // PARAMETERS GUICHET
    public static final String PARAMETER_CRM_STATUS_ID = "crm_status_id";

    // PARAMETERS ONGLET
    public static final String PARAMETER_SELECT_PROVIDER = "list_provider";
    public static final String PARAMETER_MARKER_PROVIDERS = "marker_providers";
    public static final String PARAMETER_DEMAND_STATUS = "demand_status";
    public static final String PARAMETER_BILLING_ACCOUNT_SMS = "billing_account_sms";
    public static final String PARAMETER_BILLING_GROUP_SMS = "billing_group_sms";

    // AUTHER MARKS
    public static final String MARK_POSITION = "position_";
    public static final String MARK_GRU_TITLE = "directory_title";
    public static final String MARK_GRU_DESCRIPTION = "directory_description";
    public static final String MARK_LINK = "link";
    public static final String MARK_DEFAULT_SENDER_NAME = "default_sender_name";
    public static final String MARK_LIST_ENTRIES_EMAIL_SMS = "list_entries_email_sms";
    public static final String MARK_GRU_LIST = "list_ressources";
    public static final String MARK_GRU_LIST_RESSSOURCE_DEMANDES = "list_entries_id_demand";
    public static final String MARK_GRU_LIST_RESSSOURCE_EMAIL = "list_entries_email";
    public static final String MARK_GRU_LIST_CRM_WEBAPP = "list_entries_crm_web_app_code";
    public static final String MARK_CONFIG = "config";
    public static final String MARK_STATE_LIST = "list_state";
    public static final String MARK_LIST_ENTRIES_FREEMARKER = "list_entries_freemarker";
    public static final String MARK_IS_USER_ATTRIBUTE_WS_ACTIVE = "is_user_attribute_ws_active";
    public static final String MARK_LIST_ENTRIES_USER_GUID = "list_entries_user_guid";
    public static final String MARK_MESSAGE_VALIDATION = "message_validation_success";
    public static final String MARK_MAILING_LIST = "mailing_list";
    public static final String MARK_PLUGIN_WORKFLOW = "plugin_workflow";
    public static final String MARK_TASKS_LIST = "tasks_list";
    public static final String MARK_TASK = "task_";
    public static final String MARK_FIRST_NAME = "first_name";
    public static final String MARK_LAST_NAME = "last_name";
    public static final String MARK_PHONE_NUMBER = "phone_number";
    public static final String MARK_LINK_VIEW_RECORD = "link_view_record";
    public static final String MARK_LIST_POSITION_ENTRY_FILE_CHECKED = "list_position_entry_file_checked";
    public static final String MARK_LIST_ENTRIES_FILE = "list_entries_file";
    public static final String MARK_NOTIFYGRU_MARKERS = "notifygru_markers";
    public static final String MARK_MESSAGES_ERROR = "errors";

    // PROPERTIES
    public static final String PROPERTY_IS_BILLING_ACCOUNT_SMS_NEEDED = "workflow-notifygru.sms.billingAccountNeeded";

    // TAGS
    public static final String TAG_A = "a";

    // ATTRIBUTES
    public static final String ATTRIBUTE_HREF = "href";

    /**
     * Constructor
     */
    private Constants( )
    {

    }
}
