package com.example.testapplication.profile.domain;

import com.example.testapplication.data.UserRepository;
import com.example.testapplication.entity.User;

import rx.Observable;

/**
 * Created by Chudofom on 14.07.17.
 */

public class ProfileInteractor implements IProfileInteractor {
    private UserRepository repository;

    public ProfileInteractor(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<User> loadProfile() {
        return Observable.create(subscriber -> {
            User user = repository.getUser();
            if (repository.getUser() != null) {
                subscriber.onNext(user);
            } else subscriber.onError(new NullPointerException("User is null"));
        });
    }
}
