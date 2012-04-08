package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String usersList ="{ aaData : " +new Gson().toJson(userManager.listUsers())+"}";
        response.getWriter().write(usersList);
    } 
}
