package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/26/17.
 */

public class EditWordBody {
    @SerializedName("word")
    private String word;
    @SerializedName("meaning")
    private String meaning;
    @SerializedName("sentence")
    private String sentence;
    @SerializedName("type_id")
    private String typeId;

    public EditWordBody(String word, String meaning, String sentence, String typeId) {
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.typeId = typeId;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
