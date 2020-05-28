package com.konradas.where2meet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.konradas.where2meet.Fragments.LoginFragment;
import com.konradas.where2meet.Fragments.RegisterFragment;
import com.konradas.where2meet.tools.LoginDataInterface;

public class LoginActivity extends FragmentActivity implements LoginDataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
        FragmentManager fragmentManager= getSupportFragmentManager();

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LoginFragment loginFragment = new LoginFragment();
            fragmentTransaction.add(R.id.fragment_holder, loginFragment).commit();
            loginFragment.setDataInterface(this);
        }

    }

    void inflateRegFragment() {
         FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RegisterFragment registerFragment = new RegisterFragment();
            fragmentTransaction.add(R.id.fragment_holder, registerFragment).commit();

    }

    @Override
    public void callRegFrag() {

        inflateRegFragment();
    }
}
