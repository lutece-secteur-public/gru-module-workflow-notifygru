package fr.paris.lutece.plugins.workflow.modules.notifygru.service;


import fr.paris.lutece.plugins.workflow.modules.notifygru.business.BackofficeLogging;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.NotificationFlux;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.UserDashboard;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.UserEmail;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.UserSms;
import fr.paris.lutece.plugins.workflow.modules.notifygru.utils.constants.NotifyGruConstants;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.IResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.mail.MailService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.mail.FileAttachment;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * TaskNotifyGru
 *
 */
public class TaskNotifyGru extends SimpleTask
{
    // TEMPLATES
    private static final String TEMPLATE_TASK_NOTIFY_DESK = "admin/plugins/workflow/modules/notifygru/task_notify_gru_desk.html";
    private static final String TEMPLATE_TASK_NOTIFY_AGENT = "admin/plugins/workflow/modules/notifygru/task_notify_gru_agent.html";
    private static final String TEMPLATE_TASK_NOTIFY_MAIL = "admin/plugins/workflow/modules/notifygru/task_notify_gru_mail.html";
    private static final String TEMPLATE_TASK_NOTIFY_SMS = "admin/plugins/workflow/modules/notifygru/task_notify_gru_sms.html";

    // SERVICES
    @Inject
    private IResourceHistoryService _resourceHistoryService;
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;
    @Inject
    private INotifyGruService _notifyGruService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
        //ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey( nIdResourceHistory );
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );

        if ( ( config != null ) )
        {
        	Map<String, Object> modelMessageContent = _notifyGruService.fillModelMoke( );
            
            Map<String, Object> model = new HashMap<String, Object>();

            model.put(NotifyGruConstants.MARK_CONFIG, config);
            
        	NotificationFlux notificationFlux = new NotificationFlux();
        	
        	notificationFlux.setUserGuid(_notifyGruService.getUserGuid(config, 0, 0));
        	notificationFlux.setLabelUserEmail(_notifyGruService.getEmail( config, 0,0 ));
        	
         	boolean bIsNotifyByDesk = config.isActiveOngletGuichet() ;
         	boolean bIsNotifyByViewAgent = config.isActiveOngletAgent() ;
            boolean bIsNotifyByEmail = config.isActiveOngletEmail() ;
            boolean bIsNotifyBySms = config.isActiveOngletSMS() ;
            if(bIsNotifyByDesk)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageGuichet(), locale, modelMessageContent );
            	UserDashboard userDashboard = new UserDashboard();
            	userDashboard.setStatus(config.getStatusTextGuichet());
            	userDashboard.setMessage(t.getHtml());
            	notificationFlux.setUserDashboard(userDashboard);
            }
            if(bIsNotifyByViewAgent)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageAgent(), locale, modelMessageContent );
            	BackofficeLogging backofficeLogging = new BackofficeLogging();
            	backofficeLogging.setMessage(t.getHtml());
            	notificationFlux.setBackofficeLogging(backofficeLogging);
            }
            if(bIsNotifyByEmail)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageEmail(), locale, modelMessageContent );
            	UserEmail userEmail = new UserEmail();
            	userEmail.setSenderName(config.getSenderNameEmail());
            	userEmail.setSenderEmail(MailService.getNoReplyEmail(  ));
            	userEmail.setSubject(config.getSubjectEmail());
            	userEmail.setMessage(t.getHtml());
            	notificationFlux.setUserEmail(userEmail);
            }
            if(bIsNotifyBySms)
            {
            	HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( config.getMessageSMS(), locale, modelMessageContent );
            	UserSms userSms = new UserSms();
            	userSms.setPhoneNumber(_notifyGruService.getSMSPhoneNumber( config, 0,0 ));
            	userSms.setMessage(t.getHtml());
            	notificationFlux.setUserSms(userSms);
            }
        }  
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
     */
    @Override
    public String getTitle( Locale locale )
    {
        
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );

        if ( config != null )
        {
           
            return new String("TACHE GRU CONFIGURER");
        }

        return new String("TACHE GRU CONFIGURER");
    }
}
