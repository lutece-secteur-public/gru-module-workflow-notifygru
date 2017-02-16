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

import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;

/**
 * This class represents a manager of providers
 *
 */
public abstract class AbstractProviderManager
{
    private String _strId;
    
    /**
     * Constructor
     * @param strId the id of the manager
     */
    public AbstractProviderManager( String strId )
    {
        _strId = strId;
    }
    
    /**
     * Gives the provider description for all the providers managed by this manager.
     * Must not be {@code null} since the manager must manage at least one provider.
     * @param task the workflow task calling this method
     * @return all the provider descriptions
     */
    public abstract Collection<ProviderDescription> getAllProviderDescriptions( ITask task );
    
    /**
     * Gives the description of the specified provider
     * @param strProviderId the provider id
     * @return the provider description
     */
    public abstract ProviderDescription getProviderDescription( String strProviderId );
    
    /**
     * Creates the specified provider for the specified resource.
     * @param strProviderId the provider id
     * @param resourceHistory the resource id
     * @return the provider
     */
    public abstract IProvider createProvider( String strProviderId, ResourceHistory resourceHistory );
    
    /**
     * Gives the id of the manager
     * @return the id
     */
    public String getId( )
    {
        return _strId;
    }
}
