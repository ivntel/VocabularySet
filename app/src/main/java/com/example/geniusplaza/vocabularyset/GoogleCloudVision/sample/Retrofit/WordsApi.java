package com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.Retrofit;

import com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.POJO.WordContent;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by geniusplaza on 6/20/17.
 */

public interface WordsApi {
    @Headers({
            "X-Mashape-Key: Br3yZW4Cu3mshyvxDWxB3lobxNnTp1bld95jsn90XHIBEgLe6d",
            "Accept: application/json"
    })
    @GET("{word}")
    Observable<WordContent> wordLookup(@Path("word") String word );
}