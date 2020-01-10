package com.savov.blog.service;

import com.savov.blog.domain.entities.Comment;
import com.savov.blog.domain.model.binding.CommentBindingModel;
import com.savov.blog.domain.model.service.CommentServiceModel;
import com.savov.blog.repository.CommentRepository;
import com.savov.blog.repository.PostRepository;
import com.savov.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;



    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentServiceModel> getAllComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(c->this.modelMapper.map(c,CommentServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentServiceModel> getCommentsById(Long postId, Long id) {
        List<Comment> comments = commentRepository.findByPostIdAndId(postId,id);
        return comments.stream().map(c->this.modelMapper.map(c,CommentServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public CommentServiceModel addComments(Long postId, CommentServiceModel comment) {
        Comment comment1 = this.modelMapper.map(comment,Comment.class);
        comment1.setPost(postRepository.findById(postId).orElse(null));

        return this.modelMapper.map(commentRepository.save(comment1),CommentServiceModel.class);
    }

    @Override
    public CommentServiceModel addComments(Long postId, CommentServiceModel comment, Long id) {
        Comment comment1 = this.modelMapper.map(comment,Comment.class);
        comment1.setUser(userRepository.findById(id).orElse(null));
        return addComments(postId,comment);
    }

    @Override
    public CommentServiceModel updateComments(Long postId, Long id, CommentServiceModel comment) {
        Comment c = this.modelMapper.map(comment,Comment.class);
        Comment comment1 = commentRepository.findById(id).orElse(null);
        comment1.setPost(c.getPost());
        comment1.setBody(c.getBody());
        comment1.setUser(c.getUser());
        return this.modelMapper.map(commentRepository.save(comment1),CommentServiceModel.class);
    }

    @Override
    public CommentServiceModel deleteComments(Long postId, Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        commentRepository.deleteById(id);

        return this.modelMapper.map(comment,CommentServiceModel.class);
    }

    @Override
    public List<CommentServiceModel> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        int a = 5;
        return  comments.stream().map(c->this.modelMapper.map(c,CommentServiceModel.class)).collect(Collectors.toList());
    }
}




