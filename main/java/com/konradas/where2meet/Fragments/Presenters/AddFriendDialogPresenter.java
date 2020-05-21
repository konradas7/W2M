package com.konradas.where2meet.Fragments.Presenters;

import com.konradas.where2meet.Fragments.Views.AddFriendDialogView;
import com.konradas.where2meet.obj.User;
import com.konradas.where2meet.tools.DataInterface;
import com.konradas.where2meet.tools.HTTPInterpreter;

import java.util.ArrayList;
import java.util.List;

public class AddFriendDialogPresenter {

    private DataInterface dataInterface;
    private AddFriendDialogView View;
    public AddFriendDialogPresenter() {}
    public void attach(AddFriendDialogView view) {
        this.View= view;
    }
    public void detach() {
        this.View= null;
    }

    public void addFriend(String email) {
        //TODO: add friend
        dataInterface.tryToAddFriendToList(email);
    }

    public void setDataInterface(DataInterface dataInterface) {
        this.dataInterface= dataInterface;
    }
}
