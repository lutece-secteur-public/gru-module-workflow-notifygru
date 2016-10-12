ALTER TABLE workflow_task_notify_gru_cf 
ADD COLUMN  email_broadcast VARCHAR(255) DEFAULT NULL AFTER id_mailing_list_broadcast;

ALTER TABLE workflow_task_notify_gru_history 
ADD COLUMN  email_broadcast VARCHAR(255) DEFAULT NULL AFTER id_mailing_list_broadcast;
