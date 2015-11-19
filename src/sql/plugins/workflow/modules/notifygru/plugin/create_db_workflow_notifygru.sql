/*DROP TABLE IF EXISTS task_notify_gru_ef; */
DROP TABLE IF EXISTS task_notify_gru_cf;
DROP TABLE IF EXISTS task_notify_gru_key;

/*==============================================================*/
/* Table structure for table tf_directory_cf					*/
/*==============================================================*/

CREATE TABLE task_notify_gru_cf(
  id_task INT DEFAULT NULL,
  id_ressource INT DEFAULT NULL,
  id_user_guid INT DEFAULT NULL,
  id_demand_guichet INT DEFAULT NULL,
 
  id_crm_web_app_code_guichet INT DEFAULT NULL,
  is_send_notification_guichet SMALLINT NOT NULL DEFAULT 0,
  status_text_guichet VARCHAR(255) DEFAULT '' NULL,
  subject_guichet VARCHAR(255) DEFAULT '' NULL,
  message_guichet VARCHAR(255) DEFAULT '' NULL,
  sender_name_guichet VARCHAR(255) DEFAULT '' NULL,
  level_notification_guichet VARCHAR(255) DEFAULT '' NULL,
  is_active_onglet_guichet SMALLINT NULL DEFAULT 0,


 id_ressource_record_email INT DEFAULT NULL,
 subject_email VARCHAR(255) DEFAULT ''  NULL,
 entity_email_email VARCHAR(255) DEFAULT ''  NULL,
 message_email VARCHAR(255) DEFAULT ''  NULL,
  sender_name_email VARCHAR(255) DEFAULT ''  NULL,
  recipients_email VARCHAR(255) DEFAULT ''  NULL,
  recipients_cc_email VARCHAR(255) DEFAULT ''  NULL,
  recipients_cci_email VARCHAR(255) DEFAULT ''  NULL,
 level_notification_email VARCHAR(255) DEFAULT ''  NULL,
  is_active_onglet_email SMALLINT  NULL DEFAULT 0,

  status_text_agent VARCHAR(255) DEFAULT ''  NULL,
 message_agent VARCHAR(255) DEFAULT ''  NULL,
 level_notification_agent VARCHAR(255) DEFAULT ''  NULL,
  is_active_onglet_agent SMALLINT NOT NULL DEFAULT 0,


 id_ressource_record_sms INT DEFAULT NULL,
 phone_sms VARCHAR(255) DEFAULT ''  NULL,
 message_sms VARCHAR(255) DEFAULT ''  NULL,
 level_notification_sms VARCHAR(255) DEFAULT ''  NULL,
  is_active_onglet_sms SMALLINT NOT NULL DEFAULT 0,
 

 id_mailing_list_broadcast INT DEFAULT NULL,
subject_broadcast VARCHAR(255) DEFAULT ''  NULL,
 message_broadcast VARCHAR(255) DEFAULT ''  NULL,
  sender_name_broadcast VARCHAR(255) DEFAULT ''  NULL,
  recipients_cc_broadcast VARCHAR(255) DEFAULT ''  NULL,
  recipients_cci_broadcast VARCHAR(255) DEFAULT ''  NULL,
 level_notification_broadcast VARCHAR(255) DEFAULT ''  NULL,
  is_active_onglet_broadcast SMALLINT  NULL DEFAULT 0,

  PRIMARY KEY  (id_task)
  );


    


CREATE TABLE task_notify_gru_key(
	key_email VARCHAR(255) DEFAULT NULL, 
	id_task INT DEFAULT NULL,
	id_resource INT DEFAULT NULL,
        resource_type VARCHAR(255) DEFAULT NULL, 
	date_expiry timestamp DEFAULT NULL NULL,
	PRIMARY KEY  (key_email)
);

