/*
 * Copyright (c) 2002-2025, City of Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import fr.paris.lutece.plugins.workflowcore.service.provider.InfoMarker;
import fr.paris.lutece.portal.service.editor.RichTextContentService;
import fr.paris.lutece.portal.service.editor.RichTextParsingException;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
 * The models of the notification texts, built from the markers of the providers (LUT-33153).
 * <p>
 * A marker value is plain text, escaped by the HTML messages, unless the marker is declared rich: its value is then
 * HTML (or a Markdown / BBCode content of a rich text parser) meant to be shown formatted. A rich value is rendered as
 * sanitized markup in the HTML messages (e-mail, guichet, agent, broadcast) and as plain text in the text fields (SMS,
 * subjects, status texts, recipients).
 * </p>
 * <p>
 * Contract of a marker provider: a rich marker is declared in a property of the provider's own properties file, either
 * by key, <code>workflow-notifygru.markers.rich.&lt;module&gt;=key1,key2,prefix*</code>, or for the markers of one
 * provider, <code>workflow-notifygru.markers.rich.provider.&lt;provider id&gt;=*</code> (all its markers) or
 * <code>=key1,prefix*</code>. The provider id is the id of the provider manager for the provider of the resource, the
 * id of the marker provider (IMarkerProvider.getId) for an additional marker provider. A trailing <code>*</code> matches
 * every key starting with the prefix. Any other marker holds text: no HTML built in Java, no escaping by hand.
 * </p>
 */
public final class NotifyGruMarkers
{
    /** The property declaring the rich markers of notifygru, and the prefix of the properties declared by other modules */
    public static final String PROPERTY_RICH_MARKERS = "workflow-notifygru.markers.rich";
    /** The prefix of the properties declaring the rich markers of one provider, followed by the provider id */
    public static final String PROPERTY_RICH_MARKERS_PROVIDER = PROPERTY_RICH_MARKERS + ".provider.";
    private static final String WILDCARD = "*";
    private static final String SEPARATOR = ",";

    /**
     * The declared rich markers.
     *
     * @param keys
     *            the keys rich whatever the provider
     * @param providers
     *            the keys rich for one provider, by provider id
     */
    public record RichMarkers( Set<String> keys, Map<String, Set<String>> providers )
    {
        /**
         * Tells whether a marker of a provider is rich.
         *
         * @param strProviderId
         *            the id of the provider of the marker, may be null
         * @param strMarker
         *            the marker key
         * @return true if the key is declared, or the key for this provider
         */
        public boolean isRich( String strProviderId, String strMarker )
        {
            return matches( strMarker, keys ) || ( strProviderId != null && matches( strMarker, providers.getOrDefault( strProviderId, Set.of( ) ) ) );
        }
    }

    /**
     * Static methods only.
     */
    private NotifyGruMarkers( )
    {
    }

    /**
     * Reads the rich markers declared in the properties: {@link #PROPERTY_RICH_MARKERS},
     * <code>workflow-notifygru.markers.rich.&lt;module&gt;</code> and
     * <code>workflow-notifygru.markers.rich.provider.&lt;provider id&gt;</code>.
     *
     * @return the declarations
     */
    public static RichMarkers getRichMarkers( )
    {
        Map<String, String> mapProperties = new LinkedHashMap<>( );
        for ( String strProperty : AppPropertiesService.getKeys( PROPERTY_RICH_MARKERS ) )
        {
            mapProperties.put( strProperty, AppPropertiesService.getProperty( strProperty ) );
        }
        return readRichMarkers( mapProperties );
    }

    /**
     * Reads the rich markers declared in a set of properties.
     *
     * @param mapProperties
     *            the properties, by name
     * @return the declarations
     */
    static RichMarkers readRichMarkers( Map<String, String> mapProperties )
    {
        Set<String> setKeys = new LinkedHashSet<>( );
        Map<String, Set<String>> mapProviders = new HashMap<>( );
        for ( Map.Entry<String, String> property : mapProperties.entrySet( ) )
        {
            String strName = property.getKey( );
            if ( strName.startsWith( PROPERTY_RICH_MARKERS_PROVIDER ) )
            {
                String strProviderId = strName.substring( PROPERTY_RICH_MARKERS_PROVIDER.length( ) );
                if ( !strProviderId.isEmpty( ) )
                {
                    mapProviders.computeIfAbsent( strProviderId, k -> new LinkedHashSet<>( ) ).addAll( parseKeys( property.getValue( ) ) );
                }
            }
            else
                if ( strName.equals( PROPERTY_RICH_MARKERS ) || strName.startsWith( PROPERTY_RICH_MARKERS + "." ) )
                {
                    setKeys.addAll( parseKeys( property.getValue( ) ) );
                }
        }
        return new RichMarkers( setKeys, mapProviders );
    }

