package com.savov.blog.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String categoryName;

    private List<Post> posts;


    public Category() {
    }

    @Column(name = "category_name", nullable = false, unique = true)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "category", orphanRemoval = true,cascade = CascadeType.REMOVE)
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
