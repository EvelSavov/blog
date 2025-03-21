package com.savov.blog.domain.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity  {

    private String title;

    private String body;

    private Long likeCount;

    private List<User> like;

    private Long dislikeCount;

    private List<User> dislike;

    private User user;

    private Category category;

    private List<Comment> comments;

    private List<String> photos;


    public Post() {
    }

    @Column(name = "title",unique = true,nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "body", columnDefinition = "LONGTEXT",nullable = false)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "like_count")
    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    @Column(name = "dislike_count")
    public Long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne()
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "post", orphanRemoval = true,cascade = CascadeType.REMOVE)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @ManyToMany(mappedBy = "likedPosts",cascade = CascadeType.REMOVE)
    public List<User> getLike() {
        return like;
    }


    public void setLike(List<User> like) {
        this.like = like;
    }

    @ManyToMany(mappedBy = "dislikedPosts", cascade = CascadeType.REMOVE)
    public List<User> getDislike() {
        return dislike;
    }


    public void setDislike(List<User> dislike) {
        this.dislike = dislike;
    }

    @Column(name = "photos")
    @ElementCollection(targetClass=String.class)
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
