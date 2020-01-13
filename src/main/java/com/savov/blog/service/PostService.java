package com.savov.blog.service;


import com.savov.blog.domain.model.service.PostServiceModel;

import java.util.List;

public interface PostService {
    PostServiceModel getPostById(Long id);

    List<PostServiceModel> getAll();

    List<PostServiceModel> getPostByUserId(Long id);

    PostServiceModel addPost(PostServiceModel postServiceModel);

    PostServiceModel updatePost(Long id, PostServiceModel postServiceModel);

    PostServiceModel deletePost(Long id);

    PostServiceModel addPost(PostServiceModel postServiceModel, Long UserId);

    PostServiceModel updatePost(Long postId, PostServiceModel postServiceModel, Long userId);

    List<PostServiceModel> getPostByCategoryId(Long categoryId);

    List<PostServiceModel> getTopPost();

    Long getLike(Long postId);

    Long getDislike(Long postId);

    PostServiceModel addLike(Long postId);

    PostServiceModel addDislike(Long postId);

}
