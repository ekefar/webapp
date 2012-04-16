package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.model.User;
import com.teamdev.webapp1.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    @Autowired
    UserManager userManager;
    
    @RequestMapping(value = "/Admin/EditUsers", method = RequestMethod.GET)
    public void listUsers(HttpServletResponse response) throws IOException {
        String usersList =new Gson().toJson(userManager.listUsers());
        response.getWriter().write(usersList);
    }


    @RequestMapping(value = "/Admin/EditUsers", method = RequestMethod.POST)
    public void updateUserInfo(HttpServletRequest request) throws IOException {

        String jsonObject = request.getReader().readLine();
        jsonObject = URLDecoder.decode(jsonObject, "UTF-8");

        User userChangesRequest =  new Gson().fromJson(jsonObject, User.class);

        User userToChange = userManager.getUserByName(userChangesRequest.getLogin());

        userToChange.setEnabled(userChangesRequest.isEnabled());
        userManager.updateUser(userToChange);

    }
}
