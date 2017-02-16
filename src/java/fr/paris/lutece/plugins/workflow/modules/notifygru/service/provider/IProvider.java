/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider;

import java.util.Collection;

/**
 * <p>This interface represents a provider for a specific type of resource.
 * A provider consists of providing certain data from the resource. These actual data of the resource are needed when the {@link fr.paris.lutece.plugins.grubusiness.business.notification.Notification} is send.</p>
 * <p>A Notification is linked to a {@link fr.paris.lutece.plugins.grubusiness.business.demand.Demand}. A Demand can have several notifications.</p>
 *
 */
public interface IProvider
{
    /**
     * <p>Provides the id of the demand containing the notification to send.</p>
     * <p>This id usually corresponds the id of the resource.</p>
     * <p>This id is part of the primary key of the demand. Mandatory.</p>
     * @return the demand id
     */
    String provideDemandId( );
    
    /**
     * <p>Provides the type id of the demand containing the notification to send.</p>
     * <p>This id is unique for each teleservice. It is defined by the GRU administrator.</p>
     * <p>This id is part of the primary key of the demand. Mandatory.</p>
     * @return the demand type id
     */
    String provideDemandTypeId( );
    
    /**
     * <p>Provides the demand reference.</p>
     * <p>The demand reference is the reference sent to the customer in order to follow the treatment of the demand. Mandatory.</p>
     * @return the demand reference
     */
    String provideDemandReference( );
    
    /**
     * <p>Provides the connection id of the customer.</p>
     * <p>This id usually corresponds to the GUID provided by OpenAM.</p>
     * <p>To show the notification in MyDashboard space of the customer, either the connection id or the customer id must be provided.</p>
     * @return the customer connection id
     */
    String provideCustomerConnectionId( );
    
    /**
     * <p>Provides the id of the customer.</p>
     * <p>This id corresponds to the internal id of the GRU. DO NOT provide the customer connection id in this method.</p>
     * <p>To show the notification in MyDashboard space of the customer, either the connection id or the customer id must be provided.
     * If provided, this id prevails over the customer connection id.</p>
     * @return the connection id
     */
    String provideCustomerId( );
    
    /**
     * <p>Provides the customer email.</p>
     * <p>This email address is used to send a notification by email. DO NOT provide an email which does not correspond to the customer email. Mandatory</p>
     * @return the customer email
     */
    String provideCustomerEmail( );
    
    /**
     * <p>Provides the mobile phone number of the customer.</p>
     * <p>This phone number is used to send a notification by SMS. DO NOT provide a phone number which does not correspond to the customer phone number.</p>
     * <p>Only used if a SMS notification has been configured in the Notify GRU task.
     * @return the mobile phone number of the customer
     */
    String provideCustomerMobilePhone( );
    
    /**
     * <p>Provides the values of the markers.</p>
     * <p>This method is used to replace the markers by the actual values of the resource when the notification id send.</p>
     * @return the value of the markers
     */
    Collection<NotifyGruMarker> provideMarkerValues( );
}
