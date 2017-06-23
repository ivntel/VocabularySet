package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/23/17.
 */

public class CreateVocabSetBody {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("language_id")
    private String languageId;
    @SerializedName("type_id")
    private String typeId;

    public CreateVocabSetBody(String title, String description, String languageId, String typeId) {
        this.title = title;
        this.description = description;
        this.languageId = languageId;
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
