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
package fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants;
/**
 * 
 * @author 
 *
 */
public class TaskNotifyGruConstants
{
    public static final String BEAN_MOOC1 = "workflow-notifygru.mooc1";
    
    public static final int OPTIONAL_INT_VALUE = -1000;
    public static final String OPTIONAL_STRING_VALUE = "NOTHING";
    //MARKERS NOTIFICATION
    public static final String MARK_NOTIFICATION = "notification";
    public static final String MARK_RESOURCE = "resource";
    public static final String MARK_USER_GUID = "user_guid";
    public static final String MARK_EMAIL = "email";
    public static final String MARK_CRM_STATUS_ID = "crm_status_id";
    public static final String MARK_NOTIFICATION_ID = "notification_id";
    public static final String MARK_NOTIFICATION_DATE = "notification_date";
    public static final String MARK_NOTIFICATION_TYPE = "notification_type";
    public static final String MARK_ID_DEMAND = "demand_id";
    public static final String MARK_ID_DEMAND_TYPE = "demand_id_type";
    public static final String MARK_DEMAND_MAX_STEP = "demand_max_step";
    public static final String MARK_DEMAND_USER_CURRENT_STEP = "demand_user_current_step";
    public static final String MARK_DEMAND_STATE = "demand_state";

    //MARKERS USERDASHBOARD
    public static final String MARK_STATUS_TEXT_USERDASHBOARD = "status_text";
    public static final String MARK_SENDER_NAME_USERDASHBOARD = "sender_name";
    public static final String MARK_SUBJECT_USERDASHBOARD = "subject";
    public static final String MARK_MESSAGE_USERDASHBOARD = "message";
    public static final String MARK_DATA_USERDASHBOARD = "data";
    public static final String MARK_USER_DASHBOARD = "user_dashboard";

    //MARKERS USER_EMAIL
    public static final String MARK_SENDER_NAME = "sender_name";
    public static final String MARK_SENDER_EMAIL = "sender_email";
    public static final String MARK_RECIPIENT = "recipient";
    public static final String MARK_SUBJECT = "subject";
    public static final String MARK_MESSAGE_EMAIL = "message";
    public static final String MARK_CC = "cc";
    public static final String MARK_CCI = "cci";
    public static final String MARK_USER_MAIL = "user_email";

    //MARKERS SMS
    public static final String MARK_PHONE_NUMBER = "phone_number";
    public static final String MARK_MESSAGE_SMS = "message";
    public static final String MARK_USER_SMS = "user_sms";

    //MARKERS BACK OFFICE LOGGING
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

    //MESSAGES
    public static final String MESSAGE_DISPLAY_EMAIL = "Email envoyé à l'adresse : ";
    public static final String MESSAGE_DISPLAY_OBJECT = "_Objet :";
    public static final String MESSAGE_DISPLAY_MESSAGE_EMAIL = " _ Message : ";
    public static final String MESSAGE_DISPLAY_SMS = "SMS envoyé au numéro : ";
    public static final String MESSAGE_DISPLAY_MESSAGE_SMS = " _ Message : ";

    //URL
    public static final String URL_ESB = "workflow-notifygru.urlEsb";
    public static final String TOKEN = "workflow-notifygru.token";

    //CONTENT FORMAT
    public static final String CONTENT_FORMAT = "application/json; charset=UTF-8";

    //HTTP ERROR MESSAGE
    public static final String ERROR_MESSAGE = "Failed : HTTP error code : ";
}
