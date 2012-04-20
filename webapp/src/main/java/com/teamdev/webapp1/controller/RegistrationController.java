package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.dao.RoleDAO;
import com.teamdev.webapp1.model.Role;
import com.teamdev.webapp1.model.User;
import com.teamdev.webapp1.service.MailService;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private MailService mailSender;

    @Autowired
    RoleDAO roleDAO;

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String requestRegisterPage() {
        return "Signup";
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request) throws IOException {

        String jsonObject = "{" + request.getReader().readLine().replace("&", ",") + "}";
        jsonObject = URLDecoder.decode(jsonObject, "UTF-8");
        User user = new Gson().fromJson(jsonObject, User.class);

        user.setUserProfile(null);
        user.setRole(new Role(1, "ROLE_USER"));
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
