package com.savov.blog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

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
