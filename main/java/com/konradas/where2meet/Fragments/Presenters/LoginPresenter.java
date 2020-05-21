package com.konradas.where2meet.Fragments.Presenters;

import android.util.Log;
import com.konradas.where2meet.Fragments.Views.LoginView;
import com.konradas.where2meet.tools.HTTPInterpreter;

public class LoginPresenter {
    private LoginView view = null;
    private HTTPInterpreter httpInterpreter;

    public LoginPresenter() {
        httpInterpreter= new HTTPInterpreter();
    }

    public void attach(LoginView mView) {
        this.view= mView;
    }
    public void detach() {
        this.view = null;
    }

    public void tryAuth(String usr, String pw) {
        String token= "";
        //TODO: auth with server
        httpInterpreter.sendLoginRequest(usr, pw);
        int user_id= httpInterpreter.getUser_id();
        //testavimo dalykas
        user_id= 1;
        Log.println(Log.DEBUG,"LGN", "auth request received");
        if (user_id > 0) {
            view.authSuccess(token);
        } else
        {
            view.authFail();
        }
    }

    public void callRegFrag() {
        view.showRegisterFragment();
    }
}
