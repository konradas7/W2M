package com.konradas.where2meet.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.konradas.where2meet.Fragments.Presenters.PreferencePanelPresenter;
import com.konradas.where2meet.Fragments.Views.PreferencePanelView;
import com.konradas.where2meet.R;
import com.konradas.where2meet.tools.DataInterface;

public class PreferencePanelFragment extends DialogFragment implements PreferencePanelView {

    PreferencePanelPresenter presenter;
    static DataInterface di;
    View view;
    public PreferencePanelFragment() {
        this.presenter= new PreferencePanelPresenter();
    }

    public static PreferencePanelFragment newInstance(DataInterface dataInterface) {
        PreferencePanelFragment f = new PreferencePanelFragment();
        di= dataInterface;
        Bundle args = new Bundle();
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.preference_fragment, container, false);
        this.view= view;
        presenter.attach(this);
        presenter.setDataInterface(di);
        presenter.getSwitchStatus();
        Log.d("BUG1", "Preference fragment created");
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.collectPreferences();
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public boolean collectPrefFromSwitch(int switchNumber) {
        boolean temp= false;
        switch (switchNumber) {
            case 1: {
                Switch sw= view.findViewById(R.id.bar_switch);
                temp= sw.isChecked();
                break;
            }
            case 2: {
                Switch sw= view.findViewById(R.id.restaurant_switch);
                temp= sw.isChecked();
                break;
            }
            case 3: {
                Switch sw= view.findViewById(R.id.cafe_switch);
                temp= sw.isChecked();
                break;
            }
            case 4: {
                Switch sw= view.findViewById(R.id.club_switch);
                temp= sw.isChecked();
                break;
            }
        }
        return temp;
    }

    @Override
    public void setSwitchFromFilter(int switchNumber, boolean pref) {
        switch (switchNumber) {
            case 1: {
                Switch sw= view.findViewById(R.id.bar_switch);
                sw.setChecked(pref);
                break;
            }
            case 2: {
                Switch sw= view.findViewById(R.id.restaurant_switch);
                sw.setChecked(pref);
                break;
            }
            case 3: {
                Switch sw= view.findViewById(R.id.cafe_switch);
                sw.setChecked(pref);
                break;
            }
            case 4: {
                Switch sw= view.findViewById(R.id.club_switch);
                sw.setChecked(pref);
                break;
            }
        }

    }

}
