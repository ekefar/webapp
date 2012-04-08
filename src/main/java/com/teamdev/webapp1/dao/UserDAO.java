package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 23:46
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {

    public void addUser(User contact);

    public User getUser(int id);
    
    User getUserByName(String name);

    public void updateUser(User user);

    public List<User> listUsers();
    
    User findUser(User user);

    public void removeUser(int id);
}
