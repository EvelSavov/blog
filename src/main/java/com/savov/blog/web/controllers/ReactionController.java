package com.savov.blog.web.controllers;

import com.savov.blog.service.CommentService;
import com.savov.blog.service.PostService;
import com.savov.blog.service.ReactionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ReactionController {

    private final ReactionService reactionService;
    private final PostService postService;
    private final CommentService commentService;

    public ReactionController(ReactionService reactionService, PostService postService, CommentService commentService) {
        this.reactionService = reactionService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/print/like/{id}")
    public ModelAndView like(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            reactionService.addLike(id);
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

            reactionService.addDisike(id);
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
