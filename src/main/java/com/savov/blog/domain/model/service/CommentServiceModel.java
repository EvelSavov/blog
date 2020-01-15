package com.savov.blog.domain.model.service;


import com.savov.blog.domain.entities.Post;

public class CommentServiceModel {

    private Long id;

    private String body;

    private PostServiceModel post;

    private String user;


    public CommentServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PostServiceModel getPost() {
        return post;
    }

    public void setPost(PostServiceModel post) {
        this.post = post;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
