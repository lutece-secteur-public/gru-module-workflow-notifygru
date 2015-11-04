 /*
 * Copyright (c) 2002-2012, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.module.notifygru.web.rs;

import fr.paris.lutece.plugins.workflow.module.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.plugins.rest.util.json.JSONUtil;
import fr.paris.lutece.plugins.rest.util.xml.XMLUtil;
import fr.paris.lutece.plugins.workflow.module.notifygru.business.NotifygruHome;
import fr.paris.lutece.util.xml.XmlUtil;
import fr.paris.lutece.portal.service.util.AppLogService;
import java.io.IOException;

import net.sf.json.JSONObject;

import java.util.Collection;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Page resource
 */
 
@Path( RestConstants.BASE_PATH + Constants.PLUGIN_PATH + Constants.NOTIFYGRU_PATH )
public class NotifygruRest
{
    private static final String KEY_NOTIFYGRUS = "notifygrus";
    private static final String KEY_NOTIFYGRU = "notifygru";
    
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ID_TASK = "id_task";
    
    @GET
    @Path( Constants.ALL_PATH )
    public Response getNotifygrus( @HeaderParam(HttpHeaders.ACCEPT) String accept , @QueryParam( Constants.FORMAT_QUERY ) String format ) throws IOException
    {
        String entity;
        String mediaType;
        
        if ( (accept != null && accept.contains(MediaType.APPLICATION_JSON)) || (format != null && format.equals(Constants.MEDIA_TYPE_JSON)) )
        {
            entity = getNotifygrusJson();
            mediaType = MediaType.APPLICATION_JSON;
        }
        else
        {
            entity = getNotifygrusXml();
            mediaType = MediaType.APPLICATION_XML;
        }
        return Response
            .ok(entity, mediaType)
            .build();
    }
    
    /**
     * Gets all resources list in XML format
     * @return The list
     */
    public String getNotifygrusXml( )
    {
        StringBuffer sbXML = new StringBuffer( XmlUtil.getXmlHeader() );
        Collection<TaskNotifyGruConfig> list = NotifygruHome.getNotifygrusList();
        
        XmlUtil.beginElement( sbXML , KEY_NOTIFYGRUS );

        for ( TaskNotifyGruConfig notifygru : list )
        {
            addNotifygruXml( sbXML, notifygru );
        }
        
        XmlUtil.endElement( sbXML , KEY_NOTIFYGRUS );

        return sbXML.toString(  );
    }
    
    /**
     * Gets all resources list in JSON format
     * @return The list
     */
    public String getNotifygrusJson( )
    {
        JSONObject jsonNotifygru = new JSONObject(  );
        JSONObject json = new JSONObject(  );
        
        Collection<TaskNotifyGruConfig> list = NotifygruHome.getNotifygrusList();
        
        for ( TaskNotifyGruConfig notifygru : list )
        {
            addNotifygruJson( jsonNotifygru, notifygru );
        }
        
        json.accumulate( KEY_NOTIFYGRUS, jsonNotifygru );
        
        return json.toString( );
    }
    
    @GET
    @Path( "{" + Constants.ID_PATH + "}" )
    public Response getNotifygru( @PathParam( Constants.ID_PATH ) String strId, @HeaderParam(HttpHeaders.ACCEPT) String accept , @QueryParam( Constants.FORMAT_QUERY ) String format ) throws IOException
    {
        String entity;
        String mediaType;
        
        if ( (accept != null && accept.contains(MediaType.APPLICATION_JSON)) || (format != null && format.equals(Constants.MEDIA_TYPE_JSON)) )
        {
            entity = getNotifygruJson( strId );
            mediaType = MediaType.APPLICATION_JSON;
        }
        else
        {
            entity = getNotifygruXml( strId );
            mediaType = MediaType.APPLICATION_XML;
        }
        return Response
            .ok(entity, mediaType)
            .build();
    }
    
