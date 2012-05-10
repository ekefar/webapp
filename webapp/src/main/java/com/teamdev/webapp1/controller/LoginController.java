package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    private final UserManager userManager;
    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserManager userManager,
                           UserRepository userRepository) {
        this.userManager = userManager;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(Map<String, Object> model, HttpServletRequest request) {

        User user =  userManager.getUser(request);
        createUserCart(user);

        model.put("user", user);
        return "/user/userPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror() {
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }


    private Cart createUserCart(User user) {
        Cart cart;
        if (user.getCart() == null) {
            cart = new Cart(user);
            user.setCart(cart);
            User persistedUser = userRepository.save(user);
            cart = persistedUser.getCart();
        } else {
            cart = user.getCart();
        }

        return cart;
    }

}