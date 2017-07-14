package com.example.testapplication.data;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.testapplication.entity.User;

/**
 * Created by Chudofom on 13.07.17.
 */

public class UserRepository {
    SharedPreferences sharedPreferences;

    public UserRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Nullable
    public User getUser() {
        User user = new User();
        if (!sharedPreferences.getString("email", "").isEmpty()) {
            user.email = sharedPreferences.getString("email", "");
            user.password = sharedPreferences.getString("password", "");
            user.name = sharedPreferences.getString("name", "");
            user.surname = sharedPreferences.getString("surname", "");
            return user;
        } else return null;
    }

    public void setUser(User user) {
        sharedPreferences.edit()
                .putString("email", user.email)
                .putString("password", user.password)
                .putString("name", user.name)
                .putString("surname", user.surname)
                .apply();
    }
}
