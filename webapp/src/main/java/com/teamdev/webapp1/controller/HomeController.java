package com.teamdev.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */

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
