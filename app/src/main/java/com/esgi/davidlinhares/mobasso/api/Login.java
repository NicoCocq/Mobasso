package com.esgi.davidlinhares.mobasso.api;

import com.google.gson.annotations.SerializedName;

public class Login {
    private String email;
    private String password;
    @SerializedName("account_id")
    private String accountId;

    public Login(String email, String password, String accountId) {
        this.email = email;
        this.password = password;
        this.accountId = accountId;
    }
}
