package com.example.geniusplaza.vocabularyset.POJO;

/**
 * Created by geniusplaza on 6/23/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddWordResponse {

    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("Message")
    @Expose
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}