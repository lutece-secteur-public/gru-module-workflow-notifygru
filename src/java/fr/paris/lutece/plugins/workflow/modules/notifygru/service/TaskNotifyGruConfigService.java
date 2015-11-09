package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfigDAO;
import fr.paris.lutece.plugins.workflowcore.business.config.ITaskConfig;
import fr.paris.lutece.plugins.workflowcore.service.config.TaskConfigService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.inject.Inject;


/**
 *
 * TaskNotifyGruConfigService
 *
 */
public class TaskNotifyGruConfigService extends TaskConfigService
{
    public static final String BEAN_SERVICE = "workflow-notifygru.taskNotifyGruConfigService";
    @Inject
    private TaskNotifyGruConfigDAO _taskNotifyGruConfigDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    public void create( ITaskConfig config )
    {
        super.create( config );

        TaskNotifyGruConfig notifyConfig = getConfigBean( config );

        if ( ( notifyConfig != null ) && ( notifyConfig.getListPositionEntryFile(  ) != null ) )
        {
            for ( int nPositionEntryFile : notifyConfig.getListPositionEntryFile(  ) )
            {
                this.createPositionEntryFile( config.getIdTask(  ), nPositionEntryFile );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    public void update( ITaskConfig config )
    {
        super.update( config );

        TaskNotifyGruConfig notifyConfig = getConfigBean( config );

        if ( notifyConfig != null )
        {
            // First remove the links task - entries file
            this.removeListPositionEntryFile( config.getIdTask(  ) );

            // Then add the links
            if ( notifyConfig.getListPositionEntryFile(  ) != null )
            {
                for ( int nPositionEntryFile : notifyConfig.getListPositionEntryFile(  ) )
                {
                    this.createPositionEntryFile( config.getIdTask(  ), nPositionEntryFile );
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    public void remove( int nIdTask )
    {
        super.remove( nIdTask );
        this.removeListPositionEntryFile( nIdTask );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T findByPrimaryKey( int nIdTask )
    {
        TaskNotifyGruConfig config = super.findByPrimaryKey( nIdTask );

        if ( config != null )
        {
            List<Integer> listPositionEntryFile = findPositionEntryFile( nIdTask );

            if ( ( listPositionEntryFile != null ) && !listPositionEntryFile.isEmpty(  ) )
            {
                config.setListPositionEntryFile( listPositionEntryFile );
            }
        }

        return (T) config;
    }

    /**
     * Find the positions entry file from a given id task
     * @param nIdTask the id task
     * @return a list of position
     */
    private List<Integer> findPositionEntryFile( int nIdTask )
    {
        return _taskNotifyGruConfigDAO.loadListPositionEntryFile( nIdTask );
    }

    /**
     * Create the link task - entry file
     * @param nIdTask the id task
     * @param nPositionEntryFile the position entry file
     */
    private void createPositionEntryFile( int nIdTask, int nPositionEntryFile )
    {
        _taskNotifyGruConfigDAO.insertListPositionEntryFile( nIdTask, nPositionEntryFile );
    }

    /**
     * Remove all links task - entry file
     * @param nIdTask the id task
     */
    private void removeListPositionEntryFile( int nIdTask )
    {
        _taskNotifyGruConfigDAO.deleteListPositionEntryFile( nIdTask );
    }
}
