/*
 * Copyright (c) 2002-2016, Mairie de Paris
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
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Locale;

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
     *
     * @exception Exception not instance
     */
    private Validator(  ) throws Exception
    {
        throw new Exception(  );
    }

    /**
     * verify if an email address is correct.
     *
     * @param email to validate
     * @return true if the email address is correct false otherwise
     */
    public static boolean isEmailValid( String email )
    {
        return email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" );
    }

    /**
     * verify if the length of a SMS don't exceed 160 characters.
     *
     * @param sms to validate
     * @return true if the length of a SMS don't exceed 160 characters otherwise false
     */
    public static boolean isSMSvalid( String sms )
    {
        return sms.length(  ) <= 160;
    }

    /**
     * verify if all email address in a list are correct.
     *
     * @param strRecipient to validate
     * @return true if all email address are correct otherwise false
     */
    public static boolean isRecipientCcValid( String strRecipient )
    {
        String[] emails = strRecipient.split( ";" );

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
     * Mandotory params.
     *
     * @param strParams the str params
     * @param strI18nMessage the str i18n message
     * @param locale the locale
     * @return the string
     */
    public static String mandotoryParams( String strParams, String strI18nMessage, Locale locale )
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
     * @param strNumTel to validate
     * @return true if the telephone number is valid false otherwise
     */
    public static boolean isTelephonNumberValid( String strNumTel )
    {
        return strNumTel.matches( "(0|\\+33|0033)[1-9][0-9]{8}" );
    }

    /**
     * Checks if is freemarker valid.
     *
     * @param strFreemarkerTemplateData the message to send
     * @param locale of request
     * @param model with ressource
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
        catch ( RuntimeException e )
        {
            return false;
        }

        return false;
    }

    /**
     * Checks if is valide build provider service.
     *
     * @param request the request
     * @param config the config
     * @param providerService the _provider service
     * @param locale the locale
     * @param task the task
     * @return the string
     */
    public static String isValideBuildProviderService( HttpServletRequest request, TaskNotifyGruConfig config,
        AbstractServiceProvider providerService, Locale locale, ITask task )
    {
        String strUrlRedirector = "";
        String strProvider = request.getParameter( Constants.PARAMETER_SELECT_PROVIDER );
        int nDemandStatus = ( VALUE_CHECKBOX.equals( request.getParameter( Constants.PARAMETER_DEMAND_STATUS ) ) ) ? 1 : 0;

        if ( ( strProvider != null ) && ServiceConfigTaskForm.isBeanExiste( strProvider, task ) )
        {
            config.setIdSpringProvider( strProvider );
            config.setDemandStatus( nDemandStatus );
        }

        /*if the provider is already register*/
        else if ( config.getIdSpringProvider(  ) == null )
        {
            Object[] tabRequiredFields = 
                {
                    I18nService.getLocalizedString( Constants.MESSAGE_MANDATORY_PROVIDER, locale ),
                };

            strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_MANDATORY_PROVIDER,
                    tabRequiredFields, AdminMessage.TYPE_STOP );
        }

        return strUrlRedirector;
    }

    /**
     * Gets the valide build provider service.
     *
     * @param config the config
     * @param task the task
     * @return the valide build provider service
     */
    public static AbstractServiceProvider getValideBuildProviderService( TaskNotifyGruConfig config, ITask task )
    {
        AbstractServiceProvider providerService = ServiceConfigTaskForm.getCostumizeBean( config.getIdSpringProvider(  ),
                task );

        return providerService;
    }

    /**
     * Checks if is valide build guichet.
     *
     * @param request the request
     * @param config the config
     * @param providerService the _provider service
     * @param locale the locale
     * @param strApply the str apply
     * @return the string
     */
    public static String isValideBuildGuichet( HttpServletRequest request, TaskNotifyGruConfig config,
        AbstractServiceProvider providerService, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        ArrayList<String> errors = new ArrayList<String>(  );
        String strMessageGuichet = request.getParameter( Constants.PARAMETER_MESSAGE_GUICHET );
        String strStatusTextGuichet = request.getParameter( Constants.PARAMETER_STATUS_TEXT_GUICHET );
        String strSenderNameGuichet = request.getParameter( Constants.PARAMETER_SENDER_NAME_GUICHET );
        String strSubjectGuichet = request.getParameter( Constants.PARAMETER_SUBJECT_GUICHET );

        //optional
        String strDemandMaxStepGuichet = request.getParameter( Constants.PARAMETER_DEMAND_MAX_STEP_GUICHET );
        int nDemandMaxStepGuichet = ServiceConfigTaskForm.getNumbertoString( strDemandMaxStepGuichet );
        String strDemandUserCurrentStepGuichet = request.getParameter( Constants.PARAMETER_DEMAND_USER_CURRENT_STEP_GUICHET );
        int nDemandUserCurrentStepGuichet = ServiceConfigTaskForm.getNumbertoString( strDemandUserCurrentStepGuichet );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strMessageGuichet,
                            Constants.MESSAGE_MANDATORY_GUICHET_MESSAGE_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strMessageGuichet,
                        Constants.MESSAGE_MANDATORY_GUICHET_MESSAGE_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strStatusTextGuichet,
                            Constants.MESSAGE_MANDATORY_GUICHET_STATUS_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strStatusTextGuichet,
                        Constants.MESSAGE_MANDATORY_GUICHET_STATUS_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strSenderNameGuichet,
                            Constants.MESSAGE_MANDATORY_GUICHET_SENDER_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strSenderNameGuichet,
                        Constants.MESSAGE_MANDATORY_GUICHET_SENDER_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strSubjectGuichet,
                            Constants.MESSAGE_MANDATORY_GUICHET_OBJECT_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strSubjectGuichet,
                        Constants.MESSAGE_MANDATORY_GUICHET_OBJECT_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageGuichet + ' ' + strSubjectGuichet, locale,
                        providerService.getInfos( -1 ) ) )
            {
                Object[] tabRequiredFields = 
                    {
                        I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                    };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER,
                        tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty(  ) )
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

        /*fin guichet*/
        return strUrlRedirector;
    }

    /**
     * Checks if is valide build agent.
     *
     * @param request the request
     * @param config the config
     * @param providerService the _provider service
     * @param locale the locale
     * @param strApply the str apply
     * @return the string
     */
    public static String isValideBuildAgent( HttpServletRequest request, TaskNotifyGruConfig config,
        AbstractServiceProvider providerService, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        /*Agent*/
        ArrayList<String> errors = new ArrayList<String>(  );
        String strMessageAgent = request.getParameter( Constants.PARAMETER_STATUS_MESSAGE_AGENT );
        String strStatutTextAgent = request.getParameter( Constants.PARAMETER_STATUS_TEXT_AGENT );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strMessageAgent,
                            Constants.MESSAGE_AGENT_FIELD_MESSAGE, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strMessageAgent, Constants.MESSAGE_AGENT_FIELD_MESSAGE, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strStatutTextAgent,
                            Constants.MESSAGE_AGENT_FIELD_STATUS, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strStatutTextAgent, Constants.MESSAGE_AGENT_FIELD_STATUS, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageAgent, locale, providerService.getInfos( -1 ) ) )
            {
                Object[] tabRequiredFields = 
                    {
                        I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                    };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER,
                        tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty(  ) )
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
     * Checks if is valide build email.
     *
     * @param request the request
     * @param config the config
     * @param providerService the _provider service
     * @param locale the locale
     * @param strApply the str apply
     * @return the string
     */
    public static String isValideBuildEmail( HttpServletRequest request, TaskNotifyGruConfig config,
        AbstractServiceProvider providerService, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        /*email*/
        ArrayList<String> errors = new ArrayList<String>(  );

        String strSubjectEmail = request.getParameter( Constants.PARAMETER_SUBJECT_EMAIL );
        String strMessageEmail = request.getParameter( Constants.PARAMETER_MESSAGE_EMAIL );
        String strSenderNameEmail = request.getParameter( Constants.PARAMETER_SENDER_NAME_EMAIL );
        String strRecipientsCcEmail = request.getParameter( Constants.PARAMETER_RECIPIENT_CC_EMAIL );
        String strRecipientsCciEmail = request.getParameter( Constants.PARAMETER_RECIPIENT_CCI_EMAIL );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strSubjectEmail,
                            Constants.MESSAGE_EMAIL_SUBJECT_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strSubjectEmail, Constants.MESSAGE_EMAIL_SUBJECT_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strSenderNameEmail,
                            Constants.MESSAGE_EMAIL_SENDER_NAME_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strSenderNameEmail, Constants.MESSAGE_EMAIL_SENDER_NAME_FIELD,
                        locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strMessageEmail,
                            Constants.MESSAGE_EMAIL_MESSAGE_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strMessageEmail, Constants.MESSAGE_EMAIL_MESSAGE_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageEmail + ' ' + strSubjectEmail, locale,
                        providerService.getInfos( -1 ) ) )
            {
                Object[] tabRequiredFields = 
                    {
                        I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                    };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER,
                        tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty(  ) )
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
     * Checks if is valide build sms.
     *
     * @param request the request
     * @param config the config
     * @param providerService the _provider service
     * @param locale the locale
     * @param strApply the str apply
     * @return the string
     */
    public static String isValideBuildSMS( HttpServletRequest request, TaskNotifyGruConfig config,
        AbstractServiceProvider providerService, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        /*sms*/
        ArrayList<String> errors = new ArrayList<String>(  );
        String strMessageSMS = request.getParameter( Constants.PARAMETER_MESSAGE_SMS );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strMessageSMS, Constants.MESSAGE_SMS_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strMessageSMS, Constants.MESSAGE_SMS_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strMessageSMS, locale, providerService.getInfos( -1 ) ) )
            {
                Object[] tabRequiredFields = 
                    {
                        I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                    };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER,
                        tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty(  ) )
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
     * Checks if is valide build broadcast.
     *
     * @param request the request
     * @param config the config
     * @param providerService the _provider service
     * @param locale the locale
     * @param strApply the str apply
     * @return the string
     */
    public static String isValideBuildBroadcast( HttpServletRequest request, TaskNotifyGruConfig config,
        AbstractServiceProvider providerService, Locale locale, String strApply )
    {
        String strUrlRedirector = "";

        ArrayList<String> errors = new ArrayList<String>(  );
        String strIdMailingListBroadcast = request.getParameter( Constants.PARAMETER_ID_MAILING_LIST );
        int nIdMailingListBroadcast = ( strIdMailingListBroadcast == null ) ? WorkflowUtils.CONSTANT_ID_NULL
                                                                            : Integer.parseInt( strIdMailingListBroadcast );

        String strsenderNameBroadcast = request.getParameter( Constants.PARAMETER_SENDER_NAME_BROADCAST );
        String strsubjectBroadcast = request.getParameter( Constants.PARAMETER_SUBJECT_BROADCAST );
        String strmessageBroadcast = request.getParameter( Constants.PARAMETER_MESSAGE_BROADCAST );
        String strrecipientsCcBroadcast = request.getParameter( Constants.PARAMETER_RECIPIENT_CC_BROADCAST );
        String strrecipientsCciBroadcast = request.getParameter( Constants.PARAMETER_RECIPIENT_CCI_BROADCAST );

        if ( StringUtils.isBlank( strApply ) )
        {
            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strIdMailingListBroadcast,
                            Constants.MESSAGE_LIST_ID_LISTE, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strIdMailingListBroadcast, Constants.MESSAGE_LIST_ID_LISTE,
                        locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strsenderNameBroadcast,
                            Constants.MESSAGE_LIST_SENDER_NAME_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strsenderNameBroadcast,
                        Constants.MESSAGE_LIST_SENDER_NAME_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strsubjectBroadcast,
                            Constants.MESSAGE_LIST_SUBJECT_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strsubjectBroadcast, Constants.MESSAGE_LIST_SUBJECT_FIELD, locale ) );
            }

            if ( StringUtils.isNotBlank( Validator.mandotoryParams( strmessageBroadcast,
                            Constants.MESSAGE_LIST_MESSAGE_FIELD, locale ) ) )
            {
                errors.add( Validator.mandotoryParams( strmessageBroadcast, Constants.MESSAGE_LIST_MESSAGE_FIELD, locale ) );
            }

            if ( !Validator.isFreemarkerValid( strmessageBroadcast + ' ' + strsubjectBroadcast, locale,
                        providerService.getInfos( -1 ) ) )
            {
                Object[] tabRequiredFields = 
                    {
                        I18nService.getLocalizedString( Constants.MESSAGE_ERROR_FREEMARKER, locale ),
                    };

                strUrlRedirector = AdminMessageService.getMessageUrl( request, Constants.MESSAGE_ERROR_FREEMARKER,
                        tabRequiredFields, AdminMessage.TYPE_STOP );
            }
        }

        if ( !errors.isEmpty(  ) )
        {
            strUrlRedirector = ServiceConfigTaskForm.displayErrorMessage( errors, request );
        }

        /* fin liste diffusion*/
        if ( StringUtils.isBlank( strUrlRedirector ) )
        {
            config.setIdMailingListBroadcast( nIdMailingListBroadcast );
            config.setSenderNameBroadcast( strsenderNameBroadcast );
            config.setSubjectBroadcast( strsubjectBroadcast );
            config.setMessageBroadcast( strmessageBroadcast );
            config.setRecipientsCcBroadcast( strrecipientsCcBroadcast );
            config.setRecipientsCciBroadcast( strrecipientsCciBroadcast );
        }

        return strUrlRedirector;
    }
}
