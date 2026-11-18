-- liquibase formatted sql
-- changeset workflow-notifygru:upgrade_db_workflow_notifygru-3.0.1-3.1.0.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- comment: the history keeps the rendered texts (markers replaced, mailing lists expanded): they can be longer than 255 characters
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN status_text_guichet TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN sender_name_guichet TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN subject_guichet TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN status_text_agent TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN subject_email TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN sender_name_email TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN recipients_cc_email TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN recipients_cci_email TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN email_broadcast TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN sender_name_broadcast TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN subject_broadcast TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN recipients_cc_broadcast TEXT DEFAULT NULL;
ALTER TABLE workflow_task_notify_gru_history MODIFY COLUMN recipients_cci_broadcast TEXT DEFAULT NULL;
