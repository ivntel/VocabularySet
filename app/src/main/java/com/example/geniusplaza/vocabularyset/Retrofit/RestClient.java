package com.example.geniusplaza.vocabularyset.Retrofit;

/**
 * Created by geniusplaza on 6/13/17.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://192.168.1.35:8000/";

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GeniusApi getExampleApi() {
        return RestClient.getClient(BASE_URL).create(GeniusApi.class);
    }
}
