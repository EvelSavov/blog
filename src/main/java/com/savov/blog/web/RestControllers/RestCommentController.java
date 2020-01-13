package com.savov.blog.web.RestControllers;


import com.savov.blog.domain.model.service.CommentServiceModel;
import com.savov.blog.domain.restModel.bainding.RestCommentBindingModel;
import com.savov.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("api/v1/posts/{postId}/comments")
public class RestCommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestCommentController(CommentService commentService, ModelMapper modelMapper) {

        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllComments(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            List<CommentServiceModel> allComments = commentService.getAllComments(postId);

            return new ResponseEntity<>(allComments.stream().map(c -> this.modelMapper.map(c, RestCommentBindingModel.class)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentsById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id, HttpSession session) {
        if (session.getAttribute("username") != null) {
            CommentServiceModel comments = commentService.getCommentsById(postId, id);
            return new ResponseEntity<>(this.modelMapper.map(comments, RestCommentBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addComments(@PathVariable(name = "postId") Long postId, @RequestBody RestCommentBindingModel restCommentBindingModel, HttpSession session) {
        if (session.getAttribute("username") != null) {
            Long userId = (Long) session.getAttribute("id");
            CommentServiceModel commentServiceModel = commentService.addComments(postId, this.modelMapper.map(restCommentBindingModel, CommentServiceModel.class),userId);

            return new ResponseEntity<>(this.modelMapper.map(commentServiceModel, RestCommentBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComments(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id, @RequestBody RestCommentBindingModel restCommentBindingModel, HttpSession session) {
        if (session.getAttribute("username") != null) {
            CommentServiceModel commentServiceModel = commentService.updateComments(postId, id, this.modelMapper.map(restCommentBindingModel, CommentServiceModel.class));

            return new ResponseEntity<>(this.modelMapper.map(commentServiceModel, RestCommentBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComments(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id, HttpSession session) {
        if (session.getAttribute("username") != null) {
            CommentServiceModel commentServiceModel = commentService.deleteComments(postId, id);
            return new ResponseEntity<>(this.modelMapper.map(commentServiceModel, RestCommentBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }
}
