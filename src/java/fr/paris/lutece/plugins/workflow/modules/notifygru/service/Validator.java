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

import fr.paris.lutece.portal.service.template.AppTemplateService;


import java.util.Locale;

/**
 * 
 * @author 
 *
 */
public class Validator
{
    /**
     * verify if an email address is correct
     * @param email 
     * @return true if the email address is correct false otherwise
     */
    public static boolean isEmailValid( String email )
    {
            if ( email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) )
            {
                return true;
            }

        

        return false;
    }

    /**
     * verify if the length of a SMS don't exceed 160 characters
     * @param sms 
     * @return true if the length of a SMS don't exceed 160 characters otherwise false
     */
    public static boolean isSMSvalid( String sms )
    {
        if ( sms.length(  ) <= 160 )
        {
            return true;
        }

        return false;
    }

    /**
     * verify if all email address in a list are correct
     * @param strRecipient 
     * @return true if all email address are correct otherwise false
     */
    public static boolean isRecipientCcValid( String strRecipient )
    {
        String[] emails = strRecipient.split( ";" );

        for ( int i = 0; i < emails.length; i++ )
        {
            if ( isEmailValid( emails[i] ) )
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
     * @param strNumTel 
     * @return true if the telephone number is valid false otherwise
     */
    public static boolean isTelephonNumberValid( String strNumTel )
    {
        if ( strNumTel.matches( "(0|\\+33|0033)[1-9][0-9]{8}" ) )
        {
            return true;
        }

        return false;
    }
/**
 * 
 * @param strFreemarkerTemplateData 
 * @param locale 
 * @param model 
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
