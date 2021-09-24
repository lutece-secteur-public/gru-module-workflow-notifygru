/*
 * Copyright (c) 2002-2021, City of Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.web.notificationconfig.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockHttpServletRequest;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.AbstractNotificationConfigValidator;
import fr.paris.lutece.test.LuteceTestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AgentNotificationConfigValidatorTest extends LuteceTestCase
{
    // PARAMETERS
    private static final String PARAMETER_MESSAGE = "message_agent";
    private static final String PARAMETER_STATUS_TEXT = "status_text_agent";

    // MARKS
    private static final String MARK = "mark";

    private static final String FIELD_WITH_CORRECT_MARK = "message with ${mark}";
    private static final String FIELD_WITH_UNKNOW_MARK = "message with ${unknown}";

    private MockHttpServletRequest _request;
    private AbstractNotificationConfigValidator _validator;

    public void testValidationWhenNoFieldIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationSucceeds( strErrorUrl );
    }

    private void initRequest( )
    {
        _request = new MockHttpServletRequest( );
    }

    private void fillRequest( )
    {
        _request.addParameter( PARAMETER_MESSAGE, FIELD_WITH_CORRECT_MARK );
        _request.addParameter( PARAMETER_STATUS_TEXT, FIELD_WITH_CORRECT_MARK );
    }

    private void initValidator( ) throws Exception
    {
        TaskNotifyGruConfig config = new TaskNotifyGruConfig( );

        _validator = new AgentNotificationConfig( _request, config ).getValidator( );
    }

    private Map<String, Object> initModel( )
    {
        Map<String, Object> model = new HashMap<>( );

        model.put( MARK, PARAMETER_MESSAGE );

        return model;
    }

    private void assertThatValidationSucceeds( String strErrorUrl )
    {
        assertThat( StringUtils.isEmpty( strErrorUrl ), is( true ) );
    }

    public void testValidationWhenMessageIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MESSAGE, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    private void replaceRequestParameter( String strParameterName, String strParameterValue )
    {
        _request.removeParameter( strParameterName );
        _request.addParameter( strParameterName, strParameterValue );
    }

    private void assertThatValidationFails( String strErrorUrl )
    {
        assertThat( StringUtils.isEmpty( strErrorUrl ), is( false ) );
    }

    public void testValidationWhenMessageContainsUnknownMark( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MESSAGE, FIELD_WITH_UNKNOW_MARK );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenStatusIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_STATUS_TEXT, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }
}
