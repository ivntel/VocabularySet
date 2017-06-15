package com.example.geniusplaza.vocabularyset.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by geniusplaza on 6/15/17.
 */

public class WordsResource {

    @SerializedName("assigned")
    @Expose
    private Boolean assigned;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("Words")
    @Expose
    private List<Word> words = null;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("skill")
    @Expose
    private Object skill;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("career")
    @Expose
    private Object career;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("exercise_code")
    @Expose
    private Object exerciseCode;
    @SerializedName("total_comments")
    @Expose
    private Integer totalComments;
    @SerializedName("exercise_type")
    @Expose
    private Object exerciseType;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("grade_subject")
    @Expose
    private Object gradeSubject;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("is_vr")
    @Expose
    private Boolean isVr;
    @SerializedName("career_featured_order")
    @Expose
    private Integer careerFeaturedOrder;
    @SerializedName("bank_featured_order")
    @Expose
    private Integer bankFeaturedOrder;
    @SerializedName("printable_type")
    @Expose
    private Object printableType;
    @SerializedName("game")
    @Expose
    private Object game;
    @SerializedName("benchmark")
    @Expose
    private Object benchmark;
    @SerializedName("approval_user")
    @Expose
    private Object approvalUser;
    @SerializedName("creator_username")
    @Expose
    private String creatorUsername;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("video_type")
    @Expose
    private Object videoType;
    @SerializedName("creator_avatar")
    @Expose
    private String creatorAvatar;
    @SerializedName("language")
    @Expose
    private Integer language;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("standard")
    @Expose
    private Object standard;
    @SerializedName("old_id")
    @Expose
    private Integer oldId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("resource_audio")
    @Expose
    private String resourceAudio;
    @SerializedName("resource_image")
    @Expose
    private String resourceImage;
    @SerializedName("user_hifive")
    @Expose
    private Boolean userHifive;
    @SerializedName("course")
    @Expose
    private Object course;
    @SerializedName("hifive_count")
    @Expose
    private Integer hifiveCount;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("creator_role")
    @Expose
    private String creatorRole;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("sound")
    @Expose
    private String sound;
    @SerializedName("diagnostic_type")
    @Expose
    private Object diagnosticType;

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Object getSkill() {
        return skill;
    }

    public void setSkill(Object skill) {
        this.skill = skill;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Object getCareer() {
        return career;
    }

    public void setCareer(Object career) {
        this.career = career;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getExerciseCode() {
        return exerciseCode;
    }

    public void setExerciseCode(Object exerciseCode) {
        this.exerciseCode = exerciseCode;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Object getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Object exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getGradeSubject() {
        return gradeSubject;
    }

    public void setGradeSubject(Object gradeSubject) {
        this.gradeSubject = gradeSubject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getIsVr() {
        return isVr;
    }

    public void setIsVr(Boolean isVr) {
        this.isVr = isVr;
    }

    public Integer getCareerFeaturedOrder() {
        return careerFeaturedOrder;
    }

    public void setCareerFeaturedOrder(Integer careerFeaturedOrder) {
        this.careerFeaturedOrder = careerFeaturedOrder;
    }

    public Integer getBankFeaturedOrder() {
        return bankFeaturedOrder;
    }

    public void setBankFeaturedOrder(Integer bankFeaturedOrder) {
        this.bankFeaturedOrder = bankFeaturedOrder;
    }

    public Object getPrintableType() {
        return printableType;
    }

    public void setPrintableType(Object printableType) {
        this.printableType = printableType;
    }

    public Object getGame() {
        return game;
    }

    public void setGame(Object game) {
        this.game = game;
    }

    public Object getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(Object benchmark) {
        this.benchmark = benchmark;
    }

    public Object getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(Object approvalUser) {
        this.approvalUser = approvalUser;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Object getVideoType() {
        return videoType;
    }

    public void setVideoType(Object videoType) {
        this.videoType = videoType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getStandard() {
        return standard;
    }

    public void setStandard(Object standard) {
        this.standard = standard;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

    public Boolean getUserHifive() {
        return userHifive;
    }

    public void setUserHifive(Boolean userHifive) {
        this.userHifive = userHifive;
    }

    public Object getCourse() {
        return course;
    }

    public void setCourse(Object course) {
        this.course = course;
    }

    public Integer getHifiveCount() {
        return hifiveCount;
    }

    public void setHifiveCount(Integer hifiveCount) {
        this.hifiveCount = hifiveCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatorRole() {
        return creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Object getDiagnosticType() {
        return diagnosticType;
    }

    public void setDiagnosticType(Object diagnosticType) {
        this.diagnosticType = diagnosticType;
    }

}
