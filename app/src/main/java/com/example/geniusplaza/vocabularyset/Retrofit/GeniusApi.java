package com.example.geniusplaza.vocabularyset.Retrofit;

/**
 * Created by geniusplaza on 6/13/17.
 */

import com.example.geniusplaza.vocabularyset.POJO.AuthToken;
import com.example.geniusplaza.vocabularyset.POJO.CreateResource;
import com.example.geniusplaza.vocabularyset.POJO.ResourceRequest;
import com.example.geniusplaza.vocabularyset.POJO.Resources;
import com.example.geniusplaza.vocabularyset.POJO.WordsResource;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by geniusplaza on 6/9/17.
 */

public interface GeniusApi {

    @FormUrlEncoded
    @POST("o/token/")
    Observable<AuthToken> postCredentials(@Header("Authorization") String authorization, @Query("username") String username, @Query("password") String password, @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("o/token/")
    Observable<AuthToken> postRefreshToken(@Header("Authorization") String authorization, @Query("refresh_token") String refreshToken, @Field("grant_type") String grantType);

    @POST("api/get_resources/")
    Observable<Resources> postGetResources(@Header("Authorization") String authorization, @Body ResourceRequest resourceRequest);

    @FormUrlEncoded
    @POST("api/create_resource/")
    Observable<CreateResource> createVocabSet(@Header("Authorization") String authorization, @Query("title") String title, @Query("description") String description, @Query("language_id") String lid, @Field("type_id") String type);

    @GET("api/get_resource/{id}")
    Observable<WordsResource> flashcardCreate(@Header("Authorization") String authorization, @Path("id")String id );

    @FormUrlEncoded
    @POST("api/create_word/{id}/")
    Observable<WordsResource> addVocabWords(@Header("Authorization") String authorization, @Query("order") String order, @Query("name") String name, @Query("meaning") String meaning, @Query("sentence") String sentence, @Query("type_id") String type_id, @Path("id")String id );
}