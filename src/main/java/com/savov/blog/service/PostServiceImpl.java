package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.repository.CategoryRepository;
import com.savov.blog.repository.PostRepository;
import com.savov.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepos;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper, CategoryRepository categoryRepos) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.categoryRepos = categoryRepos;
    }

    @Override
    public List<PostServiceModel> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public PostServiceModel getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return this.modelMapper.map(post,PostServiceModel.class);
    }

    @Override
    public List<PostServiceModel> getPostByUserId(Long id) {
        List<Post> posts = postRepository.findByUserId(id);
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostServiceModel> getPostByCategoryId(Long categoryId) {
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostServiceModel> getTopPost() {
        List<Post> posts = postRepository.findTop5ByOrderByLikeCountDesc();
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public PostServiceModel addPost(PostServiceModel postServiceModel, Long UserId) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        post.setCategory(categoryRepos.findById((Long.parseLong(postServiceModel.getCategory()))).orElse(null));
        post.setUser(userRepository.findById(UserId).orElse(null));
        post.setLikeCount((long) 0);
        post.setDislikeCount((long) 0);
        postRepository.saveAndFlush(post);
        return this.modelMapper.map(post,PostServiceModel.class);
    }

    @Override
    public PostServiceModel updatePost(Long postId, PostServiceModel postServiceModel, Long userId) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        Post post1 = postRepository.findById(postId).orElse(null);
        post1.setBody(post.getBody());
        post1.setCategory(categoryRepos.findById((Long.parseLong(postServiceModel.getCategory()))).orElse(null));
        post1.setTitle(post.getTitle());
        return this.modelMapper.map(postRepository.saveAndFlush(post1),PostServiceModel.class);
    }

    @Override
    public PostServiceModel deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        postRepository.deleteById(id);
        return this.modelMapper.map(post,PostServiceModel.class);
    }

    @Override
    public Long getLike(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return post.getLikeCount();
    }

    @Override
    public Long getDislike(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return post.getDislikeCount();
    }

    @Override
    public PostServiceModel addLike(Long postId,Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
//        List<User> like = post.getLike();
//        like.add(user);
//        post.setLike(like);
        post.setLikeCount(post.getLikeCount()+1);
        List<Post> likedPosts = user.getLikedPosts();
        likedPosts.add(post);
        user.setLikedPosts(likedPosts);
        return this.modelMapper.map(postRepository.saveAndFlush(post),PostServiceModel.class);
    }

    @Override
    public PostServiceModel addDislike(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        List<User> dislike = post.getDislike();
//        dislike.add(user);
//        post.setDislike(dislike);
//        post.setDislikeCount(post.getDislikeCount()+1);
        List<Post> dislikedPosts = user.getDislikedPosts();
        dislikedPosts.add(post);
        user.setDislikedPosts(dislikedPosts);

        return this.modelMapper.map(postRepository.save(post),PostServiceModel.class);
    }
}
