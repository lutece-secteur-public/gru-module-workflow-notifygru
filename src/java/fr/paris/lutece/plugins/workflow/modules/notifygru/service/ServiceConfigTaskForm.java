/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 *
 * @author root
 */
public class ServiceConfigTaskForm {
    
    
    
    
     /**
     * 
     * @param strIdBean     * @
     * @return
     */
    public static Boolean isBeanExiste(String strIdBean) {
        
        if(strIdBean==null) return false;
        try {
         AbstractServiceProvider _mokeProviderService =   SpringContextService.getBean(strIdBean);         
         if(_mokeProviderService.getKey().equals(strIdBean)) return true;
        } catch (NoSuchBeanDefinitionException e) {
            
            return false;
        }
        
         return false;
    }
    public static ReferenceList getListOnglet(TaskNotifyGruConfig config) {

        ReferenceList refenreceList = new ReferenceList();
        
        if(!config.isActiveOngletGuichet()) {
          refenreceList.addItem(NotifyGruConstants.MARK_ONGLET_GUICHET, NotifyGruConstants.VIEW_GUICHET);
        }
        
          if(!config.isActiveOngletAgent()) {
          refenreceList.addItem(NotifyGruConstants.MARK_ONGLET_AGENT, NotifyGruConstants.VIEW_AGENT);
        }
            if(!config.isActiveOngletEmail()) {
         refenreceList.addItem(NotifyGruConstants.MARK_ONGLET_EMAIL, NotifyGruConstants.VIEW_EMAIL);
        }
            
              if(!config.isActiveOngletSMS()) {
           refenreceList.addItem(NotifyGruConstants.MARK_ONGLET_SMS, NotifyGruConstants.VIEW_SMS);
        }
              
                if(!config.isActiveOngletBroadcast()) {
          refenreceList.addItem(NotifyGruConstants.MARK_ONGLET_LIST, NotifyGruConstants.VIEW_BROADCAST_LIST);
        }
      
      

        return refenreceList;
    }
    
     /**	
     * display the level of notification
     * @return
     */
    public static ReferenceList getListNotification() {

        ReferenceList refenreceList = new ReferenceList();
        refenreceList.addItem(0, NotifyGruConstants.VISIBILITY_ALL);
        refenreceList.addItem(1, NotifyGruConstants.VISIBILITY_DOMAIN);
        refenreceList.addItem(2, NotifyGruConstants.VISIBILITY_ADMIN);

        return refenreceList;
    }
    
    
    /**
     * display the error message
     * @param _errors
     * @param request
     * @return the error message
     */
    public static String displayErrorMessage(ArrayList<String> _errors, HttpServletRequest request){
    	
          Object[] tabRequiredFields = new Object[_errors.size()];
          for(int i=0;i<_errors.size();i++)
          {
        	  tabRequiredFields[i]=_errors.get(i);
          }
          if(tabRequiredFields.length > 2)
          {
          	return AdminMessageService.getMessageUrl(request,NotifyGruConstants.MESSAGE_MANDATORY_THREE_FIELD,
                      tabRequiredFields, AdminMessage.TYPE_WARNING);
          }
          else if(tabRequiredFields.length == 2)
            {
            	return AdminMessageService.getMessageUrl(request,NotifyGruConstants.MESSAGE_MANDATORY_TWO_FIELD,
                        tabRequiredFields, AdminMessage.TYPE_WARNING);
            }

            return AdminMessageService.getMessageUrl(request,NotifyGruConstants.MESSAGE_MANDATORY_ONE_FIELD,
                    tabRequiredFields, AdminMessage.TYPE_WARNING);
    }
    
    /**
     * 
     * @return
     */
     public static List<AbstractServiceProvider> getImplementationServices(  )
    {   
        return SpringContextService.getBeansOfType( AbstractServiceProvider.class );
    }
     /**
      * 
      * @return
      */
    public static ReferenceList getListProvider() {
          ReferenceList refenreceList = new ReferenceList();
        for ( AbstractServiceProvider provider : getImplementationServices(  ) )
        {
          
             refenreceList.addItem(provider.getBeanName(), provider.getTitle(Locale.getDefault()));
           
        }
        return refenreceList;
    }
    
    
    public static Boolean setConfigOnglet(String strApply, String strOnglet, String strOngletActive, Boolean bdefault, String strRemove) {
        
         Boolean bStateOnglet = bdefault;
         
           if(strApply!=null) {
      
               switch (strApply) {
            case NotifyGruConstants.PARAMETER_BUTTON_ADD:
                if(strOnglet.equals(strOngletActive)) bStateOnglet=true;                        
                break;
            case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET:
                if(strRemove.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET)) bStateOnglet = false;
                break;
            case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT:
                if(strRemove.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT)) bStateOnglet = false;
                break;
            case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL:
               if(strRemove.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL))  bStateOnglet = false;
                break;
            case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS:
                if(strRemove.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS)) bStateOnglet = false;
                break;
            case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE:
                 if(strRemove.equals(NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE)) bStateOnglet = false;
                break;             
        }
         }
    
        return bStateOnglet;
    }
    
    
    public static int getNumberOblet(String strOnglet){
        
        if(strOnglet==null) return 0;
        if(strOnglet.equals(NotifyGruConstants.MARK_ONGLET_GUICHET)) {
        return 0;
        }
        if(strOnglet.equals(NotifyGruConstants.MARK_ONGLET_AGENT)) {
        return 1;
        }
        if(strOnglet.equals(NotifyGruConstants.MARK_ONGLET_EMAIL)) {
        return 2;
        }
        if(strOnglet.equals(NotifyGruConstants.MARK_ONGLET_SMS)) {
        return 3;
        }
        if(strOnglet.equals(NotifyGruConstants.MARK_ONGLET_LIST)) {
        return 4;
        }
        
          return 0;
    }
    
}
