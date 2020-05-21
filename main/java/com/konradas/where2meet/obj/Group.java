package com.konradas.where2meet.obj;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private List<User> userList= new ArrayList<>();
    private Location medianLoc;

    public Group(int id, List<User> userList, Location medianLoc) {
        this.id= id;
        this.userList= userList;
        this.medianLoc= medianLoc;
    }

    public Group(int id) {
        this.id = id;
    }

     public void addUserToList(User user) {
        userList.add(user);
        if (userList.size() > 1) updateMedianLoc();
    }

    public void removeUser(User user) {
        userList.remove(user);
        updateMedianLoc();
    }

    void updateMedianLoc() {
        double X= 0;
        double Y= 0;
        for (User u: userList) {
            Location temp= u.getLocation();
            X= X+ temp.getX();
            Y= Y+ temp.getY();
        }
        X= X / userList.size();
        Y= Y / userList.size();
        medianLoc.setPos(X, Y);
    }

    public Location getMedianLoc() {
        return medianLoc;
    }

    void setMedianLoc(Location location) {
        this.medianLoc= location;
    }

    public List<User> getUserList() {return this.userList;}

}
