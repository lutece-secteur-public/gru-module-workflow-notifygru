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

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * This class represents the description of a {@link IProvider}
 * </p>
 *
 */
public class ProviderDescription
{
    private final String _strId;

    private final String _strLabel;

    private Collection<NotifyGruMarker> _collectionMarkerDescriptions;

    /**
     * Constructor
     * 
     * @param strId
     *            the id of the IProvider
     * @param strLabel
     *            the label of the IProvider
     */
    public ProviderDescription( String strId, String strLabel )
    {
        _strId = strId;
        _strLabel = strLabel;

        _collectionMarkerDescriptions = new ArrayList<>( );
    }

    /**
     * <p>
     * Gives the id of the IProvider.
     * </p>
     * <p>
     * Used to retrieve the used IProvider
     * </p>
     * 
     * @return the id
     */
    public String getId( )
    {
        return _strId;
    }

    /**
     * <p>
     * Gives the label of the IProvider.
     * </p>
     * <p>
     * Used in the task configuration page to select the IProvider
     * </p>
     * 
     * @return the label
     */
    public String getLabel( )
    {
        return _strLabel;
    }

    /**
     * Sets the marker descriptions.
     * 
     * @param collectionNotifyGruMarkers
     *            the marker descriptions to set
     */
    public void setMarkerDescriptions( Collection<NotifyGruMarker> collectionNotifyGruMarkers )
    {
        _collectionMarkerDescriptions = collectionNotifyGruMarkers;
    }

    /**
     * <p>
     * Gives the marker descriptions
     * </p>
     * <p>
     * Used in the task configuration page (in order to add markers in rich texts for example)
     * </p>
     * 
     * @return the marker descriptions
     */
    public Collection<NotifyGruMarker> getMarkerDescriptions( )
    {
        return _collectionMarkerDescriptions;
    }
}
