package com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.POJO;

/**
 * Created by geniusplaza on 6/23/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pronunciation {

    @SerializedName("all")
    @Expose
    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

}