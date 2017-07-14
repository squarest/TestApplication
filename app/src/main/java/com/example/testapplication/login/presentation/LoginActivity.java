package com.example.testapplication.login.presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.ServiceLocator;
import com.example.testapplication.entity.User;
import com.example.testapplication.profile.presentation.ProfileActivity;
import com.example.testapplication.sign.presentation.SignInActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    LoginPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = (LoginPresenter) ServiceLocator.instance().getService(LoginPresenter.class);
        presenter.attachView(this);

        EditText emailEditText = (EditText) findViewById(R.id.email_edittext);
        EditText passwordEditText = (EditText) findViewById(R.id.password_edittext);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v ->
        {
            User user = new User(emailEditText.getText().toString(), passwordEditText.getText().toString());
            presenter.submitButtonClicked(user);

        });
        TextView signTextView = (TextView) findViewById(R.id.signInTextView);
        signTextView.setOnClickListener(v ->
        {
            Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showProfile() {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

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
