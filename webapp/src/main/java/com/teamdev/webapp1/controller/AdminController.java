package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userManager) {
        this.userRepository = userManager;
    }

    @RequestMapping(value = "/admin/editUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> listUsers() {
        return userRepository.findAll();
    }


    @RequestMapping(value = "/admin/editUsers", method = RequestMethod.POST)
    public void updateUserInfo(@RequestParam(value = "login") String login,
                               @RequestParam(value = "enabled") boolean enabled) {

        User userToChange = userRepository.findByLogin(login);
        userToChange.setEnabled(enabled);
        userRepository.save(userToChange);
    }
}
