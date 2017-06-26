package com.example.geniusplaza.vocabularyset.POJO;

/**
 * Created by geniusplaza on 6/26/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditWordResponse {

    @SerializedName("Updated")
    @Expose
    private List<String> updated = null;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Error")
    @Expose
    private String error;

    public List<String> getUpdated() {
        return updated;
    }

    public void setUpdated(List<String> updated) {
        this.updated = updated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
