package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.NotifyGruPlugin;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfigDAO;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * TaskNotifyDirectoryConfigDAO
 *
 */
public class TaskNotifyGruConfigDAO implements ITaskConfigDAO<TaskNotifyGruConfig> {

    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = "SELECT id_task, "
            + "id_ressource,id_user_guid, id_demand_guichet,id_crm_web_app_code_guichet,"
            + "is_send_notification_guichet,status_text_guichet,subject_guichet,message_guichet,"
            + "sender_name_guichet,level_notification_guichet,is_active_onglet_guichet,"
            + "id_ressource_record_email,"
            + "subject_email,entity_email_email,message_email,sender_name_email,"
            + "recipients_cc_email,recipients_cci_email,level_notification_email,"
            + "is_active_onglet_email,"
            + "status_text_agent,message_agent,level_notification_agent,is_active_onglet_agent,"
            + "id_ressource_record_sms,"
            + "phone_sms,message_sms,"
            + "level_notification_sms,is_active_onglet_sms"
            + " FROM task_notify_gru_cf  WHERE id_task = ?";

    private static final String SQL_QUERY_INSERT = "INSERT INTO task_notify_gru_cf( "
            + "id_task,id_ressource,id_user_guid,id_demand_guichet,"
            + "id_crm_web_app_code_guichet,is_send_notification_guichet,status_text_guichet,"
            + "subject_guichet,message_guichet,sender_name_guichet,level_notification_guichet,"
            + "is_active_onglet_guichet,id_ressource_email,"
            + "subject_email,entity_email_email,message_email,"
            + "sender_name_email,recipients_email,recipients_cc_email,recipients_cci_email,"
            + "level_notification_email,is_active_onglet_email,"
            + "status_text_agent,message_agent,level_notification_agent,is_active_onglet_agent,"
            + "id_ressource_record_sms,"
            + "phone_sms,message_sms,"          
            + "level_notification_sms,is_active_onglet_sms)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_QUERY_UPDATE = "UPDATE task_notify_gru_cf "
            + " SET id_task = ?, id_ressource = ?, id_user_guid = ?, id_demand_guichet = ?"
            + ",  id_crm_web_app_code_guichet = ?,"
            + "is_send_notification_guichet = ?, status_text_guichet = ?,"
            + "subject_guichet = ?, message_guichet= ?, sender_name_guichet= ?, "
            + "level_notification_guichet = ?, is_active_onglet_guichet= ?, "
            + "id_ressource_record_email = ?, subject_email = ?, "
            + "entity_email_email = ?, message_email = ?, sender_name_email = ?,"
            + "recipients_email = ?, recipients_cc_email = ?, recipients_cci_email = ?, "
            + "level_notification_email = ?, is_active_onglet_email= ?,"
            + "status_text_agent = ? ,message_agent = ? ,"
            + "level_notification_agent = ? ,is_active_onglet_agent = ? , "
            + " id_ressource_record_sms= ?, "
            + " phone_sms = ?, message_sms = ?, "
            + " level_notification_sms = ?,"
            + "is_active_onglet_sms = ?  WHERE id_task = ? ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM task_notify_gru_cf WHERE id_task = ? ";

