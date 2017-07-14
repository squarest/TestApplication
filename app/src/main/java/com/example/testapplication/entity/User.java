package com.example.testapplication.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Chudofom on 13.07.17.
 */

public class User implements Serializable {
    public String name;
    public String surname;
    public String email;
    public String password;

    public User() {
    }

    public User(String name, String surname, String email, String password) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {

        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, password);
    }
}
