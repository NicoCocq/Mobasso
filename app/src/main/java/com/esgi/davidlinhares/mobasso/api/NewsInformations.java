package com.esgi.davidlinhares.mobasso.api;

import com.google.gson.annotations.SerializedName;

public class NewsInformations {
    String title;
    String content;
    @SerializedName("account_id")
    String accountId;

    public NewsInformations(String title, String content, String accountId) {
        this.title = title;
        this.content = content;
        this.accountId = accountId;
    }
}
