package com.teamdev.webapp1.service;

import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Service
public class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;

    @Autowired
    public UserManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getLogin(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    @Override
    public User getUser(HttpServletRequest request) {
        String login = getLogin(request);
        return userRepository.findByLogin(login);
    }

}
