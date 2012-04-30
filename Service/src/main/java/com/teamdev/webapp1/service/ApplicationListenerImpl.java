package com.teamdev.webapp1.service;

import com.teamdev.webapp1.dao.RoleRepository;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.Role;
import com.teamdev.webapp1.model.user.Roles;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 15.04.12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent> {


    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Autowired
    public ApplicationListenerImpl(RoleRepository roleRepository,
                                   UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (roleRepository.findByName(Roles.ROLE_USER.name()) == null) {
            roleRepository.save(new Role(Roles.ROLE_USER));
        }

        if (roleRepository.findByName(Roles.ROLE_ADMIN.name()) == null) {
            roleRepository.save(new Role(Roles.ROLE_ADMIN));
        }


    }


}