    /*  private static final String SQL_QUERY_DELETE_POSITION_ENTRY_FILE = "DELETE FROM task_notify_gru_ef where id_task= ? ";
     private static final String SQL_QUERY_INSERT_POSITION_ENTRY_FILE = "INSERT INTO task_notify_gru_ef( " +
     "id_task,position_directory_entry_file) VALUES (?,?) ";
     private static final String SQL_QUERY_FIND_LIST_POSITION_ENTRY_FILE = "SELECT position_directory_entry_file " +
     "FROM task_notify_gru_ef  WHERE id_task= ?";
     */
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert(TaskNotifyGruConfig config) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_INSERT, NotifyGruPlugin.getPlugin());

        int nPos = 0;

        daoUtil.setInt(++nPos, config.getIdTask());
        daoUtil.setInt(++nPos, config.getIdRessource());
        daoUtil.setInt(++nPos, config.getIdUserGuid());
        daoUtil.setInt(++nPos, config.getIdDemandGuichet());
        daoUtil.setInt(++nPos, config.getCrmWebAppCodeGuichet());
        daoUtil.setBoolean(++nPos, config.isSendNotificationGuichet());
        daoUtil.setString(++nPos, config.getStatusTextGuichet());
        daoUtil.setString(++nPos, config.getSubjectGuichet());
        daoUtil.setString(++nPos, config.getMessageGuichet());
        daoUtil.setString(++nPos, config.getLevelNotificationGuichet());
        daoUtil.setBoolean(++nPos, config.isActiveOngletGuichet());

        daoUtil.setString(++nPos, config.getRessourceRecordEmail());

        daoUtil.setString(++nPos, config.getSubjectEmail());
        daoUtil.setString(++nPos, config.getEntryEmail());
        daoUtil.setString(++nPos, config.getMessageEmail());
        daoUtil.setString(++nPos, config.getSenderNameEmail());
    
        daoUtil.setString(++nPos, config.getRecipientsCcEmail());
        daoUtil.setString(++nPos, config.getRecipientsCciEmail());
       
        daoUtil.setString(++nPos, config.getLevelNotificationEmail());
        daoUtil.setBoolean(++nPos, config.isActiveOngletEmail());

        daoUtil.setString(++nPos, config.getStatusTextAgent());
        daoUtil.setString(++nPos, config.getMessageAgent());
        daoUtil.setString(++nPos, config.getLevelNotificationAgent());
        daoUtil.setBoolean(++nPos, config.isActiveOngletAgent());

        daoUtil.setString(++nPos, config.getRessourceRecordSMS());
        daoUtil.setString(++nPos, config.getPhoneSMS());
        daoUtil.setString(++nPos, config.getMessageSMS());
       
        daoUtil.setString(++nPos, config.getLevelNotificationSMS());
        daoUtil.setBoolean(++nPos, config.isActiveOngletSMS());

        daoUtil.executeUpdate();
        daoUtil.free();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(TaskNotifyGruConfig config) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_UPDATE, NotifyGruPlugin.getPlugin());

        int nPos = 0;

        daoUtil.setInt(++nPos, config.getIdTask());
        daoUtil.setInt(++nPos, config.getIdRessource());
        daoUtil.setInt(++nPos, config.getIdUserGuid());
        daoUtil.setInt(++nPos, config.getIdDemandGuichet());
        daoUtil.setInt(++nPos, config.getCrmWebAppCodeGuichet());
        daoUtil.setBoolean(++nPos, config.isSendNotificationGuichet());
        daoUtil.setString(++nPos, config.getStatusTextGuichet());
        daoUtil.setString(++nPos, config.getSubjectGuichet());
        daoUtil.setString(++nPos, config.getMessageGuichet());
        daoUtil.setString(++nPos, config.getLevelNotificationGuichet());
        daoUtil.setBoolean(++nPos, config.isActiveOngletGuichet());

        daoUtil.setString(++nPos, config.getRessourceRecordEmail());

        daoUtil.setString(++nPos, config.getSubjectEmail());
        daoUtil.setString(++nPos, config.getEntryEmail());
        daoUtil.setString(++nPos, config.getMessageEmail());
        daoUtil.setString(++nPos, config.getSenderNameEmail());
 
        daoUtil.setString(++nPos, config.getRecipientsCcEmail());
        daoUtil.setString(++nPos, config.getRecipientsCciEmail());
     
        daoUtil.setString(++nPos, config.getLevelNotificationEmail());
        daoUtil.setBoolean(++nPos, config.isActiveOngletEmail());

        daoUtil.setString(++nPos, config.getStatusTextAgent());
        daoUtil.setString(++nPos, config.getMessageAgent());
        daoUtil.setString(++nPos, config.getLevelNotificationAgent());
        daoUtil.setBoolean(++nPos, config.isActiveOngletAgent());

        daoUtil.setString(++nPos, config.getRessourceRecordSMS());

        daoUtil.setString(++nPos, config.getPhoneSMS());

        daoUtil.setString(++nPos, config.getMessageSMS());

     
        daoUtil.setString(++nPos, config.getLevelNotificationSMS());
        daoUtil.setBoolean(++nPos, config.isActiveOngletSMS());

        daoUtil.setInt(++nPos, config.getIdTask());
        daoUtil.executeUpdate();
        daoUtil.free();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskNotifyGruConfig load(int nIdTask) {
        TaskNotifyGruConfig config = new TaskNotifyGruConfig();
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_FIND_BY_PRIMARY_KEY, NotifyGruPlugin.getPlugin());

        daoUtil.setInt(1, nIdTask);

        daoUtil.executeQuery();

        int nPos = 0;

        if (daoUtil.next()) {

            config.setIdTask(daoUtil.getInt(++nPos));
            config.setIdRessource(daoUtil.getInt(++nPos));
            config.setIdUserGuid(daoUtil.getInt(++nPos));
            config.setIdDemandGuichet(daoUtil.getInt(++nPos));
            config.setCrmWebAppCodeGuichet(daoUtil.getInt(++nPos));
            config.setSendNotificationGuichet(daoUtil.getBoolean(++nPos));
            config.setStatusTextGuichet(daoUtil.getString(++nPos));
            config.setSubjectGuichet(daoUtil.getString(++nPos));
            config.setMessageGuichet(daoUtil.getString(++nPos));
            config.setLevelNotificationGuichet(daoUtil.getString(++nPos));
            config.setActiveOngletGuichet(daoUtil.getBoolean(++nPos));

            config.setRessourceRecordEmail(daoUtil.getString(++nPos));
            config.setSubjectEmail(daoUtil.getString(++nPos));
            config.setEntryEmail(daoUtil.getString(++nPos));
            config.setMessageEmail(daoUtil.getString(++nPos));
            config.setSenderNameEmail(daoUtil.getString(++nPos));
            
            config.setRecipientsCcEmail(daoUtil.getString(++nPos));
            config.setRecipientsCciEmail(daoUtil.getString(++nPos));
         
            config.setLevelNotificationEmail(daoUtil.getString(++nPos));
            config.setActiveOngletEmail(daoUtil.getBoolean(++nPos));

            config.setStatusTextAgent(daoUtil.getString(++nPos));
            config.setMessageAgent(daoUtil.getString(++nPos));
            config.setLevelNotificationAgent(daoUtil.getString(++nPos));
            config.setActiveOngletAgent(daoUtil.getBoolean(++nPos));

            config.setRessourceRecordSMS(daoUtil.getString(++nPos));
            config.setPhoneSMS(daoUtil.getString(++nPos));
            config.setMessageSMS(daoUtil.getString(++nPos));
           
            config.setLevelNotificationSMS(daoUtil.getString(++nPos));
            config.setActiveOngletSMS(daoUtil.getBoolean(++nPos));
        }

        daoUtil.free();

        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int nIdState) {
        DAOUtil daoUtil = new DAOUtil(SQL_QUERY_DELETE, NotifyGruPlugin.getPlugin());

        daoUtil.setInt(1, nIdState);
        daoUtil.executeUpdate();
        daoUtil.free();
    }

