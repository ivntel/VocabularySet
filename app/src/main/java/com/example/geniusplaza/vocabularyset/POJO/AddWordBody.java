package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/23/17.
 */

public class AddWordBody {
    @SerializedName("order")
    private String order;
    @SerializedName("word")
    private String wordName;
    @SerializedName("meaning")
    private String wordMeaning;
    @SerializedName("sentence")
    private String wordSentence;
    @SerializedName("type_id")
    private String typeID;

    public AddWordBody(String order, String wordName, String wordMeaning, String wordSentence, String typeID) {
        this.order = order;
        this.wordName = wordName;
        this.wordMeaning = wordMeaning;
        this.wordSentence = wordSentence;
        this.typeID = typeID;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public String getWordSentence() {
        return wordSentence;
    }

    public void setWordSentence(String wordSentence) {
        this.wordSentence = wordSentence;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
}
