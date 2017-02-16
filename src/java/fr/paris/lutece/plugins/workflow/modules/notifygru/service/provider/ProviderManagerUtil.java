package fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider;

import org.jsoup.helper.StringUtil;

import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;

public class ProviderManagerUtil
{
    private static final String SEPARATOR = ".@.";
    
    public static AbstractProviderManager fetchProviderManager( String strProviderManagerId )
    {
        AbstractProviderManager result = null;
        
        try {
            result = SpringContextService.getBean( strProviderManagerId );
        }
        catch( Exception e )
        {
            AppLogService.error( "Unable to retrieve the provider manager '" + strProviderManagerId + "'" );
        }
        
        return result;
    }
    
    public static String buildCompleteProviderId( String strProviderManagerId, String strProviderId )
    {
        return strProviderManagerId + SEPARATOR + strProviderId;
    }
    
    public static String fetchProviderManagerId( String strCompleteProviderId )
    {
        if ( !StringUtil.isBlank( strCompleteProviderId ) )
        {
            return strCompleteProviderId.split( SEPARATOR )[0];
        }
        
        return null;
    }
    
    public static String fetchProviderId( String strCompleteProviderId )
    {
        String strResult = null;
        
        if ( !StringUtil.isBlank( strCompleteProviderId ) )
        {
            String[] listIds =  strCompleteProviderId.split( SEPARATOR );
            
            if ( listIds.length > 1 )
            {
                strResult = listIds[1];
            }
        }
        
         return strResult;
    }
}
