package com.savov.blog.domain.model.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savov.blog.domain.entities.Category;

import com.savov.blog.domain.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class PostServiceModel {

    private Long id;

    private String title;

    private String body;

    private Long likeCount;

    private List<UserServiceModel> like;

    private Long dislikeCount;

    private List<UserServiceModel> dislike;


    private UserServiceModel user;

    private String category;

    private List<CommentServiceModel> comments;

    private List<String> photos;


    public PostServiceModel() {
        this.comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentServiceModel> comments) {
        this.comments = comments;
    }

    public List<UserServiceModel> getLike() {
        return like;
    }

    public void setLike(List<UserServiceModel> like) {
        this.like = like;
    }

    public List<UserServiceModel> getDislike() {
        return dislike;
    }

    public void setDislike(List<UserServiceModel> dislike) {
        this.dislike = dislike;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
