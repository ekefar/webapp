package com.teamdev.webapp1.service;

import com.teamdev.webapp1.dao.RoleDAO;
import com.teamdev.webapp1.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 15.04.12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Context refreshed !@#!@#");
        
        if (roleDAO.getUserRole() == null ){
            roleDAO.addRole(new Role("ROLE_USER"));
        }

        if(roleDAO.getAdminRole() == null){
            roleDAO.addRole(new Role("ROLE_ADMIN"));
        }
    }
}
