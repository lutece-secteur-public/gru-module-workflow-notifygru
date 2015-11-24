package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import java.util.List;
import java.util.Map;

import fr.paris.lutece.portal.service.security.LuteceUser;

public interface IProviderService {
	
	
	String getUserName(LuteceUser user);
	
	String getUserEmail (LuteceUser user);
	
	String getUserGuid (LuteceUser user);
	
	String getTitle(int idResource);
	
	String getDescription(int idResource);
	
	List<String> getIdDemand(int idResource);
	
	List<String> getIdDemandType(int idResource);
	
	String getStatus(int idResource);
	
	String getIdStatusCrm(int idResource);
	
	String getSenderEmail (int idResource);
	
	String getRecipient (int idResource);
	
	String getPhoneNumber (int idResource);
	
	Object getInfosHelp();//HTML
	
	String getInfos(int idResource);//Object
	

}
