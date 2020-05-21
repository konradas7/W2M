package com.konradas.where2meet.Fragments.Presenters;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;
import com.konradas.where2meet.Fragments.Views.MapFragView;
import com.konradas.where2meet.obj.Location;

public class MapPresenter implements GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient apiClient;
    private MapFragView View;
    public MapPresenter() {}
    public void attach(MapFragView view) {
        this.View= view;
    }
    public void detach() {
        this.View = null;
    }


    public void getNearbyPlaces(Location medianLoc) {
        LatLng referencePoint= new LatLng(medianLoc.getX(), medianLoc.getY());
        if (apiClient == null) {
            apiClient = new GoogleApiClient
                    .Builder()
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            apiClient.connect();
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
