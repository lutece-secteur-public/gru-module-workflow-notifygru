package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * ResourceKeyDAO
 *
 */
public class ResourceKeyDAO implements IResourceKeyDAO
{
    private static final String SQL_QUERY_FIND_BY_PRIMARY_KEY = "SELECT key_email,id_task,id_resource,resource_type,date_expiry" +
        " FROM task_notify_directory_key  WHERE key_email=?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO task_notify_directory_key( " +
        "key_email,id_task,id_resource,resource_type,date_expiry)" + " VALUES (?,?,?,?,?)";
    private static final String SQL_QUERY_UPDATE = "UPDATE task_notify_directory_key " +
        "SET key_email=?,id_task=?,id_resource=?,resource_type=?,date_expiry=?" + " WHERE key_email=?";
    private static final String SQL_QUERY_DELETE = "DELETE FROM task_notify_directory_key WHERE key_email=?";
    private static final String SQL_QUERY_DELETE_EXPIRY = "SELECT key_email,id_task,id_resource,resource_type,date_expiry FROM task_notify_directory_key WHERE date_expiry<NOW()";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( ResourceKey resourceKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        int nPos = 0;

        daoUtil.setString( ++nPos, resourceKey.getKey(  ) );
        daoUtil.setInt( ++nPos, resourceKey.getIdTask(  ) );
        daoUtil.setInt( ++nPos, resourceKey.getIdResource(  ) );
        daoUtil.setString( ++nPos, resourceKey.getResourceType(  ) );
        daoUtil.setTimestamp( ++nPos, resourceKey.getDateExpiry(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( ResourceKey resourceKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        int nPos = 0;

        daoUtil.setString( ++nPos, resourceKey.getKey(  ) );
        daoUtil.setInt( ++nPos, resourceKey.getIdTask(  ) );
        daoUtil.setInt( ++nPos, resourceKey.getIdResource(  ) );
        daoUtil.setString( ++nPos, resourceKey.getResourceType(  ) );
        daoUtil.setTimestamp( ++nPos, resourceKey.getDateExpiry(  ) );

        daoUtil.setString( ++nPos, resourceKey.getKey(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceKey load( String strKey, Plugin plugin )
    {
        ResourceKey resourceKey = null;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_BY_PRIMARY_KEY, plugin );

        daoUtil.setString( 1, strKey );
        daoUtil.executeQuery(  );

        int nPos = 0;

        if ( daoUtil.next(  ) )
        {
            resourceKey = new ResourceKey(  );
            resourceKey.setKey( daoUtil.getString( ++nPos ) );
            resourceKey.setIdTask( daoUtil.getInt( ++nPos ) );
            resourceKey.setIdResource( daoUtil.getInt( ++nPos ) );
            resourceKey.setResourceType( daoUtil.getString( ++nPos ) );
            resourceKey.setDateExpiry( daoUtil.getTimestamp( ++nPos ) );
        }

        daoUtil.free(  );

        return resourceKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( String strKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );

        daoUtil.setString( 1, strKey );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResourceKey> selectResourceExpiry( Plugin plugin )
    {
        int nPos = 0;
        List<ResourceKey> listResourceExpiry = new ArrayList<ResourceKey>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_EXPIRY, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            nPos = 0;

            ResourceKey resourceKey = new ResourceKey(  );
            resourceKey.setKey( daoUtil.getString( ++nPos ) );
            resourceKey.setIdTask( daoUtil.getInt( ++nPos ) );
            resourceKey.setIdResource( daoUtil.getInt( ++nPos ) );
            resourceKey.setResourceType( daoUtil.getString( ++nPos ) );
            resourceKey.setDateExpiry( daoUtil.getTimestamp( ++nPos ) );
            listResourceExpiry.add( resourceKey );
        }

        daoUtil.free(  );

        return listResourceExpiry;
    }
}
