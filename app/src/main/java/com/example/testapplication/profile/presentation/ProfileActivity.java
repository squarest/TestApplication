package com.example.testapplication.profile.presentation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.ServiceLocator;
import com.example.testapplication.entity.User;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private ProfilePresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        presenter = (ProfilePresenter) ServiceLocator.instance().getService(ProfilePresenter.class);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showUser(User user) {
        TextView fioTextView = (TextView) findViewById(R.id.fio_textview);
        TextView emailTextView = (TextView) findViewById(R.id.email_textview);
        fioTextView.setText(user.name + " " + user.surname);
        emailTextView.setText(user.email);

    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "Идет загрузка", "Пожалуйста подождите", false);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }
}
