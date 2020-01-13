package com.savov.blog.service;


import com.savov.blog.domain.model.service.CommentServiceModel;

import java.util.List;

public interface CommentService {

    List<CommentServiceModel> getAllComments();

    CommentServiceModel getCommentsById(Long id);

    List<CommentServiceModel> getByPostId(Long postId);

    List<CommentServiceModel> getCommentsByUserId(Long userId);

    CommentServiceModel addComments(Long postId, CommentServiceModel comment, Long id);

    CommentServiceModel updateComments(Long commentId, CommentServiceModel comment);

    CommentServiceModel deleteComments(Long id);

}
