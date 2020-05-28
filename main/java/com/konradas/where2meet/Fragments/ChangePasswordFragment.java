package com.konradas.where2meet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.konradas.where2meet.R;

public class ChangePasswordFragment extends DialogFragment {

    String oldpw;
    String newpw;

    EditText newpw_field;
    EditText oldpw_field;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.passwordchange_fragment, container, false);
        Button confirm = view.findViewById(R.id.confirm_change_password);
        oldpw_field= view.findViewById(R.id.old_pw);
        newpw_field= view.findViewById(R.id.new_pw);
        confirm.setOnClickListener(v ->
        {
            oldpw= oldpw_field.getText().toString();
            newpw= newpw_field.getText().toString();
            dismiss();
        });
        return view;
    }
}
