/*
 * Copyright (c) 2002-2017, Mairie de Paris
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.INotificationConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.AbstractNotificationConfigValidator;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;

/**
 * This class represents a configuration for the broadcast notification
 *
 */
public class BroadcastNotificationConfig implements INotificationConfig
{
    private static final String NAME = "broadcast";

    // PARAMETERS
    private static final String PARAMETER_MAILING_TYPE = "mailing_type";
    private static final String PARAMETER_ID_MAILING_LIST = "id_mailing_list_broadcast";
    private static final String PARAMETER_EMAIL_SPECIFIC = "mailing_spec";
    private static final String PARAMETER_SENDER_NAME = "sender_name_broadcast";
    private static final String PARAMETER_SUBJECT = "subject_broadcast";
    private static final String PARAMETER_MESSAGE = "message_broadcast";
    private static final String PARAMETER_RECIPIENT_CC = "recipients_cc_broadcast";
    private static final String PARAMETER_RECIPIENT_CCI = "recipients_cci_broadcast";

    private static final String TYPE_MAILING_TYPE_LIST = "list";
    private static final String TYPE_MAILING_TYPE_SPECIFIC = "spec";

    private final HttpServletRequest _request;
    private final TaskNotifyGruConfig _config;
    private final String _strMailingType;
    private String _strIdMailingList;
    private String _strSpecificEmail;
    private final String _strSenderName;
    private final String _strSubject;
    private final String _strMessage;
    private final String _strRecipientsCc;
    private final String _strRecipientsCci;

    /**
     * Constructor
     * 
     * @param request
     *            the request used by this configuration
     * @param config
     *            the configuration of the task
     */
    public BroadcastNotificationConfig( HttpServletRequest request, TaskNotifyGruConfig config )
    {
        _request = request;
        _config = config;
        _strMailingType = request.getParameter( PARAMETER_MAILING_TYPE );

        if ( TYPE_MAILING_TYPE_LIST.equals( _strMailingType ) )
        {
            _strIdMailingList = request.getParameter( PARAMETER_ID_MAILING_LIST );
        }

        if ( TYPE_MAILING_TYPE_SPECIFIC.equals( _strMailingType ) )
        {
            _strSpecificEmail = request.getParameter( PARAMETER_EMAIL_SPECIFIC );
        }

        _strSenderName = request.getParameter( PARAMETER_SENDER_NAME );
        _strSubject = request.getParameter( PARAMETER_SUBJECT );
        _strMessage = request.getParameter( PARAMETER_MESSAGE );
        _strRecipientsCc = request.getParameter( PARAMETER_RECIPIENT_CC );
        _strRecipientsCci = request.getParameter( PARAMETER_RECIPIENT_CCI );
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
        return _config.isActiveOngletBroadcast( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive( boolean bActive )
    {
        _config.setActiveOngletBroadcast( bActive );
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
        _config.setIdMailingListBroadcast( NumberUtils.toInt( _strIdMailingList, WorkflowUtils.CONSTANT_ID_NULL ) );
        _config.setEmailBroadcast( _strSpecificEmail );
        _config.setSenderNameBroadcast( _strSenderName );
        _config.setSubjectBroadcast( _strSubject );
        _config.setMessageBroadcast( _strMessage );
        _config.setRecipientsCcBroadcast( _strRecipientsCc );
        _config.setRecipientsCciBroadcast( _strRecipientsCci );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeConfig( )
    {
        _config.setIdMailingListBroadcast( WorkflowUtils.CONSTANT_ID_NULL );
        _config.setEmailBroadcast( null );
        _config.setSenderNameBroadcast( null );
        _config.setSubjectBroadcast( null );
        _config.setMessageBroadcast( null );
        _config.setRecipientsCcBroadcast( null );
        _config.setRecipientsCciBroadcast( null );
    }

    /**
     * This class represents a validator for the broadcast notification configuration
     *
     */
    private final class Validator extends AbstractNotificationConfigValidator
    {
        // MESSAGES
        private static final String MESSAGE_FIELD_SUBJECT_MANDATORY = "module.workflow.notifygru.message.subject.field.broadcast";
        private static final String MESSAGE_FIELD_MESSAGE_MANDATORY = "module.workflow.notifygru.message.field.broadcast";
        private static final String MESSAGE_FIELD_SENDER_NAME_MANDATORY = "module.workflow.notifygru.message.sender.name.field.broadcast";
        private static final String MESSAGE_FIELD_ID_MAILING_LIST_MANDATORY = "module.workflow.notifygru.message.list.field.broadcast";

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

            if ( TYPE_MAILING_TYPE_LIST.equals( _strMailingType )
                    && ( !StringUtils.isNumeric( _strIdMailingList ) || !isMandatoryFieldValid( _strIdMailingList ) ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_ID_MAILING_LIST_MANDATORY ) );
            }

            if ( TYPE_MAILING_TYPE_SPECIFIC.equals( _strMailingType ) && !isMandatoryFieldValid( _strSpecificEmail ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_ID_MAILING_LIST_MANDATORY ) );
            }

            if ( StringUtils.isEmpty( _strIdMailingList ) && StringUtils.isEmpty( _strSpecificEmail ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_ID_MAILING_LIST_MANDATORY ) );
            }

            if ( !isMandatoryFieldValid( _strSenderName ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_SENDER_NAME_MANDATORY ) );
            }

            if ( !isMandatoryFieldValid( _strSubject ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_SUBJECT_MANDATORY ) );
            }

            if ( !isMandatoryFieldValid( _strMessage ) )
            {
                listErrors.add( getErrorMessageForMandatoryField( MESSAGE_FIELD_MESSAGE_MANDATORY ) );
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
