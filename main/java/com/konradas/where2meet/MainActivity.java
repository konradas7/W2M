package com.konradas.where2meet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.konradas.where2meet.Fragments.AddFriendDialogFragment;
import com.konradas.where2meet.Fragments.FaqPanelFragment;
import com.konradas.where2meet.Fragments.FriendPanelFragment;
import com.konradas.where2meet.Fragments.GroupPanelFragment;
import com.konradas.where2meet.Fragments.PreferencePanelFragment;
import com.konradas.where2meet.Fragments.MapFragment;
import com.konradas.where2meet.Fragments.MenuFragment;
import com.konradas.where2meet.Fragments.ProfilePanelFragment;
import com.konradas.where2meet.Fragments.Views.FriendDialogContract;
import com.konradas.where2meet.Fragments.Views.MenuContract;
import com.konradas.where2meet.obj.Filter;
import com.konradas.where2meet.obj.Group;
import com.konradas.where2meet.obj.Location;
import com.konradas.where2meet.obj.User;
import com.konradas.where2meet.tools.DataInterface;
import com.konradas.where2meet.tools.HTTPInterpreter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements MenuContract, OnMapReadyCallback, FriendDialogContract, DataInterface {

    Group group;
    List<User> friendList= new ArrayList<>();
    User me;
    Filter filter;
    HTTPInterpreter httpInterpreter= new HTTPInterpreter();
    boolean testParameters= true;
    private MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FloatingActionButton fab= findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuDialog();
            }
        });
        mapFragment = new MapFragment();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.map_frame, mapFragment).commit();
        }
        if (testParameters) setUpTestParameters();
        mapFragment.getMapAsync(this);
    }

    void showMenuDialog() {
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("MENU");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = MenuFragment.newInstance(1, this);
        newFragment.show(ft, "dialog");
    }

    void showInfoDialog(int infoDialogType) {
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("MENU");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        switch (infoDialogType) {
            case 1: {
                ProfilePanelFragment profilePanelFragment= ProfilePanelFragment.newInstance(this);
                profilePanelFragment.show(ft, "profile");
                break;
            }
            case 2: {
                FriendPanelFragment friendPanelFragment= FriendPanelFragment.newInstance(this, this);
                friendPanelFragment.show(ft, "friends");
                break;
            }
            case 3: {
                GroupPanelFragment groupPanelFragment= GroupPanelFragment.newInstance(this);
                groupPanelFragment.show(ft, "group");
                break;
            }
            case 4: {
                PreferencePanelFragment preferencePanelFragment= PreferencePanelFragment.newInstance(this);
                preferencePanelFragment.show(ft, "preferences");
                break;
            }
            case 5: {
                FaqPanelFragment faqPanelFragment= FaqPanelFragment.newInstance();
                faqPanelFragment.show(ft, "faq");
                break;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void MenuFragmentCallback(int menuFragmentCallback) {
        showInfoDialog(menuFragmentCallback);
    }

    @Override
    public void CloseDialogAndOpenFriendlist() {
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("MENU");
        if (prev != null) {
            ft.remove(prev);
        }
        prev = fragmentManager.findFragmentByTag("friends");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        FriendPanelFragment friendPanelFragment= FriendPanelFragment.newInstance(this, this);
        friendPanelFragment.show(ft, "friends");
    }

    @Override
    public void OpenAddFriendDialog() {
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("MENU");
        if (prev != null) {
            ft.remove(prev);
        }
        prev = fragmentManager.findFragmentByTag("friends");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AddFriendDialogFragment friendPanelFragment= AddFriendDialogFragment.newInstance(this);
        friendPanelFragment.show(ft, "add_friend");
    }

    @Override
    public List<User> getFriendList() {
        if (testParameters) return friendList;
        return httpInterpreter.getFriendList();
    }

    @Override
    public void CloseProfileDialog() {

    }

    @Override
    public Group getCurrentGroup() {
        if (testParameters) return group;
        return httpInterpreter.getCurrentGroup();
    }

    @Override
    public void clearCurrentGroup() {
        if (testParameters) {
            group= new Group(-1, new ArrayList<User>(), new Location(0,0));
            group.addUserToList(me);
        }
            else httpInterpreter.clearCurrentGroup();
            mapFragment.removeAllMarkers();
    }

    @Override
    public Filter getFilter() {
        if (testParameters) return filter;
        return httpInterpreter.getUserFilter(-1);
    }

    @Override
    public void storePreferences(Filter temp) {
        if (testParameters) filter= temp;
            else httpInterpreter.updatePreferences(temp);
    }

    @Override
    public void tryToAddFriendToList(String email) {

        if (testParameters) {
            //Čia baisus cheatukas yra
            if (email.equals("mantas@mantas.lt")) {
                //Sukuriam manto userį ir įdedam į friendlistą
                Filter nFilter= new Filter();
                User mantas= new User(2, "Mantas", new Location(54.673839, 25.283923),email, nFilter);
                boolean exists= false;
                for (User u: friendList) {
                    if (u.getId()== 2) exists = true;
                }
                if (!exists) friendList.add(mantas);

            }
            else if (email.equals("terese@terese.lt")) {
                //Sukuriam Teresės userį ir pridedam į listą
                Filter nFilter= new Filter();
                nFilter.setClubPref(false);
                nFilter.setRestaurantPref(false);
                User terese= new User(3, "Teresė", new Location(54.676916, 25.212345),email, nFilter);
                boolean exists= false;
                for (User u: friendList) {
                    if (u.getId()== 3) exists = true;
                }
                if (!exists) friendList.add(terese);
            }
        } else
        {
            httpInterpreter.sendAddFriendRequest(email);
        }
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment dial= fragmentManager.findFragmentByTag("add_friend");
        if (dial != null) {
            Log.d("DEBUG", "Turetu nuimti dialoga");
            ft.remove(dial).commit();
        }
        FragmentTransaction fft= fragmentManager.beginTransaction();
        FriendPanelFragment friendPanelFragment= FriendPanelFragment.newInstance(this, this);
        friendPanelFragment.show(fft, "friends");
    }

    @Override
    public void inviteToGroup(int id) {
        if (!testParameters) httpInterpreter.sendInviteToGroupRequest();
        else {
            for (User u: friendList) {
                if (u.getId() == id) {
                    group.addUserToList(u);
                }
            }
        }
        displayGroupOnMap();
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment dial= fragmentManager.findFragmentByTag("friends");
        if (dial != null) {
            Log.d("DEBUG", "Turetu nuimti dialoga");
            ft.remove(dial).commit();
        }
    }

    private void displayGroupOnMap() {
        for (User u: group.getUserList()) {
            mapFragment.displayUserMarker(u);
        }
        mapFragment.displayMedianLocation(group.getMedianLoc());
    }

    @Override
    public void kickFromFriends(int id) {
        if (!testParameters) httpInterpreter.sendKickFromFriendsRequest();
        else {
            for (User u: friendList) {
                if (u.getId() == id) friendList.remove(u);
            }
        }
    }

    @Override
    public boolean checkIfUserIsInGroup(int id) {
        for (User u: group.getUserList()) {
            if (u.getId() == id) return true;
        }
        return false;
    }

    @Override
    public void closeGroupDialog() {
        final FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment dial= fragmentManager.findFragmentByTag("group");
        if (dial != null) {
            Log.d("DEBUG", "Turetu nuimti dialoga");
            ft.remove(dial).commit();
        }
    }

    @Override
    public void logoff() {
        //TODO: log off stuff here
    }

    @Override
    public Location getCurrentGroupMedianLoc() {
        if (!testParameters) httpInterpreter.getCurrentGroupMedianLocation();
        return group.getMedianLoc();
    }

    private void setUpTestParameters() {
        httpInterpreter.setPw("test");
        group= new Group(-1, new ArrayList<User>(), new Location(0,0));
        me= new User(-1, "Testuotojas", new Location (54.711076, 25.293038));
        group.addUserToList(me);
        filter= new Filter();
    }
}
