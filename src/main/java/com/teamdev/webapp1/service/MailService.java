package com.teamdev.webapp1.service;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 03.04.12
 * Time: 0:47
 * To change this template use File | Settings | File Templates.
 */
public interface MailService {
    void Send(String to, String from, String subject, String body);
}
