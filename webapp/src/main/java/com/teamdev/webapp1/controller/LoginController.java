package com.teamdev.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class LoginController {


    @RequestMapping(value = "/Welcome", method = RequestMethod.GET)
    public String printWelcome(Map<String, Object> model) {
        String userName = "asdad";
        model.put("userName", userName);
        return "Welcome";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "Login";
    }

    @RequestMapping(value = "/Loginfailed", method = RequestMethod.GET)
    public String loginerror() {
        return "Login";

    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String logout() {
        return "Login";
    }

}