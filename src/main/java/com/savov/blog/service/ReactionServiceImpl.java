package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final PostRepository postRepository;

    public ReactionServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Long getLike(Long postId) {

        Post post = postRepository.findById(postId).orElse(null);
        return post.getLikeCount();
    }

    @Override
    public Long getDisike(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return post.getDislikeCount();
    }

    @Override
    public Post addLike(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        post.setLikeCount(post.getLikeCount()+1);
        return postRepository.save(post);
    }


    @Override
    public Post addDisike(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        post.setDislikeCount(post.getDislikeCount()+1);
        return postRepository.save(post);
    }
}
