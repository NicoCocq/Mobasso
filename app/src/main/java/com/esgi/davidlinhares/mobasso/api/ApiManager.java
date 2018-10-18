package com.esgi.davidlinhares.mobasso.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davidlinhares on 18/10/2018.
 */

public class ApiManager {
    private static ApiManager instance = null;
    private String apiUrl = "";
    private Retrofit retrofit;
    private ApiManager() {}

    public static ApiManager getInstance() {
        if(instance == null)
            instance = new ApiManager();

        return instance;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        if(retrofit == null)
            throw new RuntimeException("Url api must be set");
        return retrofit;
    }
}
