package com.konradas.where2meet.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.konradas.where2meet.Fragments.Presenters.FriendPanelPresenter;
import com.konradas.where2meet.Fragments.Views.FriendDialogContract;
import com.konradas.where2meet.Fragments.Views.FriendPanelView;
import com.konradas.where2meet.R;
import com.konradas.where2meet.obj.Group;
import com.konradas.where2meet.obj.User;
import com.konradas.where2meet.tools.DataInterface;

import java.util.List;

public class FriendPanelFragment extends DialogFragment implements FriendPanelView {

    FriendPanelPresenter presenter= new FriendPanelPresenter();
    static FriendDialogContract fdc;
    static DataInterface data;
    private View view;
    public static FriendPanelFragment newInstance(FriendDialogContract contract, DataInterface di) {
      FriendPanelFragment f = new FriendPanelFragment();
      fdc= contract;
      data= di;
      Bundle args = new Bundle();
      return f;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.friendlist_fragment, container, false);
        this.view= view;
        presenter.attach(this);
        presenter.setContract(fdc);
        Button addFriendBtn= view.findViewById(R.id.addtofriends_button);
        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdc.OpenAddFriendDialog();
            }
        });
        presenter.setFriendList();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void AddFriendButtonToList(int id, String name, Bitmap picture) {
        FragmentManager fragmentManager= getChildFragmentManager();
        FriendButton friendButton= new FriendButton(id, name, picture, data);
        FragmentTransaction ft= fragmentManager.beginTransaction();
        ft.add(R.id.friendlist_layout, friendButton, "profile_btn" + id).commit();
    }
}
