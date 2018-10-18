package com.esgi.davidlinhares.mobasso.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by davidlinhares on 18/10/2018.
 */

public class Account {
    @SerializedName("association_name")
    private String associationName;
    @SerializedName("id")
    private String id;

    public Account(String associationName, String id) {
        this.associationName = associationName;
        this.id = id;
    }

    public String getAssociationName() {
        return associationName;
    }

    public String getId() {
        return id;
    }
}
