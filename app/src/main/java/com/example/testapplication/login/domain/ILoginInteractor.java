package com.example.testapplication.login.domain;

import com.example.testapplication.entity.User;

import rx.Completable;

/**
 * Created by Chudofom on 13.07.17.
 */

public interface ILoginInteractor {
    Completable login(User user);
}
