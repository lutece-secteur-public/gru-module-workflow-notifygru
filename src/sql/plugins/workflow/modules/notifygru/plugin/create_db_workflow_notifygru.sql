/*DROP TABLE IF EXISTS task_notify_gru_ef; */
DROP TABLE IF EXISTS task_notify_gru_cf;
DROP TABLE IF EXISTS task_notify_gru_key;

/*==============================================================*/
/* Table structure for table tf_directory_cf					*/
/*==============================================================*/

CREATE TABLE task_notify_gru_cf(
  id_task INT DEFAULT NULL,
  id_ressource_guichet INT DEFAULT NULL,
  id_demand_guichet INT DEFAULT NULL,
  id_user_guid_guichet INT DEFAULT NULL,
  id_crm_web_app_code_guichet INT DEFAULT NULL,
  is_send_notification_guichet SMALLINT NOT NULL DEFAULT 0,
  status_text_guichet VARCHAR(255) DEFAULT '' NOT NULL,
  subject_guichet VARCHAR(255) DEFAULT '' NOT NULL,
  message_guichet VARCHAR(255) DEFAULT '' NOT NULL,
  sender_name_guichet VARCHAR(255) DEFAULT '' NOT NULL,
  level_notification_guichet VARCHAR(255) DEFAULT '' NOT NULL,
  is_active_onglet_guichet SMALLINT NOT NULL DEFAULT 0,

 id_ressource_email INT DEFAULT NULL,
 id_ressource_record_email INT DEFAULT NULL,
 id_ressource_user_guid_email INT DEFAULT NULL,
 subject_email VARCHAR(255) DEFAULT '' NOT NULL,
 entity_email_email VARCHAR(255) DEFAULT '' NOT NULL,
 message_email VARCHAR(255) DEFAULT '' NOT NULL,
  sender_name_email VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_email VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_cc_email VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_cci_email VARCHAR(255) DEFAULT '' NOT NULL,
 is_notify_by_email SMALLINT NOT NULL DEFAULT 0,
 level_notification_email VARCHAR(255) DEFAULT '' NOT NULL,
  is_active_onglet_email SMALLINT NOT NULL DEFAULT 0,

 id_ressource_sms INT DEFAULT NULL,
 id_ressource_record_sms INT DEFAULT NULL,
 id_ressource_user_guid_sms INT DEFAULT NULL,
 subject_sms VARCHAR(255) DEFAULT '' NOT NULL,
 entity_sms_sms VARCHAR(255) DEFAULT '' NOT NULL,
 message_sms VARCHAR(255) DEFAULT '' NOT NULL,
  sender_name_sms VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_sms VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_cc_sms VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_cci_sms VARCHAR(255) DEFAULT '' NOT NULL,
 is_notify_by_sms SMALLINT NOT NULL DEFAULT 0,
 level_notification_sms VARCHAR(255) DEFAULT '' NOT NULL,
  is_active_onglet_sms SMALLINT NOT NULL DEFAULT 0,
 

  PRIMARY KEY  (id_task)
  );

/**
CREATE TABLE task_notify_gru_cf(
  id_task INT DEFAULT NULL,
  id_directory_ongle1 INT DEFAULT NULL,
  position_directory_entry_email INT DEFAULT -1,
  position_directory_entry_sms INT DEFAULT -1,
  position_directory_entry_user_guid_ongle1 INT DEFAULT -1,
  sender_name_ongle1 VARCHAR(255) DEFAULT NULL, 
  subject_ongle1 VARCHAR(255) DEFAULT NULL, 
  message_ongle1 long VARCHAR DEFAULT NULL,
  is_notify_by_email SMALLINT NOT NULL DEFAULT 0,
  is_notify_by_sms SMALLINT NOT NULL DEFAULT 0,
  is_notify_by_mailing_list SMALLINT NOT NULL DEFAULT 0,
  is_notify_by_user_guid SMALLINT NOT NULL DEFAULT 0,
  is_email_validation SMALLINT NOT NULL DEFAULT 0,
  id_state_after_validation INT DEFAULT NULL,
  label_link VARCHAR(255) DEFAULT NULL, 
  message_validation long varchar DEFAULT NULL,
  period_validity INT DEFAULT NULL,
  recipients_cc VARCHAR(255) DEFAULT '' NOT NULL,
  recipients_bcc VARCHAR(255) DEFAULT '' NOT NULL,
  id_mailing_list INT DEFAULT NULL,
  is_view_record SMALLINT NOT NULL DEFAULT 0,
  label_link_view_record VARCHAR(255) DEFAULT NULL,
  id_directory_ongle3 INT DEFAULT NULL,
  position_directory_entry_id_demand INT DEFAULT NULL,
  position_directory_entry_user_guid_ongle3 INT DEFAULT NULL,
  send_notification SMALLINT DEFAULT 1 NOT NULL,
  sender_name_ongle3 VARCHAR(255) DEFAULT NULL, 
  subject_ongle3 VARCHAR(255) DEFAULT NULL, 
  message_ongle3 long VARCHAR DEFAULT NULL,
  status_text VARCHAR(255) DEFAULT '' NOT NULL,
  position_directory_entry_crm_web_app_code VARCHAR(255) DEFAULT '' NOT NULL,
  PRIMARY KEY  (id_task)
  );
  */
    


CREATE TABLE task_notify_gru_key(
	key_email VARCHAR(255) DEFAULT NULL, 
	id_task INT DEFAULT NULL,
	id_resource INT DEFAULT NULL,
        resource_type VARCHAR(255) DEFAULT NULL, 
	date_expiry timestamp DEFAULT NULL NULL,
	PRIMARY KEY  (key_email)
);

/*
 CREATE TABLE task_notify_gru_ef(
  id_task INT DEFAULT NULL,
  position_directory_entry_file INT DEFAULT NULL,
  PRIMARY KEY  (id_task, position_directory_entry_file)
  ); 
  
  ALTER TABLE task_notify_gru_ef ADD CONSTRAINT fk_id_task_template FOREIGN KEY (id_task)
     REFERENCES task_notify_gru_cf (id_task)  ON DELETE RESTRICT ON UPDATE RESTRICT ;

*/