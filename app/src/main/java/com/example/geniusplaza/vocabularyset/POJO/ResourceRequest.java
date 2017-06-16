package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/14/17.
 */

public class ResourceRequest {
    @SerializedName("lid")
    private String lid;
    @SerializedName("type")
    private String type;
    @SerializedName("keywords")
    private String keywords;
    @SerializedName("my_resources")
    private String my_resources;

    public ResourceRequest(String lid, String type, String keywords, String myResources) {
        this.lid = lid;
        this.type = type;
        this.keywords = keywords;
        this.my_resources = myResources;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
