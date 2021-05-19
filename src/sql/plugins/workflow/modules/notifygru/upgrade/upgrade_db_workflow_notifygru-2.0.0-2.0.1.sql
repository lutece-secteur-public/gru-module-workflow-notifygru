
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN code_event VARCHAR(45) NULL AFTER is_active_onglet_broadcast;
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN type_event VARCHAR(45) NULL AFTER code_event;
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN message_event TEXT NULL AFTER type_event;
