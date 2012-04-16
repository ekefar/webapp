package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.Activation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 07.04.12
 * Time: 0:10
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class ActivationDAOImpl implements ActivationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addActivation(Activation activation) {
        sessionFactory.getCurrentSession().save(activation);
    }

    @Override
    public Activation getActivation(int id) {
        return (Activation)sessionFactory.getCurrentSession().get(Activation.class, id);
    }

    @Override
    public Activation getActivationByKey(String activationKey) {
       return (Activation)sessionFactory.getCurrentSession().createQuery("from Activation where activation_key=:activation_key").setString("activation_key", activationKey).uniqueResult();
    }

    @Override
    public void removeActivation(Activation activation) {
        sessionFactory.getCurrentSession().delete(activation);
    }
}
