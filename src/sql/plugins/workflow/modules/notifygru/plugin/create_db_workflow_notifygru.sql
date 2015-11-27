/*DROP TABLE IF EXISTS task_notify_gru_ef; */
DROP TABLE IF EXISTS workflow_task_notify_gru_cf;


/*==============================================================*/
/* Table structure for table workflow_task_notify_gru_cf					*/
/*==============================================================*/

CREATE TABLE workflow_task_notify_gru_cf(
  id_task INT DEFAULT NULL,
 
 id_spring_provider VARCHAR(255) DEFAULT '' NULL,
 key_provider VARCHAR(255) DEFAULT '' NULL, 

  message_guichet VARCHAR(255) DEFAULT '' NULL,  
  level_notification_guichet VARCHAR(255) DEFAULT '' NULL,
  is_active_onglet_guichet SMALLINT NULL DEFAULT 0,

 message_agent VARCHAR(255) DEFAULT ''  NULL,
 level_notification_agent VARCHAR(255) DEFAULT ''  NULL,
  is_active_onglet_agent SMALLINT NOT NULL DEFAULT 0,

 subject_email VARCHAR(255) DEFAULT ''  NULL,
 message_email VARCHAR(255) DEFAULT ''  NULL,
  sender_name_email VARCHAR(255) DEFAULT ''  NULL,
  recipients_cc_email VARCHAR(255) DEFAULT ''  NULL,
  recipients_cci_email VARCHAR(255) DEFAULT ''  NULL,
 level_notification_email VARCHAR(255) DEFAULT ''  NULL,
  is_active_onglet_email SMALLINT  NULL DEFAULT 0,

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
  
set_onglet SMALLINT  NULL DEFAULT 0,

  PRIMARY KEY  (id_task)
  );

