package com.konradas.where2meet.Fragments.Views;

import android.os.Bundle;

import com.google.android.gms.maps.OnMapReadyCallback;

public interface MapFragView {
    void onCreate(Bundle savedInstanceState);

    void onResume();

    void getMapAsync(OnMapReadyCallback onMapReadyCallback);

    void onPause();

    void onDestroy();

    void onLowMemory();
}
