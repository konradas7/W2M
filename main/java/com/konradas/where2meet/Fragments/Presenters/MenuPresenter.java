package com.konradas.where2meet.Fragments.Presenters;

import com.konradas.where2meet.Fragments.Views.MenuView;

public class MenuPresenter{

    private MenuView view = null;
    public MenuPresenter() {

    }

    public void attach(MenuView mView) {
        this.view= mView;
    }

    public void detach() {
        this.view = null;
    }
}

