package com.teamdev.webapp1.service;

import com.teamdev.webapp1.dao.UserDAO;
import com.teamdev.webapp1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    @Transactional
    public void removeUser(Integer id) {
        userDAO.removeUser(id);
    }
}
