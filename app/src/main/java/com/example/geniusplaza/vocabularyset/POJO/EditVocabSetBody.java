package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/26/17.
 */

public class EditVocabSetBody {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String languageId;

    public EditVocabSetBody(String title, String description, String languageId) {
        this.title = title;
        this.description = description;
        this.languageId = languageId;
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
}