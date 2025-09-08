-- liquibase formatted sql
-- changeset workflow-notifygru:upgrade_db_workflow_notifygru-2.1.5-2.1.6.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- Suppression de la colonne content_cleaned de la table workflow_task_notify_gru_cf
ALTER TABLE workflow_task_notify_gru_cf DROP COLUMN content_cleaned;

-- Ajout de la colonne content_cleaned dans la table workflow_task_notify_gru_history
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN content_cleaned SMALLINT DEFAULT 0;
