package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/15/17.
 */

public class CreateResource {
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("resource_id")
    @Expose
    private Integer resourceId;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
