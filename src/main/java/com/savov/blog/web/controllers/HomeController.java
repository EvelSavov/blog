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

    @Autowired
    public HomeController() {
    }
    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }
















}
