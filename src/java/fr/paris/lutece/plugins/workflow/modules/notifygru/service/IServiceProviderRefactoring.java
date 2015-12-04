package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 
 *
 */
public interface IServiceProviderRefactoring
{
    /**
     * give the email of user
     * @return the userEmail
     * @param nIdResource the _nIdResource to set
     */
    String getUserEmail( int nIdResource );
    
     /**
     * give the email of user
     * @return the userEmail
     * @param nIdResource the _nIdResource to set
     */
    String getUserPhoneNumber( int nIdResource );

    /**
         * @return the userGuid
         * @param nIdResource the _nIdResource to set
         */
    String getUserGuid( int nIdResource );

    /**
         * @return the status of the resource
         * @param nIdResource the _nIdResource to set
         */
    String getStatus( int nIdResource );

    /**
         * @return the help to put the values of the resource
         * @param request 
         * @param model 
         */
    String getInfosHelp( HttpServletRequest request, Map<String, Object> model );

    /**
         * @return the resource of id _nIdResource
         * @param nIdResource the _nIdResource to set
         */
    Object getInfos( int nIdResource );
}
