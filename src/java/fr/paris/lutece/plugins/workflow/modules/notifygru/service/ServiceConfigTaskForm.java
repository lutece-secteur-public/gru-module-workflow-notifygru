/*
 * Copyright (c) 2002-2021, City of Paris
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
import fr.paris.lutece.plugins.workflow.modules.notifygru.web.INotificationConfig;
import fr.paris.lutece.plugins.workflow.service.provider.ProviderManagerUtil;
import fr.paris.lutece.plugins.workflowcore.service.provider.AbstractProviderManager;
import fr.paris.lutece.plugins.workflowcore.service.provider.ProviderDescription;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class ServiceConfigTaskForm.
 */
public final class ServiceConfigTaskForm
{
    // MESSAGES
    private static final String MESSAGE_NOTIFICATION_CONFIG_TITLE_PREFIX = "module.workflow.notifygru.notification.config.title.";

    /**
     * Instantiates a new service config task form.
     */
    private ServiceConfigTaskForm( )
    {

    }

    /**
     * Builds a {@code ReferenceList} of inactive notification configurations
     * 
     * @param listNotificationConfig
     *            the list of all notification configurations
     * @param locale
     *            the locale used to build the {@code ReferenceList}
     * @return the {@code ReferenceList}
     */
    public static ReferenceList buildReferenceListOfInactiveNotificationConfigs( List<INotificationConfig> listNotificationConfig, Locale locale )
    {
        ReferenceList refenreceList = new ReferenceList( );

        for ( INotificationConfig notificationConfig : listNotificationConfig )
        {
            if ( !notificationConfig.isActive( ) )
            {
                refenreceList.addItem( notificationConfig.getName( ),
                        I18nService.getLocalizedString( MESSAGE_NOTIFICATION_CONFIG_TITLE_PREFIX + notificationConfig.getName( ), locale ) );
            }
        }

        return refenreceList;
    }

    /**
     * display the level of notification.
     *
     * @param locale
     *            to localize resources
     * @return the list of notification
     */
    public static ReferenceList getListNotification( Locale locale )
    {
        ReferenceList refenreceList = new ReferenceList( );
        refenreceList.addItem( 0, I18nService.getLocalizedString( Constants.VISIBILITY_ALL, locale ) );
        refenreceList.addItem( 1, I18nService.getLocalizedString( Constants.VISIBILITY_DOMAIN, locale ) );
        refenreceList.addItem( 2, I18nService.getLocalizedString( Constants.VISIBILITY_ADMIN, locale ) );

        return refenreceList;
    }

    /**
     * display the error message.
     *
     * @param lstErrors
     *            the list of errors
     * @param request
     *            the http request
     * @return the error message
     */
    public static String displayErrorMessage( List<String> lstErrors, HttpServletRequest request )
    {
        Object [ ] tabRequiredFields = new Object [ lstErrors.size( )];

        for ( int i = 0; i < lstErrors.size( ); i++ )
        {
            tabRequiredFields [i] = lstErrors.get( i );
        }

        if ( tabRequiredFields.length > 2 )
        {
            return AdminMessageService.getMessageUrl( request, Constants.MESSAGE_MANDATORY_THREE_FIELD, tabRequiredFields, AdminMessage.TYPE_WARNING );
        }
        else
            if ( tabRequiredFields.length == 2 )
            {
                return AdminMessageService.getMessageUrl( request, Constants.MESSAGE_MANDATORY_TWO_FIELD, tabRequiredFields, AdminMessage.TYPE_WARNING );
            }

        return AdminMessageService.getMessageUrl( request, Constants.MESSAGE_MANDATORY_ONE_FIELD, tabRequiredFields, AdminMessage.TYPE_WARNING );
    }

    /**
     * Gets the list provider.
     *
     * @param task
     *            the task
     * @return the list of providers
     */
    public static ReferenceList getProviderReferenceList( ITask task )
    {
        ReferenceList referenceList = new ReferenceList( );
        List<AbstractProviderManager> listProviderManagers = SpringContextService.getBeansOfType( AbstractProviderManager.class );

        for ( AbstractProviderManager providerManager : listProviderManagers )
        {
            Collection<ProviderDescription> collectionProviderDescriptions = providerManager.getAllProviderDescriptions( task );

            for ( ProviderDescription providerDescription : collectionProviderDescriptions )
            {
                referenceList.addItem( ProviderManagerUtil.buildCompleteProviderId( providerManager.getId( ), providerDescription.getId( ) ),
                        providerDescription.getLabel( ) );
            }
        }

        return referenceList;
    }

    /**
     * Gets the index of the specified tab.
     *
     * @param strTabName
     *            the name of the tab
     * @return the index of the tab
     */
    public static int getTabIndex( String strTabName )
    {
        int nNumber = 0;

        if ( strTabName == null )
        {
            return nNumber;
        }

        switch( strTabName )
        {
            case Constants.MARK_TAB_GUICHET:
                nNumber = 0;

                break;

            case Constants.MARK_TAB_AGENT:
                nNumber = 1;

                break;

            case Constants.MARK_TAB_EMAIL:
                nNumber = 2;

                break;

            case Constants.MARK_TAB_SMS:
                nNumber = 3;

                break;

            case Constants.MARK_TAB_BROADCAST:
                nNumber = 4;

                break;

            default:
                nNumber = 0;
        }

        return nNumber;
    }
}
