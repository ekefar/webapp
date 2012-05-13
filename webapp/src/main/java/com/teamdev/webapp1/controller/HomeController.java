package com.teamdev.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping({"/index", "/"})
    public String indexRequest() {
        return "home";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }


    @RequestMapping("/catalog")
    public String catalog() {
        return "catalog";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
