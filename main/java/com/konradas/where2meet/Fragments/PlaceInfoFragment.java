package com.konradas.where2meet.Fragments;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.google.android.libraries.places.api.model.OpeningHours;
import com.konradas.where2meet.MainActivity;
import com.konradas.where2meet.R;
import com.konradas.where2meet.obj.Place;
import com.konradas.where2meet.tools.DataInterface;

public class PlaceInfoFragment extends DialogFragment {

    DataInterface di;

    TextView placename;
    TextView addressfield;
    Button vote_button;
    Button show_in_google_button;

    String id;
    String name;
    String address;
    OpeningHours hours;

    public PlaceInfoFragment(String id, String name, String address, OpeningHours hours) {
        this.id= id;
        this.name= name;
        this.address= address;
        this.hours= hours;
    }

    public void setDataInterface(DataInterface di) {
        this.di= di;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.placeinfopanel_fragment, container, false);
        vote_button = view.findViewById(R.id.vote_place_button);
        placename = view.findViewById(R.id.place_name);
        show_in_google_button = view.findViewById(R.id.view_place_in_google);
        addressfield = view.findViewById(R.id.address_field);
                vote_button.setOnClickListener(v -> {
                    di.voteForPlace(id);
                    dismiss();
                });
                show_in_google_button.setOnClickListener(v -> {
                    di.openBrowserWithSearch(name, address);
                });
        placename.setText(name);
        addressfield.setText(address);
        return view;
    }


}
