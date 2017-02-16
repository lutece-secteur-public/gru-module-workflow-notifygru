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
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.AbstractProviderManager;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.ProviderDescription;
import fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider.ProviderManagerUtil;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.Constants;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class ServiceConfigTaskForm.
 */
public final class ServiceConfigTaskForm
{
    /**
     * Instantiates a new service config task form.
     *
     * @exception Exception
     *                not instance
     */
    private ServiceConfigTaskForm( ) throws Exception
    {
        throw new Exception( );
    }

    /**
     * Checks if bean exists.
     *
     * @param strIdBean
     *            the str id bean
     * @param task
     *            the task
     * @return true if the bean exist
     */
    public static Boolean isBeanExists( String strIdBean, ITask task )
    {
        Boolean bexist = false;

        if ( strIdBean != null )
        {
            AbstractServiceProvider providerService = getServiceProviderFromKey( strIdBean );

            if ( providerService != null )
            {
                providerService.updateListProvider( task );

                bexist = providerService.isKeyProvider( strIdBean );
            }
            else
            {
                bexist = isSpringBeanExists( strIdBean );
            }
        }

        return bexist;
    }

    /**
     * Checks if bean exists.
     *
     * @param strIdBean
     *            the str id bean
     * @return the boolean
     */
    public static Boolean isBeanExists( String strIdBean )
    {
        Boolean bexist = false;

        if ( strIdBean != null )
        {
            AbstractServiceProvider providerService = getServiceProviderFromKey( strIdBean );

            if ( providerService != null )
            {
                providerService.updateListProvider( );

                bexist = providerService.isKeyProvider( strIdBean );
            }
            else
            {
                bexist = isSpringBeanExists( strIdBean );
            }
        }

        return bexist;
    }

    /**
     * Gets the customized bean for a given task.
     *
     * @param strIdBean
     *            the str id bean
     * @param task
     *            the task
     * @return the customized bean
     */
    public static AbstractServiceProvider getCustomizedBean( String strIdBean, ITask task )
    {
        AbstractServiceProvider provider = null;

        if ( strIdBean != null )
        {
            AbstractServiceProvider providerService = getServiceProviderFromKey( strIdBean );

            if ( providerService != null )
            {
                providerService.updateListProvider( task );

                provider = providerService.getInstanceProvider( strIdBean );
            }
            else
            {
                provider = SpringContextService.getBean( strIdBean );
            }
        }

        return provider;
    }

    /**
     * Gets the customized bean.
     *
     * @param strIdBean
     *            the str id bean
     * @return the customized bean
     */
    public static AbstractServiceProvider getCustomizedBean( String strIdBean )
    {
        AbstractServiceProvider provider = null;

        if ( strIdBean != null )
        {
            AbstractServiceProvider providerService = getServiceProviderFromKey( strIdBean );

            if ( providerService != null )
            {
                providerService.updateListProvider( );

                provider = providerService.getInstanceProvider( strIdBean );
            }
            else
            {
                provider = SpringContextService.getBean( strIdBean );
            }
        }

        return provider;
    }

    /**
     * Checks if spring bean exists.
     *
     * @param strIdBean
     *            the str id bean
     * @return the boolean
     */
    public static Boolean isSpringBeanExists( String strIdBean )
    {
        Boolean bexist = false;

        if ( strIdBean == null )
        {
            bexist = false;
        }
        else
        {
            try
            {
                AbstractServiceProvider providerService = SpringContextService.getBean( strIdBean );

                if ( providerService.getKey( ).equals( strIdBean ) )
                {
                    bexist = true;
                }
            }
            catch( NoSuchBeanDefinitionException e )
            {
                bexist = false;
            }
        }

        return bexist;
    }

    /**
     * retrieve AbstractServiceProvider bean if exists
     * 
     * @param strFullKeyProvider
     *            the key at format beanManagerProvider.@.specificPart
     * @return true if a ManagerProvider exists in Spring context
     */
    public static AbstractServiceProvider getServiceProviderFromKey( String strFullKeyProvider )
    {
        String [ ] providerGabarit = strFullKeyProvider.split( ".@." );

        if ( ( providerGabarit.length == 2 ) && isSpringBeanExists( providerGabarit [0] ) )
        {
            return SpringContextService.getBean( providerGabarit [0] );
        }

        return null;
    }

