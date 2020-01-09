package com.savov.blog.service;

import com.savov.blog.domain.entities.Comment;
import com.savov.blog.repository.CommentRepository;
import com.savov.blog.repository.PostRepository;
import com.savov.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> getAllComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }

    @Override
    public List<Comment> getCommentsById(Long postId, Long id) {
        List<Comment> comments = commentRepository.findByPostIdAndId(postId,id);
        return comments;
    }

    @Override
    public Comment addComments(Long postId, Comment comment) {
        comment.setPost(postRepository.findById(postId).orElse(null));
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment addComments(Long postId, Comment comment, Long id) {
        comment.setUser(userRepository.findById(id).orElse(null));
        return addComments(postId,comment);
    }

    @Override
    public Comment updateComments(Long postId, Long id, Comment comment) {
        Comment comment1 = commentRepository.findById(id).orElse(null);
        comment1.setPost(comment.getPost());
        comment1.setBody(comment.getBody());
        comment1.setUser(comment.getUser());
        return commentRepository.saveAndFlush(comment1);
    }

    @Override
    public Comment deleteComments(Long postId, Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        commentRepository.deleteById(id);

        return comment;
    }
}




