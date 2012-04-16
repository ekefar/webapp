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
 * Date: 06.04.12
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDAO.removeUser(id);
    }

    @Override
    public void findUser(User user) {
        userDAO.findUser(user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDAO.listUsers();
    }
}
