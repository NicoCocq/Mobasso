package com.esgi.davidlinhares.mobasso.Common;

import com.google.gson.annotations.SerializedName;

final public class Config {
    private static Config instance = null;

    @SerializedName("title")
    private static String association_name = "MobAsso";
    @SerializedName("description")
    private static String association_detail = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l\\'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n\\'a pas fait que survivre cinq siècles, mais s\\'est aussi adapté à la bureautique informatique, sans que son contenu n\\'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.";
    @SerializedName("primaryColor")
    private static String main_color = "";
    @SerializedName("donation")
    private static String donation_link = "";
    @SerializedName("facebook")
    private static String facebook_link = "";
    @SerializedName("twitter")
    private static String twitter_link = "";
    @SerializedName("youtube")
    private static String youtube_link = "";
    @SerializedName("instagram")
    private static String instagram_link = "";

    private Config (){
    }

    public static String getDonation_link() {
        return donation_link;
    }

    public static void setDonation_link(String donation_link) {
        Config.donation_link = donation_link;
    }

    public static String getFacebook_link() {
        return facebook_link;
    }

    public static void setFacebook_link(String facebook_link) {
        Config.facebook_link = facebook_link;
    }

    public static String getTwitter_link() {
        return twitter_link;
    }

    public static void setTwitter_link(String twitter_link) {
        Config.twitter_link = twitter_link;
    }

    public static String getYoutube_link() {
        return youtube_link;
    }

    public static void setYoutube_link(String youtube_link) {
        Config.youtube_link = youtube_link;
    }

    public static String getInstagram_link() {
        return instagram_link;
    }

    public static void setInstagram_link(String instagram_link) {
        Config.instagram_link = instagram_link;
    }

    public static String getAssociation_name() {
        return association_name;
    }

    public static void setAssociation_name(String association_name) {
        Config.association_name = association_name;
    }

    public static String getAssociation_detail() {
        return association_detail;
    }

    public static void setAssociation_detail(String association_detail) {
        Config.association_detail = association_detail;
    }


    public static Config getInstance() {
        if(instance == null) {
            new Config();
        }
        return instance;
    }

    public static void setInstance(Config instance) {
        Config.instance = instance;
    }
}
