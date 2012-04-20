package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.model.User;
import com.teamdev.webapp1.model.UserProfile;
import com.teamdev.webapp1.service.UserManager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 07.04.12
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/Settings")
public class UserSettingsController {

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/Account", method = RequestMethod.GET)
    public String showAccountSettings() {
        return "/Settings/Account";
    }

    @RequestMapping(value = "/Profile", method = RequestMethod.GET)
    public void getProfileSettings(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = getUser(request);
        UserProfile userProfile = user.getUserProfile();
        String userInfo = new Gson().toJson(userProfile);
        response.getWriter().write(userInfo);

    }

    @RequestMapping(value = "/Profile", method = RequestMethod.POST)
    public void saveProfileSettings(HttpServletRequest request) throws IOException {

        String jsonObject = "{" + request.getReader().readLine().replace("&", ",") + "}";
        jsonObject = URLDecoder.decode(jsonObject, "UTF-8");

        UserProfile userProfile = new Gson().fromJson(jsonObject, UserProfile.class);

        User user = getUser(request);

        if (user.getUserProfile() != null)
            userProfile.setId(user.getUserProfile().getId());

        user.setUserProfile(userProfile);
        userManager.updateUser(user);
    }

    @RequestMapping(value = "/Profile/avatar", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getUserAvatar(HttpServletRequest request) {
        return getUser(request).getUserProfile().getAvatar();
    }

    @ResponseBody
    @RequestMapping(value = "/Profile/avatar", method = RequestMethod.POST)
    public String setUserAvatar(HttpServletRequest request) throws IOException {

        User user = getUser(request);

        InputStream inputStream = request.getInputStream();

        user.getUserProfile().setAvatar(IOUtils.toByteArray(inputStream));

        userManager.updateUser(user);

        return "{'success':true}";
    }


    private User getUser(HttpServletRequest request) {
        String userName = request.getUserPrincipal().getName();
        return userManager.getUserByName(userName);
    }


}
