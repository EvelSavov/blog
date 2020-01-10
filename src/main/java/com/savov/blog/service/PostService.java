package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.model.binding.PostBindingModel;
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
}
