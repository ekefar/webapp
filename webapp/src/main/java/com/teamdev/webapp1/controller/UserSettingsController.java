package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserManager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

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

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccountSettings() {
        return "/settings/account";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public void getProfileSettings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final User user = userManager.getUser(request);
        //final UserProfile userProfile = user.getUserProfile();
        final String userInfo = new Gson().toJson(user);
        response.getWriter().write(userInfo);
    }

    @RequestMapping(value = "/profile/avatar", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getUserAvatar(HttpServletRequest request) {
        return userManager.getUser(request).getAvatar();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public void saveProfileSettings(HttpServletRequest request,
                                    @RequestParam(value = "name") String userName,
                                    @RequestParam(value = "age") Byte age,
                                    @RequestParam(value = "skype") String skype,
                                    @RequestParam(value = "hobby") String hobby) {

        final User user = userManager.getUser(request);
        user.setName(userName);
        user.setAge(age);
        user.setSkype(skype);
        user.setHobby(hobby);

        userRepository.save(user);
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