    /**
     * Gets a resource in XML format
     * @param strId The resource ID
     * @return The XML output
     */
    public String getNotifygruXml( String strId )
    {
        StringBuffer sbXML = new StringBuffer(  );
        
        try
        {
            int nId = Integer.parseInt( strId );
            TaskNotifyGruConfig notifygru = NotifygruHome.findByPrimaryKey( nId );

            if ( notifygru != null )
            {
                sbXML.append( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" );
                addNotifygruXml( sbXML, notifygru );
            }
        }
        catch ( NumberFormatException e )
        {
            sbXML.append( XMLUtil.formatError( "Invalid notifygru number", 3 ) );
        }
        catch ( Exception e )
        {
            sbXML.append( XMLUtil.formatError( "Notifygru not found", 1 ) );
        }

        return sbXML.toString(  );
    }
    
    /**
     * Gets a resource in JSON format
     * @param strId The resource ID
     * @return The JSON output
     */
    public String getNotifygruJson( String strId )
    {
        JSONObject json = new JSONObject(  );
        String strJson = "";

        try
        {
            int nId = Integer.parseInt( strId );
            TaskNotifyGruConfig notifygru = NotifygruHome.findByPrimaryKey( nId );

            if ( notifygru != null )
            {
                addNotifygruJson( json, notifygru );
                strJson = json.toString( );
            }
        }
        catch ( NumberFormatException e )
        {
            strJson = JSONUtil.formatError( "Invalid notifygru number", 3 );
        }
        catch ( Exception e )
        {
            strJson = JSONUtil.formatError( "Notifygru not found", 1 );
        }

        return strJson;
    }
    
    @DELETE
    @Path( "{" + Constants.ID_PATH + "}" )
    public Response deleteNotifygru( @PathParam( Constants.ID_PATH ) String strId, @HeaderParam(HttpHeaders.ACCEPT) String accept, @QueryParam( Constants.FORMAT_QUERY ) String format ) throws IOException
    {
        try
        {
            int nId = Integer.parseInt( strId );
            
            if ( NotifygruHome.findByPrimaryKey( nId ) != null )
            {
                NotifygruHome.remove( nId );
            }
        }
        catch ( NumberFormatException e )
        {
            AppLogService.error( "Invalid notifygru number" );
        }
        return getNotifygrus(accept, format);
    }
    
    @POST
    public Response createNotifygru(
    @FormParam( KEY_ID ) String id,
    @FormParam( "title" ) String title, 
    @FormParam( "id_task" ) String id_task, 
    @HeaderParam(HttpHeaders.ACCEPT) String accept, @QueryParam( Constants.FORMAT_QUERY ) String format) throws IOException
    {
        if( id != null )
        {
            int nId = Integer.parseInt( KEY_ID );

            TaskNotifyGruConfig notifygru = NotifygruHome.findByPrimaryKey( nId );

            if ( notifygru != null )
            {
                notifygru.setTitle( title );
                notifygru.setIdTask( Integer.parseInt( id_task ) );
                NotifygruHome.update( notifygru );
            }
        }
        else
        {
            TaskNotifyGruConfig notifygru = new TaskNotifyGruConfig( );
            
            notifygru.setTitle( title );
            notifygru.setIdTask( Integer.parseInt( id_task ) );
            NotifygruHome.create( notifygru );
        }
        return getNotifygrus(accept, format);
    }
    
    /**
     * Write a notifygru into a buffer
     * @param sbXML The buffer
     * @param notifygru The notifygru
     */
    private void addNotifygruXml( StringBuffer sbXML, TaskNotifyGruConfig notifygru )
    {
        XmlUtil.beginElement( sbXML, KEY_NOTIFYGRU );
        XmlUtil.addElement( sbXML, KEY_ID , notifygru.getId( ) );
        XmlUtil.addElement( sbXML, KEY_TITLE , notifygru.getTitle( ) );
        XmlUtil.addElement( sbXML, KEY_ID_TASK , notifygru.getIdTask( ) );
        XmlUtil.endElement( sbXML, KEY_NOTIFYGRU );
    }
    
    /**
     * Write a notifygru into a JSON Object
     * @param json The JSON Object
     * @param notifygru The notifygru
     */
    private void addNotifygruJson( JSONObject json, TaskNotifyGruConfig notifygru )
    {
        JSONObject jsonNotifygru = new JSONObject(  );
        jsonNotifygru.accumulate( KEY_ID , notifygru.getId( ) );
        jsonNotifygru.accumulate( KEY_TITLE, notifygru.getTitle( ) );
        jsonNotifygru.accumulate( KEY_ID_TASK, notifygru.getIdTask( ) );
        json.accumulate( KEY_NOTIFYGRU, jsonNotifygru );
    }
}