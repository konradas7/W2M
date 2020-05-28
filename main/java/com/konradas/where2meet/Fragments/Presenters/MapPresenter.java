package com.konradas.where2meet.Fragments.Presenters;

import android.util.Log;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.konradas.where2meet.Fragments.Views.MapFragView;
import com.konradas.where2meet.obj.Filter;
import com.konradas.where2meet.obj.Location;
import com.konradas.where2meet.tools.DataInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapPresenter {

    private MapFragView View;
    DataInterface di;
    public MapPresenter() {}
    public void attach(MapFragView view) {
        this.View= view;
    }
    public void detach() {
        this.View = null;
    }
    List<String> placeIdArray= new ArrayList<>();
    AutocompleteSessionToken token= AutocompleteSessionToken.newInstance();


    public void getNearbyPlaces(Location medianLoc, Filter filter) {
        LatLng referencePoint= new LatLng(medianLoc.getX(), medianLoc.getY());
        RectangularBounds bounds= RectangularBounds.newInstance(new LatLngBounds(new LatLng(referencePoint.latitude - 0.009, referencePoint.longitude - 0.009), new LatLng(referencePoint.latitude + 0.009, referencePoint.longitude+ 0.009)));
        //TODO: išimti api key iš čia ir įdėti į env variables
        Places.initialize(View.getActivityContext(), "AIzaSyBKLZ-oBn0b1vW_m9qdxL0AnFhWOe_T1p0");
        PlacesClient placesClient= Places.createClient(View.getActivityContext());

        if (filter.isBarPref()) {
            FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                    .setLocationRestriction(bounds)
                    .setOrigin(referencePoint)
                    .setSessionToken(token)
                    .setQuery("bar")
                    .setTypeFilter(TypeFilter.ESTABLISHMENT)
                    .build();

            placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                    getPlaceName(prediction.getPlaceId());
                }
            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {
                    View.showNoPlacesFoundError();
                }
            });
        }

        if (filter.isClubPref()) {
            FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                    .setLocationRestriction(bounds)
                    .setOrigin(referencePoint)
                    .setSessionToken(token)
                    .setQuery("club")
                    .setTypeFilter(TypeFilter.ESTABLISHMENT)
                    .build();

            placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                    getPlaceName(prediction.getPlaceId());
                }
            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {
                    View.showNoPlacesFoundError();
                }
            });
        }

        if (filter.isRestaurantPref()) {
            FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                    .setLocationRestriction(bounds)
                    .setOrigin(referencePoint)
                    .setSessionToken(token)
                    .setQuery("restaurant")
                    .setTypeFilter(TypeFilter.ESTABLISHMENT)
                    .build();

            placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                    getPlaceName(prediction.getPlaceId());
                }
            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {
                    View.showNoPlacesFoundError();
                }
            });
        }
        if (filter.isCafePref()) {
            FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                    .setLocationRestriction(bounds)
                    .setOrigin(referencePoint)
                    .setSessionToken(token)
                    .setQuery("cafe")
                    .setTypeFilter(TypeFilter.ESTABLISHMENT)
                    .build();

            placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {

                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                    getPlaceName(prediction.getPlaceId());
                }
            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {
                    View.showNoPlacesFoundError();
                }
            });
        }
        Log.d("DEBUG", "Found places size: " + placeIdArray.size());
    }

    public void setDataInterface (DataInterface di) {
        this.di= di;
    }

    private void getPlaceName(String s) {
        Places.initialize(View.getActivityContext(), "AIzaSyBKLZ-oBn0b1vW_m9qdxL0AnFhWOe_T1p0");
        PlacesClient placesClient= Places.createClient(View.getActivityContext());
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.OPENING_HOURS);
        FetchPlaceRequest request = FetchPlaceRequest.newInstance(s, placeFields);
        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            di.addPlaceToList(s, place.getName(), place.getAddress(), place.getOpeningHours());
            View.setPlaceMarker(s, place.getName(), place.getLatLng());
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                View.noPlaceInfoError();
            }
        });
    }
}
