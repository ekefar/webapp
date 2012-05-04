package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserManager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 07.04.12
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/settings")
public class UserSettingsController {

    private final UserRepository userRepository;
    private final UserManager userManager;

    @Autowired
    public UserSettingsController(UserRepository userRepository,
                                  UserManager userManager) {
        this.userRepository = userRepository;
        this.userManager = userManager;
    }


    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable("id") Integer userId,
                                     Map<String, Object> model) {
        model.put("user", userRepository.findOne(userId));
        return "userProfile";
    }

    @RequestMapping(value = "/profile/refresh/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String refreshProfile(@PathVariable("id") Integer userId) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userRepository.findOne(userId));
    }

    @RequestMapping(value = "/profile/avatar", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getUserAvatar(HttpServletRequest request) {
        return userManager.getUser(request).getAvatar();
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
    public String saveProfileSettings(@PathVariable ("id") Integer userId,
                                      @RequestParam(value = "name") String userName,
                                      @RequestParam(value = "dateOfBirth") Date dateOfBirth,
                                      @RequestParam(value = "skype") String skype,
                                      @RequestParam(value = "hobby") String hobby) {

        final User user = userRepository.findOne(userId);
        user.setName(userName);
        user.setDateOfBirth(dateOfBirth);
        user.setSkype(skype);
        user.setHobby(hobby);
        userRepository.save(user);

        return String.valueOf(HttpServletResponse.SC_OK);
    }

    @ResponseBody
    @RequestMapping(value = "/profile/avatar", method = RequestMethod.POST)
    public String setUserAvatar(HttpServletRequest request) throws IOException {

        final User user = userManager.getUser(request);

        final InputStream inputStream = request.getInputStream();

        user.setAvatar(IOUtils.toByteArray(inputStream));

        userRepository.save(user);

        return "{'success':true}";
    }
}
