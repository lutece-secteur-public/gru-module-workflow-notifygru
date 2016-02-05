/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.paris.lutece.plugins.workflow.modules.notifygru.service;

import fr.paris.lutece.plugins.workflow.modules.notifygru.business.AgentHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.BroadcastHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.EmailHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.GuichetHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.SMSHistory;
import fr.paris.lutece.plugins.workflow.modules.notifygru.business.TaskNotifyGruConfig;


/**
 *
 * @author root
 */
public final class NotificationToHistory
{
    private NotificationToHistory(  )
    {
    }

    public static SMSHistory populateSMS( TaskNotifyGruConfig config, String strMessageSMS )
    {
        SMSHistory oSMSHistory = new SMSHistory(  );
        
        oSMSHistory.setMessageSMS(strMessageSMS);
        oSMSHistory.setLevelNotificationSMS(config.getLevelNotificationSMS());

        return oSMSHistory;
    }

    public static EmailHistory populateEmail( TaskNotifyGruConfig config, String strMessageEmail )
    {
        EmailHistory oEmailHistory = new EmailHistory(  );
        
        oEmailHistory.setLevelNotificationEmail(config.getLevelNotificationEmail());
        oEmailHistory.setMessageEmail(strMessageEmail);
        oEmailHistory.setRecipientsCcEmail(config.getRecipientsCcEmail());
        oEmailHistory.setRecipientsCciEmail(config.getRecipientsCciEmail());
        oEmailHistory.setSenderNameEmail(config.getSenderNameEmail());
        oEmailHistory.setSubjectEmail(config.getSubjectEmail());
       

        return oEmailHistory;
    }

    public static BroadcastHistory populateBroadcast( TaskNotifyGruConfig config, String strMessageBroadcast )
    {
        BroadcastHistory oBroadcastHistory = new BroadcastHistory(  );
        
        oBroadcastHistory.setIdMailingListBroadcast(config.getIdMailingListBroadcast());
        oBroadcastHistory.setLevelNotificationBroadcast(config.getLevelNotificationBroadcast());
        oBroadcastHistory.setMessageBroadcast(strMessageBroadcast);
        oBroadcastHistory.setRecipientsCcBroadcast(config.getRecipientsCcBroadcast());
        oBroadcastHistory.setRecipientsCciBroadcast(config.getRecipientsCciBroadcast());
        oBroadcastHistory.setSenderNameBroadcast(config.getSenderNameBroadcast());
        oBroadcastHistory.setSubjectBroadcast(config.getSubjectBroadcast());
      

        return oBroadcastHistory;
    }

    public static AgentHistory populateAgent( TaskNotifyGruConfig config, String strMessageAgent )
    {
        AgentHistory oAgentHistory = new AgentHistory(  );
        
        oAgentHistory.setMessageAgent(strMessageAgent);
        oAgentHistory.setLevelNotificationAgent(config.getLevelNotificationAgent());
        

        return oAgentHistory;
    }

    public static GuichetHistory populateGuichet( TaskNotifyGruConfig config, String strMessageGuichet )
    {
        GuichetHistory oGuichetHistory = new GuichetHistory(  );

        oGuichetHistory.setLevelNotificationGuichet( config.getLevelNotificationGuichet(  ) );
        oGuichetHistory.setMessageGuichet( strMessageGuichet );
        oGuichetHistory.setSenderNameGuichet( config.getSenderNameGuichet(  ) );
        oGuichetHistory.setStatustextGuichet( config.getStatustextGuichet(  ) );
        oGuichetHistory.setSubjectGuichet( config.getSubjectGuichet(  ) );
        oGuichetHistory.setDemandMaxStepGuichet( config.getDemandMaxStepGuichet(  ) );
        oGuichetHistory.setDemandStateGuichet( config.getDemandStateGuichet(  ) );
        oGuichetHistory.setDemandUserCurrentStepGuichet( config.getDemandUserCurrentStepGuichet(  ) );

        return oGuichetHistory;
    }
}
