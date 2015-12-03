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

import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author
 *
 */
public class Mook1ProviderService extends AbstractServiceProvider
{
    private static final String BEAN_RESOURCE = "workflow-notifygru.gruResource1";
    private static final String TEMPLATE_FREEMARKER_LIST = "admin/plugins/workflow/modules/notifygru/freemarker_list.html";

    @Override
    public String getUserEmail( int nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource.getEmail(  );
    }

    @Override
    public String getUserGuid( int nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource.getIdUser(  );
    }

    @Override
    public String getStatus( int nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource.getStatusText(  );
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public String getInfosHelp( HttpServletRequest request, Map<String, Object> model )
    {
        Locale locale = ( request != null ) ? request.getLocale(  ) : I18nService.getDefaultLocale(  );
        String strResourceInfo = StringUtils.EMPTY;
        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( AppTemplateService.getTemplate( 
                    TEMPLATE_FREEMARKER_LIST, locale, model ).getHtml(  ), locale, model );

        strResourceInfo = t.getHtml(  );

        return strResourceInfo;
    }

    @Override
    public Object getInfos( int nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource;
    }

	@Override
	public String getPhoneNumber(int nIdResource) {
		// TODO Auto-generated method stub
		return null;
	}
}
