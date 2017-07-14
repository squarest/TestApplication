package com.example.testapplication.login.presentation;

/**
 * Created by Chudofom on 13.07.17.
 */

public interface LoginView {
    void showProfile();

    void showMessage(String message);

    void showProgress();

    void hideProgress();
}
