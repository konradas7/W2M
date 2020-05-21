package com.konradas.where2meet.Fragments.Presenters;

import com.konradas.where2meet.Fragments.Views.FriendDialogContract;
import com.konradas.where2meet.Fragments.Views.FriendPanelView;
import com.konradas.where2meet.obj.User;

import java.util.ArrayList;
import java.util.List;

public class FriendPanelPresenter {

    private FriendDialogContract contract;
    private FriendPanelView View;
    List<User> friends= new ArrayList<>();

    public FriendPanelPresenter() {}

    public void attach(FriendPanelView view) {
        this.View = view;

    }

    public void setFriendList() {
        friends= contract.getFriendList();
        for (User f: friends) {
            View.AddFriendButtonToList(f.getId(), f.getName(), f.getPicture());
        }
    }

    public void detach() {
        this.View = null;
    }

    public void setContract(FriendDialogContract fd) {
        contract= fd;
    }


}
