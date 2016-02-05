/*
 * Copyright (c) 2002-2015, Mairie de Paris
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

import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import java.util.ArrayList;

import java.util.Locale;
import org.apache.commons.lang.StringUtils;


/**
 *
 * @author
 *
 */
public final class Validator
{
    /**
     * @exception Exception not instance
     * */
    private Validator(  ) throws Exception
    {
        throw new Exception(  );
    }

    /**
     * verify if an email address is correct
     * @param email to validate
     * @return true if the email address is correct false otherwise
     */
    public static boolean isEmailValid( String email )
    {
        return email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" );
    }

    /**
     * verify if the length of a SMS don't exceed 160 characters
     * @param sms to validate
     * @return true if the length of a SMS don't exceed 160 characters otherwise false
     */
    public static boolean isSMSvalid( String sms )
    {
        return sms.length(  ) <= 160;
    }

    /**
     * verify if all email address in a list are correct
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
     * verify if the telephone number is valid
     * @param strNumTel to validate
     * @return true if the telephone number is valid false otherwise
     */
    public static String mandotoryParams( String strParams, String strI18nMessage,Locale locale )
    {
       
        if (StringUtils.isBlank(strParams)) {
           return I18nService.getLocalizedString(strI18nMessage, locale);
        }
        
         return "";
    }

    /**
     * verify if the telephone number is valid
     * @param strNumTel to validate
     * @return true if the telephone number is valid false otherwise
     */
    public static boolean isTelephonNumberValid( String strNumTel )
    {
        return strNumTel.matches( "(0|\\+33|0033)[1-9][0-9]{8}" );
    }

    /**
     *
     * @param strFreemarkerTemplateData the message to send
     * @param locale of request
     * @param model with ressource
     * @return true if the strFreemarkerTemplateData is correct
     */
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
}
