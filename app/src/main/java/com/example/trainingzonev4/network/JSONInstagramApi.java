package com.example.trainingzonev4.network;

import com.example.trainingzonev4.network.dataClasses.InstagramDataPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONInstagramApi {

    @GET("/me/media")
    public Call<InstagramDataPOJO> getPostWithID(@Query("access_token")  String token
            , @Query("fields") String fields);

}
