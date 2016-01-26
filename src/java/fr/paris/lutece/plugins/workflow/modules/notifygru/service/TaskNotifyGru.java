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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.TaskNotifyGruConstants;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.html.HtmlTemplate;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 *
 * TaskNotifyGru
 *
 */
public class TaskNotifyGru extends SimpleTask
{
    private static final Pattern REMOVE_TAGS = Pattern.compile( "<.+?>" );
    private static final String _DEFAULT_VALUE_JSON = "";
    private static final int HTTP_CODE_RESPONSE_CREATED = 201;

    // SERVICES 
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;
    private AbstractServiceProvider _notifyGruService;

    /**
     * {@inheritDoc}
     *
     * @param nIdResourceHistory
     * @param request
     * @param locale
     */
    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
        /*Task Config*/
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );

        /*process if Task config not null and valide provider*/
        if ( ( config != null ) && ServiceConfigTaskForm.isBeanExiste( config.getIdSpringProvider(  ) ) )
        {
            //get provider
            _notifyGruService = SpringContextService.getBean( config.getIdSpringProvider(  ) );

            JSONObject fluxJson = new JSONObject(  );
            JSONObject notificationJson = this.buildJsonGlobal( config, nIdResourceHistory, locale );

            //if active config for Mail : user_email
            String strMessageEmail = _DEFAULT_VALUE_JSON;

            if ( config.isActiveOngletEmail(  ) )
            {
                JSONObject userEmailJson = this.buildJsonMessageEmail( config, nIdResourceHistory, locale );
                notificationJson.accumulate( TaskNotifyGruConstants.MARK_USER_MAIL, userEmailJson );
            }

            //if active config for Desk : user_dashboard
            String strMessageGuichet = "";

            if ( config.isActiveOngletGuichet(  ) )
            {
                JSONObject userDashBoardJson = this.buildJsonMessageGuichet( config, nIdResourceHistory, locale );
                notificationJson.accumulate( TaskNotifyGruConstants.MARK_USER_DASHBOARD, userDashBoardJson );
            }

            //if active config for SMS : user_sms
            String strMessageSMS = "";

            if ( config.isActiveOngletSMS(  ) &&
                    !_notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory )
                                          .equals( TaskNotifyGruConstants.OPTIONAL_STRING_VALUE ) )
            {
                JSONObject smsJson = this.buildJsonMessageSMS( config, nIdResourceHistory, locale );
                notificationJson.accumulate( TaskNotifyGruConstants.MARK_USER_SMS, smsJson );
            }

            //if active config for Agent : backoffice_logging
            if ( config.isActiveOngletAgent(  ) )
            {
                JSONObject backOfficeLogginJson = this.buildJsonMessageBackOfficeLoggin( strMessageSMS,
                        strMessageGuichet, strMessageEmail, config, nIdResourceHistory, locale );
                notificationJson.accumulate( TaskNotifyGruConstants.MARK_BACK_OFFICE_LOGGING, backOfficeLogginJson );
            }

            //build JSON notification
            fluxJson.accumulate( TaskNotifyGruConstants.MARK_NOTIFICATION, notificationJson );

            String strJsontoESB = fluxJson.toString( 2 );

            try
            {
                //get token from API Manager
                JSONObject tokenAuth = this.getToken(  );
                //send Json flux to ESB
                this.senJsonFlux( strJsontoESB, tokenAuth );
            }
            catch ( Exception e )
            {
                e.getMessage(  );
            }
        }

        /*end process if Task config not null and valide provider*/
    }

    /**
     * @param config the config
     * @param nIdResourceHistory thr ressource history
     * @param locale the local of request
     *  * @return JSONObject Global
     *  */
    private JSONObject buildJsonGlobal( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        JSONObject notificationJson = new JSONObject(  );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_USER_GUID,
            _notifyGruService.getUserGuid( nIdResourceHistory ) );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_EMAIL,
            _notifyGruService.getUserEmail( nIdResourceHistory ) );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_CRM_STATUS_ID, config.getCrmStatusIdCommune(  ) );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_NOTIFICATION_TYPE, _DEFAULT_VALUE_JSON );

        int nIdDemand = _notifyGruService.getOptionalDemandId( nIdResourceHistory );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_ID_DEMAND,
            ( ( nIdDemand != TaskNotifyGruConstants.OPTIONAL_INT_VALUE )
            ? _notifyGruService.getOptionalDemandId( nIdResourceHistory ) : _DEFAULT_VALUE_JSON ) );

        int nIdDemandType = _notifyGruService.getOptionalDemandIdType( nIdResourceHistory );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_ID_DEMAND_TYPE,
            ( ( nIdDemandType != TaskNotifyGruConstants.OPTIONAL_INT_VALUE ) ? 1 : _DEFAULT_VALUE_JSON ) );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_DEMAND_MAX_STEP,
            ( ( config.getDemandMaxStepGuichet(  ) >= 0 ) ? config.getDemandMaxStepGuichet(  ) : _DEFAULT_VALUE_JSON ) );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_DEMAND_USER_CURRENT_STEP,
            ( ( config.getDemandMaxStepGuichet(  ) >= 0 ) ? config.getDemandUserCurrentStepGuichet(  )
                                                          : _DEFAULT_VALUE_JSON ) );
        notificationJson.accumulate( TaskNotifyGruConstants.MARK_DEMAND_STATE,
            ( ( config.getDemandStateGuichet(  ) >= 0 ) ? config.getDemandStateGuichet(  ) : _DEFAULT_VALUE_JSON ) );

        return notificationJson;
    }

    /**
     * @param config the config
     * @param nIdResourceHistory thr ressource history
     * @param locale the local of request
     *  * @return JSONObject Guichet
     *  */
    private JSONObject buildJsonMessageGuichet( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        JSONObject userDashBoardJson = new JSONObject(  );
        HtmlTemplate tMessageUserDashboard = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(  ),
                locale, _notifyGruService.getInfos( nIdResourceHistory ) );

        userDashBoardJson.accumulate( TaskNotifyGruConstants.MARK_STATUS_TEXT_USERDASHBOARD,
            config.getStatustextGuichet(  ) );
        userDashBoardJson.accumulate( TaskNotifyGruConstants.MARK_SENDER_NAME_USERDASHBOARD,
            config.getSenderNameGuichet(  ) );
        userDashBoardJson.accumulate( TaskNotifyGruConstants.MARK_SUBJECT_USERDASHBOARD, config.getSubjectGuichet(  ) );
        userDashBoardJson.accumulate( TaskNotifyGruConstants.MARK_MESSAGE_USERDASHBOARD,
            this.getHTMLEntities( tMessageUserDashboard.getHtml(  ) ) );
        userDashBoardJson.accumulate( TaskNotifyGruConstants.MARK_DATA_USERDASHBOARD, _DEFAULT_VALUE_JSON );

        return userDashBoardJson;
    }

    /**
     * @param strMessageSMS initialization message of SMS
     * * @param strMessageGuichet initialization message of Guichet
     * * @param strMessageEmail initialization message of Email
     * @param config the config
     * @param nIdResourceHistory thr ressource history
     * @param locale the local of request
     *  * @return JSONObject Backoffice
     *  */
    private JSONObject buildJsonMessageBackOfficeLoggin( String strMessageSMS, String strMessageGuichet,
        String strMessageEmail, TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        JSONObject backOfficeLogginJson = new JSONObject(  );
        backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_STATUS_TEXT_BACK_OFFICE_LOGGING,
            config.getStatustextGuichet(  ) );

        HtmlTemplate tMessageAgent = AppTemplateService.getTemplateFromStringFtl( config.getMessageAgent(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );
        String strMessageAgent = this.getHTMLEntities( tMessageAgent.getHtml(  ) );

        backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_MESSAGE_BACK_OFFICE_LOGGING, strMessageAgent );
        backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_NOTIFIED_ON_DASHBOARD,
            ( config.isActiveOngletGuichet(  ) ) ? 1 : 0 );
        backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_NOTIFIED_BY_EMAIL,
            ( config.isActiveOngletEmail(  ) ) ? 1 : 0 );
        backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_NOTIFIED_BY_SMS,
            ( config.isActiveOngletSMS(  ) ) ? 1 : 0 );

        if ( config.isActiveOngletGuichet(  ) )
        {
            backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_DASHBOARD_NOTIFICATION,
                config.getLevelNotificationGuichet(  ) );
            backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_VIEW_DASHBOARD_NOTIFICATION,
                TaskNotifyGruConstants.MARK_DISPLAY_MESSAGE + " " + strMessageGuichet );
        }

        if ( config.isActiveOngletEmail(  ) )
        {
            backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_EMAIL_NOTIFICATION,
                config.getLevelNotificationEmail(  ) );
            backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_VIEW_EMAIL_NOTIFICATION,
                TaskNotifyGruConstants.MESSAGE_DISPLAY_EMAIL + " " +
                _notifyGruService.getUserEmail( nIdResourceHistory ) + TaskNotifyGruConstants.MESSAGE_DISPLAY_OBJECT +
                " " + config.getSubjectEmail(  ) + TaskNotifyGruConstants.MESSAGE_DISPLAY_MESSAGE_EMAIL + " " +
                strMessageEmail );
        }

        if ( config.isActiveOngletSMS(  ) &&
                !_notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory )
                                      .equals( TaskNotifyGruConstants.OPTIONAL_STRING_VALUE ) )
        {
            backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_DISPLAY_LEVEL_SMS_NOTIFICATION,
                config.getLevelNotificationSMS(  ) );
            backOfficeLogginJson.accumulate( TaskNotifyGruConstants.MARK_VIEW_SMS_NOTIFICATION,
                TaskNotifyGruConstants.MESSAGE_DISPLAY_SMS + " " +
                _notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory ) +
                TaskNotifyGruConstants.MESSAGE_DISPLAY_MESSAGE_SMS + " " + strMessageSMS );
        }

        return backOfficeLogginJson;
    }

    /**
     * @param config the config
     * @param nIdResourceHistory thr ressource history
     * @param locale the local of request
     *  * @return JSONObject SMS
     *  */
    private JSONObject buildJsonMessageSMS( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        JSONObject smsJson = new JSONObject(  );
        HtmlTemplate tMessageSMS = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );
        smsJson.accumulate( TaskNotifyGruConstants.MARK_PHONE_NUMBER,
            _notifyGruService.getOptionalMobilePhoneNumber( nIdResourceHistory ) );
        smsJson.accumulate( TaskNotifyGruConstants.MARK_MESSAGE_SMS, tMessageSMS.getHtml(  ) );

        return smsJson;
    }

    /**
     * @param config the config
     * @param nIdResourceHistory thr ressource history
     * @param locale the local of request
     * @return JSONObject Mail
     * */
    private JSONObject buildJsonMessageEmail( TaskNotifyGruConfig config, int nIdResourceHistory, Locale locale )
    {
        JSONObject userEmailJson = new JSONObject(  );
        HtmlTemplate tMessageEmail = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(  ), locale,
                _notifyGruService.getInfos( nIdResourceHistory ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_SENDER_NAME, config.getSenderNameEmail(  ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_SENDER_EMAIL, MailService.getNoReplyEmail(  ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_RECIPIENT,
            _notifyGruService.getUserEmail( nIdResourceHistory ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_SUBJECT, config.getSubjectEmail(  ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_MESSAGE_EMAIL,
            this.getHTMLEntities( tMessageEmail.getHtml(  ) ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_CC,
            ( StringUtils.isNotBlank( config.getRecipientsCcEmail(  ) ) ? config.getRecipientsCcEmail(  ) : "" ) );
        userEmailJson.accumulate( TaskNotifyGruConstants.MARK_CCI,
            ( StringUtils.isNotBlank( config.getRecipientsCciEmail(  ) ) ? config.getRecipientsCcEmail(  ) : "" ) );

        return userEmailJson;
    }

    /**
     * @param htmlData to encode
     * @return String without entities HTML
     * */
    private String getHTMLEntities( String htmlData )
    {
        //        htmlData = StringEscapeUtils.unescapeHtml4(htmlData);
        //        AppLogService.info("Apres unescapeHtml4 \n" + htmlData);
        //        htmlData = removeTags(htmlData);
        return htmlData;
    }

    /**
     * @param string to remove tag
     * @return String without html tag
     * */
    public static String removeTags( String string )
    {
        if ( ( string == null ) || ( string.length(  ) == 0 ) )
        {
            return string;
        }

        Matcher m = REMOVE_TAGS.matcher( string );

        return m.replaceAll( "" );
    }

    /**
     * @param strJsontoESB json flux te send
      * @param tokenAuth token to auth in API Manager
     * */
    private void senJsonFlux( String strJsontoESB, JSONObject tokenAuth )
    {
        Client client = Client.create(  );

        WebResource webResource = client.resource( AppPropertiesService.getProperty( TaskNotifyGruConstants.URL_ESB ) );

        ClientResponse response = webResource.type( TaskNotifyGruConstants.CONTENT_FORMAT )
                                             .header( HttpHeaders.AUTHORIZATION,
                TaskNotifyGruConstants.TYPE_AUTHENTIFICATION +" "+
                (String) tokenAuth.get( TaskNotifyGruConstants.PARAMS_ACCES_TOKEN ) ).accept( MediaType.APPLICATION_JSON )
                                             .post( ClientResponse.class, strJsontoESB );

        if ( response.getStatus(  ) != HTTP_CODE_RESPONSE_CREATED )
        {
            throw new RuntimeException( TaskNotifyGruConstants.ERROR_MESSAGE + response.getStatus(  ) );
        }
    }

    /**
     *
     * @return JSONObject auth to API manager
     * */
    private JSONObject getToken(  )
    {
        Client client = Client.create(  );

        WebResource webResource = client.resource( AppPropertiesService.getProperty( TaskNotifyGruConstants.URL_TOKEN ) );

        javax.ws.rs.core.MultivaluedMap<String, String> params = new com.sun.jersey.core.util.MultivaluedMapImpl(  );
        params.add( TaskNotifyGruConstants.PARAMS_GRANT_TYPE, TaskNotifyGruConstants.PARAMS_GRANT_TYPE_VALUE );

        ClientResponse response = webResource.type( TaskNotifyGruConstants.CONTENT_FORMAT_TOKEN )
                                             .header( HttpHeaders.AUTHORIZATION,
                TaskNotifyGruConstants.TYPE_AUTHENTIFICATION + " " +
                AppPropertiesService.getProperty( TaskNotifyGruConstants.TOKEN ) ).accept( MediaType.APPLICATION_JSON )
                                             .post( ClientResponse.class, params );

        String output = response.getEntity( String.class );

        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( output );

        return jsonObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveConfig(  )
    {
        _taskNotifyGruConfigService.remove( this.getId(  ) );
    }

    /**
     * {@inheritDoc}
     * @param locale
     * @return
     */
    @Override
    public String getTitle( Locale locale )
    {
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );

        if ( config != null )
        {
            return I18nService.getLocalizedString( NotifyGruConstants.TITLE_NOTIFY, locale );
        }

        return I18nService.getLocalizedString( NotifyGruConstants.TITLE_NOTIFY, locale );
    }
}
