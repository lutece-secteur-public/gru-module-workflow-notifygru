/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.portal.service.security.LuteceUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fallphenix
 */
public class ImplementationProvider1 extends AbstractServiceProvider{
 
 
 
 @Override
	public String getUserGuid(LuteceUser user) {
		// TODO Auto-generated method stub
		return "12312";
	}

	@Override
	public String getUserEmail(LuteceUser user) {
		// TODO Auto-generated method stub
		return "john.doe@somewhere.com";
	}

	@Override
	public Object getInfosHelp() {
		// TODO Auto-generated method stub
		Map<String,String> infos = new  HashMap<String,String>();
		infos.put("Status", "${status}");
		infos.put("Titre du Provider", "${provider_title}");
		infos.put("Description du Provider", "${provider_desc}");
		infos.put("Nom de l'utilisateur", "${name_user}");
		infos.put("Titre de demande 1", "${title_demand_1}");
		infos.put("Titre de demande 2", "${title_demand_2}");
		infos.put("Titre de demande 3", "${title_demand_3}");
		return infos;
	}

	@Override
	public String getInfos(int idResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatus(int idResource) {
		// TODO Auto-generated method stub
		return "En cours de validation";
	}

	@Override
	public List<String> getIdDemand(int idResource) {
		// TODO Auto-generated method stub
		List<String> liste = new ArrayList<String>();
		liste.add("1108");liste.add("1109");liste.add("1110");
		return liste;
	}

	@Override
	public List<String> getIdDemandType(int idResource) {
		// TODO Auto-generated method stub
		List<String> liste = new ArrayList<String>();
		liste.add("14");liste.add("15");liste.add("16");
		return liste;
	}

	@Override
	public String getIdStatusCrm(int idResource) {
		// TODO Auto-generated method stub
		return "1";
	}

	@Override
	public String getSenderEmail(int idResource) {
		// TODO Auto-generated method stub
		return "no-replay@paris.fr";
	}

	@Override
	public String getRecipient(int idResource) {
		// TODO Auto-generated method stub
		return "john.doe@somewhere.com";
	}

	@Override
	public String getPhoneNumber(int idResource) {
		// TODO Auto-generated method stub
		return "0655447788";
	}

	@Override
	public String getUserName(LuteceUser user) {
		// TODO Auto-generated method stub
		return "John Deo";
	}

	@Override
	public String getTitle(int idResource) {
		// TODO Auto-generated method stub
		return "Provider";
	}

	@Override
	public String getDescription(int idResource) {
		// TODO Auto-generated method stub
		return "Desc Provider";
	}

    @Override
    public String getName() {
    return "Implementation1";   
    }

   
    }

    
