package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;

public interface ReactionService {
    Long getLike(Long postId);

    Long getDisike(Long postId);

    Post addLike(Long postId);

    Post addDisike(Long postId);
}
