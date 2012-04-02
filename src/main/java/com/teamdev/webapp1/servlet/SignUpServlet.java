package com.teamdev.webapp1.servlet;

import com.google.gson.Gson;
import com.teamdev.webapp1.model.User;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class SignUpServlet extends HttpServlet {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        User user = new User(1, "Luke", "IAmYourFather", "Luke@gmail.com");
        resp.getWriter().write(gson.toJson(user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonObject ="{" +req.getReader().readLine().replace("&",",")+"}";
        User user =  new Gson().fromJson(jsonObject, User.class);
        userRegistrationService.addUser(user);
    }
}
