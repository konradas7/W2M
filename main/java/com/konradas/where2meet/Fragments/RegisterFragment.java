package com.konradas.where2meet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.konradas.where2meet.Fragments.Presenters.RegisterPresenter;
import com.konradas.where2meet.Fragments.Views.RegisterView;
import com.konradas.where2meet.R;

public class RegisterFragment extends Fragment implements RegisterView {

  //  RegisterPresenter presenter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.login_fragment, container, false);
     //   presenter.attach(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
     //   presenter.detach();
    }
}
