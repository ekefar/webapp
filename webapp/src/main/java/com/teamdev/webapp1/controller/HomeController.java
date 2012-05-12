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

}
