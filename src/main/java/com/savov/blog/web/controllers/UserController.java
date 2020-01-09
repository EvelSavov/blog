package com.savov.blog.web.controllers;

import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {

        modelAndView.setViewName("login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel, ModelAndView modelAndView, HttpSession session){

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



}
