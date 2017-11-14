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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class Validator.
 */
public final class Validator
{
    /** The Constant VALUE_CHECKBOX. */
    public static final String VALUE_CHECKBOX = "on";

    /**
     * Instantiates a new validator.
     */
    private Validator( )
    {

    }

    /**
     * verify if an email address is correct.
     *
     * @param strEmail
     *            to validate
     * @return true if the email address is correct false otherwise
     */
    public static boolean isEmailValid( String strEmail )
    {
        return strEmail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" );
    }

    /**
     * verify if the length of a SMS don't exceed 160 characters.
     *
     * @param strSms
     *            to validate
     * @return true if the length of a SMS don't exceed 160 characters otherwise false
     */
    public static boolean isSMSvalid( String strSms )
    {
        return strSms.length( ) <= 160;
    }

    /**
     * verify if all email address in a list are correct.
     *
     * @param strRecipient
     *            to validate
     * @return true if all email address are correct otherwise false
     */
    public static boolean isRecipientCcValid( String strRecipient )
    {
        String [ ] emails = strRecipient.split( Constants.SEMICOLON );

        for ( String email : emails )
        {
            if ( isEmailValid( email ) )
            {
                return true;
            }
            else
            {
                break;
            }
        }

        return false;
    }

    /**
     * Mandatory params.
     *
     * @param strParams
     *            str value
     * @param strI18nMessage
     *            I18N key error message
     * @param locale
     *            the locale
     * @return the string
     */
    public static String mandatoryParams( String strParams, String strI18nMessage, Locale locale )
    {
        if ( StringUtils.isBlank( strParams ) )
        {
            return I18nService.getLocalizedString( strI18nMessage, locale );
        }

        return "";
    }

    /**
     * verify if the telephone number is valid.
     *
     * @param strNumTel
     *            to validate
     * @return true if the telephone number is valid false otherwise
     */
    public static boolean isTelephonNumberValid( String strNumTel )
    {
        return strNumTel.matches( "(0|\\+33|0033)[1-9][0-9]{8}" );
    }

    /**
     * Checks if is freemarker valid.
     *
     * @param strFreemarkerTemplateData
     *            the message to send
     * @param locale
     *            of request
     * @param model
     *            with ressource
     * @return true if the strFreemarkerTemplateData is correct
     */
    @SuppressWarnings( "deprecation" )
    public static boolean isFreemarkerValid( String strFreemarkerTemplateData, Locale locale, Object model )
    {
        try
        {
            if ( AppTemplateService.getTemplateFromStringFtl( strFreemarkerTemplateData, locale, model ) != null )
            {
                return true;
            }
        }
        catch( RuntimeException e )
        {
            return false;
        }

        return false;
    }

