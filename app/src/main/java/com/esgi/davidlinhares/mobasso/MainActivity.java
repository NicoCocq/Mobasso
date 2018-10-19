package com.esgi.davidlinhares.mobasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esgi.davidlinhares.mobasso.Common.Config;
import com.esgi.davidlinhares.mobasso.Donation.DonationFragment;
import com.esgi.davidlinhares.mobasso.Home.HomeFragment;
import com.esgi.davidlinhares.mobasso.News.NewsFragment;
import com.esgi.davidlinhares.mobasso.api.Account;
import com.esgi.davidlinhares.mobasso.api.AccountService;
import com.esgi.davidlinhares.mobasso.api.ApiManager;
import com.esgi.davidlinhares.mobasso.api.ContainerService;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final int STATIC_INTEGER_VALUE = 200;
    @BindView(R.id.bottomNavigationView) public BottomNavigationView bottomNavigationView;
    private Fragment fragment = new HomeFragment();

    public boolean isSuperUserActivated() {
        return isSuperUserActivated;
    }

    private boolean isSuperUserActivated = false;
    private int currentItem = R.id.navigation_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupApiUrl();
        updateConfig();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                currentItem = item.getItemId();
                return navigationLoad(currentItem);
            }
        });
    }

    private boolean navigationLoad(int itemId) {
        switch (itemId) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_news:
                fragment = new NewsFragment();
                break;
            case R.id.navigation_donation:
                fragment = new DonationFragment();
                break;
            default:
                return false;
        }
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ctnFragment, fragment).commit();
        return true;
    }

    public void setSuperUserActivated(boolean activated) {

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.SUPER_USER_ACTIVATED), activated);
        editor.apply();
        editor.commit();

        this.isSuperUserActivated = activated;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean activated = sharedPref.getBoolean(getString(R.string.SUPER_USER_ACTIVATED), false);
        this.setSuperUserActivated(activated);
    }

    private void setupApiUrl() {
        ApiManager.getInstance().setApiUrl(getString(R.string.URL_api));
        AccountService service = ApiManager.getInstance().getRetrofit().create(AccountService.class);
        service.account("1")
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        System.out.println(response);
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        System.out.println(call);
                    }
                });
    }

    private void updateConfig() {
        ApiManager.getInstance().getRetrofit().create(ContainerService.class)
                .downloadConfig(getString(R.string.user_id))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String res = new String(response.body().bytes());

                            Config.setInstance(stringToConfig(res));
                            Config.getInstance().saveConfig(res, fragment.getContext());

                            navigationLoad(currentItem);
                        }catch(Exception e) {
                            System.out.println();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println();
                    }
                });

        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        String config = sharedPreferences.getString(getString(R.string.config_file), "");
        if(config.isEmpty())
            return;

        stringToConfig(config);
        navigationLoad(currentItem);
    }

    private Config stringToConfig(String conf) {
        return new Gson().fromJson(conf, Config.class);
    }
}
