package com.konradas.where2meet.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.konradas.where2meet.R;

public class SelectedPlaceFragment extends DialogFragment {

    String name;
    String address;
    Button navigate;
    TextView nametext;
    public SelectedPlaceFragment(String name, String address) {
        this.name= name;
        this.address= address;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.vote_result_fragment, container, false);
        navigate = view.findViewById(R.id.navigate_button);
        nametext = view.findViewById(R.id.vote_selected_place_name);
        nametext.setText(name);
        navigate.setOnClickListener(v -> {
            address= address.replace(' ', '+');
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+address);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
        return view;
    }

}
