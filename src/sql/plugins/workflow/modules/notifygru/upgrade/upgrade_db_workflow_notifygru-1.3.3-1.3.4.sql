--liquibase formatted sql
--changeset workflow-notifygru:upgrade_db_workflow_notifygru-1.3.3-1.3.4.sql
--preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE workflow_task_notify_gru_history DROP PRIMARY KEY, ADD PRIMARY KEY(id_history, id_task);