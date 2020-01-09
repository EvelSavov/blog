package com.savov.blog.web.controllers;

import com.savov.blog.domain.entities.Comment;
import com.savov.blog.domain.entities.Post;
import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.repository.PostRepository;
import com.savov.blog.service.CommentService;
import com.savov.blog.service.PostService;
import com.savov.blog.service.ReactionService;
import com.savov.blog.service.UserService;
import com.savov.blog.web.RestControllers.RestTestControllers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final PostService postService;
    private final CommentService commentService;
    private final ReactionService reactionService;
    private final UserService userService;


    @Autowired
    public HomeController(PostRepository postRepository, RestTestControllers testControllers, PostService postService, CommentService commentService, ReactionService reactionService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.reactionService = reactionService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.addObject("documents",postService.getAll());
        modelAndView.setViewName("home");
        return modelAndView;
    }













}
