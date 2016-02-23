
package fr.paris.lutece.plugins.workflow.modules.notifygru.business;

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;


/**
 *
 * TaskNotifyDirectoryConfig
 *
 */
public class TaskNotifyGruConfig extends TaskConfig
{
    // Variables declarations 
	/*global config*/
    private String _strIdSpringProvider;
    private int _nDemandStatus;
    private int _nSetOnglet;
    /*desk config*/
    private String _strMessageGuichet;
    private String _strStatustextGuichet;
    private String _strSenderNameGuichet;
    private String _strSubjectGuichet;
    private int _nDemandMaxStepGuichet;
    private int _nDemandUserCurrentStepGuichet;
    private boolean _ActiveOngletGuichet;
    /*agent config*/
    private String _strStatustextAgent;
    private String _strMessageAgent;
    private boolean _ActiveOngletAgent;
    /*email config*/
    private String _strSubjectEmail;
    private String _strMessageEmail;
    private String _strSenderNameEmail;
    private String _strRecipientsCcEmail;
    private String _strRecipientsCciEmail;
    private boolean _ActiveOngletEmail;
    /*sms config*/
    private String _strMessageSMS;  
    private boolean _ActiveOngletSMS;
    /*broadcast config*/
    private int _nIdMailingListBroadcast;
    private String _strSenderNameBroadcast;
    private String _strSubjectBroadcast;
    private String _strMessageBroadcast;
    private String _strRecipientsCcBroadcast;
    private String _strRecipientsCciBroadcast;
    private boolean _ActiveOngletBroadcast;
    
    
       /**
        * Returns the IdSpringProvider
        * @return The IdSpringProvider
        */ 
    public String getIdSpringProvider()
    {
        return _strIdSpringProvider;
    }
    
       /**
        * Sets the IdSpringProvider
        * @param strIdSpringProvider The IdSpringProvider
        */ 
    public void setIdSpringProvider( String strIdSpringProvider )
    {
        _strIdSpringProvider = strIdSpringProvider;
    }
    
       /**
        * Returns the DemandStatus
        * @return The DemandStatus
        */ 
    public int getDemandStatus()
    {
        return _nDemandStatus;
    }
    
       /**
        * Sets the DemandStatus
        * @param nDemandStatus The DemandStatus
        */ 
    public void setDemandStatus( int nDemandStatus )
    {
        _nDemandStatus = nDemandStatus;
    }
    
       /**
        * Returns the SetOnglet
        * @return The SetOnglet
        */ 
    public int getSetOnglet()
    {
        return _nSetOnglet;
    }
    
       /**
        * Sets the SetOnglet
        * @param nSetOnglet The SetOnglet
        */ 
    public void setSetOnglet( int nSetOnglet )
    {
        _nSetOnglet = nSetOnglet;
    }
    
       /**
        * Returns the MessageGuichet
        * @return The MessageGuichet
        */ 
    public String getMessageGuichet()
    {
        return _strMessageGuichet;
    }
    
       /**
        * Sets the MessageGuichet
        * @param strMessageGuichet The MessageGuichet
        */ 
    public void setMessageGuichet( String strMessageGuichet )
    {
        _strMessageGuichet = strMessageGuichet;
    }
    
       /**
        * Returns the StatustextGuichet
        * @return The StatustextGuichet
        */ 
    public String getStatustextGuichet()
    {
        return _strStatustextGuichet;
    }
    
       /**
        * Sets the StatustextGuichet
        * @param strStatustextGuichet The StatustextGuichet
        */ 
    public void setStatustextGuichet( String strStatustextGuichet )
    {
        _strStatustextGuichet = strStatustextGuichet;
    }
    
       /**
        * Returns the SenderNameGuichet
        * @return The SenderNameGuichet
        */ 
    public String getSenderNameGuichet()
    {
        return _strSenderNameGuichet;
    }
    
       /**
        * Sets the SenderNameGuichet
        * @param strSenderNameGuichet The SenderNameGuichet
        */ 
    public void setSenderNameGuichet( String strSenderNameGuichet )
    {
        _strSenderNameGuichet = strSenderNameGuichet;
    }
    
       /**
        * Returns the SubjectGuichet
        * @return The SubjectGuichet
        */ 
    public String getSubjectGuichet()
    {
        return _strSubjectGuichet;
    }
    
       /**
        * Sets the SubjectGuichet
        * @param strSubjectGuichet The SubjectGuichet
        */ 
    public void setSubjectGuichet( String strSubjectGuichet )
    {
        _strSubjectGuichet = strSubjectGuichet;
    }
    
       /**
        * Returns the DemandMaxStepGuichet
        * @return The DemandMaxStepGuichet
        */ 
    public int getDemandMaxStepGuichet()
    {
        return _nDemandMaxStepGuichet;
    }
    
