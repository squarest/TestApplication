package com.example.testapplication.sign.presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.ServiceLocator;
import com.example.testapplication.entity.User;
import com.example.testapplication.login.presentation.LoginActivity;

public class SignInActivity extends AppCompatActivity implements SignInView {
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private SignInPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        presenter = (SignInPresenter) ServiceLocator.instance().getService(SignInPresenter.class);
        presenter.attachView(this);

        nameEditText = (EditText) findViewById(R.id.name_edittext);
        surnameEditText = (EditText) findViewById(R.id.surname_edittext);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        repeatPasswordEditText = (EditText) findViewById(R.id.password_repeat_edittext);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v ->
        {
            validate();
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void validate() {
        if (!passwordEditText.getText().toString().isEmpty() &&
                passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())) {
            if (!nameEditText.getText().toString().isEmpty() && !surnameEditText.getText().toString().isEmpty()
                    && !emailEditText.getText().toString().isEmpty()) {
                User user = new User();
                user.name = nameEditText.getText().toString();
                user.surname = surnameEditText.getText().toString();
                user.email = emailEditText.getText().toString();
                user.password = passwordEditText.getText().toString();
                presenter.submitButtonClicked(user);
            } else showMessage("Заполните все поля");
        } else showMessage("Пароли не совпадают");
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
        startActivity(intent);
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
