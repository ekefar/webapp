package com.teamdev.webapp1.service;

import com.teamdev.webapp1.dao.ActivationRepository;
import com.teamdev.webapp1.model.user.Activation;
import com.teamdev.webapp1.model.user.User;
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

    private final UserManager userManager;
    private final MailService mailSender;
    private final ActivationRepository activationRepository;

    @Autowired
    public UserRegistrationServiceImpl(UserManager userManager,
                                       MailService mailSender,
                                       ActivationRepository activationRepository) {
        this.userManager = userManager;
        this.mailSender = mailSender;
        this.activationRepository = activationRepository;
    }

    @Override
    @Transactional
    public void activateUser(String activationKey) {
        Activation activation = activationRepository.findByActivationKey(activationKey);

        if (activation != null) {
            User user = activation.getUser();
            user.setEnabled(true);
            userManager.update(user);
            activationRepository.delete(activation);
            sendActivationSuccessLetter(user);
        }
    }

    @Override
    @Transactional
    public void requestActivation(User user) {
        User userToRequest = userManager.save(user);

        Activation userActivation = new Activation(createUserActivationKey(userToRequest));
        userActivation.setUser(userToRequest);
        activationRepository.save(userActivation);

        sendConfirmationLetter(userToRequest);
    }


    public String createUserActivationKey(User user) {
        return user.getLogin();
    }

    private void sendConfirmationLetter(User user) {
        StringBuilder message = new StringBuilder();
        message.append("Welcome, " + user.getLogin() + "!");
        message.append("\n\n");
        message.append("Your activation link: ");
        message.append("http://localhost:8080/Activation/");
        message.append(createUserActivationKey(user));
        mailSender.Send(user.getEmail(), "ekefar@gmail.com", "Registration success", message.toString());
    }

    private void sendActivationSuccessLetter(User user) {
        StringBuilder message = new StringBuilder();
        message.append("Congratulations, " + user.getLogin() + "!");
        message.append("\n\n");
        message.append("Your account have been successfully activated.");
        mailSender.Send(user.getEmail(), "ekefar@gmail.com", "Registration success", message.toString());
    }


}
