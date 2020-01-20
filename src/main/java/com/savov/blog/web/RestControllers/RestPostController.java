package com.savov.blog.web.RestControllers;


import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.domain.restModel.bainding.RestPostBindingModel;
import com.savov.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
public class RestPostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestPostController(PostService postService, ModelMapper modelMapper) {

        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(HttpSession session) {
        if (session.getAttribute("username") != null) {
            List<PostServiceModel> model = postService.getAll();
            return new ResponseEntity<>(model.stream().map(p -> this.modelMapper.map(p, RestPostBindingModel.class)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopPosts(HttpSession session) {
      //  if (session.getAttribute("username") != null) {
            List<PostServiceModel> model = postService.getTopPost();
            return new ResponseEntity<>(model.stream().map(p -> this.modelMapper.map(p, RestPostBindingModel.class)), HttpStatus.OK);
        //} else {
          //  return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        //}
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(name = "id") Long id, HttpSession session) {
        if (session.getAttribute("username") != null) {
            PostServiceModel post = postService.getPostById(id);

            return new ResponseEntity<>(this.modelMapper.map(post, RestPostBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("category/{id}")
    public ResponseEntity<?> getPostByCategoryId(@PathVariable(name = "id") Long id, HttpSession session) {
        if (session.getAttribute("username") != null) {
            List<PostServiceModel> post = postService.getPostByCategoryId(id);
            return new ResponseEntity<>(post.stream().map(p->this.modelMapper.map(p,RestPostBindingModel.class)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getPostByUserId(@PathVariable(name = "id") Long id, HttpSession session) {
        if (session.getAttribute("username") != null) {
            List<PostServiceModel> post = postService.getPostByUserId(id);
            return new ResponseEntity<>(post.stream().map(p->this.modelMapper.map(p,RestPostBindingModel.class)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody RestPostBindingModel restPostBindingModel, HttpSession session) {
        if (session.getAttribute("username") != null) {
            Long userId = (Long) session.getAttribute("id");
            PostServiceModel model = postService.addPost(this.modelMapper.map(restPostBindingModel, PostServiceModel.class), userId);
            return new ResponseEntity<>(this.modelMapper.map(model, RestPostBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable(name = "id") Long id, @RequestBody RestPostBindingModel restPostBindingModel, HttpSession session) {
        if (session.getAttribute("username") != null) {
            Long userId = (Long) session.getAttribute("id");
            PostServiceModel model = postService.updatePost(id, this.modelMapper.map(restPostBindingModel, PostServiceModel.class),userId );
            return new ResponseEntity<>(this.modelMapper.map(model, RestPostBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id, HttpSession session) {
        if (session.getAttribute("username") != null) {
            PostServiceModel model = postService.deletePost(id);
            return new ResponseEntity<>(this.modelMapper.map(model, RestPostBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{postId}/like")
    public ResponseEntity<?> getLike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(postService.getLike(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{postId}/dislike")
    public ResponseEntity<?> getDislike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return new ResponseEntity<>(postService.getDislike(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<?> addLike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            Long userId = (Long) session.getAttribute("id");
            PostServiceModel model = postService.addLike(postId,userId);
            return new ResponseEntity<>(this.modelMapper.map(model,RestPostBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{postId}/dislike")
    public ResponseEntity<?> addDislike(@PathVariable(name = "postId") Long postId, HttpSession session) {
        if (session.getAttribute("username") != null) {
            Long userId = (Long) session.getAttribute("id");
            PostServiceModel model = postService.addDislike(postId,userId);
            return new ResponseEntity<>(this.modelMapper.map(model,RestPostBindingModel.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no login user", HttpStatus.FORBIDDEN);
        }
    }




}
