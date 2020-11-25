/*
 * Copyright (c) 2002-2020, City of Paris
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
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockHttpServletRequest;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.AbstractNotificationConfigValidator;
import fr.paris.lutece.test.LuteceTestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BroadcastNotificationConfigValidatorTest extends LuteceTestCase
{
    // PARAMETERS
    private static final String PARAMETER_MAILING_TYPE = "mailing_type";
    private static final String PARAMETER_ID_MAILING_LIST = "id_mailing_list_broadcast";
    private static final String PARAMETER_MAIL_SPECIFIC = "mailing_spec";
    private static final String PARAMETER_SENDER_NAME = "sender_name_broadcast";
    private static final String PARAMETER_SUBJECT = "subject_broadcast";
    private static final String PARAMETER_MESSAGE = "message_broadcast";
    private static final String PARAMETER_RECIPIENT_CC = "recipients_cc_broadcast";
    private static final String PARAMETER_RECIPIENT_CCI = "recipients_cci_broadcast";

    // MARKS
    private static final String MARK = "mark";

    private static final String FIELD_WITH_CORRECT_MARK = "message with ${mark}";
    private static final String FIELD_WITH_UNKNOW_MARK = "message with ${unknown}";

    private static final String TYPE_MAILING_TYPE_LIST = "list";
    private static final String TYPE_MAILING_TYPE_SPECIFIC = "spec";
    private static final int ID_MAILING_LIST = new Random( ).nextInt( 100 );

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
        _request.addParameter( PARAMETER_MAILING_TYPE, TYPE_MAILING_TYPE_SPECIFIC );
        _request.addParameter( PARAMETER_ID_MAILING_LIST, String.valueOf( ID_MAILING_LIST ) );
        _request.addParameter( PARAMETER_MAIL_SPECIFIC, FIELD_WITH_CORRECT_MARK );
        _request.addParameter( PARAMETER_SENDER_NAME, FIELD_WITH_CORRECT_MARK );
        _request.addParameter( PARAMETER_SUBJECT, FIELD_WITH_CORRECT_MARK );
        _request.addParameter( PARAMETER_MESSAGE, FIELD_WITH_CORRECT_MARK );
        _request.addParameter( PARAMETER_RECIPIENT_CC, FIELD_WITH_CORRECT_MARK );
        _request.addParameter( PARAMETER_RECIPIENT_CCI, FIELD_WITH_CORRECT_MARK );
    }

    private void initValidator( ) throws Exception
    {
        TaskNotifyGruConfig config = new TaskNotifyGruConfig( );

        _validator = new BroadcastNotificationConfig( _request, config ).getValidator( );
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

    public void testValidationWhenMailingListIdIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MAILING_TYPE, TYPE_MAILING_TYPE_LIST );
        replaceRequestParameter( PARAMETER_ID_MAILING_LIST, null );
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

    public void testValidationWhenMailingListIdIsNegative( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MAILING_TYPE, String.valueOf( ID_MAILING_LIST * -1 ) );
        replaceRequestParameter( PARAMETER_ID_MAILING_LIST, FIELD_WITH_CORRECT_MARK );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenMailingListIdIsNotANumber( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MAILING_TYPE, TYPE_MAILING_TYPE_LIST );
        replaceRequestParameter( PARAMETER_ID_MAILING_LIST, FIELD_WITH_CORRECT_MARK );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenSpecificEmailIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MAILING_TYPE, TYPE_MAILING_TYPE_SPECIFIC );
        replaceRequestParameter( PARAMETER_MAIL_SPECIFIC, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenSenderNameIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_SENDER_NAME, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenSubjectIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_SUBJECT, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenSubjectContainsUnknownMark( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_SUBJECT, FIELD_WITH_UNKNOW_MARK );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
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

    public void testValidationWhenMessageContainsUnknownMark( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_MESSAGE, FIELD_WITH_UNKNOW_MARK );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationFails( strErrorUrl );
    }

    public void testValidationWhenRecipientCcIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_RECIPIENT_CC, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationSucceeds( strErrorUrl );
    }

    public void testValidationWhenRecipientCciIsEmpty( ) throws Exception
    {
        initRequest( );
        fillRequest( );
        replaceRequestParameter( PARAMETER_RECIPIENT_CCI, null );
        initValidator( );

        String strErrorUrl = _validator.validate( initModel( ) );

        assertThatValidationSucceeds( strErrorUrl );
    }
}
