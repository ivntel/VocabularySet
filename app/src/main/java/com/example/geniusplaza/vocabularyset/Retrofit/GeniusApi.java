package com.example.geniusplaza.vocabularyset.Retrofit;

/**
 * Created by geniusplaza on 6/13/17.
 */

import com.example.geniusplaza.vocabularyset.POJO.AuthToken;
import com.example.geniusplaza.vocabularyset.POJO.ResourceNew;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.POJO.Resources;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by geniusplaza on 6/9/17.
 */

public interface GeniusApi {

    @FormUrlEncoded
    @POST("o/token/")
    Call<AuthToken> postCredentials(@Header("Authorization") String authorization, @Query("username") String username, @Query("password") String password, @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("o/token/")
    Call<AuthToken> postRefreshToken(@Header("Authorization") String authorization, @Query("refresh_token") String refreshToken, @Field("grant_type") String grantType);

    @POST("api/get_resources/")
    Call<Resources> postGetResources(@Header("Authorization") String authorization, @Body ResourceRequest resourceRequest);

}