       /**
        * Sets the DemandMaxStepGuichet
        * @param nDemandMaxStepGuichet The DemandMaxStepGuichet
        */ 
    public void setDemandMaxStepGuichet( int nDemandMaxStepGuichet )
    {
        _nDemandMaxStepGuichet = nDemandMaxStepGuichet;
    }
    
       /**
        * Returns the DemandUserCurrentStepGuichet
        * @return The DemandUserCurrentStepGuichet
        */ 
    public int getDemandUserCurrentStepGuichet()
    {
        return _nDemandUserCurrentStepGuichet;
    }
    
       /**
        * Sets the DemandUserCurrentStepGuichet
        * @param nDemandUserCurrentStepGuichet The DemandUserCurrentStepGuichet
        */ 
    public void setDemandUserCurrentStepGuichet( int nDemandUserCurrentStepGuichet )
    {
        _nDemandUserCurrentStepGuichet = nDemandUserCurrentStepGuichet;
    }
    
       /**
        * Returns the ActiveOngletGuichet
        * @return The ActiveOngletGuichet
        */ 
    public boolean isActiveOngletGuichet()
    {
        return _ActiveOngletGuichet;
    }
    
       /**
        * Sets the ActiveOngletGuichet
        * @param ActiveOngletGuichet The ActiveOngletGuichet
        */ 
    public void setActiveOngletGuichet( boolean ActiveOngletGuichet )
    {
        _ActiveOngletGuichet = ActiveOngletGuichet;
    }
    
       /**
        * Returns the StatustextAgent
        * @return The StatustextAgent
        */ 
    public String getStatustextAgent()
    {
        return _strStatustextAgent;
    }
    
       /**
        * Sets the StatustextAgent
        * @param strStatustextAgent The StatustextAgent
        */ 
    public void setStatustextAgent( String strStatustextAgent )
    {
        _strStatustextAgent = strStatustextAgent;
    }
    
       /**
        * Returns the MessageAgent
        * @return The MessageAgent
        */ 
    public String getMessageAgent()
    {
        return _strMessageAgent;
    }
    
       /**
        * Sets the MessageAgent
        * @param strMessageAgent The MessageAgent
        */ 
    public void setMessageAgent( String strMessageAgent )
    {
        _strMessageAgent = strMessageAgent;
    }
    
       /**
        * Returns the ActiveOngletAgent
        * @return The ActiveOngletAgent
        */ 
    public boolean isActiveOngletAgent()
    {
        return _ActiveOngletAgent;
    }
    
       /**
        * Sets the ActiveOngletAgent
        * @param ActiveOnglet The ActiveOngletAgent
        */ 
    public void setActiveOngletAgent( boolean ActiveOngletAgent )
    {
        _ActiveOngletAgent = ActiveOngletAgent;
    }
    
       /**
        * Returns the SubjectEmail
        * @return The SubjectEmail
        */ 
    public String getSubjectEmail()
    {
        return _strSubjectEmail;
    }
    
       /**
        * Sets the SubjectEmail
        * @param strSubjectEmail The SubjectEmail
        */ 
    public void setSubjectEmail( String strSubjectEmail )
    {
        _strSubjectEmail = strSubjectEmail;
    }
    
       /**
        * Returns the MessageEmail
        * @return The MessageEmail
        */ 
    public String getMessageEmail()
    {
        return _strMessageEmail;
    }
    
       /**
        * Sets the MessageEmail
        * @param strMessageEmail The MessageEmail
        */ 
    public void setMessageEmail( String strMessageEmail )
    {
        _strMessageEmail = strMessageEmail;
    }
    
       /**
        * Returns the SenderNameEmail
        * @return The SenderNameEmail
        */ 
    public String getSenderNameEmail()
    {
        return _strSenderNameEmail;
    }
    
       /**
        * Sets the SenderNameEmail
        * @param strSenderNameEmail The SenderNameEmail
        */ 
    public void setSenderNameEmail( String strSenderNameEmail )
    {
        _strSenderNameEmail = strSenderNameEmail;
    }
    
       /**
        * Returns the RecipientsCcEmail
        * @return The RecipientsCcEmail
        */ 
    public String getRecipientsCcEmail()
    {
        return _strRecipientsCcEmail;
    }
    
       /**
        * Sets the RecipientsCcEmail
        * @param strRecipientsCcEmail The RecipientsCcEmail
        */ 
    public void setRecipientsCcEmail( String strRecipientsCcEmail )
    {
        _strRecipientsCcEmail = strRecipientsCcEmail;
    }
    
       /**
        * Returns the RecipientsCciEmail
        * @return The RecipientsCciEmail
        */ 
    public String getRecipientsCciEmail()
    {
        return _strRecipientsCciEmail;
    }
    
       /**
        * Sets the RecipientsCciEmail
        * @param strRecipientsCciEmail The RecipientsCciEmail
        */ 
    public void setRecipientsCciEmail( String strRecipientsCciEmail )
    {
        _strRecipientsCciEmail = strRecipientsCciEmail;
    }
    
