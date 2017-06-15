package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/15/17.
 */

public class Word {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("word_video")
    @Expose
    private String wordVideo;
    @SerializedName("old_id")
    @Expose
    private Integer oldId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sentence_audio")
    @Expose
    private String sentenceAudio;
    @SerializedName("meaning_audio")
    @Expose
    private String meaningAudio;
    @SerializedName("sentence")
    @Expose
    private String sentence;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("meaning")
    @Expose
    private String meaning;
    @SerializedName("word_audio")
    @Expose
    private String wordAudio;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("resource")
    @Expose
    private Integer resource;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordVideo() {
        return wordVideo;
    }

    public void setWordVideo(String wordVideo) {
        this.wordVideo = wordVideo;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSentenceAudio() {
        return sentenceAudio;
    }

    public void setSentenceAudio(String sentenceAudio) {
        this.sentenceAudio = sentenceAudio;
    }

    public String getMeaningAudio() {
        return meaningAudio;
    }

    public void setMeaningAudio(String meaningAudio) {
        this.meaningAudio = meaningAudio;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getWordAudio() {
        return wordAudio;
    }

    public void setWordAudio(String wordAudio) {
        this.wordAudio = wordAudio;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }
}
