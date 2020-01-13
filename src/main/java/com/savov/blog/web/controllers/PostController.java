package com.savov.blog.web.controllers;

import com.savov.blog.domain.model.binding.PostBindingModel;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.service.CommentService;
import com.savov.blog.service.PostService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final CommentService commentService;


    public PostController(PostService postService, ModelMapper modelMapper, CommentService commentService) {
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }


    @GetMapping("/allpost")
    public ModelAndView allPost(ModelAndView modelAndView) {
        modelAndView.addObject("documents",postService.getAll());
        modelAndView.setViewName("allpost");
        return modelAndView;
    }

    @GetMapping("/print/{id}")
    public ModelAndView getPostById(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.setViewName("print");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;

    }

    @GetMapping("/myposts")
    public ModelAndView myPosts(ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("documents",postService.getPostByUserId((Long) session.getAttribute("id")));
            modelAndView.setViewName("myposts");
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
    public ModelAndView confirmAddPost(@ModelAttribute PostBindingModel post, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {

            postService.addPost(this.modelMapper.map(post, PostServiceModel.class), (Long) session.getAttribute("id"));
            modelAndView.setViewName("redirect:/");
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
    public ModelAndView updateConfirm(@ModelAttribute PostBindingModel post,@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session){

        postService.updatePost(id,this.modelMapper.map(post, PostServiceModel.class), (Long) session.getAttribute("id"));
        modelAndView.setViewName("redirect:/profile");
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

    @GetMapping("/print/like/{id}")
    public ModelAndView like(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            postService.addLike(id);
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.addObject("comments", commentService.getAllComments(id));
            modelAndView.setViewName("print");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping("/print/dislike/{id}")
    public ModelAndView dislike(@PathVariable(name = "id") Long id,ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {

            postService.addDislike(id);
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.addObject("comments", commentService.getAllComments(id));
            modelAndView.setViewName("print");

        }else
        {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }
}
