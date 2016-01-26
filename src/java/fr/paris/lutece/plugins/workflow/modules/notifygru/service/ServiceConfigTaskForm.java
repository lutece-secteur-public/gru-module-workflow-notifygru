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

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;



/**
 *
 * @author
 */
public final class ServiceConfigTaskForm
{
    /**
     * @exception Exception not instance
     * */
    private ServiceConfigTaskForm(  ) throws Exception
    {
        throw new Exception(  );
    }

    /**
    *
    * @param strIdBean     * @
    * @return true if the bean exist
    */
    public static Boolean isBeanExiste( String strIdBean )
    {
        if ( strIdBean == null )
        {
            return false;
        }

        try
        {
            AbstractServiceProvider mokeProviderService = SpringContextService.getBean( strIdBean );

            if ( mokeProviderService.getKey(  ).equals( strIdBean ) )
            {
                return true;
            }
        }
        catch ( NoSuchBeanDefinitionException e )
        {
            return false;
        }

        return false;
    }

    /**
     *
     * @param config of the task
         * @param locale of request
     * @return the  list of onglet
     */
    public static ReferenceList getListOnglet( TaskNotifyGruConfig config, Locale locale )
    {
        ReferenceList refenreceList = new ReferenceList(  );

        if ( !config.isActiveOngletGuichet(  ) )
        {
            refenreceList.addItem( NotifyGruConstants.MARK_ONGLET_GUICHET,
                I18nService.getLocalizedString( NotifyGruConstants.VIEW_GUICHET, locale ) );
        }

        if ( !config.isActiveOngletAgent(  ) )
        {
            refenreceList.addItem( NotifyGruConstants.MARK_ONGLET_AGENT,
                I18nService.getLocalizedString( NotifyGruConstants.VIEW_AGENT, locale ) );
        }

        if ( !config.isActiveOngletEmail(  ) )
        {
            refenreceList.addItem( NotifyGruConstants.MARK_ONGLET_EMAIL,
                I18nService.getLocalizedString( NotifyGruConstants.VIEW_EMAIL, locale ) );
        }

        if ( !config.isActiveOngletSMS(  ) )
        {
            refenreceList.addItem( NotifyGruConstants.MARK_ONGLET_SMS,
                I18nService.getLocalizedString( NotifyGruConstants.VIEW_SMS, locale ) );
        }

        if ( !config.isActiveOngletBroadcast(  ) )
        {
            refenreceList.addItem( NotifyGruConstants.MARK_ONGLET_LIST,
                I18nService.getLocalizedString( NotifyGruConstants.VIEW_BROADCAST_LIST, locale ) );
        }

        return refenreceList;
    }

    /**
     * @param srtNumner to convert
     * @return nimber or if exception
     *
     * */
    public static int getNumbertoString( String srtNumner )
    {
        try
        {
            return Integer.parseInt( srtNumner );
        }
        catch ( NumberFormatException e )
        {
            return -1;
        }
    }

    /**
    * display the level of notification
    * @param locale to localize resources
    * @return the list of notification
    */
    public static ReferenceList getListNotification( Locale locale )
    {
        ReferenceList refenreceList = new ReferenceList(  );
        refenreceList.addItem( 0, I18nService.getLocalizedString( NotifyGruConstants.VISIBILITY_ALL, locale ) );
        refenreceList.addItem( 1, I18nService.getLocalizedString( NotifyGruConstants.VISIBILITY_DOMAIN, locale ) );
        refenreceList.addItem( 2, I18nService.getLocalizedString( NotifyGruConstants.VISIBILITY_ADMIN, locale ) );

        return refenreceList;
    }

