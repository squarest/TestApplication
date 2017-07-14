package com.example.testapplication.sign.domain;

import com.example.testapplication.entity.User;

import rx.Completable;

/**
 * Created by Chudofom on 14.07.17.
 */

public interface ISignInteractor {
    Completable sign(User user);
}
