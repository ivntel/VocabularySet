package com.example.geniusplaza.vocabularyset.Retrofit;

/**
 * Created by geniusplaza on 6/13/17.
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://192.168.1.79:8000/";


    public static Retrofit getClient(String baseUrl) {

        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging)
            .readTimeout(30, TimeUnit.SECONDS);
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GeniusApi getExampleApi() {
        return RestClient.getClient(BASE_URL).create(GeniusApi.class);
    }
}
