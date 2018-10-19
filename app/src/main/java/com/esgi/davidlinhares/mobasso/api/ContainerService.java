package com.esgi.davidlinhares.mobasso.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by davidlinhares on 18/10/2018.
 */

public interface ContainerService {
    @GET("containers/{id}/download/{name}")
    Call<ResponseBody> download(@Path("id") String id, @Path("name") String imageName);

    @GET("containers/{id}/download/apk_config.txt")
    Call<ResponseBody> downloadConfig(@Path("id") String id);

    @Multipart
    @POST("containers/{id}/upload/")
    Call<ResponseBody> upload(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );
}
