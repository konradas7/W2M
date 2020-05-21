package com.konradas.where2meet.Fragments.Views;

public interface LoginView {
    void authSuccess(String token);

    void showRegisterFragment();

    void authFail();
}
