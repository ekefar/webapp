package com.teamdev.webapp1.service;

import com.teamdev.webapp1.dao.ActivationDAO;
import com.teamdev.webapp1.dao.UserDAO;
import com.teamdev.webapp1.model.Activation;
import com.teamdev.webapp1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MailService mailSender;

    @Autowired
    private ActivationDAO activationDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void activateUser(String activationKey) {
        Activation activation = activationDAO.getActivationByKey(activationKey);

        if (activation != null) {
            User user = activation.getUser();
            user.setEnabled(true);
            userDAO.updateUser(user);
            activationDAO.removeActivation(activation);

            sendActivationSuccessLetter(user);
        }
    }

    @Override
    @Transactional
    public void requestActivation(User user) {
        userDAO.addUser(user);

        Activation userActivation = new Activation(createUserActivationKey(user));
        userActivation.setUser(user);
        activationDAO.addActivation(userActivation);

        sendConfirmationLetter(user);
    }

    public String createUserActivationKey(User user) {
        return user.getLogin();
    }
    
    private void sendConfirmationLetter(User user){
        StringBuilder message = new StringBuilder();
        message.append("Welcome, " + user.getLogin() + "!");
        message.append("\n\n");
        message.append("Your activation link: ");
        message.append("http://localhost:8080/Activation/");
        message.append(createUserActivationKey(user));
        mailSender.Send(user.getEmail(), "ekefar@gmail.com", "Registration success", message.toString());
    }

    private void sendActivationSuccessLetter(User user){
        StringBuilder message = new StringBuilder();
        message.append("Congratulations, " + user.getLogin() + "!");
        message.append("\n\n");
        message.append("Your account have been successfully activated.");
        mailSender.Send(user.getEmail(), "ekefar@gmail.com", "Registration success", message.toString());
    }
    
    
}