    /**
     * Splits a comma separated list of marker keys.
     *
     * @param strKeys
     *            the list, may be null
     * @return the keys, trimmed, without the empty ones
     */
    static Set<String> parseKeys( String strKeys )
    {
        Set<String> setKeys = new LinkedHashSet<>( );
        if ( strKeys != null )
        {
            for ( String strKey : strKeys.split( SEPARATOR ) )
            {
                if ( StringUtils.isNotBlank( strKey ) )
                {
                    setKeys.add( strKey.trim( ) );
                }
            }
        }
        return setKeys;
    }

    /**
     * Tells whether a marker key matches a set of declared keys.
     *
     * @param strMarker
     *            the marker key
     * @param setKeys
     *            the declared keys, a key ending with * being a prefix (* alone: every key)
     * @return true if the key is declared, or starts with a declared prefix
     */
    static boolean matches( String strMarker, Set<String> setKeys )
    {
        if ( strMarker == null )
        {
            return false;
        }
        for ( String strKey : setKeys )
        {
            if ( strKey.endsWith( WILDCARD ) ? strMarker.startsWith( StringUtils.removeEnd( strKey, WILDCARD ) ) : strMarker.equals( strKey ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds the model of the HTML messages: a rich value as sanitized markup, any other value as a string the template
     * escapes.
     *
     * @param mapMarkers
     *            the markers by provider id, in the order the providers are called
     * @param richMarkers
     *            the declared rich markers
     * @return the model
     */
    public static Map<String, Object> toHtmlModel( Map<String, Collection<InfoMarker>> mapMarkers, RichMarkers richMarkers )
    {
        return toModel( mapMarkers, richMarkers, RichTextContentService::getMarkup );
    }

    /**
     * Builds the model of the text fields: a rich value as plain text (no tag, entities decoded), any other value as is.
     *
     * @param mapMarkers
     *            the markers by provider id, in the order the providers are called
     * @param richMarkers
     *            the declared rich markers
     * @return the model
     */
    public static Map<String, Object> toTextModel( Map<String, Collection<InfoMarker>> mapMarkers, RichMarkers richMarkers )
    {
        return toModel( mapMarkers, richMarkers, NotifyGruMarkers::richToText );
    }

    /**
     * Builds a model, converting the rich values. A marker of a later provider replaces a marker of the same key.
     *
     * @param mapMarkers
     *            the markers by provider id, in the order the providers are called
     * @param richMarkers
     *            the declared rich markers
     * @param richConverter
     *            the conversion of a rich value
     * @return the model, a null value kept null
     */
    static Map<String, Object> toModel( Map<String, Collection<InfoMarker>> mapMarkers, RichMarkers richMarkers, Function<String, Object> richConverter )
    {
        Map<String, Object> model = new HashMap<>( );
        for ( Map.Entry<String, Collection<InfoMarker>> provider : mapMarkers.entrySet( ) )
        {
            for ( InfoMarker marker : provider.getValue( ) )
            {
                String strValue = marker.getValue( );
                boolean bRich = strValue != null && richMarkers.isRich( provider.getKey( ), marker.getMarker( ) );
                model.put( marker.getMarker( ), bRich ? richConverter.apply( strValue ) : strValue );
            }
        }
        return model;
    }

    /**
     * Converts a rich value to plain text.
     *
     * @param strValue
     *            the rich value
     * @return the text; if the content cannot be converted, the text of its HTML
     */
    private static Object richToText( String strValue )
    {
        try
        {
            return RichTextContentService.getText( strValue );
        }
        catch( RichTextParsingException e )
        {
            AppLogService.error( "Rich marker value written as the text of its HTML: it could not be converted", e );
            return Jsoup.parse( strValue ).text( );
        }
    }
}
