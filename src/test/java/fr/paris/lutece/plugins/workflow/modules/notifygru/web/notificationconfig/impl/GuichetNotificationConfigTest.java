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

import org.springframework.mock.web.MockHttpServletRequest;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.INotificationConfig;
import fr.paris.lutece.test.LuteceTestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Random;

public class GuichetNotificationConfigTest extends LuteceTestCase
{
    private static final String NAME = "guichet";

    // PARAMETERS
    private static final String PARAMETER_MESSAGE = "message_guichet";
    private static final String PARAMETER_STATUS_TEXT = "status_text_guichet";
    private static final String PARAMETER_SENDER_NAME = "sender_name_guichet";
    private static final String PARAMETER_SUBJECT = "subject_guichet";
    private static final String PARAMETER_DEMAND_MAX_STEP = "demand_max_step_uichet";
    private static final String PARAMETER_DEMAND_USER_CURRENT_STEP = "demand_user_current_step_guichet";

    private static final int DEMAND_MAX_STEP = new Random( ).nextInt( 100 );
    private static final int DEMAND_USER_CURRENT_STEP = new Random( ).nextInt( 100 );

    private MockHttpServletRequest _request;
    private TaskNotifyGruConfig _config;
    private INotificationConfig _notificationConfig;

    public void testName( ) throws Exception
    {
        init( );

        assertThat( _notificationConfig.getName( ), is( NAME ) );
    }

    private void init( ) throws Exception
    {
        initRequestAndConfig( );
        initNotification( );
    }

    private void initRequestAndConfig( )
    {
        _request = new MockHttpServletRequest( );
        _config = new TaskNotifyGruConfig( );
    }

    private void initNotification( )
    {
        _notificationConfig = new GuichetNotificationConfig( _request, _config );
    }

    public void testIsActiveWhenActivationIsTrue( )
    {
        initRequestAndConfig( );
        _config.setActiveOngletGuichet( true );
        initNotification( );

        assertThat( _notificationConfig.isActive( ), is( true ) );
    }

    public void testIsActiveWhenActivationIsFalse( )
    {
        initRequestAndConfig( );
        _config.setActiveOngletGuichet( false );
        initNotification( );

        assertThat( _notificationConfig.isActive( ), is( false ) );
    }

    public void testActivation( ) throws Exception
    {
        init( );

        _notificationConfig.setActive( true );

        assertThat( _config.isActiveOngletGuichet( ), is( true ) );
    }

    public void testDeactivation( ) throws Exception
    {
        init( );

        _notificationConfig.setActive( false );

        assertThat( _config.isActiveOngletGuichet( ), is( false ) );
    }

    public void testValidatorIsNotNull( ) throws Exception
    {
        init( );

        assertThat( _notificationConfig.getValidator( ), is( notNullValue( ) ) );
    }

    public void testAddConfig( ) throws Exception
    {
        initRequestAndConfig( );
        fillRequest( );
        initNotification( );

        _notificationConfig.addConfig( );

        assertThatConfigIsFilled( );
    }

    private void fillRequest( )
    {
        _request.addParameter( PARAMETER_MESSAGE, PARAMETER_MESSAGE );
        _request.addParameter( PARAMETER_STATUS_TEXT, PARAMETER_STATUS_TEXT );
        _request.addParameter( PARAMETER_SENDER_NAME, PARAMETER_SENDER_NAME );
        _request.addParameter( PARAMETER_SUBJECT, PARAMETER_SUBJECT );
        _request.addParameter( PARAMETER_DEMAND_MAX_STEP, String.valueOf( DEMAND_MAX_STEP ) );
        _request.addParameter( PARAMETER_DEMAND_USER_CURRENT_STEP, String.valueOf( DEMAND_USER_CURRENT_STEP ) );
    }

    private void assertThatConfigIsFilled( )
    {
        assertThat( _config.getMessageGuichet( ), is( PARAMETER_MESSAGE ) );
        assertThat( _config.getStatustextGuichet( ), is( PARAMETER_STATUS_TEXT ) );
        assertThat( _config.getSenderNameGuichet( ), is( PARAMETER_SENDER_NAME ) );
        assertThat( _config.getSubjectGuichet( ), is( PARAMETER_SUBJECT ) );
        assertThat( _config.getDemandMaxStepGuichet( ), is( DEMAND_MAX_STEP ) );
        assertThat( _config.getDemandUserCurrentStepGuichet( ), is( DEMAND_USER_CURRENT_STEP ) );
    }

    public void testRemoveConfig( ) throws Exception
    {
        initRequestAndConfig( );
        fillConfig( );
        initNotification( );

        _notificationConfig.removeConfig( );

        assertThatConfigIsEmpty( );
    }

    private void fillConfig( )
    {
        _config.setMessageGuichet( PARAMETER_MESSAGE );
        _config.setStatustextGuichet( PARAMETER_STATUS_TEXT );
        _config.setSenderNameGuichet( PARAMETER_SENDER_NAME );
        _config.setSubjectGuichet( PARAMETER_SUBJECT );
        _config.setDemandMaxStepGuichet( DEMAND_MAX_STEP );
        _config.setDemandUserCurrentStepGuichet( DEMAND_USER_CURRENT_STEP );
    }

    private void assertThatConfigIsEmpty( )
    {
        assertThat( _config.getMessageGuichet( ), is( nullValue( ) ) );
        assertThat( _config.getStatustextGuichet( ), is( nullValue( ) ) );
        assertThat( _config.getSenderNameGuichet( ), is( nullValue( ) ) );
        assertThat( _config.getSubjectGuichet( ), is( nullValue( ) ) );
        assertThat( _config.getDemandMaxStepGuichet( ), is( Constants.INT_DEFAULT_VALUE ) );
        assertThat( _config.getDemandUserCurrentStepGuichet( ), is( Constants.INT_DEFAULT_VALUE ) );
    }
}