    /**
     * display the error message
     * @param errors the list of errors
     * @param request the http request
     * @return the error message
     */
    public static String displayErrorMessage( ArrayList<String> errors, HttpServletRequest request )
    {
        Object[] tabRequiredFields = new Object[errors.size(  )];

        for ( int i = 0; i < errors.size(  ); i++ )
        {
            tabRequiredFields[i] = errors.get( i );
        }

        if ( tabRequiredFields.length > 2 )
        {
            return AdminMessageService.getMessageUrl( request, NotifyGruConstants.MESSAGE_MANDATORY_THREE_FIELD,
                tabRequiredFields, AdminMessage.TYPE_WARNING );
        }
        else if ( tabRequiredFields.length == 2 )
        {
            return AdminMessageService.getMessageUrl( request, NotifyGruConstants.MESSAGE_MANDATORY_TWO_FIELD,
                tabRequiredFields, AdminMessage.TYPE_WARNING );
        }

        return AdminMessageService.getMessageUrl( request, NotifyGruConstants.MESSAGE_MANDATORY_ONE_FIELD,
            tabRequiredFields, AdminMessage.TYPE_WARNING );
    }

    /**
     *
     * @return the different implementation
     */
    public static List<AbstractServiceProvider> getImplementationServices(  )
    {
        return SpringContextService.getBeansOfType( AbstractServiceProvider.class );
    }

    /**
     *
     * @return the list of providers
     */
    public static ReferenceList getListProvider(  )
    {
        ReferenceList refenreceList = new ReferenceList(  );

        for ( AbstractServiceProvider provider : getImplementationServices(  ) )
        {
            refenreceList.addItem( provider.getBeanName(  ), provider.getTitle( Locale.getDefault(  ) ) );
        }

        return refenreceList;
    }

    /**
     *
     * @param strApply of form
     * @param strOnglet of config
     * @param strOngletActive the active onglet
     * @param bdefault default onglet
     * @param strRemove  if removing
     * @return true if the Onglet is active
     */
    public static Boolean setConfigOnglet( String strApply, String strOnglet, String strOngletActive, Boolean bdefault,
        String strRemove )
    {
        boolean bStateOnglet = bdefault;

        if ( strApply != null )
        {
            switch ( strApply )
            {
                case NotifyGruConstants.PARAMETER_BUTTON_ADD:

                    if ( strOnglet.equals( strOngletActive ) )
                    {
                        bStateOnglet = true;
                    }

                    break;

                case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET:

                    if ( strRemove.equals( NotifyGruConstants.PARAMETER_BUTTON_REMOVE_GUICHET ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT:

                    if ( strRemove.equals( NotifyGruConstants.PARAMETER_BUTTON_REMOVE_AGENT ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL:

                    if ( strRemove.equals( NotifyGruConstants.PARAMETER_BUTTON_REMOVE_EMAIL ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS:

                    if ( strRemove.equals( NotifyGruConstants.PARAMETER_BUTTON_REMOVE_SMS ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                case NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE:

                    if ( strRemove.equals( NotifyGruConstants.PARAMETER_BUTTON_REMOVE_LISTE ) )
                    {
                        bStateOnglet = false;
                    }

                    break;

                default:
                    bStateOnglet = false; break;
            }
        }

        return bStateOnglet;
    }

    /**
     *
     * @param strOnglet of config
     * @return  the number of onglet
     */
    public static int getNumberOblet( String strOnglet )
    {
        int nNumber = 0;
        
        if( strOnglet == null ) 
        {
			return nNumber;
		}
        
        switch ( strOnglet ) 
        {       
        case NotifyGruConstants.MARK_ONGLET_GUICHET :  nNumber = 0; break;
        case NotifyGruConstants.MARK_ONGLET_AGENT :  nNumber = 1; break;
        case NotifyGruConstants.MARK_ONGLET_EMAIL :  nNumber = 2; break;
        case NotifyGruConstants.MARK_ONGLET_SMS :  nNumber = 3; break;
        case NotifyGruConstants.MARK_ONGLET_LIST :  nNumber = 4; break;
         default: nNumber = 0;
        }

        return nNumber;
    }
}
