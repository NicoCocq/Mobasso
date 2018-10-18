package com.esgi.davidlinhares.mobasso.Common;

import com.google.gson.annotations.SerializedName;

final public class Config {
    private static Config instance = null;

    @SerializedName("title")
    private String association_name = "MobAsso";
    @SerializedName("description")
    private String association_detail = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l\\'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n\\'a pas fait que survivre cinq siècles, mais s\\'est aussi adapté à la bureautique informatique, sans que son contenu n\\'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.";
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
}
