package com.teamdev.webapp1.service;

import com.teamdev.webapp1.model.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public interface UserRegistrationService {

    public void addUser(User user);

    public List<User> listUsers();

    public void removeUser(Integer id);
    
    public void activateUser(String activationKey);
    
    public void requestActivation(User user);
}
