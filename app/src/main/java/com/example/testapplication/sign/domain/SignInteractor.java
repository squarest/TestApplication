package com.example.testapplication.sign.domain;

import com.example.testapplication.data.UserRepository;
import com.example.testapplication.entity.User;

import rx.Completable;

/**
 * Created by Chudofom on 14.07.17.
 */

public class SignInteractor implements ISignInteractor {
    private UserRepository repository;

    public SignInteractor(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable sign(User user) {
        return Completable.create(completableSubscriber -> {
            repository.setUser(user);
            completableSubscriber.onCompleted();
        });
    }
}
