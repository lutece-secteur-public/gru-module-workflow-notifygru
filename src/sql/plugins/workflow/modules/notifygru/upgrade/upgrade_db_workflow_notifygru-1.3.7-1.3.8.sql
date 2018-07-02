ALTER TABLE workflow_task_notify_gru_cf ADD COLUMN billing_account_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN billing_account_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
ALTER TABLE workflow_task_notify_gru_cf ADD COLUMN billing_group_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN billing_group_sms VARCHAR(255) DEFAULT NULL AFTER message_sms ;
