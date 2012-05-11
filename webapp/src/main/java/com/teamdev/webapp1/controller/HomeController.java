package com.teamdev.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping({"/index", "/"})
    public String indexRequest() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @RequestMapping("/editUsers")
    public String editUsers() {
        return "editUsers";
    }


}
