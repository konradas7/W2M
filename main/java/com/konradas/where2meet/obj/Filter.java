package com.konradas.where2meet.obj;

public class Filter {

    private boolean club= true;
    private boolean bar= true;
    private boolean cafe= true;
    private boolean restaurant= true;

    public void setClubPref(boolean pref) {
        this.club= pref;
    }
    public void setBarPref(boolean pref) {
        this.bar= pref;
    }
    public void setCafePref(boolean pref) {
        this.cafe= pref;
    }
    public void setRestaurantPref(boolean pref) {
        this.restaurant= pref;
    }

    public boolean isRestaurantPref() {
        return restaurant;
    }
    public boolean isCafePref() {
        return cafe;
    }
    public boolean isBarPref() {
        return bar;
    }
    public boolean isClubPref() {
        return club;
    }
}
