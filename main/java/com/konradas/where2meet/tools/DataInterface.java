package com.konradas.where2meet.tools;

import com.konradas.where2meet.obj.Filter;
import com.konradas.where2meet.obj.Group;
import com.konradas.where2meet.obj.Location;

public interface DataInterface {
    void CloseProfileDialog();
    Group getCurrentGroup();
    void clearCurrentGroup();
    Filter getFilter();
    void storePreferences(Filter temp);
    void tryToAddFriendToList(String email);
    void inviteToGroup(int id);
    void kickFromFriends(int id);
    boolean checkIfUserIsInGroup(int id);
    void closeGroupDialog();
    void logoff();
    Location getCurrentGroupMedianLoc();
}
