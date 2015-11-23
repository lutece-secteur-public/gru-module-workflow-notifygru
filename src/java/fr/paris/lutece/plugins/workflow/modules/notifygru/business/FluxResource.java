package fr.paris.lutece.plugins.workflow.modules.notifygru.business;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.IProviderService;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ProviderService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import net.sf.json.JSONObject;

@Path("rest/")
public class FluxResource {
	
	IProviderService _providerService = new ProviderService();
	LuteceUser user = null;
	
	@GET
	@Path("flux")
	@Produces(MediaType.APPLICATION_JSON )
	public String getFlux() {
		JSONObject obj = new JSONObject();
		obj.put("User Name", _providerService.getUserName(user));
		obj.put("User Email", _providerService.getUserEmail(user));
		
		return obj.toString();	
	}
}
