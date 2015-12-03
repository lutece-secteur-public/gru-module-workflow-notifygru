package fr.paris.lutece.plugins.workflow.modules.notifygru.provider.directory;

import fr.paris.lutece.plugins.directory.business.Directory;
import fr.paris.lutece.plugins.directory.business.DirectoryHome;
import fr.paris.lutece.plugins.directory.business.Record;
import fr.paris.lutece.plugins.directory.business.RecordHome;
import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.AbstractServiceProvider;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import java.util.Map;
import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;


public class ProviderDirectory extends AbstractServiceProvider {
    
     @Inject
    private IResourceHistoryService _resourceHistoryService;
        @Inject
    private IProviderDirectoryService _notifyDirectoryService;
        
         
      Plugin pluginDirectory = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );

    @Override
    public String getUserEmail(int nIdResource) {
        
        
       return  _notifyDirectoryService.getEmail(this, nIdResource, this.getIdDirectory());
        
    }

    @Override
    public String getUserGuid(int nIdResource) {
        return _notifyDirectoryService.getUserGuid(this, nIdResource, this.getIdDirectory());
    }

    @Override
    public String getStatus(int nIdResource) {
       return "status disponible pour le directory";
    }

    @Override
    public String getInfosHelp(HttpServletRequest request, Map<String, Object> model) {
       
        return "texte html des marker disponible pour le directory";
    }

    @Override
    public Object getInfos(int nIdResource) {
          ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey( nIdResource );
           Record record = RecordHome.findByPrimaryKey( resourceHistory.getIdResource(  ), pluginDirectory );
           
             Directory directory = DirectoryHome.findByPrimaryKey( record.getDirectory(  ).getIdDirectory(  ),
                        pluginDirectory );
             
             return directory;
    }
    @Override
	public String getPhoneNumber(int nIdResource) {
		// TODO Auto-generated method stub
    	
		return _notifyDirectoryService.getSMSPhoneNumber(this, nIdResource, this.getIdDirectory());
	}
    public int getIdDirectory() {
    
    return 1;
    }
    public int getPositionEntryDirectoryEmail(  ) {
    
    return 1;
    }
    public int getPositionEntryDirectorySms(  ) {
    
    return 2;
    }
    public int getPositionEntryDirectoryUserGuid(  ) {
    
    return 3;
    }
}
