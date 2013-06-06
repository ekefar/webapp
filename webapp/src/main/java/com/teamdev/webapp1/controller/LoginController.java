package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.Roles;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/welcome")
    public String welcome(Map<String, Object> model, HttpServletRequest request) {

        User user = userManager.getUser(request);
        createUserCart(user);
        model.put("user", user);

        /*SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication().getAuthorities().containsAll(AuthorityUtils.createAuthorityList(Roles.ROLE_ADMIN.name())) ){
            return "/admin/adminPage";
        }*/

        if (user.getRole().getName().equals(Roles.ROLE_ADMIN.name())) {
            return "/admin/home";
        }

        return "/user/home";
    }


    @RequestMapping(value = "/loginfailed")
    public String loginError(Map<String, Object> model) {
        model.put("showError", true);
        return "login";
    }

    @RequestMapping(value = "/logout")
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