package com.esgi.davidlinhares.mobasso.api;

import com.esgi.davidlinhares.mobasso.News.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by davidlinhares on 18/10/2018.
 */

public interface AccountService {
    @GET("accounts/{id}")
    Call<Account> account(@Path("id") String id);
    @GET("accounts/{id}/news")
    Call<List<News>> getNews(@Path("id") String id);
    @POST("accounts/{id}/news")
    Call<News> createNews(@Path("id") String id, @Body NewsInformations body);
    @POST("accounts/login")
    Call<ResponseBody> login(@Body Login body);
}

