package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 09.04.12
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userManager) {
        this.userRepository = userManager;
    }

    @RequestMapping(value = "/Admin/EditUsers", method = RequestMethod.GET)
    @ResponseBody
    public String listUsers() {
        return new Gson().toJson(userRepository.findAll());
    }


    @RequestMapping(value = "/Admin/EditUsers", method = RequestMethod.POST)
    public void updateUserInfo(HttpServletRequest request) throws IOException {

        String jsonObject = request.getReader().readLine();
        jsonObject = URLDecoder.decode(jsonObject, "UTF-8");

        User userChangesRequest = new Gson().fromJson(jsonObject, User.class);

        User userToChange = userRepository.findByLogin(userChangesRequest.getLogin());

        userToChange.setEnabled(userChangesRequest.isEnabled());
        userRepository.save(userToChange);

    }
}
