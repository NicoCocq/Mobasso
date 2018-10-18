package com.esgi.davidlinhares.mobasso.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.esgi.davidlinhares.mobasso.MainActivity;
import com.esgi.davidlinhares.mobasso.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText login;
    @BindView(R.id.login_password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    void onLoginClicked() {
        String login = this.login.getText().toString();
        String password = this.password.getText().toString();
        if(login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.missing_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO connection

        Intent resultIntent = new Intent();
        resultIntent.putExtra(getString(R.string.SUPER_USER_ACTIVATED), true);
        setResult(MainActivity.STATIC_INTEGER_VALUE, resultIntent);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.SUPER_USER_ACTIVATED), true);
        editor.apply();
        editor.commit();
        finish();
    }

    @OnClick(R.id.login_cancel)
    void onCancelClicked() {
        finish();
    }
}
