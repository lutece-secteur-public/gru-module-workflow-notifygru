--liquibase formatted sql
--changeset workflow-notifygru:upgrade_db_workflow_notifygru-1.3.7-1.3.8.sql
--preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE workflow_task_notify_gru_cf ADD COLUMN billing_account_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN billing_account_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
ALTER TABLE workflow_task_notify_gru_cf ADD COLUMN billing_group_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN billing_group_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
