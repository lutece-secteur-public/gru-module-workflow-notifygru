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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Collection;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import fr.paris.lutece.plugins.workflowcore.service.provider.InfoMarker;
import fr.paris.lutece.test.LuteceTestCase;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Renders a stored notification text per recipient with the rich markers of the providers (LUT-33153), the conversions
 * done by the core (RichTextContentService, XSSSanitizerService, hence the CDI container of LuteceTestCase): an HTML
 * message keeps the formatting of a rich marker and drops its scripts, and escapes the plain markers; a SMS or a status
 * text holds plain text, without tags, its entities decoded.
 */
public class NotifyGruRichMarkerRenderTest extends LuteceTestCase
{
    private static final String PROVIDER = "notifygru-forms.ProviderService";
    private static final NotifyGruMarkers.RichMarkers RICH = NotifyGruMarkers.readRichMarkers( Map.of( "workflow-notifygru.markers.rich",
            "comments,url_list", "workflow-notifygru.markers.rich.provider." + PROVIDER, "*" ) );
    private static final String COMMENT = "<p><strong>Dossier</strong> complet &amp; valid&eacute;</p><script>alert(1)</script>";
    private static final String URL_LIST = "<ul class='uploadTaskList'><li><a href=\"https://example.org/file?id=1&amp;key=2\">avis.pdf</a></li></ul>";
    private static final String FIRSTNAME = "Zoé <b>\"l'école\"</b> & co";
    private static final String ANSWERS = "a &lt;b&gt; &amp; c<br>d";
    private static final Map<String, Collection<InfoMarker>> MARKERS = Map.of( PROVIDER, List.of( new InfoMarker( "question_code", ANSWERS ) ),
            "workflow-notifygru.commentMarkerProvider", List.of( new InfoMarker( "comments", COMMENT ), new InfoMarker( "url_list", URL_LIST ),
                    new InfoMarker( "firstname", FIRSTNAME ) ) );

    /**
     * Renders a stored text as the string templates of the core are rendered.
     *
     * @param strTemplate
     *            the stored text
     * @param model
     *            the model
     * @return the rendering
     * @throws Exception
     *             if the rendering fails
     */
    private static String render( String strTemplate, Map<String, Object> model ) throws Exception
    {
        Configuration cfg = new Configuration( Configuration.VERSION_2_3_0 );
        cfg.setOutputFormat( HTMLOutputFormat.INSTANCE );
        cfg.setAutoEscapingPolicy( Configuration.ENABLE_IF_DEFAULT_AUTO_ESCAPING_POLICY );
        StringWriter writer = new StringWriter( );
        new Template( "a1b2c3", new StringReader( strTemplate ), cfg ).process( model, writer );

        return writer.toString( );
    }

    /**
     * An e-mail (and the guichet, agent and broadcast messages): the rich markers keep their formatting and links, without
     * script; a plain marker is escaped.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testMailKeepsTheFormattingWithoutScript( ) throws Exception
    {
        String strMail = render( "<p>Bonjour ${firstname!}</p>${comments!}<div>${url_list!}</div>", NotifyGruMarkers.toHtmlModel( MARKERS, RICH ) );

        assertTrue( strMail.contains( "<strong>Dossier</strong>" ), strMail );
        assertEquals( "https://example.org/file?id=1&key=2", Jsoup.parse( strMail ).select( "a" ).attr( "href" ), strMail );
        assertTrue( strMail.contains( "avis.pdf</a>" ), strMail );
        assertTrue( strMail.contains( "<li>" ), strMail );
        assertFalse( strMail.contains( "<script" ), strMail );
        assertFalse( strMail.contains( "alert(1)" ), strMail );
        assertTrue( strMail.contains( "<p>Bonjour Zoé &lt;b&gt;&quot;l&#39;école&quot;&lt;/b&gt; &amp; co</p>" ), strMail );
    }

    /**
     * A SMS: plain text, the rich markers without tags nor script, the entities decoded, a plain marker as typed.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testSmsIsPlainText( ) throws Exception
    {
        String strSms = TaskNotifyGru.toPlainText( render( "Bonjour ${firstname!} : ${comments!}", NotifyGruMarkers.toTextModel( MARKERS, RICH ) ) );

        assertEquals( "Bonjour " + FIRSTNAME + " : Dossier complet & validé", strSms );
    }

    /**
     * The answers of a provider declared rich with *: escaped parts joined by br, kept as HTML in a mail, one line each
     * in a SMS, entities decoded.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testProviderAnswersPerRecipient( ) throws Exception
    {
        String strMail = render( "<p>${question_code!}</p>", NotifyGruMarkers.toHtmlModel( MARKERS, RICH ) );
        String strSms = TaskNotifyGru.toPlainText( render( "${question_code!}", NotifyGruMarkers.toTextModel( MARKERS, RICH ) ) );

        assertTrue( strMail.contains( "a &lt;b&gt; &amp; c<br" ) && strMail.contains( "d</p>" ), strMail );
        assertEquals( "a <b> & c\nd", strSms );
    }

    /**
     * A GRU status text: plain text, the link list as its text.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testStatusTextIsPlainText( ) throws Exception
    {
        String strStatus = TaskNotifyGru.toPlainText( render( "Pièces : ${url_list!}", NotifyGruMarkers.toTextModel( MARKERS, RICH ) ) );

        assertEquals( "Pièces : avis.pdf", strStatus );
        assertFalse( strStatus.contains( "<" ), strStatus );
    }
}
