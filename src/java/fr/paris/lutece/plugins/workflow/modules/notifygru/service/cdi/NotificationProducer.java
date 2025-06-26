/*
 * Copyright (c) 2002-2025, City of Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service.cdi;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.paris.lutece.plugins.librarynotifygru.rs.service.NotificationTransportApiManagerRest;
import fr.paris.lutece.plugins.librarynotifygru.rs.service.NotificationTransportRest;
import fr.paris.lutece.plugins.librarynotifygru.services.INotificationTransportProvider;
import fr.paris.lutece.plugins.librarynotifygru.services.NotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.literal.NamedLiteral;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Named;

@ApplicationScoped
public class NotificationProducer 
{
	@Produces
    @ApplicationScoped
    @Named( "workflow-notifygru.lib-notifygru.apiManagerTransport" )
    public INotificationTransportProvider produceNotificationTransportApiManagerRest( 
    		@ConfigProperty( name = "workflow-notifygru.lib-notifygru.apiManagerTransport.notificationEndPoint" ) String notificationEndPoint,
            @ConfigProperty( name = "workflow-notifygru.lib-notifygru.apiManagerTransport.apiManagerEndPoint" ) String apiManagerEndPoint,
            @ConfigProperty( name = "workflow-notifygru.lib-notifygru.apiManagerTransport.apiManagerCredentials" ) String apiManagerCredentials )
    {
        NotificationTransportApiManagerRest notifApiManagerRest = new NotificationTransportApiManagerRest( );
        notifApiManagerRest.setNotificationEndPoint( notificationEndPoint );
        notifApiManagerRest.setApiManagerEndPoint( apiManagerEndPoint );
        notifApiManagerRest.setApiManagerCredentials( apiManagerCredentials );

        return notifApiManagerRest;
    }

	@Produces
    @ApplicationScoped
    @Named( "workflow-notifygru.lib-notifygru.restTransport" )
    public INotificationTransportProvider produceNotificationTransportRest( 
    		@ConfigProperty( name = "workflow-notifygru.lib-notifygru.restTransport.notificationEndPoint" ) String notificationEndPoint )
    {
		NotificationTransportRest notifRest = new NotificationTransportRest( );
		notifRest.setNotificationEndPoint( notificationEndPoint );

        return notifRest;
    }

	@Produces
    @ApplicationScoped
    @Named( "workflow-notifygru.lib-notifygru.notificationService" )
    public NotificationService produceNotificationService( 
    		@ConfigProperty( name = "workflow-notifygru.lib-notifygru.notificationService.transportProviderBeanName" ) String transportProviderBeanName )
    {
		NotificationService notifService = new NotificationService( );
		notifService.setTransportProvider( CDI.current().select( INotificationTransportProvider.class, NamedLiteral.of( transportProviderBeanName ) ).get( ) );

        return notifService;
    }
}
