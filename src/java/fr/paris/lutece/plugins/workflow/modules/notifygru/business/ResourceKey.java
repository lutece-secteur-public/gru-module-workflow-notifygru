package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import java.sql.Timestamp;


/**
 *
 * ResourceKey
 *
 */
public class ResourceKey
{
    private int _nIdTask;
    private String _strKey;
    private int _nIdResource;
    private String _strResourceType;
    private Timestamp _tDateExpiry;

    /**
     *
     * @return id Task
     */
    public int getIdTask(  )
    {
        return _nIdTask;
    }

    /**
     * set id Task
     * @param idTask id task
     */
    public void setIdTask( int idTask )
    {
        _nIdTask = idTask;
    }

    /**
    *
    * @return id directory
    */
    public int getIdResource(  )
    {
        return _nIdResource;
    }

    /**
     * set id resource
     * @param nIdResource id resource
     */
    public void setIdResource( int nIdResource )
    {
        _nIdResource = nIdResource;
    }

    /**
     * Get the key
     * @return the key
     */
    public String getKey(  )
    {
        return _strKey;
    }

    /**
     * set id directory
     * @param strKey the key
     */
    public void setKey( String strKey )
    {
        _strKey = strKey;
    }

    /**
     * Get the resource type
     * @return the resource type
     */
    public String getResourceType(  )
    {
        return _strResourceType;
    }

    /**
     * Set the resource type
     * @param strResourceType the resource type
     */
    public void setResourceType( String strResourceType )
    {
        _strResourceType = strResourceType;
    }

    /**
     * Get the date expiry
     * @return the date expiry
     */
    public Timestamp getDateExpiry(  )
    {
        return _tDateExpiry;
    }

    /**
     * Set the date expiry
     * @param tDateExpiry the date expiry
     */
    public void setDateExpiry( Timestamp tDateExpiry )
    {
        _tDateExpiry = tDateExpiry;
    }
}
