![](https://dev.lutece.paris.fr/jenkins/buildStatus/icon?job=gru-module-workflow-notifygru-deploy)
[![Alerte](https://dev.lutece.paris.fr/sonar/api/project_badges/measure?project=fr.paris.lutece.plugins%3Amodule-workflow-notifygru&metric=alert_status)](https://dev.lutece.paris.fr/sonar/dashboard?id=fr.paris.lutece.plugins%3Amodule-workflow-notifygru)
[![Line of code](https://dev.lutece.paris.fr/sonar/api/project_badges/measure?project=fr.paris.lutece.plugins%3Amodule-workflow-notifygru&metric=ncloc)](https://dev.lutece.paris.fr/sonar/dashboard?id=fr.paris.lutece.plugins%3Amodule-workflow-notifygru)
[![Coverage](https://dev.lutece.paris.fr/sonar/api/project_badges/measure?project=fr.paris.lutece.plugins%3Amodule-workflow-notifygru&metric=coverage)](https://dev.lutece.paris.fr/sonar/dashboard?id=fr.paris.lutece.plugins%3Amodule-workflow-notifygru)

# Module workflow NotifyGru 

## Introduction
Notify Gru The module is a workflow task that build a notification from a resource and send it. The notification can be of the type Mail, JSON...
## Configuration

NotifyGru requires the library-notifygru to send the notification.

This library provides "Notifiers" that correspond to each type of notification (mail, sms, ...)

The "notifiers" should be injected as beans in the context of the module to be used, examples :

```

      <!--  NotificationStore Rest (with ApiManager access) : --> 
    <bean id="workflow-notifygru.lib-notifygru.apiManagerTransport"
        class="fr.paris.lutece.plugins.librarynotifygru.rs.service.NotificationTransportApiManagerRest">
        <property name="notificationEndPoint">
            <value>${library-notifygru.NotificationStoreNotifierService.notificationEndPoint}</value>
        </property>
        <property name="apiManagerEndPoint">
            <value>${library-notifygru.NotificationStoreNotifierService.apiManagerEndPoint}</value>
        </property>
        <property name="apiManagerCredentials">
            <value>${library-notifygru.NotificationStoreNotifierService.apiManagerCredentials}</value>
        </property>
    </bean>
    <bean id="workflow-notifygru.lib-notifygru.notificationStoreNotifierRestService"
        class="fr.paris.lutece.plugins.librarynotifygru.services.NotificationStoreNotifierRestService">
        <constructor-arg
            ref="workflow-notifygru.lib-notifygru.apiManagerTransport" />
    </bean>
	
	
	<!-- Email : -->
  	<bean id="workflow-notifygru.lib-notifygru.emailNotifierService"
        class="fr.paris.lutece.plugins.librarynotifygru.services.EmailNotifierService">
    </bean>
    

	<!-- BroadCast Email : -->
  	<bean id="workflow-notifygru.lib-notifygru.broadcastEmailNotifierService"
        class="fr.paris.lutece.plugins.librarynotifygru.services.BroadcastEmailNotifierService">
    </bean>
    
    
    <!-- Mock : -->
  	<bean id="workflow-notifygru.lib-notifygru.MockNotifierService"
        class="fr.paris.lutece.plugins.librarynotifygru.services.MockNotifierService">
    </bean>
                
                
```

See example in the workflow-notifygru_context.xml file, or the library-notifygru repository for more details about this configuration.



Notify Gru can use extra information providers to include in the content of the notification.

Those providers are provided by the different type of workflow resources that can use this notification task ( forms, appointment, ...)

There are two types of resource provider :


 
*  **The resource providers :** 

A resource provider is the link between the NotifyGru task and a simple set of workflow resource infos.
*  **The resource providers managers:** 

A resource provider manager is the link between the           notifygru task and multiple sets of resources infos (for example, in the plugin forms each form corresponds to a provider. )




Resources are treated as "demands", which contain information such as id, type, user reference, etc... and thus all notifications corresponding to the same resource will be attached to this "demand" entity.

## Usage

 **NotifyGru configuration is done in two steps:** 
 **A first page with two fields:** 
 
* A list to choose the provider of                       resource among different implementations of the available providers
* The status checkbox field : the demand will be considered as "closed" if checked.

Then NotifyGru configuration is done on multiple tabs. To set up a tab you must activate it via the select input "Add a notification ...".(Only the available notifications types are proposed, regarding the type of injected Notifiers) For each tab you can use bookmarks resource provider to enrich dynamically the message or the subject.
 **Dashboard tab :** 
 
* Status (required) : new status of the demand
* Serder Name (required): the message sender
* Subject (required): subject of the notification (May use bookmarks)
* Message (required): Message of the notification (May use bookmarks)
* Number of steps (optional): total number of steps in demand
* Current Step (optional): current step when sending the notification


 **View Tab Agent 360°:** 
 
* Status in the 360° View (required) : status for the 360° view
* Event Description in the 360 ° View (required): message to the view 360. (May use bookmarks)


 **Tab MAIL :** 
 
* Sender (required): sender email
* Subject (required) email subject. (May use bookmarks)
* Cc (optional): in copy
* Bcc (optional): blind carbon copy.
* Message (required): message of the email. (May use bookmarks)


 **Tab SMS :** 
 
* Message (required): the SMS. (May use bookmarks)


 **Tab Mailing List :** 
 
* mailing list (required): list of recipients, separated with ";"
* Sender (required): email of the sender
* Subject (required): subject (May use bookmarks)
* Cc (optional): in copy
* Bcc (optional): blind carbon copy.
* Message (required) : the main content (May use bookmarks)




[Maven documentation and reports](https://dev.lutece.paris.fr/plugins/module-workflow-notifygru/)



 *generated by [xdoc2md](https://github.com/lutece-platform/tools-maven-xdoc2md-plugin) - do not edit directly.*