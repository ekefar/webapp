package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.Role;
import com.teamdev.webapp1.model.user.Roles;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String requestRegisterPage() {
        return "signup";
    }


    @RequestMapping("/register/email/check")
    @ResponseBody
    public String checkEmail(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        return Boolean.toString(user == null);
    }

    @RequestMapping("/register/login/check")
    @ResponseBody
    public String checkLogin(@RequestParam("login") String login) {
        User user = userRepository.findByLogin(login);
        return Boolean.toString(user == null);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam(value = "login") String login,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "email") String email) {


        User user = new User(login, password, email);
        user.setRole(new Role(Roles.ROLE_USER));
        user.setEnabled(false);

        userRegistrationService.requestActivation(user);

        return "login";
    }

    @RequestMapping(value = "/activation/{activationKey}")
    public String activateUser(@PathVariable("activationKey") String activationKey) {
        userRegistrationService.activateUser(activationKey);
        return "welcome";
    }
}
