package com.example.testapplication.profile.presentation;

import com.example.testapplication.entity.User;

/**
 * Created by Chudofom on 13.07.17.
 */

public interface ProfileView {
    void showUser(User user);
    void showProgress();
    void hideProgress();
}
