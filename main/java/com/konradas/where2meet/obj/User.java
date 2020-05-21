package com.konradas.where2meet.obj;


import android.graphics.Bitmap;

public class User {

    private int id;
    private String name;
    private Bitmap picture;
    private Location location;
    private String email;
    private Filter filter;

    public User(int id, String name, Location location) {
        this.id = id;
        this.name= name;
        this.location= location;
    }

    public User(int id, String name, Location location, String email) {
        this.id = id;
        this.name= name;
        this.location= location;
        this.email= email;
    }

    public User(int id, String name, Location location, Filter filter) {
        this.id = id;
        this.name= name;
        this.location= location;
        this.filter= filter;
    }

    public User(int id, String name, Bitmap picture) {
        this.id = id;
        this.name= name;
        this.picture= picture;
    }

    public User(int id, String name, Location location, String email, Filter filter) {
        this.id = id;
        this.name= name;
        this.email= email;
        this.location= location;
        this.filter= filter;
    }

    public void setLocation(Location location) {
        this.location= location;
    }

    public Location getLocation() {
        return location;
    }

    void setFilter(Filter filter) {
        this.filter= filter;
    }

    Filter getFilter() {
        return filter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }
}
