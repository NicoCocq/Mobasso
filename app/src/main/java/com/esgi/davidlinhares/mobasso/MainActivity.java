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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esgi.davidlinhares.mobasso.Donation.DonationFragment;
import com.esgi.davidlinhares.mobasso.Home.HomeFragment;
import com.esgi.davidlinhares.mobasso.News.NewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final int STATIC_INTEGER_VALUE = 200;
    @BindView(R.id.bottomNavigationView) public BottomNavigationView bottomNavigationView;
    private Fragment fragment = new HomeFragment();

    public boolean isSuperUserActivated() {
        return isSuperUserActivated;
    }

    private boolean isSuperUserActivated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
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
        });
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
}
