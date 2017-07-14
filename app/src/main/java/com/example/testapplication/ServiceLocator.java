package com.example.testapplication;


import android.content.Context;
import android.preference.PreferenceManager;

import com.example.testapplication.data.UserRepository;
import com.example.testapplication.login.domain.ILoginInteractor;
import com.example.testapplication.login.domain.LoginInteractor;
import com.example.testapplication.login.presentation.LoginPresenter;
import com.example.testapplication.profile.domain.IProfileInteractor;
import com.example.testapplication.profile.domain.ProfileInteractor;
import com.example.testapplication.profile.presentation.ProfilePresenter;
import com.example.testapplication.sign.domain.ISignInteractor;
import com.example.testapplication.sign.domain.SignInteractor;
import com.example.testapplication.sign.presentation.SignInPresenter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chudofom on 12.07.17.
 */

public class ServiceLocator {
    private static ServiceLocator instance;

    private Map<Type, Object> instantiatedServices;

    private Context context;

    private ServiceLocator() {

    }

    private void initDependencies() {
        this.instantiatedServices = new HashMap<>();
        this.instantiatedServices.put(UserRepository.class, new UserRepository(PreferenceManager.getDefaultSharedPreferences(getContext())));

        this.instantiatedServices.put(ILoginInteractor.class, new LoginInteractor((UserRepository) getService(UserRepository.class)));
        this.instantiatedServices.put(LoginPresenter.class, new LoginPresenter((LoginInteractor) getService(ILoginInteractor.class)));

        this.instantiatedServices.put(ISignInteractor.class, new SignInteractor((UserRepository) getService(UserRepository.class)));
        this.instantiatedServices.put(SignInPresenter.class, new SignInPresenter((SignInteractor) getService(ISignInteractor.class)));

        this.instantiatedServices.put(IProfileInteractor.class, new ProfileInteractor((UserRepository) getService(UserRepository.class)));
        this.instantiatedServices.put(ProfilePresenter.class, new ProfilePresenter((ProfileInteractor) getService(IProfileInteractor.class)));
    }

    public static ServiceLocator instance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        initDependencies();
    }

    public Context getContext() {
        return context;
    }

    public Object getService(Type type) {
        return instantiatedServices.get(type);
    }
}