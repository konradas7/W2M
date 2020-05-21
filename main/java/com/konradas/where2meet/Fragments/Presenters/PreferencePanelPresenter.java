package com.konradas.where2meet.Fragments.Presenters;

import com.konradas.where2meet.Fragments.Views.PreferencePanelView;
import com.konradas.where2meet.obj.Filter;
import com.konradas.where2meet.tools.DataInterface;
import com.konradas.where2meet.tools.HTTPInterpreter;

public class PreferencePanelPresenter {

    DataInterface dataInterface;
    private PreferencePanelView View;
    public PreferencePanelPresenter() {}

    public void attach(PreferencePanelView view) {
        this.View= view;
    }
    public void detach() {
        this.View = null;
    }

    public void setDataInterface(DataInterface di) {
        dataInterface= di;
    }

    public void collectPreferences() {
        Filter temp= new Filter();
        temp.setBarPref(View.collectPrefFromSwitch(1));
        temp.setRestaurantPref(View.collectPrefFromSwitch(2));
        temp.setCafePref(View.collectPrefFromSwitch(3));
        temp.setClubPref(View.collectPrefFromSwitch(4));
        dataInterface.storePreferences(temp);
    }

    public void getSwitchStatus() {
        Filter filter= dataInterface.getFilter();
        View.setSwitchFromFilter(1, filter.isBarPref());
        View.setSwitchFromFilter(2, filter.isRestaurantPref());
        View.setSwitchFromFilter(3, filter.isCafePref());
        View.setSwitchFromFilter(4, filter.isClubPref());
    }
}
