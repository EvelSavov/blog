package com.savov.blog.web.controllers;

import com.savov.blog.domain.entities.User;
import com.savov.blog.domain.model.binding.UserLoginBindingModel;
import com.savov.blog.domain.model.service.UserServiceModel;
import com.savov.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {

        modelAndView.setViewName("login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel, ModelAndView modelAndView, HttpSession session) {

        UserServiceModel userServiceModel = userService.loginUser(userLoginBindingModel);
        if (userServiceModel != null) {
            User user = this.modelMapper.map(userServiceModel, User.class);
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            modelAndView.setViewName("redirect:/");
        } else {
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
    public ModelAndView registerConfirm(@ModelAttribute User user, ModelAndView modelAndView) {
        userService.addUser(this.modelMapper.map(user, UserServiceModel.class));

        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @PostMapping("/updateUser")
    public ModelAndView updateUser(@ModelAttribute User user, ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            UserServiceModel userServiceModel = userService.updateUser(username, this.modelMapper.map(user, UserServiceModel.class));
            session.setAttribute("username", userServiceModel.getUsername());
            modelAndView.setViewName("redirect:/profile");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }


    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") != null) {
            session.invalidate();
        }
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView myProfile(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") != null) {
            modelAndView.addObject("user", userService.getUserByUsername((String) session.getAttribute("username")));
            modelAndView.setViewName("profile");
        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

}
