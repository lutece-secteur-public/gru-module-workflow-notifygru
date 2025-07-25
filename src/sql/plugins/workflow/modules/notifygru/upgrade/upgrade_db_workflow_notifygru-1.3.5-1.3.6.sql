--liquibase formatted sql
--changeset workflow-notifygru:upgrade_db_workflow_notifygru-1.3.5-1.3.6.sql
--preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE workflow_task_notify_gru_cf ADD COLUMN marker_provider_ids VARCHAR(1000) NULL AFTER id_spring_provider;
