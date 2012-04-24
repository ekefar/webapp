package com.teamdev.webapp1.service;

import com.teamdev.webapp1.model.user.User;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public interface UserRegistrationService {

    public void requestActivation(User user);

    public void activateUser(String activationKey);


}
