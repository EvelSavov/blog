package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.model.service.PostServiceModel;

public interface ReactionService {
    Long getLike(Long postId);

    Long getDisike(Long postId);

    PostServiceModel addLike(Long postId);

    PostServiceModel addDisike(Long postId);
}
