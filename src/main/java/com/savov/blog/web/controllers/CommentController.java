package com.savov.blog.web.controllers;

import com.savov.blog.domain.model.binding.CommentBindingModel;
import com.savov.blog.domain.model.service.CommentServiceModel;
import com.savov.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addcomment/{postid}")
    public ModelAndView addComment(@ModelAttribute CommentBindingModel comment, @PathVariable(name = "postid") Long postid, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            commentService.addComments(postid, this.modelMapper.map(comment, CommentServiceModel.class), (Long) session.getAttribute("id"));
            modelAndView.setViewName(String.format("redirect:/print/%d", postid));
        }else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping("/mycomments")
    public ModelAndView myComments(ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("documents",commentService.getCommentsByUserId((Long) session.getAttribute("id")));
            int a=5;
            modelAndView.setViewName("mycomments");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

}
