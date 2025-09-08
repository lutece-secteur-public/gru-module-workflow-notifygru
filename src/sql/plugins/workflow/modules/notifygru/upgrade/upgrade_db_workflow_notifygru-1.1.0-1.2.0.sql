-- liquibase formatted sql
-- changeset workflow-notifygru:upgrade_db_workflow_notifygru-1.1.0-1.2.0.sql
-- preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE workflow_task_notify_gru_cf 
ADD COLUMN  email_broadcast VARCHAR(255) DEFAULT NULL AFTER id_mailing_list_broadcast;

ALTER TABLE workflow_task_notify_gru_history 
ADD COLUMN  email_broadcast VARCHAR(255) DEFAULT NULL AFTER id_mailing_list_broadcast;
