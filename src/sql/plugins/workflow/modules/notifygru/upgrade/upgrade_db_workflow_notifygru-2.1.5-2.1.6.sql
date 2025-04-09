-- Suppression de la colonne content_cleaned de la table workflow_task_notify_gru_cf
ALTER TABLE workflow_task_notify_gru_cf DROP COLUMN content_cleaned;

-- Ajout de la colonne content_cleaned dans la table workflow_task_notify_gru_history
ALTER TABLE workflow_task_notify_gru_history ADD COLUMN content_cleaned SMALLINT DEFAULT 0;
