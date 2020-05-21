package com.konradas.where2meet.Fragments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.konradas.where2meet.Fragments.Presenters.AddFriendDialogPresenter;
import com.konradas.where2meet.Fragments.Views.AddFriendDialogView;
import com.konradas.where2meet.Fragments.Views.ProfilePanelView;
import com.konradas.where2meet.Fragments.Views.RegisterView;
import com.konradas.where2meet.R;
import com.konradas.where2meet.tools.DataInterface;

public class AddFriendDialogFragment extends DialogFragment implements AddFriendDialogView {

    static DataInterface dataInterface;
    AddFriendDialogPresenter presenter= new AddFriendDialogPresenter();
    public static AddFriendDialogFragment newInstance(DataInterface di) {
      AddFriendDialogFragment f = new AddFriendDialogFragment();
      dataInterface = di;
      Bundle args = new Bundle();
      return f;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.addfriend_dialog, container, false);
        presenter.attach(this);
        presenter.setDataInterface(dataInterface);
        Button button= view.findViewById(R.id.add_friend_email_button);
        final EditText et= view.findViewById(R.id.addfriend_email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addFriend(et.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

}
