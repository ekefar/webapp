package com.teamdev.webapp1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 03.04.12
 * Time: 0:51
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void Send(String to, String from, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setFrom(from);
        msg.setText(body);
        mailSender.send(msg);
    }
}

