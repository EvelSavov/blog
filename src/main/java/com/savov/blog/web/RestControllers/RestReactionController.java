package com.savov.blog.web.RestControllers;

import com.savov.blog.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts/{postId}")
public class RestReactionController {

    private final ReactionService reactionService;

    @Autowired
    public RestReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }


    @GetMapping("/like")
    public ResponseEntity<?> getLike(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(reactionService.getLike(postId), HttpStatus.OK);
    }

    @GetMapping("/dislike")
    public ResponseEntity<?>  getDislike(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(reactionService.getDisike(postId), HttpStatus.OK);
    }


    @PostMapping("/like")
    public ResponseEntity<?>  addLike(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(reactionService.addLike(postId), HttpStatus.OK);
    }

    @PostMapping("/dislike")
    public ResponseEntity<?>  addDislike(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(reactionService.addDisike(postId), HttpStatus.OK);
    }
}
