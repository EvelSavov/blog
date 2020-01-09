package com.savov.blog.web.controllers;

import com.savov.blog.domain.entities.Post;
import com.savov.blog.service.CommentService;
import com.savov.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;


    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;

        this.commentService = commentService;
    }

    @GetMapping("/print/{id}")
    public ModelAndView getPost(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.addObject("comments", commentService.getAllComments(id));
            modelAndView.setViewName("print");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;

    }

    @GetMapping("/profile")
    public ModelAndView myProfile(ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("documents",postService.getPostByUserId((Long) session.getAttribute("id")));
            modelAndView.setViewName("profile");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping("/addpost")
    public ModelAndView addPost(ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.setViewName("addpost");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @PostMapping("/addpost")
    public ModelAndView confirmAddPost(@ModelAttribute Post post, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {

            postService.addPost(post, (Long) session.getAttribute("id"));
            modelAndView.setViewName("redirect:/");
        }else{
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePost(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            postService.deletePost(id);
            modelAndView.setViewName("redirect:/profile");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;

    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.setViewName("update");

        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;

    }

    @PostMapping("/update/{id}")
    public ModelAndView updateConfirm(@ModelAttribute Post post,@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session){

        postService.updatePost(id,post, (Long) session.getAttribute("id"));
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }

}
