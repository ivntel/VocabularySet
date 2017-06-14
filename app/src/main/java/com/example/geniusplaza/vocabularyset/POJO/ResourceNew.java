package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by geniusplaza on 6/14/17.
 */

public class ResourceNew {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("benchmark")
    @Expose
    private Integer benchmark;
    @SerializedName("resource_image")
    @Expose
    private String resourceImage;
//    @SerializedName("grade")
//    @Expose
//    private Grade grade;
    @SerializedName("creator_avatar")
    @Expose
    private String creatorAvatar;
    @SerializedName("language")
    @Expose
    private Integer language;
    @SerializedName("career_featured_order")
    @Expose
    private Integer careerFeaturedOrder;
    @SerializedName("id")
    @Expose
    private Integer id;
//    @SerializedName("subject")
//    @Expose
//    private Subject subject;
    @SerializedName("creator_username")
    @Expose
    private String creatorUsername;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("assigned")
    @Expose
    private Boolean assigned;
    @SerializedName("user_hifive")
    @Expose
    private Boolean userHifive;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("creator_role")
    @Expose
    private String creatorRole;
    @SerializedName("old_id")
    @Expose
    private Integer oldId;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("resource_audio")
    @Expose
    private String resourceAudio;
    @SerializedName("total_comments")
    @Expose
    private Integer totalComments;
    @SerializedName("hifive_count")
    @Expose
    private Integer hifiveCount;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("grade_subject")
    @Expose
    private Integer gradeSubject;
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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(Integer benchmark) {
        this.benchmark = benchmark;
    }

    public String getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

//    public Grade getGrade() {
//        return grade;
//    }
//
//    public void setGrade(Grade grade) {
//        this.grade = grade;
//    }

    public String getCreatorAvatar() {
        return creatorAvatar;
    }

    public void setCreatorAvatar(String creatorAvatar) {
        this.creatorAvatar = creatorAvatar;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getCareerFeaturedOrder() {
        return careerFeaturedOrder;
    }

    public void setCareerFeaturedOrder(Integer careerFeaturedOrder) {
        this.careerFeaturedOrder = careerFeaturedOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Subject getSubject() {
//        return subject;
//    }
//
//    public void setSubject(Subject subject) {
//        this.subject = subject;
//    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Boolean getUserHifive() {
        return userHifive;
    }

    public void setUserHifive(Boolean userHifive) {
        this.userHifive = userHifive;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCreatorRole() {
        return creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getResourceAudio() {
        return resourceAudio;
    }

    public void setResourceAudio(String resourceAudio) {
        this.resourceAudio = resourceAudio;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Integer getHifiveCount() {
        return hifiveCount;
    }

    public void setHifiveCount(Integer hifiveCount) {
        this.hifiveCount = hifiveCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGradeSubject() {
        return gradeSubject;
    }

    public void setGradeSubject(Integer gradeSubject) {
        this.gradeSubject = gradeSubject;
    }


}
