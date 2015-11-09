
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
public class TaskNotifyGruConfigDAO implements ITaskConfigDAO<TaskNotifyGruConfig>
{
    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = "SELECT id_task,"
            + "id_directory_ongle1,position_directory_entry_email,"
            + "position_directory_entry_sms,position_directory_entry_user_guid_ongle1,"
            + "sender_name_ongle1,subject_ongle1,message_ongle1,is_notify_by_email,is_notify_by_sms,"
            + "is_notify_by_mailing_list,is_notify_by_user_guid,is_email_validation,"
            + "id_state_after_validation,label_link,message_validation,period_validity,"
            + "recipients_cc,recipients_bcc,id_mailing_list,is_view_record,"
            + "label_link_view_record "
            + ",id_directory_ongle3,position_directory_entry_id_demand,"
            + "position_directory_entry_user_guid_ongle3, send_notification,  "
            + "sender_name_ongle3, subject_ongle3, message_ongle3, status_text,"
            + "position_directory_entry_crm_web_app_code "+
        "FROM task_notify_gru_cf  WHERE id_task=?";
    
    private static final String SQL_QUERY_INSERT = "INSERT INTO task_notify_gru_cf( " +
        "id_task,id_directory_ongle1,"
            + "position_directory_entry_email,"
            + "position_directory_entry_sms,"
            + "position_directory_entry_user_guid_ongle1,"
            + "sender_name_ongle1,subject_ongle1,"
            + "message_ongle1,is_notify_by_email,"
            + "is_notify_by_sms,is_notify_by_mailing_list,"
            + "is_notify_by_user_guid,is_email_validation,"
            + "id_state_after_validation,label_link,message_validation,"
            + "period_validity,recipients_cc,recipients_bcc,id_mailing_list,"
            + "is_view_record,label_link_view_record,"
            + "id_directory_ongle3, position_directory_entry_id_demand, "
            + "position_directory_entry_user_guid_ongle3, send_notification, "
            + "sender_name_ongle3, subject_ongle3, message_ongle3, "
            + "status_text, position_directory_entry_crm_web_app_code)" +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?,?,?,?,?,?)";
    private static final String SQL_QUERY_UPDATE = "UPDATE task_notify_gru_cf " +
        " SET id_task = ?, id_directory_ongle1 = ?,"
            + " position_directory_entry_email = ?,"
            + " position_directory_entry_sms = ?,"
            + " position_directory_entry_user_guid_ongle1 = ?,"
            + " sender_name_ongle1 = ?, subject_ongle1 = ?, message_ongle1 = ?,"
            + " is_notify_by_email = ?, is_notify_by_sms = ?, "
            + "is_notify_by_mailing_list = ?, is_notify_by_user_guid = ?,"
            + "is_email_validation = ?, id_state_after_validation = ?, "
            + "label_link = ?, message_validation = ?, period_validity = ?, "
            + "recipients_cc = ?, recipients_bcc = ?, id_mailing_list = ?, "
            + "is_view_record = ?,"
            + " label_link_view_record = ?,"
            + "id_directory_ongle3 = ?, position_directory_entry_id_demand = ?, "
            + "position_directory_entry_user_guid_ongle3 = ?,"
            + " send_notification = ?, sender_name_ongle3 = ?, subject_ongle3 = ?, "
            + "message_ongle3 = ?, status_text = ?, "
            + " position_directory_entry_crm_web_app_code = ? " +
        " WHERE id_task = ? ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM task_notify_gru_cf WHERE id_task = ? ";
    private static final String SQL_QUERY_DELETE_POSITION_ENTRY_FILE = "DELETE FROM task_notify_gru_ef where id_task= ? ";
    private static final String SQL_QUERY_INSERT_POSITION_ENTRY_FILE = "INSERT INTO task_notify_gru_ef( " +
        "id_task,position_directory_entry_file) VALUES (?,?) ";
    private static final String SQL_QUERY_FIND_LIST_POSITION_ENTRY_FILE = "SELECT position_directory_entry_file " +
        "FROM task_notify_gru_ef  WHERE id_task= ?";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( TaskNotifyGruConfig config )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, NotifyGruPlugin.getPlugin(  ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.setInt( ++nPos, config.getIdDirectoryOngle1());
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryEmail(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectorySms(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryUserGuidOngle1());
        daoUtil.setString( ++nPos, config.getSenderNameOngle1());
        daoUtil.setString( ++nPos, config.getSubjectOngle1());
        daoUtil.setString( ++nPos, config.getMessageOngle1());
        daoUtil.setBoolean( ++nPos, config.isNotifyByEmail(  ) );
        daoUtil.setBoolean( ++nPos, config.isNotifyBySms(  ) );
        daoUtil.setBoolean( ++nPos, config.isNotifyByMailingList(  ) );
        daoUtil.setBoolean( ++nPos, config.isNotifyByUserGuid(  ) );
        daoUtil.setBoolean( ++nPos, config.isEmailValidation(  ) );
        daoUtil.setInt( ++nPos, config.getIdStateAfterValidation(  ) );
        daoUtil.setString( ++nPos, config.getLabelLink(  ) );
        daoUtil.setString( ++nPos, config.getMessageValidation(  ) );
        daoUtil.setInt( ++nPos, config.getPeriodValidity(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCc(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsBcc(  ) );
        daoUtil.setInt( ++nPos, config.getIdMailingList(  ) );
        daoUtil.setBoolean( ++nPos, config.isViewRecord(  ) );
        daoUtil.setString( ++nPos, config.getLabelLinkViewRecord(  ) );
        
       
        daoUtil.setInt(++nPos, config.getIdDirectoryOngle3());
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryIdDemand(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryUserGuidOngle3());
        daoUtil.setBoolean( ++nPos, config.getSendNotification(  ) );
        daoUtil.setString( ++nPos, config.getSenderNameOngle3());
        daoUtil.setString( ++nPos, config.getSubjectOngle3());
        daoUtil.setString(++nPos, config.getMessageOngle3());
        daoUtil.setString( ++nPos, config.getStatusText(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryCrmWebAppCode() );
        

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( TaskNotifyGruConfig config )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, NotifyGruPlugin.getPlugin(  ) );

        int nPos = 0;

        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.setInt( ++nPos, config.getIdDirectoryOngle1());
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryEmail(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectorySms(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryUserGuidOngle1());
        daoUtil.setString( ++nPos, config.getSenderNameOngle1());
        daoUtil.setString( ++nPos, config.getSubjectOngle1());
        daoUtil.setString( ++nPos, config.getMessageOngle1());
        daoUtil.setBoolean( ++nPos, config.isNotifyByEmail(  ) );
        daoUtil.setBoolean( ++nPos, config.isNotifyBySms(  ) );
        daoUtil.setBoolean( ++nPos, config.isNotifyByMailingList(  ) );
        daoUtil.setBoolean( ++nPos, config.isNotifyByUserGuid(  ) );
        daoUtil.setBoolean( ++nPos, config.isEmailValidation(  ) );
        daoUtil.setInt( ++nPos, config.getIdStateAfterValidation(  ) );
        daoUtil.setString( ++nPos, config.getLabelLink(  ) );
        daoUtil.setString( ++nPos, config.getMessageValidation(  ) );
        daoUtil.setInt( ++nPos, config.getPeriodValidity(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsCc(  ) );
        daoUtil.setString( ++nPos, config.getRecipientsBcc(  ) );
        daoUtil.setInt( ++nPos, config.getIdMailingList(  ) );
        daoUtil.setBoolean( ++nPos, config.isViewRecord(  ) );
        daoUtil.setString( ++nPos, config.getLabelLinkViewRecord(  ) );

        
        
        daoUtil.setInt( ++nPos, config.getIdDirectoryOngle3());
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryIdDemand(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryUserGuidOngle3());
        daoUtil.setBoolean( ++nPos, config.getSendNotification(  ) );
        daoUtil.setString( ++nPos, config.getSenderNameOngle3());
        daoUtil.setString( ++nPos, config.getSubjectOngle3());
        daoUtil.setString( ++nPos, config.getMessageOngle3());
        daoUtil.setString( ++nPos, config.getStatusText(  ) );
        daoUtil.setInt( ++nPos, config.getPositionEntryDirectoryCrmWebAppCode() );

        
        
        daoUtil.setInt( ++nPos, config.getIdTask(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskNotifyGruConfig load( int nIdTask )
    {
        TaskNotifyGruConfig config = null;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_PRIMARY_KEY, NotifyGruPlugin.getPlugin(  ) );

        daoUtil.setInt( 1, nIdTask );

        daoUtil.executeQuery(  );

        int nPos = 0;

        if ( daoUtil.next(  ) )
        {
            config = new TaskNotifyGruConfig(  );
            config.setIdTask( daoUtil.getInt( ++nPos ) );
            config.setIdDirectoryOngle1(daoUtil.getInt( ++nPos ) );
            config.setPositionEntryDirectoryEmail( daoUtil.getInt( ++nPos ) );
            config.setPositionEntryDirectorySms( daoUtil.getInt( ++nPos ) );
            config.setPositionEntryDirectoryUserGuidOngle1(daoUtil.getInt( ++nPos ) );
            config.setSenderNameOngle1(daoUtil.getString( ++nPos ) );
            config.setSubjectOngle1(daoUtil.getString( ++nPos ) );
            config.setMessageOngle1(daoUtil.getString( ++nPos ) );
            config.setNotifyByEmail( daoUtil.getBoolean( ++nPos ) );
            config.setNotifyBySms( daoUtil.getBoolean( ++nPos ) );
            config.setNotifyByMailingList( daoUtil.getBoolean( ++nPos ) );
            config.setNotifyByUserGuid( daoUtil.getBoolean( ++nPos ) );
            config.setEmailValidation( daoUtil.getBoolean( ++nPos ) );
            config.setIdStateAfterValidation( daoUtil.getInt( ++nPos ) );
            config.setLabelLink( daoUtil.getString( ++nPos ) );
            config.setMessageValidation( daoUtil.getString( ++nPos ) );
            config.setPeriodValidity( daoUtil.getInt( ++nPos ) );
            config.setRecipientsCc( daoUtil.getString( ++nPos ) );
            config.setRecipientsBcc( daoUtil.getString( ++nPos ) );
            config.setIdMailingList( daoUtil.getInt( ++nPos ) );
            config.setViewRecord( daoUtil.getBoolean( ++nPos ) );
            config.setLabelLinkViewRecord( daoUtil.getString( ++nPos ) );
        
           
            config.setIdDirectoryOngle3(daoUtil.getInt( ++nPos ) );
            config.setPositionEntryDirectoryIdDemand( daoUtil.getInt( ++nPos ) );
            config.setPositionEntryDirectoryUserGuidOngle3(daoUtil.getInt( ++nPos ) );
            config.setSendNotification( daoUtil.getBoolean( ++nPos ) );
            config.setSenderNameOngle3(daoUtil.getString( ++nPos ) );
            config.setSubjectOngle3(daoUtil.getString( ++nPos ) );
            config.setMessageOngle3(daoUtil.getString( ++nPos ) );
            config.setStatusText( daoUtil.getString( ++nPos ) );
            config.setPositionEntryDirectoryCrmWebAppCode(daoUtil.getInt( ++nPos ));
        }

        daoUtil.free(  );

        return config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdState )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, NotifyGruPlugin.getPlugin(  ) );

        daoUtil.setInt( 1, nIdState );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    public List<Integer> loadListPositionEntryFile( int nIdTask )
    {
        List<Integer> listIntegerPositionEntryFile = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_LIST_POSITION_ENTRY_FILE, NotifyGruPlugin.getPlugin(  ) );
        daoUtil.setInt( 1, nIdTask );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            listIntegerPositionEntryFile.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return listIntegerPositionEntryFile;
    }

 
    public void deleteListPositionEntryFile( int nIdTask )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_POSITION_ENTRY_FILE, NotifyGruPlugin.getPlugin(  ) );

        daoUtil.setInt( 1, nIdTask );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    
    public void insertListPositionEntryFile( int nIdTask, int nPositionEntryFile )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT_POSITION_ENTRY_FILE, NotifyGruPlugin.getPlugin(  ) );

        daoUtil.setInt( 1, nIdTask );
        daoUtil.setInt( 2, nPositionEntryFile );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
