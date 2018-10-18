package com.esgi.davidlinhares.mobasso.News;

public class News {
    private String title;
    private String details;

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public News(String title, String details) {
        this.title = title;
        this.details = details;
    }
}
