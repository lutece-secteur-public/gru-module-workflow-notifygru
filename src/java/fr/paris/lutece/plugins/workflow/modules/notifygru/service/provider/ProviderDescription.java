package fr.paris.lutece.plugins.workflow.modules.notifygru.service.provider;

import java.util.ArrayList;
import java.util.Collection;

public class ProviderDescription
{
    private String _strId;

    private String _strLabel;

    Collection<NotifyGruMarker> _collectionMarkerDescriptions;

    public ProviderDescription( String strId, String strLabel )
    {
        _strId = strId;
        _strLabel = strLabel;

        _collectionMarkerDescriptions = new ArrayList<>( );
    }

    public String getId( )
    {
        return _strId;
    }

    public String getLabel( )
    {
        return _strLabel;
    }

    public void setMarkerDescriptions( Collection<NotifyGruMarker> collectionNotifyGruMarkers )
    {
        _collectionMarkerDescriptions = collectionNotifyGruMarkers;
    }

    public Collection<NotifyGruMarker> getMarkerDescriptions( )
    {
        return _collectionMarkerDescriptions;
    }
}
