package com.konradas.where2meet.Fragments.Presenters;

import com.konradas.where2meet.Fragments.Views.RegisterView;

    public class RegisterPresenter{

        private RegisterView view = null;
        public RegisterPresenter() {

        }

        public void attach(RegisterView mView) {
            this.view= mView;
        }
        public void detach() {
            this.view = null;
        }
    }

