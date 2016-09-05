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

import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.util.ReferenceList;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;


/**
 * The Class AbstractServiceProvider.
 */
public abstract class AbstractServiceProvider implements IProvider
{
    /** The _str key. */
    private String _strKey;

    /** The _strtitle i18n key. */
    private String _strTitleI18nKey;

    /** The _strbean name. */
    private String _strBeanName;

    /** The _b is manager provider. */
    private Boolean _bIsManagerProvider;

    /**
     * Update list providers limited to the given task
     *
     * @param task the task
     */
    public abstract void updateListProvider( ITask task );

    /**
     * Update list of all providers
     */
    public abstract void updateListProvider(  );

    /**
     * Builds the referente list provider.
     *
     * @return the reference list
     */
    public abstract ReferenceList buildReferenteListProvider(  );

    /**
     * Gets the referente list entity provider.
     *
     * @return the referente list entity provider
     */
    public abstract ReferenceList getReferenteListEntityProvider(  );

    /**
     * Checks if is key provider.
     *
     * @param strKey the str key
     * @return the boolean
     */
    public abstract Boolean isKeyProvider( String strKey );

    /**
     * Gets the instance provider.
     *
     * @param strKey the str key
     * @return the instance provider
     */
    public abstract AbstractServiceProvider getInstanceProvider( String strKey );

    /**
     * Checks if is manager provider.
     *
     * @return the boolean
     */
    public Boolean isManagerProvider(  )
    {
        return _bIsManagerProvider;
    }

    /**
     * Sets the manager provider.
     *
     * @param bIsManager the new manager provider
     */
    public void setManagerProvider( Boolean bIsManager )
    {
        _bIsManagerProvider = bIsManager;
    }

    /**
     * Gets the key.
     *
     * @return _strKey
     */
    public String getKey(  )
    {
        return _strKey;
    }

    /**
     * Gets the bean name.
     *
     * @return _strbeanName
     */
    public String getBeanName(  )
    {
        return _strBeanName;
    }

    /**
     * Sets the key.
     *
     * @param strKey to set _strKey
     */
    public void setKey( String strKey )
    {
        _strKey = strKey;
    }

    /**
     * Sets the bean name.
     *
     * @param strBeanName to set _strBeanName
     */
    public void setBeanName( String strBeanName )
    {
        _strBeanName = strBeanName;
    }

    /**
     * Gets the title.
     *
     * @param locale to localize the title
     * @return the title
     */
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( _strTitleI18nKey, locale );
    }

    /**
     * Gets the title i18n key.
     *
     * @return _strtitleI18nKey
     */
    public String getTitleI18nKey(  )
    {
        return _strTitleI18nKey;
    }

    /**
     * Sets the title i18n key.
     *
     * @param strTitleI18nKey to set _strTitleI18nKey
     */
    public void setTitleI18nKey( String strTitleI18nKey )
    {
        _strTitleI18nKey = strTitleI18nKey;
    }

    /**
     * Checks if is invoked.
     *
     * @param strExtenderType of provider
     * @return Boolean if the provider is invoked
     */
    public boolean isInvoked( String strExtenderType )
    {
        if ( StringUtils.isNotBlank( strExtenderType ) )
        {
            return getKey(  ).equals( strExtenderType );
        }

        return false;
    }
}
