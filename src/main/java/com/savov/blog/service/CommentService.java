package com.savov.blog.service;

import com.savov.blog.domain.entities.Comment;
import com.savov.blog.domain.model.binding.CommentBindingModel;
import com.savov.blog.domain.model.service.CommentServiceModel;

import java.util.List;

public interface CommentService {

    List<CommentServiceModel> getAllComments(Long postId);

    List<CommentServiceModel> getCommentsById(Long postId, Long id);

    CommentServiceModel addComments(Long postId, CommentServiceModel comment);

    CommentServiceModel updateComments(Long postId, Long id, CommentServiceModel comment);

    CommentServiceModel deleteComments(Long postId, Long id);

    CommentServiceModel addComments(Long postId, CommentServiceModel comment, Long id);

    List<CommentServiceModel> getCommentsByUserId(Long userId);
}
