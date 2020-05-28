package com.konradas.where2meet.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.konradas.where2meet.Fragments.Presenters.RegisterPresenter;
import com.konradas.where2meet.Fragments.Views.RegisterView;
import com.konradas.where2meet.MainActivity;
import com.konradas.where2meet.R;

public class RegisterFragment extends Fragment implements RegisterView {

  //  RegisterPresenter presenter;
    Button register;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.register_fragment, container, false);
        register= view.findViewById(R.id.register);
        register.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            if (getActivity() != null) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Log.d("BUG1", "Destroy fragment called");
                    fragmentTransaction.remove(this).commit();
                }
                getActivity().finish();
            }
        });
     //   presenter.attach(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
     //   presenter.detach();
    }
}
