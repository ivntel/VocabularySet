package com.example.geniusplaza.vocabularyset.POJO;

/**
 * Created by geniusplaza on 6/14/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource {
    /*@SerializedName("approval_user")
    @Expose
    private Object approvalUser;*/
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
    /*@SerializedName("standard")
    @Expose
    private Object standard;
    @SerializedName("course")
    @Expose
    private Object course;*/
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
    /*@SerializedName("exercise_type")
    @Expose
    private Object exerciseType;*/
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject")
    @Expose
    private Subject subject;
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
    @SerializedName("resource_image")
    @Expose
    private String resourceImage;
    @SerializedName("grade")
    @Expose
    private Grade grade;
    @SerializedName("creator_avatar")
    @Expose
    private String creatorAvatar;
    @SerializedName("language")
    @Expose
    private Integer language;
    @SerializedName("career_featured_order")
    @Expose
    private Integer careerFeaturedOrder;
    /*@SerializedName("exercise_code")
    @Expose
    private Object exerciseCode;
    @SerializedName("game")
    @Expose
    private Object game;
    @SerializedName("career")
    @Expose
    private Object career;*/
    @SerializedName("bank_featured_order")
    @Expose
    private Integer bankFeaturedOrder;
    @SerializedName("benchmark")
    @Expose
    private Integer benchmark;
    /*@SerializedName("skill")
    @Expose
    private Object skill;
    @SerializedName("printable_type")
    @Expose
    private Object printableType;*/
    @SerializedName("title")
    @Expose
    private String title;
    /*@SerializedName("video_type")
    @Expose
    private Object videoType;*/
    @SerializedName("user")
    @Expose
    private Integer user;
    /*@SerializedName("diagnostic_type")
    @Expose
    private Object diagnosticType;*/
    @SerializedName("sound")
    @Expose
    private String sound;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_vr")
    @Expose
    private Boolean isVr;
    @SerializedName("featured")
    @Expose
    private Boolean featured;


   /* public Object getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(Object approvalUser) {
        this.approvalUser = approvalUser;
    }*/

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

  /*  public Object getStandard() {
        return standard;
    }

    public void setStandard(Object standard) {
        this.standard = standard;
    }

    public Object getCourse() {
        return course;
    }

    public void setCourse(Object course) {
        this.course = course;
    }
*/
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

    /*public Object getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Object exerciseType) {
        this.exerciseType = exerciseType;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

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

    public String getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

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

    /*public Object getExerciseCode() {
        return exerciseCode;
    }

    public void setExerciseCode(Object exerciseCode) {
        this.exerciseCode = exerciseCode;
    }

    public Object getGame() {
        return game;
    }

    public void setGame(Object game) {
        this.game = game;
    }

    public Object getCareer() {
        return career;
    }

    public void setCareer(Object career) {
        this.career = career;
    }*/

    public Integer getBankFeaturedOrder() {
        return bankFeaturedOrder;
    }

    public void setBankFeaturedOrder(Integer bankFeaturedOrder) {
        this.bankFeaturedOrder = bankFeaturedOrder;
    }

    public Integer getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(Integer benchmark) {
        this.benchmark = benchmark;
    }

   /* public Object getSkill() {
        return skill;
    }

    public void setSkill(Object skill) {
        this.skill = skill;
    }

    public Object getPrintableType() {
        return printableType;
    }

    public void setPrintableType(Object printableType) {
        this.printableType = printableType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getVideoType() {
        return videoType;
    }*/

   /* public void setVideoType(Object videoType) {
        this.videoType = videoType;
    }*/

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

   /* public Object getDiagnosticType() {
        return diagnosticType;
    }

    public void setDiagnosticType(Object diagnosticType) {
        this.diagnosticType = diagnosticType;
    }*/

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVr() {
        return isVr;
    }

    public void setVr(Boolean vr) {
        isVr = vr;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }
}