package com.esgi.davidlinhares.mobasso.Common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.esgi.davidlinhares.mobasso.R;
import com.esgi.davidlinhares.mobasso.api.ApiManager;
import com.esgi.davidlinhares.mobasso.api.ContainerService;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static android.content.Context.MODE_PRIVATE;

final public class Config {
    private static Config instance = null;

    @SerializedName("title")
    private String association_name;
    @SerializedName("description")
    private String association_detail;
    @SerializedName("primaryColor")
    private String main_color = "";
    @SerializedName("donation")
    private String donation_link = "";
    @SerializedName("facebook")
    private String facebook_link = "";
    @SerializedName("twitter")
    private String twitter_link = "";
    @SerializedName("youtube")
    private String youtube_link = "";
    @SerializedName("instagram")
    private String instagram_link = "";
    private static boolean isConfigLoaded = false;

    public void setAssociation_name(String association_name) {
        this.association_name = association_name;
    }

    public void setAssociation_detail(String association_detail) {
        this.association_detail = association_detail;
    }

    public void setMain_color(String main_color) {
        this.main_color = main_color;
    }

    public void setDonation_link(String donation_link) {
        this.donation_link = donation_link;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
    }

    public void setTwitter_link(String twitter_link) {
        this.twitter_link = twitter_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }

    public void setInstagram_link(String instagram_link) {
        this.instagram_link = instagram_link;
    }

    private Config(String association_name, String association_detail, String main_color, String donation_link, String facebook_link, String twitter_link, String youtube_link, String instagram_link) {
        this.association_name = association_name;
        this.association_detail = association_detail;
        this.main_color = main_color;
        this.donation_link = donation_link;
        this.facebook_link = facebook_link;
        this.twitter_link = twitter_link;
        this.youtube_link = youtube_link;
        this.instagram_link = instagram_link;
    }

    public static Config getInstance() {
        return instance;
    }

    public String getAssociation_name() {
        return association_name;
    }

    public String getAssociation_detail() {
        return association_detail;
    }

    public String getMain_color() {
        return main_color;
    }

    public String getDonation_link() {
        return donation_link;
    }

    public String getFacebook_link() {
        return facebook_link;
    }

    public String getTwitter_link() {
        return twitter_link;
    }

    public String getYoutube_link() {
        return youtube_link;
    }

    public String getInstagram_link() {
        return instagram_link;
    }

    public static void setInstance(Config instance) {
        isConfigLoaded = true;
        Config.instance = instance;
    }

    public static boolean isIsConfigLoaded() {
        return isConfigLoaded;
    }

    public void saveConfig(String conf, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.config_file), conf);
        editor.apply();
        editor.commit();
    }
}
