package com.savov.blog.domain.restModel.bainding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savov.blog.domain.entities.Comment;
import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.entities.Role;

import java.util.List;

public class RestUserBindingModel {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String address;


    @JsonIgnore
    private Role role;

    @JsonIgnore
    private List<RestPostBindingModel> posts;
    @JsonIgnore
    private List<RestCommentBindingModel> comments;


    public RestUserBindingModel() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RestPostBindingModel> getPosts() {
        return posts;
    }

    public void setPosts(List<RestPostBindingModel> posts) {
        this.posts = posts;
    }

    public List<RestCommentBindingModel> getComments() {
        return comments;
    }

    public void setComments(List<RestCommentBindingModel> comments) {
        this.comments = comments;
    }
}
