/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fallphenix
 */
public class Mook2ProviderService extends AbstractServiceProvider{

	private static final String BEAN_RESOURCE = "workflow-notifygru.gruResource2";
    private static final String TEMPLATE_FREEMARKER_LIST = "admin/plugins/workflow/modules/notifygru/freemarker_list.html";

    @Override
    public String getUserEmail( int _nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource.getEmail(  );
    }

    @Override
    public String getUserGuid( int _nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource.getIdUser(  );
    }

    @Override
    public String getStatus( int _nIdResource )
    {
        // TODO Auto-generated method stub
    	Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource.getStatusText();
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public String getInfosHelp( HttpServletRequest request, Map<String, Object> model )
    {
    	Locale locale = request != null ? request.getLocale(  ) : I18nService.getDefaultLocale(  );
        String strResourceInfo = StringUtils.EMPTY;
        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( AppTemplateService.getTemplate( 
                    TEMPLATE_FREEMARKER_LIST, locale, model ).getHtml(  ), locale, model );

        strResourceInfo = t.getHtml(  );

        return strResourceInfo;
    }

    @Override
    public Object getInfos( int _nIdResource )
    {
        // TODO Auto-generated method stub
        Resource resource = SpringContextService.getBean( BEAN_RESOURCE );

        return resource;
    }
   
}
