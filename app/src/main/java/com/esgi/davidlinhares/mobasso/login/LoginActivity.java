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
import com.esgi.davidlinhares.mobasso.api.AccountService;
import com.esgi.davidlinhares.mobasso.api.ApiManager;
import com.esgi.davidlinhares.mobasso.api.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ApiManager.getInstance().getRetrofit().create(AccountService.class)
                .login(new Login(login, password, getString(R.string.user_id)))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200) {
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
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.bad_credentials, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }

    @OnClick(R.id.login_cancel)
    void onCancelClicked() {
        finish();
    }
}
