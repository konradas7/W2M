package com.konradas.where2meet.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.konradas.where2meet.Fragments.Presenters.GroupPanelPresenter;
import com.konradas.where2meet.Fragments.Views.GroupPanelView;
import com.konradas.where2meet.Fragments.Views.RegisterView;
import com.konradas.where2meet.MainActivity;
import com.konradas.where2meet.R;
import com.konradas.where2meet.tools.DataInterface;

public class GroupPanelFragment extends DialogFragment implements GroupPanelView {

    static DataInterface di;
    GroupPanelPresenter presenter= new GroupPanelPresenter();
    public static GroupPanelFragment newInstance(DataInterface dataInterface) {
      GroupPanelFragment f = new GroupPanelFragment();
      Bundle args = new Bundle();
      di= dataInterface;
      return f;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.group_fragment, container, false);
        presenter.attach(this);;
        presenter.setDataInterface(di);
        presenter.setCurrentGroup();
        Button leaveGroupButton= view.findViewById(R.id.leavegroup_button);
        leaveGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.LeaveGroup();
            }
        });
        presenter.showPeopleInGroup();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void addUserInGroupButton(int id, String name, Bitmap picture) {
        FragmentManager fragmentManager= getChildFragmentManager();
        FriendButton friendButton= new FriendButton(id, name, picture, true, di);
        FragmentTransaction ft= fragmentManager.beginTransaction();
        ft.add(R.id.groupmember_layout, friendButton).commit();
    }
}
