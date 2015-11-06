package fr.paris.lutece.plugins.workflow.modules.notifygru.service;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.IResourceKeyDAO;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.ResourceKey;
import fr.paris.lutece.portal.service.plugin.Plugin;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.inject.Inject;


/**
 *
 * ResourceKeyService
 *
 */
public class ResourceKeyService implements IResourceKeyService
{
    public static final String BEAN_SERVICE = "workflow-notifygru.resourceKeyService";
    @Inject
    private IResourceKeyDAO _resourceKeyDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    public void create( ResourceKey resourceKey, Plugin plugin )
    {
        _resourceKeyDAO.insert( resourceKey, plugin );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    public void update( ResourceKey resourceKey, Plugin plugin )
    {
        _resourceKeyDAO.store( resourceKey, plugin );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    public void remove( String strKey, Plugin plugin )
    {
        _resourceKeyDAO.delete( strKey, plugin );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceKey findByPrimaryKey( String strKey, Plugin plugin )
    {
        ResourceKey resourceKey = _resourceKeyDAO.load( strKey, plugin );

        return resourceKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResourceKey> selectResourceExpiry( Plugin plugin )
    {
        return _resourceKeyDAO.selectResourceExpiry( plugin );
    }
}
