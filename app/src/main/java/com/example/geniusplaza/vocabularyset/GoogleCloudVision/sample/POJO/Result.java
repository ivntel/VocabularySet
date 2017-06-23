package com.example.geniusplaza.vocabularyset.GoogleCloudVision.sample.POJO;

/**
 * Created by geniusplaza on 6/23/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("partOfSpeech")
    @Expose
    private String partOfSpeech;
    @SerializedName("synonyms")
    @Expose
    private List<String> synonyms = null;
    @SerializedName("typeOf")
    @Expose
    private List<String> typeOf = null;
    @SerializedName("examples")
    @Expose
    private List<String> examples = null;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(List<String> typeOf) {
        this.typeOf = typeOf;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

}
