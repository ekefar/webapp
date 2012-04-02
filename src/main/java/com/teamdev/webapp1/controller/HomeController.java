package com.teamdev.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String indexRequest(Model model){
        return "Login";
    }
    
    @RequestMapping("/Signup")
    public String signUp(){
        return "Signup";
    }
    
    @RequestMapping("/EditUsers")
    public String editUsers(){
        return "EditUsers";
    }
    

            
}
