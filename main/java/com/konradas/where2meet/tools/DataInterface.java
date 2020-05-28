package com.konradas.where2meet.tools;

import com.google.android.libraries.places.api.model.OpeningHours;
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

    void addPlaceToList(String s, String name, String address, OpeningHours openingHours);

    void displayPlaceInfoFromList(Object tag);

    void voteForPlace(String id);

    void openBrowserWithSearch(String name, String address);

    void openChangePasswordDialog();
}
