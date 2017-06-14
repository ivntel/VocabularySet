package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/14/17.
 */

public class Subject {
    @SerializedName("icon")
    @Expose
    private String icon;
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
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("icon_es")
    @Expose
    private String iconEs;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("old_id")
    @Expose
    private Integer oldId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getIconEs() {
        return iconEs;
    }

    public void setIconEs(String iconEs) {
        this.iconEs = iconEs;
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
