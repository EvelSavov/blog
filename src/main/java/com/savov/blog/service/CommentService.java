package com.savov.blog.service;


import com.savov.blog.domain.model.service.CommentServiceModel;

import java.util.List;

public interface CommentService {

    List<CommentServiceModel> getAllComments(Long postId);

    CommentServiceModel getCommentsById(Long postId, Long id);

    List<CommentServiceModel> getCommentsByUserId(Long userId);

//    CommentServiceModel addComments(Long postId, CommentServiceModel comment);

    CommentServiceModel addComments(Long postId, CommentServiceModel comment, Long id);

    CommentServiceModel updateComments(Long postId, Long commentId, CommentServiceModel comment);

    CommentServiceModel deleteComments(Long postId, Long id);

}
