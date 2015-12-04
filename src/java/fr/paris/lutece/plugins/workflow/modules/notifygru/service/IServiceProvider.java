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

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 
 *
 */
public interface IServiceProvider
{
    /**
     * give the email of user
     * @return the userEmail
     * @param nIdResource the _nIdResource to set
     */
    String getUserEmail( int nIdResource );

    /**
         * @return the userGuid
         * @param nIdResource the _nIdResource to set
         */
    String getUserGuid( int nIdResource );

    /**
         * @return the status of the resource
         * @param nIdResource the _nIdResource to set
         */
    String getStatus( int nIdResource );
    
       /**
         * @return the phone number 
         * @param nIdResource the _nIdResource to set
         */
    String getMobilePhoneNumber( int nIdResource );
    
       /**
         * @param nIdResource
         * @return boolean
         */
    Boolean isMobilePhoneNumberAvailable( int nIdResource );
    
     /**
         * @return the IdDemand
         * @param nIdResource the _nIdResource to set
         */
    int getIdDemand( int nIdResource );
    
      /**
         * @param nIdResource
         * @return boolean
         */
    Boolean isIdDemandAvailable( int nIdResource );
    
      /**
         * @return the IdDemandType
         * @param nIdResource the _nIdResource to set
         */
    int getIdDemandType( int nIdResource );
    
      /**
         * @param nIdResource
         * @return boolean
         */
    Boolean isIdDemandTypeAvailable( int nIdResource );

    /**
     * @param local
         * @return the help to put the values of the resource 
         */
    String getInfosHelp( Locale local );

    /**
         * @return the resource of id _nIdResource
         * @param nIdResource the _nIdResource to set
         */
    Object getInfos( int nIdResource );
}
