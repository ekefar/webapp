package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.model.Role;
import com.teamdev.webapp1.model.User;
import com.teamdev.webapp1.service.MailService;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 02.04.12
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RegistrationController {
    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    MailService mailSender;

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String requestRegisterPage(){
        return "Signup";
    }
    
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonObject ="{" +req.getReader().readLine().replace("&",",")+"}";
        User user =  new Gson().fromJson(jsonObject, User.class);
        user.setRole(new Role(1,"user"));
        user.setEnabled(true);
        userRegistrationService.addUser(user);

        mailSender.Send(user.getEmail(), "ekefar@gmail.com", "Registration success", "Welcome, "+user.getLogin()+"!");

        return "Welcome";
    }
}