       /**
        * Returns the ActiveOngletEmail
        * @return The ActiveOngletEmail
        */ 
    public boolean isActiveOngletEmail()
    {
        return _ActiveOngletEmail;
    }
    
       /**
        * Sets the ActiveOngletEmail
        * @param ActiveOngletEmail The ActiveOngletEmail
        */ 
    public void setActiveOngletEmail( boolean ActiveOngletEmail )
    {
        _ActiveOngletEmail = ActiveOngletEmail;
    }
    
       /**
        * Returns the MessageSMS
        * @return The MessageSMS
        */ 
    public String getMessageSMS()
    {
        return _strMessageSMS;
    }
    
       /**
        * Sets the MessageSMS
        * @param strMessageSMS The MessageSMS
        */ 
    public void setMessageSMS( String strMessageSMS )
    {
        _strMessageSMS = strMessageSMS;
    }
    
    
    
       /**
        * Returns the ActiveOngletSMS
        * @return The ActiveOngletSMS
        */ 
    public boolean isActiveOngletSMS()
    {
        return _ActiveOngletSMS;
    }
    
       /**
        * Sets the ActiveOngletSMS
        * @param ActiveOngletSMS The ActiveOngletSMS
        */ 
    public void setActiveOngletSMS( boolean ActiveOngletSMS )
    {
        _ActiveOngletSMS = ActiveOngletSMS;
    }
    
       /**
        * Returns the IdMailingListBroadcast
        * @return The IdMailingListBroadcast
        */ 
    public int getIdMailingListBroadcast()
    {
        return _nIdMailingListBroadcast;
    }
    
       /**
        * Sets the IdMailingListBroadcast
        * @param nIdMailingListBroadcast The IdMailingListBroadcast
        */ 
    public void setIdMailingListBroadcast( int nIdMailingListBroadcast )
    {
        _nIdMailingListBroadcast = nIdMailingListBroadcast;
    }
    
       /**
        * Returns the SenderNameBroadcast
        * @return The SenderNameBroadcast
        */ 
    public String getSenderNameBroadcast()
    {
        return _strSenderNameBroadcast;
    }
    
       /**
        * Sets the SenderNameBroadcast
        * @param strSenderNameBroadcast The SenderNameBroadcast
        */ 
    public void setSenderNameBroadcast( String strSenderNameBroadcast )
    {
        _strSenderNameBroadcast = strSenderNameBroadcast;
    }
    
       /**
        * Returns the SubjectBroadcast
        * @return The SubjectBroadcast
        */ 
    public String getSubjectBroadcast()
    {
        return _strSubjectBroadcast;
    }
    
       /**
        * Sets the SubjectBroadcast
        * @param strSubjectBroadcast The SubjectBroadcast
        */ 
    public void setSubjectBroadcast( String strSubjectBroadcast )
    {
        _strSubjectBroadcast = strSubjectBroadcast;
    }
    
       /**
        * Returns the MessageBroadcast
        * @return The MessageBroadcast
        */ 
    public String getMessageBroadcast()
    {
        return _strMessageBroadcast;
    }
    
       /**
        * Sets the MessageBroadcast
        * @param strMessageBroadcast The MessageBroadcast
        */ 
    public void setMessageBroadcast( String strMessageBroadcast )
    {
        _strMessageBroadcast = strMessageBroadcast;
    }
    
       /**
        * Returns the RecipientsCcBroadcast
        * @return The RecipientsCcBroadcast
        */ 
    public String getRecipientsCcBroadcast()
    {
        return _strRecipientsCcBroadcast;
    }
    
       /**
        * Sets the RecipientsCcBroadcast
        * @param strRecipientsCcBroadcast The RecipientsCcBroadcast
        */ 
    public void setRecipientsCcBroadcast( String strRecipientsCcBroadcast )
    {
        _strRecipientsCcBroadcast = strRecipientsCcBroadcast;
    }
    
       /**
        * Returns the RecipientsCciBroadcast
        * @return The RecipientsCciBroadcast
        */ 
    public String getRecipientsCciBroadcast()
    {
        return _strRecipientsCciBroadcast;
    }
    
       /**
        * Sets the RecipientsCciBroadcast
        * @param strRecipientsCciBroadcast The RecipientsCciBroadcast
        */ 
    public void setRecipientsCciBroadcast( String strRecipientsCciBroadcast )
    {
        _strRecipientsCciBroadcast = strRecipientsCciBroadcast;
    }
    
       /**
        * Returns the ActiveOngletBroadcast
        * @return The ActiveOngletBroadcast
        */ 
    public boolean isActiveOngletBroadcast()
    {
        return _ActiveOngletBroadcast;
    }
    
       /**
        * Sets the ActiveOngletBroadcast
        * @param ActiveOngletBroadcast The ActiveOngletBroadcast
        */ 
    public void setActiveOngletBroadcast( boolean ActiveOngletBroadcast )
    {
        _ActiveOngletBroadcast = ActiveOngletBroadcast;
    }
 }
