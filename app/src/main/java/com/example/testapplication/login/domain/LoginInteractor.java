package com.example.testapplication.login.domain;

import com.example.testapplication.data.UserRepository;
import com.example.testapplication.entity.User;

import rx.Completable;

/**
 * Created by Chudofom on 13.07.17.
 */

public class LoginInteractor implements ILoginInteractor {

    private UserRepository userRepository;

    public LoginInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Completable login(User user) {
        return Completable.create(completableSubscriber -> {
            User returnedUser = userRepository.getUser();
            if (returnedUser != null && returnedUser.equals(user)) {
                completableSubscriber.onCompleted();
            } else {
                completableSubscriber.onError(new NullPointerException("User is null"));
            }
        });
    }
}
