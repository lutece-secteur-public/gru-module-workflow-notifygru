package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.plugins.directory.business.Directory;
import fr.paris.lutece.plugins.directory.business.DirectoryHome;
import fr.paris.lutece.plugins.directory.business.Record;
import fr.paris.lutece.plugins.directory.business.RecordHome;
import fr.paris.lutece.plugins.directory.service.DirectoryPlugin;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;
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
    private static final String TEMPLATE_TASK_NOTIFY_MAIL = "admin/plugins/workflow/modules/notifygru/task_notify_gru_mail.html";
    private static final String TEMPLATE_TASK_NOTIFY_SMS = "admin/plugins/workflow/modules/notifygru/task_notify_gru_sms.html";

    // SERVICES
    @Inject
    private IResourceHistoryService _resourceHistoryService;
    @Inject
    @Named( TaskNotifyGruConfigService.BEAN_SERVICE )
    private ITaskConfigService _taskNotifyGruConfigService;
    @Inject
    private INotifyGruService _notifyDirectoryService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
        ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey( nIdResourceHistory );
        TaskNotifyGruConfig config = _taskNotifyGruConfigService.findByPrimaryKey( this.getId(  ) );
/*
        if ( ( config != null ) && ( resourceHistory != null ) &&
                Record.WORKFLOW_RESOURCE_TYPE.equals( resourceHistory.getResourceType(  ) ) )
        {
            Plugin pluginDirectory = PluginService.getPlugin( DirectoryPlugin.PLUGIN_NAME );

            // Record
            Record record = RecordHome.findByPrimaryKey( resourceHistory.getIdResource(  ), pluginDirectory );

            if ( record != null )
            {
                Directory directory = DirectoryHome.findByPrimaryKey( record.getDirectory(  ).getIdDirectory(  ),
                        pluginDirectory );

                if ( directory != null )
                {
                    record.setDirectory( directory );

                    // Get email
                    String strEmail = _notifyDirectoryService.getEmail( config, record.getIdRecord(  ),
                            directory.getIdDirectory(  ) );
                    String strEmailContent = StringUtils.EMPTY;

                    // Get Sms
                    String strSms = _notifyDirectoryService.getSMSPhoneNumber( config, record.getIdRecord(  ),
                            directory.getIdDirectory(  ) );
                    String strSmsContent = StringUtils.EMPTY;

                    //Get Files Attachment
                    List<FileAttachment> listFileAttachment = _notifyDirectoryService.getFilesAttachment( config,
                            record.getIdRecord(  ), directory.getIdDirectory(  ) );

                    // Get sender email
                    String strSenderEmail = MailService.getNoReplyEmail(  );

                    Map<String, Object> model = _notifyDirectoryService.fillModel( config, resourceHistory, record,
                            directory, request, getAction(  ).getId(  ), nIdResourceHistory );

                    String strSubject = AppTemplateService.getTemplateFromStringFtl( config.getSubjectOngle1(), locale,
                            model ).getHtml(  );

                    boolean bIsNotifyByEmail = config.isNotifyByEmail(  ) && StringUtils.isNotBlank( strEmail );
                    boolean bIsNotifyBySms = config.isNotifyBySms(  ) && StringUtils.isNotBlank( strSms );
                    boolean bIsNotifyByMailingList = config.isNotifyByMailingList(  );
                    boolean bHasRecipients = ( StringUtils.isNotBlank( config.getRecipientsBcc(  ) ) ||
                        StringUtils.isNotBlank( config.getRecipientsCc(  ) ) );

                    if ( bIsNotifyByEmail || bIsNotifyByMailingList || bHasRecipients )
                    {
                        HtmlTemplate t = AppTemplateService.getTemplateFromStringFtl( AppTemplateService.getTemplate( 
                                    TEMPLATE_TASK_NOTIFY_MAIL, locale, model ).getHtml(  ), locale, model );
                        strEmailContent = t.getHtml(  );
                    }

                    if ( bIsNotifyBySms )
                    {
                        HtmlTemplate tSMS = AppTemplateService.getTemplateFromStringFtl( AppTemplateService.getTemplate( 
                                    TEMPLATE_TASK_NOTIFY_SMS, locale, model ).getHtml(  ), locale, model );
                        strSmsContent = tSMS.toString(  );
                    }

                    _notifyDirectoryService.sendMessage( config, strEmail, strSms, strSenderEmail, strSubject,
                        strEmailContent, strSmsContent, listFileAttachment );
                }
            }  
        }  */
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
