package com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.Retrofit;

/**
 * Created by geniusplaza on 6/23/17.
 */

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://wordsapiv1.p.mashape.com/words/";


    public static Retrofit getClient(String baseUrl) {

        if (retrofit==null) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static WordsApi getExampleApi() {
        return RestClient.getClient(BASE_URL).create(WordsApi.class);
    }
}