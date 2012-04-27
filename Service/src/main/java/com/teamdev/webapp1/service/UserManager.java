package com.teamdev.webapp1.service;

import com.teamdev.webapp1.model.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 06.04.12
 * Time: 23:42
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {

    String getLogin(HttpServletRequest request);

    User getUser(HttpServletRequest request);
}
