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
package fr.paris.lutece.plugins.workflow.modules.notifygru.web;

import fr.paris.lutece.plugins.workflow.modules.notifygru.service.ServiceConfigTaskForm;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This class represents a validator for the {@link INotificationConfig}
 */
public abstract class AbstractNotificationConfigValidator
{
    private static final String MARKER_START = "${";
    private final HttpServletRequest _request;

    /**
     * Constructor
     * 
     * @param request
     *            the request used to validate the configuration
     */
    public AbstractNotificationConfigValidator( HttpServletRequest request )
    {
        _request = request;
    }

    /**
     * Validates the configuration
     * 
     * @param model
     *            the model used to vaidate the configuration
     * @return the URL of the error page when there is a validation error, an empty String otherwise
     */
    public final String validate( Map<String, Object> model )
    {
        List<String> listErrors = validateFieldsWithoutMarker( );

        if ( !listErrors.isEmpty( ) )
        {
            return buildUrlForFieldValidationErrors( listErrors );
        }

        List<String> listInvalidEmails = findInvalidEmails( );

        if ( !listInvalidEmails.isEmpty( ) )
        {
            return AdminMessageService.getMessageUrl( _request, Constants.MESSAGE_INVALID_EMAIL, new Object [ ] {
                    String.join( ", ", listInvalidEmails )
            }, AdminMessage.TYPE_STOP );
        }

        if ( !areFieldsWithMarkersValid( model ) )
        {
            return buildUrlForMarkerValidationError( model );
        }

        return null;
    }

    /**
     * Builds the URL of the error page for field validation errors
     * 
     * @param listErrors
     *            the list of field validation errors
     * @return the URL of the error page
     */
    private String buildUrlForFieldValidationErrors( List<String> listErrors )
    {
        return ServiceConfigTaskForm.displayErrorMessage( listErrors, _request );
    }

    /**
     * Builds the URL of the error page for marker validation error
     * 
     * @return the URL of the error page
     */
    private String buildUrlForMarkerValidationError( Map<String, Object> model )
    {
        Object [ ] tabRequiredFields = {
                model.get( Constants.MARK_MESSAGES_ERROR ),
        };

        return AdminMessageService.getMessageUrl( _request, Constants.MESSAGE_ERROR_FREEMARKER, tabRequiredFields, AdminMessage.TYPE_STOP );
    }

    /**
     * Gives the error message for mandatory field
     * 
     * @param strI18nMessage
     *            the i18n message key
     * @return the localized error message
     */
    protected String getErrorMessageForMandatoryField( String strI18nMessage )
    {
        return I18nService.getLocalizedString( strI18nMessage, _request.getLocale( ) );
    }

    /**
     * Tests if a mandatory field is valid
     * 
     * @param strFieldValue
     *            the value of the mandatory field
     * @return {@code true} if the mandatory field is valid, {@code false} otherwise
     */
    protected boolean isMandatoryFieldValid( String strFieldValue )
    {
        return StringUtils.isNotBlank( strFieldValue );
    }

    /**
     * Checks if a field containing markers is valid.
     *
     * @param strFieldValue
     *            the the value of the field containing the markers
     * @param model
     *            the model representing the markers
     * @return {@code true} if the field is valid, {@code false} otherwise
     */
    protected boolean areMarkersValid( String strFieldValue, Map<String, Object> model )
    {
        try
        {
            if ( AppTemplateService.getTemplateFromStringFtl( strFieldValue, _request.getLocale( ), model ) != null )
            {
                return true;
            }
        }
        catch( RuntimeException e )
        {
            model.put( Constants.MARK_MESSAGES_ERROR, e.getMessage( ) );
            AppLogService.error( "Error validating the field with markers : " + e.getMessage( ), e );
            return false;
        }

        return false;
    }

    /**
     * Gives the e-mail addresses of the configuration that are not valid. No address is checked by default
     *
     * @return the list of the invalid e-mail addresses, empty if every address is valid
     */
    protected List<String> findInvalidEmails( )
    {
        return new ArrayList<>( );
    }

    /**
     * Gives the addresses of a list of e-mail addresses separated by semicolons that are not valid. An address containing a marker is not checked, its value
     * being known only when the notification is sent
     *
     * @param strEmails
     *            the e-mail addresses separated by semicolons
     * @return the list of the invalid e-mail addresses
     */
    protected static List<String> getInvalidEmails( String strEmails )
    {
        List<String> listInvalidEmails = new ArrayList<>( );

        if ( StringUtils.isBlank( strEmails ) )
        {
            return listInvalidEmails;
        }

        for ( String strEmail : strEmails.split( Constants.SEMICOLON ) )
        {
            String strTrimmedEmail = strEmail.replaceAll( "[\\r\\n\\t ]", "" );

            if ( !strTrimmedEmail.isEmpty( ) && !strTrimmedEmail.contains( MARKER_START ) && !AdminUserService.checkEmail( strTrimmedEmail ) )
            {
                listInvalidEmails.add( strTrimmedEmail );
            }
        }

        return listInvalidEmails;
    }

    /**
     * Validates the fields without marker
     * 
     * @return the list of validation errors
     */
    protected abstract List<String> validateFieldsWithoutMarker( );

    /**
     * Tests if the fields with markers are valid or not
     * 
     * @param model
     *            the model representing the markers
     * @return {@code true} if the fields are valid, {@code false} otherwise
     */
    protected abstract boolean areFieldsWithMarkersValid( Map<String, Object> model );
}
