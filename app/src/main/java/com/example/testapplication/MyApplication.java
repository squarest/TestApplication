package com.example.testapplication;

import android.app.Application;

/**
 * Created by Chudofom on 13.07.17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceLocator.instance().setContext(this);

    }
}
