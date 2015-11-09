
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.ResourceKey;
import fr.paris.lutece.portal.service.plugin.Plugin;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *
 * IResourceKeyService
 *
 */
public interface IResourceKeyService
{
    /**
    * Insert new resourceKey
    * @param resourceKey object ResourceKey
    * @param plugin the plugin
    */
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    void create( ResourceKey resourceKey, Plugin plugin );

    /**
     * Update a ResourceKey
     *
     * @param resourceKey object ResourceKey
     * @param plugin the plugin
     */
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    void update( ResourceKey resourceKey, Plugin plugin );

    /**
     * Delete a ResourceKey
     * @param strKey key
     * @param plugin the plugin
     */
    @Transactional( NotifyGruPlugin.BEAN_TRANSACTION_MANAGER )
    void remove( String strKey, Plugin plugin );

    /**
     * Delete a ResourceKey
     * @param strKey key
     * @param plugin the plugin
     * @return a ResourceKey
     */
    ResourceKey findByPrimaryKey( String strKey, Plugin plugin );

    /**
     * Delete a ResourceKey expiry
     * @param plugin the plugin
     * @return a list of {@link ResourceKey}
     */
    List<ResourceKey> selectResourceExpiry( Plugin plugin );
}
