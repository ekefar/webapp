package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.Role;
import com.teamdev.webapp1.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 15.04.12
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public List<Role> listRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }

    @Override
    public Role getUserRole() {
        return (Role) sessionFactory.getCurrentSession().createQuery("from Role where name=:name").setString("name", "ROLE_USER").uniqueResult();
    }

    @Override
    public Role getAdminRole() {
        return (Role) sessionFactory.getCurrentSession().createQuery("from Role where name=:name").setString("name", "ROLE_ADMIN").uniqueResult();
    }
}
