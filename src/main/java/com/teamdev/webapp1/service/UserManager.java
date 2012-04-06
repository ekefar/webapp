package com.teamdev.webapp1.service;

import com.teamdev.webapp1.model.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 06.04.12
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {
    
    User getUser(int id);

    void updateUser(User user);

    void addUser(User user);
    
    void removeUser(int id);
    
    void findUser(User user);
    
    public List<User> listUsers();
    
    
}
