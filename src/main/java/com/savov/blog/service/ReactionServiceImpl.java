//package com.savov.blog.service;
//
//import com.savov.blog.domain.entities.Post;
//import com.savov.blog.domain.model.service.PostServiceModel;
//import com.savov.blog.repository.PostRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReactionServiceImpl implements ReactionService {
//
//    private final PostRepository postRepository;
//    private final ModelMapper modelMapper;
//
//    public ReactionServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
//        this.postRepository = postRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public Long getLike(Long postId) {
//
//        Post post = postRepository.findById(postId).orElse(null);
//        return post.getLikeCount();
//    }
//
//    @Override
//    public Long getDisike(Long postId) {
//        Post post = postRepository.findById(postId).orElse(null);
//        return post.getDislikeCount();
//    }
//
//    @Override
//    public PostServiceModel addLike(Long postId) {
//        Post post = postRepository.findById(postId).orElse(null);
//        post.setLikeCount(post.getLikeCount()+1);
//        return this.modelMapper.map(postRepository.save(post),PostServiceModel.class);
//    }
//
//
//    @Override
//    public PostServiceModel addDisike(Long postId) {
//        Post post = postRepository.findById(postId).orElse(null);
//        post.setDislikeCount(post.getDislikeCount()+1);
//        return this.modelMapper.map(postRepository.save(post),PostServiceModel.class);
//    }
//}
