package com.konradas.where2meet.Fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.konradas.where2meet.Fragments.Presenters.MenuPresenter;
import com.konradas.where2meet.Fragments.Views.MenuContract;
import com.konradas.where2meet.Fragments.Views.MenuView;
import com.konradas.where2meet.R;

public class MenuFragment extends DialogFragment implements MenuView {

    private static MenuContract contract;
    private int mNum;
    public static MenuFragment newInstance(int num, MenuContract cntrct) {
        MenuFragment f = new MenuFragment();
        contract= cntrct;
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    // MenuPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Fragment fragment=this;
        final View view = inflater.inflate(R.layout.menu_fragment, container, false);
        Button profile_button= view.findViewById(R.id.profile_btn);
        Button friend_button= view.findViewById(R.id.friend_btn);
        Button group_button= view.findViewById(R.id.group_btn);
        Button preference_button= view.findViewById(R.id.pref_btn);
        Button faq_button= view.findViewById(R.id.help_btn);
        profile_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                contract.MenuFragmentCallback(1);
                fragmentTransaction.remove(fragment).commit();
            }
        });
        friend_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                contract.MenuFragmentCallback(2);
                fragmentTransaction.remove(fragment).commit();
            }
        });
        group_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                contract.MenuFragmentCallback(3);
                fragmentTransaction.remove(fragment).commit();
            }
        });
        preference_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                contract.MenuFragmentCallback(4);
                fragmentTransaction.remove(fragment).commit();
            }
        });
        faq_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                contract.MenuFragmentCallback(5);
                fragmentTransaction.remove(fragment).commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //presenter.detach();
    }

}