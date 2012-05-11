package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.Role;
import com.teamdev.webapp1.model.user.Roles;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String registerUser(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        user.setRole(new Role(Roles.ROLE_USER));
        user.setEnabled(false);
        encodePassword(user);

        userRegistrationService.requestActivation(user);

        return "login";
    }

    @RequestMapping(value = "/activation/{activationKey}")
    public String activateUser(@PathVariable("activationKey") String activationKey) {
        userRegistrationService.activateUser(activationKey);
        return "login";
    }

    /**
     * Encodes user`s password using MD5
     *
     * @param user user, whose password needs to be encoded
     */
    private void encodePassword(User user) {
        PasswordEncoder  encoder=new Md5PasswordEncoder();
        String hashedPassword = encoder.encodePassword(user.getPassword(), null);
        user.setPassword(hashedPassword);

    }
}
