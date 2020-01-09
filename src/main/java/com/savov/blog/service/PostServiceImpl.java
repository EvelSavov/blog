package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.repository.PostRepository;
import com.savov.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public List<Post> getPostByUserId(Long id) {
        List<Post> posts = postRepository.findByUserId(id);
        return posts;
    }

    @Override
    public Post addPost(Post post) {
        post.setLikeCount((long) 0);
        post.setDislikeCount((long) 0);
        return postRepository.saveAndFlush(post);
    }
    public Post addPost(Post post,Long UserId) {
        post.setUser(userRepository.findById(UserId).orElse(null));
        return addPost(post);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post post1 = postRepository.findById(id).orElse(null);
        post1.setBody(post.getBody());
        post1.setCategory(post.getCategory());
        post1.setTitle(post.getTitle());

        return postRepository.saveAndFlush(post1);
    }

    @Override
    public Post updatePost(Long postId, Post post, Long userId) {

        post.setUser(userRepository.findById(userId).orElse(null));

        return updatePost(postId,post);
    }

    @Override
    public Post deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        postRepository.deleteById(id);

        return post;
    }
}
