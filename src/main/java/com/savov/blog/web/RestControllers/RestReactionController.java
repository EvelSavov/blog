package com.savov.blog.web.RestControllers;

import com.savov.blog.service.ReactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/v1/posts/{postId}")
public class RestReactionController {

    private final ReactionService reactionService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestReactionController(ReactionService reactionService, ModelMapper modelMapper) {
        this.reactionService = reactionService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/like")
    public ResponseEntity<?> getLike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(reactionService.getLike(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/dislike")
    public ResponseEntity<?> getDislike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(reactionService.getDisike(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("/like")
    public ResponseEntity<?> addLike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(reactionService.addLike(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/dislike")
    public ResponseEntity<?> addDislike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(reactionService.addDisike(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }
}
