package com.konradas.where2meet.Fragments.Views;

import android.content.Context;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public interface MapFragView {


    void getMapAsync(OnMapReadyCallback onMapReadyCallback);


    void onDestroy();


    Context getActivityContext();

    void showNoPlacesFoundError();

    void setPlaceMarker(String s, String name, LatLng latLng);

    void noPlaceInfoError();
}
