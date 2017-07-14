package com.example.testapplication.sign.presentation;

import com.example.testapplication.entity.User;
import com.example.testapplication.sign.domain.ISignInteractor;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chudofom on 13.07.17.
 */

public class SignInPresenter {
    private ISignInteractor interactor;
    private SignInView view;

    public SignInPresenter(ISignInteractor interactor) {
        this.interactor = interactor;
    }

    public void attachView(SignInView view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void submitButtonClicked(User user) {
        interactor.sign(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> view.showProgress())
                .delay(2, TimeUnit.SECONDS)
                .doOnTerminate(view::hideProgress)
                .subscribe(view::showLogin);
    }
}
