package com.example.testapplication.profile.domain;

import com.example.testapplication.entity.User;

import rx.Observable;

/**
 * Created by Chudofom on 14.07.17.
 */

public interface IProfileInteractor {
    Observable<User> loadProfile();
}
