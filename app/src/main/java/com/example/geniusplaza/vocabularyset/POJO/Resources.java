package com.example.geniusplaza.vocabularyset.POJO;

/**
 * Created by geniusplaza on 6/14/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resources {

    @SerializedName("Resources")
    @Expose
    private List<ResourceNew> resources = null;

    public List<ResourceNew> getResources() {
        return resources;
    }

    public void setResources(List<ResourceNew> resources) {
        this.resources = resources;
    }


}