package com.konradas.where2meet.Fragments.Presenters;

import android.util.Log;

import com.konradas.where2meet.Fragments.Views.GroupPanelView;
import com.konradas.where2meet.obj.Group;
import com.konradas.where2meet.obj.User;
import com.konradas.where2meet.tools.DataInterface;

import java.util.ArrayList;

public class GroupPanelPresenter {

    private GroupPanelView View;
    private Group currentGroup= new Group(-1);
    private DataInterface data;
    public void attach(GroupPanelView view) {
        this.View= view;
    }
    public void detach() {
        this.View= null;
    }

    public void setCurrentGroup() {
        if (data != null) currentGroup= data.getCurrentGroup();
        else Log.d("DEBUG", "The activity is null, what the fuck?");
    }

    public void showPeopleInGroup() {
        for (User u : currentGroup.getUserList()) {
            View.addUserInGroupButton(u.getId(), u.getName(), u.getPicture());
        }
    }

    public void LeaveGroup() {
        data.clearCurrentGroup();
        data.closeGroupDialog();
    }

    public void setDataInterface(DataInterface di) {
        this.data= di;
    }
}
