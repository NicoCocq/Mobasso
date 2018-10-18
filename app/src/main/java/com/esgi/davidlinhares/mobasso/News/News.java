package com.esgi.davidlinhares.mobasso.News;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String details;
    @SerializedName("image")
    private String image;

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public News(String title, String details, String image) {
        this.title = title;
        this.details = details;
        this.image = image;
    }

    public String getImage() {
        if(image == null)
            image = "";
        return image;
    }
}
