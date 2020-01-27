package com.savov.blog.web.controllers;

import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        List<PostServiceModel> topPosts = postService.getTopPost();
        modelAndView.addObject("topPosts", topPosts);
        modelAndView.setViewName("home");
        return modelAndView;
    }


}
