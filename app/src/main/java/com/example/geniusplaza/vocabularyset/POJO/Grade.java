package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/14/17.
 */

public class Grade {
    @SerializedName("name_es")
    @Expose
    private String nameEs;
    @SerializedName("language")
    @Expose
    private Integer language;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_spanish")
    @Expose
    private String nameSpanish;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("old_id")
    @Expose
    private Integer oldId;

    public Grade() {
    }

    @SerializedName("name")

    @Expose
    private String name;

    public String getNameEs() {
        return nameEs;
    }

    public void setNameEs(String nameEs) {
        this.nameEs = nameEs;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSpanish() {
        return nameSpanish;
    }

    public void setNameSpanish(String nameSpanish) {
        this.nameSpanish = nameSpanish;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}