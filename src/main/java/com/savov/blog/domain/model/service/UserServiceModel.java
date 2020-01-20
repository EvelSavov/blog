package com.savov.blog.domain.model.service;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savov.blog.domain.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String address;

    private String role;

    private List<PostServiceModel> posts;

    private List<CommentServiceModel> comments;

    private List<PostServiceModel> likedPosts;

    private List<PostServiceModel> dislikedPosts;


    public UserServiceModel() {
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PostServiceModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostServiceModel> posts) {
        this.posts = posts;
    }

    public List<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentServiceModel> comments) {
        this.comments = comments;
    }

    public List<PostServiceModel> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<PostServiceModel> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<PostServiceModel> getDislikedPosts() {
        return dislikedPosts;
    }

    public void setDislikedPosts(List<PostServiceModel> dislikedPosts) {
        this.dislikedPosts = dislikedPosts;
    }
}
