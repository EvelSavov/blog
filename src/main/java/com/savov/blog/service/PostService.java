package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;

import java.util.List;

public interface PostService {
    Post getPostById(Long id);

    List<Post> getAll();

    List<Post> getPostByUserId(Long id);

    Post addPost(Post post);

    Post updatePost(Long id, Post post);

    Post deletePost(Long id);

    Post addPost(Post post,Long UserId);

    Post updatePost(Long postId, Post post, Long userId);
}
