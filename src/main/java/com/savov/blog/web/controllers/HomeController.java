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
import com.savov.blog.web.RestControllers.TestControllers;
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
    public HomeController(PostRepository postRepository, TestControllers testControllers, PostService postService, CommentService commentService, ReactionService reactionService, UserService userService) {
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
    @GetMapping("/print/like/{id}")
    public ModelAndView like(@PathVariable(name = "id") Long id,ModelAndView modelAndView,HttpSession session) {
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

    @GetMapping("/addpost")
    public ModelAndView addPost(ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.setViewName("addpost");
        }else{
            modelAndView.setViewName("redirect:/login");
        }


        return modelAndView;
    }

    @PostMapping("/addpost")
    public ModelAndView confirmAddPost(@ModelAttribute Post post, ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {

            postService.addPost(post, (Long) session.getAttribute("id"));
            modelAndView.setViewName("redirect:/");
        }else{
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    @PostMapping("/addcomment/{postid}")
    public ModelAndView addComment(@ModelAttribute Comment comment, @PathVariable(name = "postid") Long postid, ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {
            commentService.addComments(postid, comment, (Long) session.getAttribute("id"));
            modelAndView.setViewName(String.format("redirect:/print/%d", postid));
        }else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {

            modelAndView.setViewName("login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel, ModelAndView modelAndView,HttpSession session){

        User user = userService.loginUser(userLoginBindingModel);
        if(user!=null){
            session.setAttribute("id",user.getId());
            session.setAttribute("username",user.getUsername());
            modelAndView.setViewName("redirect:/");
        }else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView register(ModelAndView modelAndView) {

        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registerConfirm(@ModelAttribute User user, ModelAndView modelAndView){
        userService.addUser(user);

        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpSession session){
        if(session.getAttribute("username")!=null) {
            session.invalidate();
        }
        modelAndView.setViewName("redirect:/login");

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