    /**
     * Checks if is valid build guichet.
     *
     * @param request
     *            the request
     * @param config
     *            the config
     * @param model
     *            the model
     * @param locale
     *            the locale
     * @param strApply
     *            the str apply
     * @return an empty string or url redirection if the validation has error
     */
    public static String isValidBuildGuichet( HttpServletRequest request, TaskNotifyGruConfig config, Map<String, Object> model, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        ArrayList<String> errors = new ArrayList<String>( );
        String strMessageGuichet = request.getParameter( Constants.PARAMETER_MESSAGE_GUICHET );
        String strStatusTextGuichet = request.getParameter( Constants.PARAMETER_STATUS_TEXT_GUICHET );
        String strSenderNameGuichet = request.getParameter( Constants.PARAMETER_SENDER_NAME_GUICHET );
        String strSubjectGuichet = request.getParameter( Constants.PARAMETER_SUBJECT_GUICHET );

        // optional
        String strDemandMaxStepGuichet = request.getParameter( Constants.PARAMETER_DEMAND_MAX_STEP_GUICHET );
        int nDemandMaxStepGuichet = ServiceConfigTaskForm.parseStringToPositiveNumber( strDemandMaxStepGuichet );
        String strDemandUserCurrentStepGuichet = request.getParameter( Constants.PARAMETER_DEMAND_USER_CURRENT_STEP_GUICHET );
        int nDemandUserCurrentStepGuichet = ServiceConfigTaskForm.parseStringToPositiveNumber( strDemandUserCurrentStepGuichet );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strMessageGuichet, Constants.MESSAGE_MANDATORY_GUICHET_MESSAGE_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strMessageGuichet, Constants.MESSAGE_MANDATORY_GUICHET_MESSAGE_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strStatusTextGuichet, Constants.MESSAGE_MANDATORY_GUICHET_STATUS_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strStatusTextGuichet, Constants.MESSAGE_MANDATORY_GUICHET_STATUS_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strSenderNameGuichet, Constants.MESSAGE_MANDATORY_GUICHET_SENDER_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strSenderNameGuichet, Constants.MESSAGE_MANDATORY_GUICHET_SENDER_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strSubjectGuichet, Constants.MESSAGE_MANDATORY_GUICHET_OBJECT_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strSubjectGuichet, Constants.MESSAGE_MANDATORY_GUICHET_OBJECT_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageGuichet + ' ' + strSubjectGuichet, locale, model ) )
            {
                Object [ ] tabRequiredFields = {
                    I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER, tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty( ) )
        {
            strUrlRedirector = ServiceConfigTaskForm.displayErrorMessage( errors, request );
        }

        if ( StringUtils.isBlank( strUrlRedirector ) )
        {
            config.setMessageGuichet( strMessageGuichet );
            config.setStatustextGuichet( strStatusTextGuichet );
            config.setSenderNameGuichet( strSenderNameGuichet );
            config.setSubjectGuichet( strSubjectGuichet );
            config.setDemandMaxStepGuichet( nDemandMaxStepGuichet );
            config.setDemandUserCurrentStepGuichet( nDemandUserCurrentStepGuichet );
        }

        /* fin guichet */
        return strUrlRedirector;
    }

    /**
     * Checks if is valid build agent.
     *
     * @param request
     *            the request
     * @param config
     *            the config
     * @param model
     *            the model
     * @param locale
     *            the locale
     * @param strApply
     *            the str apply
     * @return an empty string or url redirection if the validation has error
     */
    public static String isValidBuildAgent( HttpServletRequest request, TaskNotifyGruConfig config, Map<String, Object> model, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        /* Agent */
        ArrayList<String> errors = new ArrayList<String>( );
        String strMessageAgent = request.getParameter( Constants.PARAMETER_STATUS_MESSAGE_AGENT );
        String strStatutTextAgent = request.getParameter( Constants.PARAMETER_STATUS_TEXT_AGENT );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strMessageAgent, Constants.MESSAGE_AGENT_FIELD_MESSAGE, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strMessageAgent, Constants.MESSAGE_AGENT_FIELD_MESSAGE, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strStatutTextAgent, Constants.MESSAGE_AGENT_FIELD_STATUS, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strStatutTextAgent, Constants.MESSAGE_AGENT_FIELD_STATUS, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageAgent, locale, model ) )
            {
                Object [ ] tabRequiredFields = {
                    I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER, tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty( ) )
        {
            strUrlRedirector = ServiceConfigTaskForm.displayErrorMessage( errors, request );
        }

        if ( StringUtils.isBlank( strUrlRedirector ) )
        {
            config.setMessageAgent( strMessageAgent );
            config.setStatustextAgent( strStatutTextAgent );
        }

        return strUrlRedirector;
    }

    /**
     * Checks if is valid build email.
     *
     * @param request
     *            the request
     * @param config
     *            the config
     * @param model
     *            the model
     * @param locale
     *            the locale
     * @param strApply
     *            the str apply
     * @return an empty string or url redirection if the validation has error
     */
    public static String isValidBuildEmail( HttpServletRequest request, TaskNotifyGruConfig config, Map<String, Object> model, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        /* email */
        ArrayList<String> errors = new ArrayList<String>( );

        String strSubjectEmail = request.getParameter( Constants.PARAMETER_SUBJECT_EMAIL );
        String strMessageEmail = request.getParameter( Constants.PARAMETER_MESSAGE_EMAIL );
        String strSenderNameEmail = request.getParameter( Constants.PARAMETER_SENDER_NAME_EMAIL );
        String strRecipientsCcEmail = request.getParameter( Constants.PARAMETER_RECIPIENT_CC_EMAIL );
        String strRecipientsCciEmail = request.getParameter( Constants.PARAMETER_RECIPIENT_CCI_EMAIL );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strSubjectEmail, Constants.MESSAGE_EMAIL_SUBJECT_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strSubjectEmail, Constants.MESSAGE_EMAIL_SUBJECT_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strSenderNameEmail, Constants.MESSAGE_EMAIL_SENDER_NAME_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strSenderNameEmail, Constants.MESSAGE_EMAIL_SENDER_NAME_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strMessageEmail, Constants.MESSAGE_EMAIL_MESSAGE_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strMessageEmail, Constants.MESSAGE_EMAIL_MESSAGE_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageEmail + ' ' + strSubjectEmail, locale, model ) )
            {
                Object [ ] tabRequiredFields = {
                    I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER, tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty( ) )
        {
            strUrlRedirector = ServiceConfigTaskForm.displayErrorMessage( errors, request );
        }

        if ( StringUtils.isBlank( strUrlRedirector ) )
        {
            config.setSubjectEmail( strSubjectEmail );
            config.setMessageEmail( strMessageEmail );
            config.setSenderNameEmail( strSenderNameEmail );
            config.setRecipientsCcEmail( strRecipientsCcEmail );
            config.setRecipientsCciEmail( strRecipientsCciEmail );
        }

        return strUrlRedirector;
    }

    /**
     * Checks if is valid build sms.
     *
     * @param request
     *            the request
     * @param config
     *            the config
     * @param model
     *            the model
     * @param locale
     *            the locale
     * @param strApply
     *            the str apply
     * @return an empty string or url redirection if the validation has error
     */
    public static String isValidBuildSMS( HttpServletRequest request, TaskNotifyGruConfig config, Map<String, Object> model, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        /* sms */
        ArrayList<String> errors = new ArrayList<String>( );
        String strMessageSMS = request.getParameter( Constants.PARAMETER_MESSAGE_SMS );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strMessageSMS, Constants.MESSAGE_SMS_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strMessageSMS, Constants.MESSAGE_SMS_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageSMS, locale, model ) )
            {
                Object [ ] tabRequiredFields = {
                    I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER, tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty( ) )
        {
            strUrlRedirector = ServiceConfigTaskForm.displayErrorMessage( errors, request );
        }

        if ( StringUtils.isBlank( strUrlRedirector ) )
        {
            config.setMessageSMS( strMessageSMS );
        }

        return strUrlRedirector;
    }

    /**
     * Checks if is valid build broadcast.
     *
     * @param request
     *            the request
     * @param config
     *            the config
     * @param model
     *            the model
     * @param locale
     *            the locale
     * @param strApply
     *            the str apply
     * @return an empty string or url redirection if the validation has error
     */
    public static String isValidBuildBroadcast( HttpServletRequest request, TaskNotifyGruConfig config, Map<String, Object> model, Locale locale,
            String strApply )
    {
        String strUrlRedirector = "";

        ArrayList<String> errors = new ArrayList<String>( );
        String strMailingType = request.getParameter( Constants.PARAMETER_MAILING_TYPE );
        String strIdMailingListBroadcast = request.getParameter( Constants.PARAMETER_ID_MAILING_LIST );
        int nIdMailingListBroadcast = WorkflowUtils.CONSTANT_ID_NULL;

        if ( Constants.TYPE_MAILING_TYPE_LIST.equals( strMailingType ) && StringUtils.isNumeric( strIdMailingListBroadcast ) )
        {
            nIdMailingListBroadcast = Integer.parseInt( strIdMailingListBroadcast );
        }

        String strEmailBroadcast = StringUtils.EMPTY;

        if ( Constants.TYPE_MAILING_TYPE_SPEC.equals( strMailingType ) )
        {
            strEmailBroadcast = request.getParameter( Constants.PARAMETER_MAIL_SPECIFIC );
        }

        String strsenderNameBroadcast = request.getParameter( Constants.PARAMETER_SENDER_NAME_BROADCAST );
        String strsubjectBroadcast = request.getParameter( Constants.PARAMETER_SUBJECT_BROADCAST );
        String strmessageBroadcast = request.getParameter( Constants.PARAMETER_MESSAGE_BROADCAST );
        String strrecipientsCcBroadcast = request.getParameter( Constants.PARAMETER_RECIPIENT_CC_BROADCAST );
        String strrecipientsCciBroadcast = request.getParameter( Constants.PARAMETER_RECIPIENT_CCI_BROADCAST );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( Constants.TYPE_MAILING_TYPE_LIST.equals( strMailingType )
                    && StringUtils.isNotBlank( Validator.mandatoryParams( strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale ) );
                nIdMailingListBroadcast = WorkflowUtils.CONSTANT_ID_NULL;
            }

            if ( Constants.TYPE_MAILING_TYPE_SPEC.equals( strMailingType )
                    && StringUtils.isNotBlank( Validator.mandatoryParams( strEmailBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale ) );
                strEmailBroadcast = StringUtils.EMPTY;
            }

            if ( StringUtils.isEmpty( strEmailBroadcast ) && ( nIdMailingListBroadcast == WorkflowUtils.CONSTANT_ID_NULL ) )
            {
                errors.add( Validator.mandatoryParams( strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strsenderNameBroadcast, Constants.MESSAGE_LIST_SENDER_NAME_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strsenderNameBroadcast, Constants.MESSAGE_LIST_SENDER_NAME_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strsubjectBroadcast, Constants.MESSAGE_LIST_SUBJECT_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strsubjectBroadcast, Constants.MESSAGE_LIST_SUBJECT_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandatoryParams( strmessageBroadcast, Constants.MESSAGE_LIST_MESSAGE_FIELD, locale ) ) )
            {
                errors.add( Validator.mandatoryParams( strmessageBroadcast, Constants.MESSAGE_LIST_MESSAGE_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strmessageBroadcast + ' ' + strsubjectBroadcast, locale, model ) )
            {
                Object [ ] tabRequiredFields = {
                    I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER, tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty( ) )
        {
            strUrlRedirector = ServiceConfigTaskForm.displayErrorMessage( errors, request );
        }

        /* fin liste diffusion */
        if ( StringUtils.isBlank( strUrlRedirector ) )
        {
            config.setIdMailingListBroadcast( nIdMailingListBroadcast );
            config.setEmailBroadcast( strEmailBroadcast );
            config.setSenderNameBroadcast( strsenderNameBroadcast );
            config.setSubjectBroadcast( strsubjectBroadcast );
            config.setMessageBroadcast( strmessageBroadcast );
            config.setRecipientsCcBroadcast( strrecipientsCcBroadcast );
            config.setRecipientsCciBroadcast( strrecipientsCciBroadcast );
        }

        return strUrlRedirector;
    }
}
