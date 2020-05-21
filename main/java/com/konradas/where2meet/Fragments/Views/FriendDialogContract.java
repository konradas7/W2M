package com.konradas.where2meet.Fragments.Views;

import com.konradas.where2meet.obj.User;

import java.util.List;

public interface FriendDialogContract {

    void CloseDialogAndOpenFriendlist();
    void OpenAddFriendDialog();

    List<User> getFriendList();
}
