package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;


/**
 *
 * IResourceKeyDAO
 *
 */
public interface IResourceKeyDAO
{
    /**
     * Insert object ResourceKey
     * @param resourceKey the resource key
     * @param plugin the plugin
     */
    void insert( ResourceKey resourceKey, Plugin plugin );

    /**
     * Update object ResourceKey
     * @param resourceKey the resource key
     * @param plugin the plugin
     */
    void store( ResourceKey resourceKey, Plugin plugin );

    /**
     * Load object ResourceKey
     * @param strKey the key
     * @param plugin the plugin
     * @return ResourceKey
     *
     */
    ResourceKey load( String strKey, Plugin plugin );

    /**
     * Delete object ResourceKey
     * @param strKey the key
     * @param plugin le plugin
     */
    void delete( String strKey, Plugin plugin );

    /**
     * Delete resourceKey expiry
     * @param plugin le plugin
     * @return a list of {@link ResourceKey}
     */
    List<ResourceKey> selectResourceExpiry( Plugin plugin );
}
