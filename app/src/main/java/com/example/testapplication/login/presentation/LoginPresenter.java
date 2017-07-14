package com.example.testapplication.login.presentation;

import com.example.testapplication.entity.User;
import com.example.testapplication.login.domain.LoginInteractor;

import java.util.concurrent.TimeUnit;

import rx.Completable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chudofom on 13.07.17.
 */

public class LoginPresenter {
    LoginView loginView;
    LoginInteractor interactor;

    public LoginPresenter(LoginInteractor interactor) {
        this.interactor = interactor;
    }

    public void attachView(LoginView view) {
        this.loginView = view;
    }

    public void detachView() {
        this.loginView = null;
    }


    public void submitButtonClicked(User user) {
        interactor.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(a -> loginView.showProgress())
                .delay(2, TimeUnit.SECONDS)
                .doOnTerminate(loginView::hideProgress)
                .subscribe(new Completable.CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        loginView.showProfile();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onSubscribe(Subscription d) {

                    }
                });
    }
}
