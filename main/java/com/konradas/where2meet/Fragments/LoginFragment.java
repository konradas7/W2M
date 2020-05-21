package com.konradas.where2meet.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.konradas.where2meet.Fragments.Presenters.LoginPresenter;
import com.konradas.where2meet.Fragments.Views.LoginView;
import com.konradas.where2meet.MainActivity;
import com.konradas.where2meet.R;

public class LoginFragment extends Fragment implements LoginView {

    private LoginPresenter presenter = new LoginPresenter();
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.login_fragment, container, false);
        presenter.attach(this);
        Button loginBtn = view.findViewById(R.id.login_btn);
        Button regBtn = view.findViewById(R.id.register_btn);
        final EditText usrF = view.findViewById(R.id.email_field);
        final EditText pwF = view.findViewById(R.id.pw_field);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUG1", "Login button clicked");
                String pw = pwF.getText().toString();
                String usr= usrF.getText().toString();
                presenter.tryAuth(usr, pw);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callRegFrag();
            }
        });

        return view;
    }

    @Override
    public void authSuccess(String token) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            FragmentManager fragmentManager= getFragmentManager();
            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Log.d("BUG1", "Destroy fragment called");
                fragmentTransaction.remove(this).commit();
            }
            getActivity().finish();
        }
    }

    @Override
    public void showRegisterFragment() {

    }

    @Override
    public void authFail() {
        //display toast about bad credentials
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }




}
