package com.konradas.where2meet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.konradas.where2meet.Fragments.Views.FaqPanelView;
import com.konradas.where2meet.Fragments.Views.RegisterView;
import com.konradas.where2meet.R;

public class FaqPanelFragment extends DialogFragment implements FaqPanelView {

    public static FaqPanelFragment newInstance() {
      FaqPanelFragment f = new FaqPanelFragment();
      Bundle args = new Bundle();
      return f;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.faq_fragment, container, false);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
