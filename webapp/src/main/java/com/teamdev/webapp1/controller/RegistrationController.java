package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.model.user.Role;
import com.teamdev.webapp1.model.user.Roles;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 02.04.12
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public RegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String requestRegisterPage() {
        return "Signup";
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String registerUser(@RequestParam(value = "login") String login,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "email") String email) throws IOException {


        User user = new User(login, password, email);
        user.setUserProfile(null);
        user.setRole(new Role(Roles.ROLE_USER));
        user.setEnabled(false);

        userRegistrationService.requestActivation(user);

        return "redirect:/Welcome";
    }


    @RequestMapping(value = "/Activation/{activationKey}")
    public String activateUser(@PathVariable("activationKey") String activationKey) {
        userRegistrationService.activateUser(activationKey);
        return "Welcome";
    }
}