    /**
     * Gets the list onglet.
     *
     * @param config
     *            of the task
     * @param locale
     *            of request
     * @return the list of onglet
     */
    public static ReferenceList getListOnglet( TaskNotifyGruConfig config, Locale locale )
    {
        ReferenceList refenreceList = new ReferenceList( );

        if ( !config.isActiveOngletGuichet( ) )
        {
            refenreceList.addItem( Constants.MARK_ONGLET_GUICHET, I18nService.getLocalizedString( Constants.VIEW_GUICHET, locale ) );
        }

        if ( !config.isActiveOngletAgent( ) )
        {
            refenreceList.addItem( Constants.MARK_ONGLET_AGENT, I18nService.getLocalizedString( Constants.VIEW_AGENT, locale ) );
        }

        if ( !config.isActiveOngletEmail( ) )
        {
            refenreceList.addItem( Constants.MARK_ONGLET_EMAIL, I18nService.getLocalizedString( Constants.VIEW_EMAIL, locale ) );
        }

        if ( !config.isActiveOngletSMS( ) )
        {
            refenreceList.addItem( Constants.MARK_ONGLET_SMS, I18nService.getLocalizedString( Constants.VIEW_SMS, locale ) );
        }

        if ( !config.isActiveOngletBroadcast( ) )
        {
            refenreceList.addItem( Constants.MARK_ONGLET_LIST, I18nService.getLocalizedString( Constants.VIEW_BROADCAST_LIST, locale ) );
        }

        return refenreceList;
    }

    /**
     * Gets the number of string.
     *
     * @param srtNumber
     *            to convert
     * @return positive int value or -1 if exception or negative value
     */
    public static int parseStringToPositiveNumber( String srtNumber )
    {
        try
        {
            return Math.max( -1, Integer.parseInt( srtNumber ) );
        }
        catch( NumberFormatException e )
        {
            return -1;
        }
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
            Collection<ProviderDescription> collectionProviderDescriptions =  providerManager.getAllProviderDescriptions( task );
            
            for ( ProviderDescription providerDescription : collectionProviderDescriptions ) {
              referenceList.addItem( ProviderManagerUtil.buildCompleteProviderId( providerManager.getId( ), providerDescription.getId( ) ), 
                  providerDescription.getLabel( ) );
          }
        }

        return referenceList;
    }

    /**
     * Sets the config onglet.
     *
     * @param strApply
     *            of form
     * @param strOnglet
     *            of config
     * @param strOngletActive
     *            the active onglet
     * @param bdefault
     *            default onglet
     * @param strRemove
     *            if removing
     * @return true if the Onglet is active
     */
    public static Boolean setConfigOnglet( String strApply, String strOnglet, String strOngletActive, Boolean bdefault, String strRemove )
    {
        boolean bStateOnglet = bdefault;

        if ( strApply != null )
        {
            switch( strApply )
            {
                case Constants.PARAMETER_BUTTON_ADD:

                    if ( strOnglet.equals( strOngletActive ) )
                    {
                        bStateOnglet = true;
                    }

                    break;

                case Constants.PARAMETER_BUTTON_REMOVE_GUICHET:

                    if ( strRemove.equals( Constants.PARAMETER_BUTTON_REMOVE_GUICHET ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case Constants.PARAMETER_BUTTON_REMOVE_AGENT:

                    if ( strRemove.equals( Constants.PARAMETER_BUTTON_REMOVE_AGENT ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case Constants.PARAMETER_BUTTON_REMOVE_EMAIL:

                    if ( strRemove.equals( Constants.PARAMETER_BUTTON_REMOVE_EMAIL ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case Constants.PARAMETER_BUTTON_REMOVE_SMS:

                    if ( strRemove.equals( Constants.PARAMETER_BUTTON_REMOVE_SMS ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case Constants.PARAMETER_BUTTON_REMOVE_LISTE:

                    if ( strRemove.equals( Constants.PARAMETER_BUTTON_REMOVE_LISTE ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                default:
                    bStateOnglet = false;

                    break;
            }
        }

        return bStateOnglet;
    }

    /**
     * Gets the number oblet.
     *
     * @param strOnglet
     *            of config
     * @return the number of onglet
     */
    public static int getNumberOblet( String strOnglet )
    {
        int nNumber = 0;

        if ( strOnglet == null )
        {
            return nNumber;
        }

        switch( strOnglet )
        {
            case Constants.MARK_ONGLET_GUICHET:
                nNumber = 0;

                break;

            case Constants.MARK_ONGLET_AGENT:
                nNumber = 1;

                break;

            case Constants.MARK_ONGLET_EMAIL:
                nNumber = 2;

                break;

            case Constants.MARK_ONGLET_SMS:
                nNumber = 3;

                break;

            case Constants.MARK_ONGLET_LIST:
                nNumber = 4;

                break;

            default:
                nNumber = 0;
        }

        return nNumber;
    }
}