//    public List<Integer> loadListPositionEntryFile( int nIdTask )
//    {
//        List<Integer> listIntegerPositionEntryFile = new ArrayList<Integer>(  );
//        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_LIST_POSITION_ENTRY_FILE, NotifyGruPlugin.getPlugin(  ) );
//        daoUtil.setInt( 1, nIdTask );
//        daoUtil.executeQuery(  );
//
//        while ( daoUtil.next(  ) )
//        {
//            listIntegerPositionEntryFile.add( daoUtil.getInt( 1 ) );
//        }
//
//        daoUtil.free(  );
//
//        return listIntegerPositionEntryFile;
//    }
//    public void deleteListPositionEntryFile( int nIdTask )
//    {
//        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_POSITION_ENTRY_FILE, NotifyGruPlugin.getPlugin(  ) );
//
//        daoUtil.setInt( 1, nIdTask );
//        daoUtil.executeUpdate(  );
//        daoUtil.free(  );
//    }
//    public void insertListPositionEntryFile( int nIdTask, int nPositionEntryFile )
//    {
//        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT_POSITION_ENTRY_FILE, NotifyGruPlugin.getPlugin(  ) );
//
//        daoUtil.setInt( 1, nIdTask );
//        daoUtil.setInt( 2, nPositionEntryFile );
//        daoUtil.executeUpdate(  );
//        daoUtil.free(  );
//    }
}
