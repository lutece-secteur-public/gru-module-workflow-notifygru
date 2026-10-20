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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import freemarker.core.HTMLOutputFormat;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Renders a notification text stored in workflow_task_notify_gru_cf, a FreeMarker template with markers, as
 * TaskNotifyGru does with the string templates of AppTemplateService (HTML output format, auto-escaping; LUT-33153):
 * before the upgrade script a stored ?html no longer parses; after it, the HTML message keeps the tags the administrator
 * wrote and escapes the marker values, and a text (SMS, subject) shows the values as they are. The upgrade script is read
 * and applied as the database would. Only the FreeMarker engine is needed.
 */
public class NotifyGruStoredMessageRenderTest
{
    private static final String UPGRADE_SCRIPT = "src/sql/plugins/workflow/modules/notifygru/upgrade/upgrade_db_workflow_notifygru-3.0.0-3.0.1.sql";

    /** The columns of workflow_task_notify_gru_cf that TaskNotifyGru renders as templates. */
    private static final List<String> TEMPLATED_COLUMNS = List.of( "message_guichet", "status_text_guichet", "subject_guichet", "status_text_agent",
            "message_agent", "subject_email", "message_email", "message_sms", "email_broadcast", "recipients_cc_broadcast", "subject_broadcast",
            "message_broadcast" );

    private static final Pattern UPDATE = Pattern.compile( "UPDATE workflow_task_notify_gru_cf SET (\\w+) = (.*?)\\s+WHERE", Pattern.DOTALL );
    private static final Pattern REPLACED = Pattern.compile( "'(\\?\\w+)', ''" );

    private static final String MESSAGE_EMAIL = "<p>Bonjour ${firstname!?html} ${lastname!},</p><p>Votre demande ${reference!?html} est traitée.</p>";
    private static final String MESSAGE_SMS = "Bonjour ${firstname!?html}, votre demande ${reference!} est traitée.";
    private static final String FIRSTNAME = "Zoé <b>\"l'école\"</b> & co";
    private static final Map<String, Object> MODEL = Map.of( "firstname", FIRSTNAME, "lastname", "Martin", "reference", "42" );
    private static final String FIRSTNAME_ESCAPED = "Zoé &lt;b&gt;&quot;l&#39;école&quot;&lt;/b&gt; &amp; co";

    /**
     * Renders a stored text as the string templates of the core are rendered.
     *
     * @param strTemplate
     *            the stored text
     * @return the rendering
     * @throws IOException
     *             if the text does not parse
     * @throws TemplateException
     *             if the rendering fails
     */
    private static String render( String strTemplate ) throws IOException, TemplateException
    {
        Configuration cfg = new Configuration( Configuration.VERSION_2_3_0 );
        cfg.setOutputFormat( HTMLOutputFormat.INSTANCE );
        cfg.setAutoEscapingPolicy( Configuration.ENABLE_IF_DEFAULT_AUTO_ESCAPING_POLICY );
        StringWriter writer = new StringWriter( );
        new Template( "a1b2c3", new StringReader( strTemplate ), cfg ).process( MODEL, writer );

        return writer.toString( );
    }

    /**
     * Reads the built-ins the upgrade script removes from each column, in the order of its REPLACE calls.
     *
     * @return the built-ins by column
     * @throws IOException
     *             if the script cannot be read
     */
    private static Map<String, List<String>> upgrade( ) throws IOException
    {
        String strScript = Files.readString( Path.of( UPGRADE_SCRIPT ), StandardCharsets.UTF_8 );
        Map<String, List<String>> mapReplaced = new LinkedHashMap<>( );
        Matcher update = UPDATE.matcher( strScript );
        while ( update.find( ) )
        {
            List<String> listBuiltIns = new ArrayList<>( );
            Matcher replaced = REPLACED.matcher( update.group( 2 ) );
            while ( replaced.find( ) )
            {
                listBuiltIns.add( replaced.group( 1 ) );
            }
            mapReplaced.put( update.group( 1 ), listBuiltIns );
        }
        return mapReplaced;
    }

    /**
     * Applies the upgrade script to a stored value of a column, as REPLACE does.
     *
     * @param strColumn
     *            the column
     * @param strValue
     *            the stored value
     * @return the value after the upgrade
     * @throws IOException
     *             if the script cannot be read
     */
    private static String applyUpgrade( String strColumn, String strValue ) throws IOException
    {
        String strUpgraded = strValue;
        for ( String strBuiltIn : upgrade( ).get( strColumn ) )
        {
            strUpgraded = strUpgraded.replace( strBuiltIn, "" );
        }
        return strUpgraded;
    }

    /**
     * Before the upgrade, a stored ?html stops the parse: the notification would fail when the task runs.
     */
    @Test
    public void testStoredHtmlBuiltInNoLongerParses( )
    {
        assertThrows( ParseException.class, ( ) -> render( MESSAGE_EMAIL ) );
        assertThrows( ParseException.class, ( ) -> render( MESSAGE_SMS ) );
    }

    /**
     * After the upgrade, the HTML message keeps its tags and every marker value is escaped once, the one written with
     * ?html as before, the one written without it as well (it was written raw).
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testUpgradedHtmlMessageEscapesTheMarkers( ) throws Exception
    {
        String strHtml = render( applyUpgrade( "message_email", MESSAGE_EMAIL ) );

        assertEquals( "<p>Bonjour " + FIRSTNAME_ESCAPED + " Martin,</p><p>Votre demande 42 est traitée.</p>", strHtml );
    }

    /**
     * After the upgrade, a text (SMS) shows the marker values as they are: TaskNotifyGru decodes the escaping of the
     * string template once.
     *
     * @throws Exception
     *             if the rendering fails
     */
    @Test
    public void testUpgradedSmsIsPlainText( ) throws Exception
    {
        String strSms = TaskNotifyGru.toPlainText( render( applyUpgrade( "message_sms", MESSAGE_SMS ) ) );

        assertEquals( "Bonjour " + FIRSTNAME + ", votre demande 42 est traitée.", strSms );
    }

    /**
     * The upgrade script covers every templated column, removes every hand escaping built-in, and a second run changes
     * nothing.
     *
     * @throws Exception
     *             if the script cannot be read
     */
    @Test
    public void testUpgradeCoversEveryColumnAndIsIdempotent( ) throws Exception
    {
        Map<String, List<String>> mapReplaced = upgrade( );

        assertEquals( TEMPLATED_COLUMNS, new ArrayList<>( mapReplaced.keySet( ) ) );
        for ( String strColumn : TEMPLATED_COLUMNS )
        {
            assertTrue( mapReplaced.get( strColumn ).containsAll( List.of( "?html", "?xhtml", "?xml", "?rtf", "?web_safe" ) ), strColumn );
            String strOnce = applyUpgrade( strColumn, "${a!?html} ${b?xhtml} ${c?xml} ${d?rtf} ${e?web_safe}" );
            assertEquals( "${a!} ${b} ${c} ${d} ${e}", strOnce, strColumn );
            assertEquals( strOnce, applyUpgrade( strColumn, strOnce ), strColumn );
            assertFalse( strOnce.contains( "?" ), strColumn );
        }
    }
}
