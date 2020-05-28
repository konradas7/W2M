package com.konradas.where2meet.Fragments;

import android.content.Context;
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
import com.google.android.libraries.places.api.model.OpeningHours;
import com.konradas.where2meet.Fragments.Presenters.MapPresenter;
import com.konradas.where2meet.Fragments.Views.MapFragView;
import com.konradas.where2meet.MainActivity;
import com.konradas.where2meet.R;
import com.konradas.where2meet.obj.Filter;
import com.konradas.where2meet.obj.Location;
import com.konradas.where2meet.obj.User;
import com.konradas.where2meet.tools.DataInterface;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements MapFragView {

    DataInterface di;
    MapPresenter presenter;
    MapView mMapFragView;
    List<Marker> userMarkerList= new ArrayList<>();
    List<Marker> placeMarkerList= new ArrayList<>();
    private GoogleMap googleMap;
    Marker median;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        presenter= new MapPresenter();
        mMapFragView = (MapView) rootView.findViewById(R.id.mapView);
        presenter.attach(this);
        presenter.setDataInterface(di);
        mMapFragView.onCreate(savedInstanceState);
        mMapFragView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapFragView.getMapAsync(mMap -> {
            googleMap = mMap;
            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMarkerClickListener(marker -> {
                if (marker.getTag() != null)
                {
                    di.displayPlaceInfoFromList(marker.getTag());
                }
                return false;
            });
            CameraUpdate center=
                    CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(54.710187,25.297154)).zoom(15).build());
            googleMap.moveCamera(center);
       });
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
        for (Marker m: placeMarkerList) {
            m.remove();
        }
        if (median != null) median.remove();
    }

    public void displayMedianLocation(Location medianLoc, Filter filter) {
        if (median != null) median.remove();
        median= googleMap.addMarker(new MarkerOptions().position(new LatLng(medianLoc.getX(), medianLoc.getY())).title("Vidurio taškas").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        CameraUpdate center=
                CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(medianLoc.getX(),medianLoc.getY())).zoom(13).build());
        googleMap.moveCamera(center);
        for (Marker m: placeMarkerList) {
            m.remove();
        }
        presenter.getNearbyPlaces(medianLoc, filter);

    }

    @Override
    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public Context getActivityContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void showNoPlacesFoundError() {
        Log.d("DEBUG", "No places found");
    }

    @Override
    public void setPlaceMarker(String s, String name, LatLng latLng) {
        Marker temp= googleMap.addMarker(new MarkerOptions().position(latLng).title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        temp.setTag(s);
        placeMarkerList.add(temp);

    }

    @Override
    public void noPlaceInfoError() {

    }


    public void setDataInterface(DataInterface dataInterface) {
        this.di= dataInterface;
    }

}
