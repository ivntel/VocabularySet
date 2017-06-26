package com.example.geniusplaza.vocabularyset.POJO;

/**
 * Created by geniusplaza on 6/26/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditVocabSetResponse {

    @SerializedName("Resource")
    @Expose
    private ResourceNew resource;
    @SerializedName("Error")
    @Expose
    private String error;

    public ResourceNew getResource() {
        return resource;
    }

    public void setResource(ResourceNew resource) {
        this.resource = resource;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
