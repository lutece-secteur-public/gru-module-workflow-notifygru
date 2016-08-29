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

import java.util.Locale;
import java.util.Map;


/**
 *
 */
public interface IProvider
{
    /**
     * @return the userGuid
     * @param nIdResourceHistory the _nIdResourceHistory to set
     */
    String getUserGuid( int nIdResourceHistory );

    /**
     * give the email of user
     *
     * @return the userEmail
     * @param nIdResourceHistory the _nIdResourceHistory to set
     */
    String getUserEmail( int nIdResourceHistory );

    /**
     * @param nIdResourceHistory of ressource
     * @return DemandId if available else return TaskNotifyGruConstants.OPTIONAL_INT_VALUE
     */
    int getOptionalDemandId( int nIdResourceHistory );

    /**
    * @param nIdResourceHistory of ressource
    * @return strReference if available else return TaskNotifyGruConstants.OPTIONAL_INT_VALUE
    */
    String getDemandReference( int nIdResourceHistory );

    /**
     * @param nIdResourceHistory of ressource
     * @return CustomerId if available else return TaskNotifyGruConstants.OPTIONAL_INT_VALUE
     */
    String getCustomerId( int nIdResourceHistory );

    /**
     * @param nIdResourceHistory of ressource
     * @return DemandIdType if available else return TaskNotifyGruConstants.OPTIONAL_INT_VALUE
     */
    int getOptionalDemandIdType( int nIdResourceHistory );

    /**
     * @return the phone number
     * @param nIdResourceHistory the _nIdResourceHistory to set else return TaskNotifyGruConstants.OPTIONAL_STRING_VALUE
     */
    String getOptionalMobilePhoneNumber( int nIdResourceHistory );

    /**
     * @param local of request
     * @return html template for maker resources attributs
     */
    String getInfosHelp( Locale local );

    /**
     * @param nIdResourceHistory of ressource
     * @return model marker from resources attributs
     */
    Map<String, Object> getInfos( int nIdResourceHistory );
}
