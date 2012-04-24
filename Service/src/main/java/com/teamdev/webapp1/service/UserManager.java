package com.teamdev.webapp1.service;

import com.teamdev.webapp1.model.user.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 06.04.12
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {

    User find(int id);

    User findByLogin(String login);

    void update(User user);

    User save(User user);

    void delete(int id);

    List<User> listUsers();

}
