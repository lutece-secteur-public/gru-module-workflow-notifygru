/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.portal.service.i18n.I18nService;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fallphenix
 */
public abstract class AbstractServiceProvider implements IServiceProvider{
    
     private String _strKey;
    private String _strtitleI18nKey;
    private String _strbeanName;
    
      public String getKey(  )
    {
        return _strKey;
    }

       public String getBeanName(  )
    {
        return _strbeanName;
    }
 
    public void setKey( String strKey )
    {
        _strKey = strKey;
    }
    
     public void setBeanName( String strbeanName )
    {
        _strbeanName = strbeanName;
    }
    
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( _strtitleI18nKey, locale );
    }

     public String gettitleI18nKey( )
    {
        return _strtitleI18nKey;
    }

 
 
    public void settitleI18nKey( String strtitleI18nKey )
    {
        _strtitleI18nKey = strtitleI18nKey;
    }
    
     public boolean isInvoked( String strExtenderType )
    {
        if ( StringUtils.isNotBlank( strExtenderType ) )
        {
            return getKey(  ).equals( strExtenderType );
        }

        return false;
    }
    
}
