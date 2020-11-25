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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.INotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.AbstractNotificationConfigValidator;

/**
 * This class represents a configuration for the guichet notification
 *
 */
public class GuichetNotificationConfig implements INotificationConfig
{
    private static final String NAME = "guichet";

    // PARAMETERS
    private static final String PARAMETER_MESSAGE = "message_guichet";
    private static final String PARAMETER_STATUS_TEXT = "status_text_guichet";
    private static final String PARAMETER_SENDER_NAME = "sender_name_guichet";
    private static final String PARAMETER_SUBJECT = "subject_guichet";
    private static final String PARAMETER_DEMAND_MAX_STEP = "demand_max_step_uichet";
    private static final String PARAMETER_DEMAND_USER_CURRENT_STEP = "demand_user_current_step_guichet";

    private static final int STEP_NOT_SET = -1;

    private final HttpServletRequest _request;
    private final TaskNotifyGruConfig _config;
    private final String _strMessage;
    private final String _strStatusText;
    private final String _strSenderName;
    private final String _strSubject;
    private final int _nDemandMaxStep;
    private final int _nDemandUserCurrentStep;

    /**
     * Constructor
     * 
     * @param request
     *            the request used by this configuration
     * @param config
     *            the configuration of the task
     */
    public GuichetNotificationConfig( HttpServletRequest request, TaskNotifyGruConfig config )
    {
        _request = request;
        _config = config;
        _strMessage = request.getParameter( PARAMETER_MESSAGE );
        _strStatusText = request.getParameter( PARAMETER_STATUS_TEXT );
        _strSenderName = request.getParameter( PARAMETER_SENDER_NAME );
        _strSubject = request.getParameter( PARAMETER_SUBJECT );
        _nDemandMaxStep = NumberUtils.toInt( request.getParameter( PARAMETER_DEMAND_MAX_STEP ), STEP_NOT_SET );
        _nDemandUserCurrentStep = NumberUtils.toInt( request.getParameter( PARAMETER_DEMAND_USER_CURRENT_STEP ), STEP_NOT_SET );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName( )
    {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive( )
    {
        return _config.isActiveOngletGuichet( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive( boolean bActive )
    {
        _config.setActiveOngletGuichet( bActive );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractNotificationConfigValidator getValidator( )
    {
        return new Validator( _request );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addConfig( )
    {
        _config.setMessageGuichet( _strMessage );
        _config.setStatustextGuichet( _strStatusText );
        _config.setSenderNameGuichet( _strSenderName );
        _config.setSubjectGuichet( _strSubject );
        _config.setDemandMaxStepGuichet( _nDemandMaxStep );
        _config.setDemandUserCurrentStepGuichet( _nDemandUserCurrentStep );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeConfig( )
    {
        _config.setMessageGuichet( null );
        _config.setStatustextGuichet( null );
        _config.setSenderNameGuichet( null );
        _config.setSubjectGuichet( null );
        _config.setDemandMaxStepGuichet( Constants.INT_DEFAULT_VALUE );
        _config.setDemandUserCurrentStepGuichet( Constants.INT_DEFAULT_VALUE );
    }

    /**
     * This class represents a validator for the guichet notification configuration
     *
     */
    private final class Validator extends AbstractNotificationConfigValidator
    {
        // MESSAGES
        private static final String MESSAGE_FIELD_MESSAGE_MANDATORY = "module.workflow.notifygru.task_notify_gru_config.label_message_guichet_mandatory";
        private static final String MESSAGE_FIELD_SENDER_MANDATORY = "module.workflow.notifygru.task_notify_gru_config.label_sender_name_guichet_mandatory";
        private static final String MESSAGE_FIELD_STATUS_MANDATORY = "module.workflow.notifygru.task_notify_gru_config.label_status_text_guichet_mandatory";
        private static final String MESSAGE_FIELD_SUBJECT_MANDATORY = "module.workflow.notifygru.task_notify_gru_config.label_subject_guichet_mandatory";

        /**
         * Constructor
         * 
         * @param request
         *            the request used by this validator
         */
        private Validator( HttpServletRequest request )
        {
            super( request );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected List<String> validateFieldsWithoutMarker( )
        {
            List<String> listErrors = new ArrayList<>( );

            if ( !isMandatoryFieldValid( _strMessage ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_MESSAGE_MANDATORY ) );
            }

            if ( !isMandatoryFieldValid( _strStatusText ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_STATUS_MANDATORY ) );
            }

            if ( !isMandatoryFieldValid( _strSenderName ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_SENDER_MANDATORY ) );
            }

            if ( !isMandatoryFieldValid( _strSubject ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_SUBJECT_MANDATORY ) );
            }

            return listErrors;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean areFieldsWithMarkersValid( Map<String, Object> model )
        {
            return areMarkersValid( _strMessage, model ) && areMarkersValid( _strSubject, model );
        }

    }

}
