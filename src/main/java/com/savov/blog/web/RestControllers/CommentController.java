package com.savov.blog.web.RestControllers;

import com.savov.blog.domain.entities.Comment;

import com.savov.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllComments(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentsById(@PathVariable(name = "postId") Long postId,
                                     @PathVariable(name = "id") Long id){

        return new ResponseEntity<>(commentService.getCommentsById(postId,id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addComments(@PathVariable(name = "postId") Long postId,@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.addComments(postId,comment), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComments(@PathVariable(name = "postId") Long postId,
                                            @PathVariable(name = "id") Long id,
                                            @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.updateComments(postId,id,comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComments(@PathVariable(name = "postId") Long postId,
                                 @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.deleteComments(postId,id), HttpStatus.OK);
    }
}
