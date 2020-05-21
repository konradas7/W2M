package com.konradas.where2meet.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.konradas.where2meet.Fragments.Presenters.MapPresenter;
import com.konradas.where2meet.Fragments.Views.MapFragView;
import com.konradas.where2meet.R;
import com.konradas.where2meet.obj.Location;
import com.konradas.where2meet.obj.User;
import com.konradas.where2meet.tools.DataInterface;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements MapFragView {

    MapPresenter presenter;
    MapView mMapFragView;
    List<Marker> userMarkerList= new ArrayList<>();
    private GoogleMap googleMap;
    Marker median;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        mMapFragView = (MapView) rootView.findViewById(R.id.mapView);
        presenter.attach(this);
        mMapFragView.onCreate(savedInstanceState);
        mMapFragView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapFragView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMyLocationEnabled(true);
                CameraUpdate center=
                        CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(54.710187,25.297154)).zoom(15).build());
                googleMap.moveCamera(center);
           }
        });
        Log.d("MAP", "onMapReady: Map ready");
        return rootView;
    }



    public void displayUserMarker(User u) {
        Marker temp;
        temp =googleMap.addMarker(new MarkerOptions().position(new LatLng(u.getLocation().getX(), u.getLocation().getY())).title(u.getName()));
        userMarkerList.add(temp);
    }

    public void removeAllMarkers() {
        for (Marker m: userMarkerList) {
            m.remove();
        }
        if (median != null) median.remove();
    }

    public void displayMedianLocation(Location medianLoc) {
        if (median != null) median.remove();
        median= googleMap.addMarker(new MarkerOptions().position(new LatLng(medianLoc.getX(), medianLoc.getY())).title("Vidurio taškas").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        CameraUpdate center=
                CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(medianLoc.getX(),medianLoc.getY())).zoom(13).build());
        googleMap.moveCamera(center);
        presenter.getNearbyPlaces(medianLoc);

    }

    @Override
    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
