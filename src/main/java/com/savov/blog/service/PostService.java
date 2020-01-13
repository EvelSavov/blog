package com.savov.blog.service;


import com.savov.blog.domain.model.service.PostServiceModel;

import java.util.List;

public interface PostService {

    List<PostServiceModel> getAll();

    PostServiceModel getPostById(Long id);

    List<PostServiceModel> getPostByUserId(Long id);

    List<PostServiceModel> getPostByCategoryId(Long categoryId);

    List<PostServiceModel> getTopPost();

    PostServiceModel addPost(PostServiceModel postServiceModel, Long UserId);

    PostServiceModel updatePost(Long postId, PostServiceModel postServiceModel, Long userId);

    PostServiceModel deletePost(Long id);

    Long getLike(Long postId);

    Long getDislike(Long postId);

    PostServiceModel addLike(Long postId);

    PostServiceModel addDislike(Long postId);

}
