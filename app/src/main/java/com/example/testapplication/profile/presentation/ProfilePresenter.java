package com.example.testapplication.profile.presentation;

import android.util.Log;

import com.example.testapplication.profile.domain.IProfileInteractor;
import com.example.testapplication.profile.domain.ProfileInteractor;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chudofom on 13.07.17.
 */

public class ProfilePresenter {
    private ProfileView profileView;
    private IProfileInteractor profileInteractor;

    public ProfilePresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    public void attachView(ProfileView view) {
        this.profileView = view;
        profileInteractor.loadProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user ->
                {
                    profileView.showUser(user);
                }, throwable -> Log.d("Error", throwable.getMessage()));
    }

    public void detachView() {
        this.profileView = null;
    }

}
