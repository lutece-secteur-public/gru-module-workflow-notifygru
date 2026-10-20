-- liquibase formatted sql
-- changeset workflow-notifygru:upgrade_db_workflow_notifygru-3.0.0-3.0.1.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- LUT-33153 (FreeMarker HTML auto-escaping): the notification texts of workflow_task_notify_gru_cf are FreeMarker
-- templates (with markers of the user data) rendered with HTML auto-escaping. Hand escaping (?html, ?xhtml, ?xml, ?rtf,
-- ?web_safe) no longer parses: the notification would fail at run time. It is removed; auto-escaping escapes the marker
-- values of the HTML messages, and the text fields (SMS, subjects, status texts, recipients) are written unescaped.
-- This script rewrites site data (table workflow_task_notify_gru_cf, the 12 columns below). Idempotent: a value without
-- these built-ins is not updated. workflow_task_notify_gru_history holds rendered texts, not templates: unchanged.
-- Not handled, to review by hand: <#escape>/<#noescape> directives, and a built-in inside a <script> of a message
-- (it needs ?js_string there). To list them before the upgrade:
--   SELECT id_task FROM workflow_task_notify_gru_cf WHERE CONCAT(COALESCE(message_guichet,''), COALESCE(message_agent,''),
--     COALESCE(message_email,''), COALESCE(message_broadcast,'')) LIKE '%<#escape%'
--     OR CONCAT(COALESCE(message_guichet,''), COALESCE(message_agent,''), COALESCE(message_email,''),
--     COALESCE(message_broadcast,'')) LIKE '%<script%';

UPDATE workflow_task_notify_gru_cf SET message_guichet = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( message_guichet, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE message_guichet LIKE '%?html%' OR message_guichet LIKE '%?xhtml%' OR message_guichet LIKE '%?xml%' OR message_guichet LIKE '%?rtf%' OR message_guichet LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET status_text_guichet = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( status_text_guichet, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE status_text_guichet LIKE '%?html%' OR status_text_guichet LIKE '%?xhtml%' OR status_text_guichet LIKE '%?xml%' OR status_text_guichet LIKE '%?rtf%' OR status_text_guichet LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET subject_guichet = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( subject_guichet, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE subject_guichet LIKE '%?html%' OR subject_guichet LIKE '%?xhtml%' OR subject_guichet LIKE '%?xml%' OR subject_guichet LIKE '%?rtf%' OR subject_guichet LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET status_text_agent = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( status_text_agent, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE status_text_agent LIKE '%?html%' OR status_text_agent LIKE '%?xhtml%' OR status_text_agent LIKE '%?xml%' OR status_text_agent LIKE '%?rtf%' OR status_text_agent LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET message_agent = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( message_agent, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE message_agent LIKE '%?html%' OR message_agent LIKE '%?xhtml%' OR message_agent LIKE '%?xml%' OR message_agent LIKE '%?rtf%' OR message_agent LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET subject_email = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( subject_email, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE subject_email LIKE '%?html%' OR subject_email LIKE '%?xhtml%' OR subject_email LIKE '%?xml%' OR subject_email LIKE '%?rtf%' OR subject_email LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET message_email = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( message_email, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE message_email LIKE '%?html%' OR message_email LIKE '%?xhtml%' OR message_email LIKE '%?xml%' OR message_email LIKE '%?rtf%' OR message_email LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET message_sms = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( message_sms, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE message_sms LIKE '%?html%' OR message_sms LIKE '%?xhtml%' OR message_sms LIKE '%?xml%' OR message_sms LIKE '%?rtf%' OR message_sms LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET email_broadcast = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( email_broadcast, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE email_broadcast LIKE '%?html%' OR email_broadcast LIKE '%?xhtml%' OR email_broadcast LIKE '%?xml%' OR email_broadcast LIKE '%?rtf%' OR email_broadcast LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET recipients_cc_broadcast = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( recipients_cc_broadcast, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE recipients_cc_broadcast LIKE '%?html%' OR recipients_cc_broadcast LIKE '%?xhtml%' OR recipients_cc_broadcast LIKE '%?xml%' OR recipients_cc_broadcast LIKE '%?rtf%' OR recipients_cc_broadcast LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET subject_broadcast = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( subject_broadcast, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE subject_broadcast LIKE '%?html%' OR subject_broadcast LIKE '%?xhtml%' OR subject_broadcast LIKE '%?xml%' OR subject_broadcast LIKE '%?rtf%' OR subject_broadcast LIKE '%?web_safe%';
UPDATE workflow_task_notify_gru_cf SET message_broadcast = REPLACE( REPLACE( REPLACE( REPLACE( REPLACE( message_broadcast, '?html', '' ), '?xhtml', '' ), '?xml', '' ), '?rtf', '' ), '?web_safe', '' )
  WHERE message_broadcast LIKE '%?html%' OR message_broadcast LIKE '%?xhtml%' OR message_broadcast LIKE '%?xml%' OR message_broadcast LIKE '%?rtf%' OR message_broadcast LIKE '%?web_safe%';
