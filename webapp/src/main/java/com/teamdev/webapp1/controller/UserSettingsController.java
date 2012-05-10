package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/settings")
public class UserSettingsController {

    private final UserRepository userRepository;


    @Autowired
    public UserSettingsController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable("id") Integer userId,
                              Map<String, Object> model) {
        model.put("user", userRepository.findOne(userId));
        return "userProfile";
    }

    @RequestMapping(value = "/profile/refresh/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User refreshProfile(@PathVariable("id") Integer userId) {
        return userRepository.findOne(userId);
    }


    @RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String saveProfileSettings(@PathVariable("id") Integer userId,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "description") String description,
                                      @RequestParam(value = "contact") String contact) {

        final User user = userRepository.findOne(userId);
        user.setCompanyName(name);
        user.setCompanyDescription(description);
        user.setCompanyContact(contact);
        userRepository.save(user);
        return String.valueOf(HttpServletResponse.SC_OK);
    }

}
