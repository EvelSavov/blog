package com.savov.blog.web.RestControllers;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class RestPostController {

    private final PostService postService;

    @Autowired
    public RestPostController(PostService postService) {

        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {

        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getPostById(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post) {

        return new ResponseEntity<>(postService.addPost(post), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable(name = "id") Long id,@RequestBody Post post) {

        return new ResponseEntity<>(postService.updatePost(id,post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {

        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }


}
