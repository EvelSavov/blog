package com.savov.blog.web.controllers;

import com.savov.blog.domain.entities.Comment;
import com.savov.blog.service.CommentService;
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

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addcomment/{postid}")
    public ModelAndView addComment(@ModelAttribute Comment comment, @PathVariable(name = "postid") Long postid, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            commentService.addComments(postid, comment, (Long) session.getAttribute("id"));
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
            modelAndView.setViewName("mycomments");
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

}
