package com.esgi.davidlinhares.mobasso.Donation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.esgi.davidlinhares.mobasso.Common.Config;
import com.esgi.davidlinhares.mobasso.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class DonationFragment extends Fragment {

    @BindView(R.id.button_donation)
    Button btn_donation;
    @BindView(R.id.facebook_image)
    ImageView btn_facebook;
    @BindView(R.id.twitter_image)
    ImageView btn_twitter;
    @BindView(R.id.youtube_image)
    ImageView btn_youtube;
    @BindView(R.id.instagram_image)
    ImageView btn_instagram;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        ButterKnife.bind(this, view);

        DisplayButtonUrls();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.button_donation)
    void OnDonationClicked() {
        RunUrl(Config.getDonation_link());
    }

    @OnClick(R.id.facebook_image)
    void OnFacebookClicked() {
        RunUrl(Config.getFacebook_link());
    }

    @OnClick(R.id.twitter_image)
    void OnTwitterClicked() {
        RunUrl(Config.getTwitter_link());
    }

    @OnClick(R.id.youtube_image)
    void OnYoutubeClicked() {
        RunUrl(Config.getYoutube_link());
    }

    @OnClick(R.id.instagram_image)
    void OnInstagramClicked() {
        RunUrl(Config.getInstagram_link());
    }

    private void DisplayButtonUrls() {
        if(!Config.getDonation_link().isEmpty()) {
            btn_donation.setVisibility(View.VISIBLE);
        }
        if(!Config.getFacebook_link().isEmpty()) {
            btn_facebook.setVisibility(View.VISIBLE);
        }
        if(!Config.getTwitter_link().isEmpty()) {
            btn_twitter.setVisibility(View.VISIBLE);
        }
        if(!Config.getYoutube_link().isEmpty()) {
            btn_youtube.setVisibility(View.VISIBLE);
        }
        if(!Config.getInstagram_link().isEmpty()) {
            btn_instagram.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Opens the android browser with the URL provided
     * @param url : url to run
     */
    private void RunUrl(String url) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (RuntimeException re) {
            Toast.makeText(getContext(), R.string.URL_error,Toast.LENGTH_LONG).show();
        }
    }
}
