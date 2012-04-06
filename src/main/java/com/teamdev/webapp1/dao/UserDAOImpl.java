package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.TransactionManager;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUser(int id) {
        return (User)sessionFactory.getCurrentSession().load(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User findUser(User user) {
        int userId =(Integer) sessionFactory.getCurrentSession().getIdentifier(user);
        return getUser(userId);
    }

    @Override
    public void removeUser(int id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
