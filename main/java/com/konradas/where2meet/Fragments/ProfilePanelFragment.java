package com.konradas.where2meet.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.konradas.where2meet.Fragments.Presenters.ProfilePanelPresenter;
import com.konradas.where2meet.Fragments.Views.ProfilePanelView;
import com.konradas.where2meet.Fragments.Views.RegisterView;
import com.konradas.where2meet.MainActivity;
import com.konradas.where2meet.R;
import com.konradas.where2meet.tools.DataInterface;

public class ProfilePanelFragment extends DialogFragment implements ProfilePanelView {

    static DataInterface di;
    ProfilePanelPresenter presenter= new ProfilePanelPresenter();
    public static ProfilePanelFragment newInstance(DataInterface dataInterface) {
      ProfilePanelFragment f = new ProfilePanelFragment();
      Bundle args = new Bundle();
      di= dataInterface;
      return f;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.profile_fragment, container, false);
        presenter.attach(this);
        presenter.setDataInterface(di);
        presenter.getProfileInformation();
        Button logoff = view.findViewById(R.id.logoff_button);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: atsijungti ir i6jungti activity
                di.logoff();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.saveChanges();
        presenter.detach();
        di.CloseProfileDialog();
    }
}
