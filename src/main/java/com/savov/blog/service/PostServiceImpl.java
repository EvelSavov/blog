package com.savov.blog.service;

import com.savov.blog.domain.entities.Post;
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
    public PostServiceModel getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return this.modelMapper.map(post,PostServiceModel.class);
    }

    @Override
    public List<PostServiceModel> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostServiceModel> getPostByUserId(Long id) {
        List<Post> posts = postRepository.findByUserId(id);
        return posts.stream().map(p->this.modelMapper.map(p,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public PostServiceModel addPost(PostServiceModel postServiceModel) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        post.setLikeCount((long) 0);
        post.setDislikeCount((long) 0);
        return this.modelMapper.map(postRepository.save(post),PostServiceModel.class);
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
    public PostServiceModel updatePost(Long id, PostServiceModel postServiceModel) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        Post post1 = postRepository.findById(id).orElse(null);
        post1.setBody(post.getBody());
        post1.setCategory(post.getCategory());
        post1.setTitle(post.getTitle());

        return this.modelMapper.map(postRepository.save(post1),PostServiceModel.class);
    }

    @Override
    public PostServiceModel updatePost(Long postId, PostServiceModel postServiceModel, Long userId) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        post.setUser(userRepository.findById(userId).orElse(null));

        return updatePost(postId,this.modelMapper.map(post,PostServiceModel.class));
    }

    @Override
    public PostServiceModel deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        postRepository.deleteById(id);
        return this.modelMapper.map(post,PostServiceModel.class);
    }
}
