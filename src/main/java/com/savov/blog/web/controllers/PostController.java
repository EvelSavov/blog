package com.savov.blog.web.controllers;

import com.savov.blog.domain.model.binding.PostBindingModel;
import com.savov.blog.domain.model.service.PostServiceModel;
import com.savov.blog.domain.model.service.UserServiceModel;
import com.savov.blog.service.CategoryService;
import com.savov.blog.service.CommentService;
import com.savov.blog.service.PostService;
import com.savov.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;
    private final CategoryService categoryService;
    private final UserService userServicel;

    public PostController(PostService postService, ModelMapper modelMapper, CommentService commentService, CategoryService categoryService, UserService userServicel) {
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.userServicel = userServicel;
    }


    @GetMapping("/allpost")
    public ModelAndView allPost(ModelAndView modelAndView) {
        modelAndView.addObject("posts",postService.getAll());
        modelAndView.setViewName("allpost");
        return modelAndView;
    }

    @GetMapping("/print/{id}")
    public ModelAndView getPostById(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            PostServiceModel post = postService.getPostById(id);

            modelAndView.addObject("post", post);
            UserServiceModel user = userServicel.getUserByUsername((String) session.getAttribute("username"));
            boolean a = true;
            List<PostServiceModel> likedPosts = user.getLikedPosts();
            List<PostServiceModel> dislikedPosts = user.getDislikedPosts();
            for (PostServiceModel dislikedPost : dislikedPosts) {
                if (dislikedPost.getId().equals(post.getId())) {
                    a = false;
                    break;
                }
            }
            for (PostServiceModel likedPost : likedPosts) {
                if (likedPost.getId().equals(post.getId())) {
                    a = false;
                    break;
                }
            }
            if(a) {
                modelAndView.addObject("btnVisibility", "yes");
            }else{
                modelAndView.addObject("btnVisibility", "no");
            }


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
            modelAndView.addObject("categories", categoryService.getAllCategories());
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @PostMapping("/addpost")
    public ModelAndView confirmAddPost(@ModelAttribute PostBindingModel post, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {

            postService.addPost(this.modelMapper.map(post, PostServiceModel.class), (Long) session.getAttribute("id"));
            modelAndView.setViewName("redirect:/allpost");
        }else{
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session) {
        if(session.getAttribute("username")!=null) {
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.addObject("categories",categoryService.getAllCategories());
            modelAndView.setViewName("update");

        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;

    }

    @PostMapping("/update/{id}")
    public ModelAndView updateConfirm(@ModelAttribute PostBindingModel post,@PathVariable(name = "id") Long id, ModelAndView modelAndView, HttpSession session){

        postService.updatePost(id,this.modelMapper.map(post, PostServiceModel.class), (Long) session.getAttribute("id"));
        modelAndView.setViewName("redirect:/allpost");
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
            Long userId = (Long) session.getAttribute("id");
            postService.addLike(id,userId);
            UserServiceModel user = userServicel.getUserByUsername((String) session.getAttribute("username"));
            modelAndView.addObject("user",user);
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.addObject("comments", commentService.getByPostId(id));
            modelAndView.setViewName("redirect:/print/"+id);
        }else{
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping("/print/dislike/{id}")
    public ModelAndView dislike(@PathVariable(name = "id") Long id,ModelAndView modelAndView,HttpSession session) {
        if(session.getAttribute("username")!=null) {
            Long userId = (Long) session.getAttribute("id");
            postService.addDislike(id,userId);
            UserServiceModel user = userServicel.getUserByUsername((String) session.getAttribute("username"));
            modelAndView.addObject("user",user);
            modelAndView.addObject("post", postService.getPostById(id));
            modelAndView.addObject("comments", commentService.getByPostId(id));
            modelAndView.setViewName("redirect:/print/"+id);

        }else
        {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }
}
