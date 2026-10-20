/*
 * Copyright (c) 2002-2026, City of Paris
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.plugins.workflowcore.service.provider.InfoMarker;

/**
 * The declaration of the rich markers, by key and by provider, and the routing of their values (LUT-33153): only a
 * declared marker is converted, a plain marker keeps its string. The conversions themselves (sanitized markup, plain
 * text) are tested with the core services in NotifyGruRichMarkerRenderTest.
 */
public class NotifyGruMarkersTest
{
    private static final String FORMS = "notifygru-forms.ProviderService";
    private static final String UPLOAD = "workflow-upload.taskUploadMarkerProvider";
    private static final String OTHER = "other.ProviderService";

    private static final NotifyGruMarkers.RichMarkers RICH = NotifyGruMarkers.readRichMarkers( Map.of(
            "workflow-notifygru.markers.rich", " comments, url_list ,, ",
            "workflow-notifygru.markers.rich.mymodule", "upload_*",
            "workflow-notifygru.markers.rich.provider." + FORMS, "*",
            "workflow-notifygru.markers.rich.provider." + UPLOAD, "files, position_*",
            "workflow-notifygru.markers.richer", "ignored",
            "workflow-notifygru.markers.rich.provider.", "ignored" ) );

    /**
     * The keys are trimmed, the empty ones dropped; the provider properties are read apart, by provider id; a property
     * that only starts like the declaration is ignored.
     */
    @Test
    public void testDeclarations( )
    {
        assertEquals( Set.of( "comments", "url_list", "upload_*" ), RICH.keys( ) );
        assertEquals( Set.of( FORMS, UPLOAD ), RICH.providers( ).keySet( ) );
        assertEquals( Set.of( "*" ), RICH.providers( ).get( FORMS ) );
        assertEquals( Set.of( "files", "position_*" ), RICH.providers( ).get( UPLOAD ) );
        assertTrue( NotifyGruMarkers.parseKeys( null ).isEmpty( ) );
    }

    /**
     * A key declared for every provider, a prefix, * for all the markers of a provider, and keys for one provider only.
     */
    @Test
    public void testRichMarkers( )
    {
        assertTrue( RICH.isRich( OTHER, "comments" ) );
        assertTrue( RICH.isRich( null, "url_list" ) );
        assertTrue( RICH.isRich( OTHER, "upload_files" ) );
        assertFalse( RICH.isRich( OTHER, "comment" ) );
        assertFalse( RICH.isRich( OTHER, "firstname" ) );
        assertFalse( RICH.isRich( null, "firstname" ) );

        assertTrue( RICH.isRich( FORMS, "any_question_code" ) );
        assertTrue( RICH.isRich( FORMS, "position_12" ) );
        assertTrue( RICH.isRich( UPLOAD, "files" ) );
        assertTrue( RICH.isRich( UPLOAD, "position_3" ) );
        assertFalse( RICH.isRich( UPLOAD, "firstname" ) );
        assertFalse( RICH.isRich( OTHER, "position_12" ) );
        assertFalse( RICH.isRich( OTHER, null ) );
    }

    /**
     * A rich value goes through the conversion, a plain value and a null value are kept; the markers of a provider
     * declared with * are all converted, the same key from another provider is not.
     */
    @Test
    public void testOnlyRichValuesAreConverted( )
    {
        Map<String, Collection<InfoMarker>> markers = new LinkedHashMap<>( );
        markers.put( FORMS, List.of( new InfoMarker( "question_a", "a &lt;b&gt;<br>c" ) ) );
        markers.put( OTHER, List.of( new InfoMarker( "comments", "<p>x</p>" ), new InfoMarker( "firstname", "<b>Zoé</b>" ),
                new InfoMarker( "question_b", "<i>y</i>" ), new InfoMarker( "url_list" ) ) );

        Map<String, Object> model = NotifyGruMarkers.toModel( markers, RICH, v -> "converted:" + v );

        assertEquals( "converted:a &lt;b&gt;<br>c", model.get( "question_a" ) );
        assertEquals( "converted:<p>x</p>", model.get( "comments" ) );
        assertEquals( "<b>Zoé</b>", model.get( "firstname" ) );
        assertEquals( "<i>y</i>", model.get( "question_b" ) );
        assertTrue( model.containsKey( "url_list" ) );
        assertNull( model.get( "url_list" ) );
    }
}
