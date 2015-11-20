package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;


@Path("rest/workflow-notifygru.notificationFlux")
public class NotificationFlux {
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON )
	public String getFlux(){
		JSONObject obj = new JSONObject();
		obj.put("aaaaaakey", "aaaaaval");
		obj.put("bbbbbbbkey", "bbbbbbval");
		obj.put("cccckey", "cccccval");
		return obj.toString();
	}
}
