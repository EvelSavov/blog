package com.savov.blog.service;

import com.savov.blog.domain.entities.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments(Long postId);

    List<Comment> getCommentsById(Long postId, Long id);

    Comment addComments(Long postId, Comment comment);

    Comment updateComments(Long postId, Long id, Comment comment);

    Comment deleteComments(Long postId, Long id);

    Comment addComments(Long postId, Comment comment, Long id);
}
