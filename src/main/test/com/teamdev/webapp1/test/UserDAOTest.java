package com.teamdev.webapp1.test;


import com.teamdev.webapp1.dao.UserDAO;
import com.teamdev.webapp1.dao.UserDAOImpl;
import com.teamdev.webapp1.model.Role;
import com.teamdev.webapp1.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 07.04.12
 * Time: 7:45
 * To change this template use File | Settings | File Templates.
 */

public class UserDAOTest {

    @Autowired
    UserDAO userDAO;
    
    @Test
    public void userAddTest(){

        User user = new User();
        user.setLogin("testuser");
        user.setEnabled(true);
        user.setEmail("e@awe.com");
        user.setRole(new Role(1, "role_user"));
        
        userDAO.addUser(user);
    }
}
