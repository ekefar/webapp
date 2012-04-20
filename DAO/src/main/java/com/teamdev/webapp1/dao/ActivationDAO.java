package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.Activation;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 07.04.12
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public interface ActivationDAO {

    void addActivation(Activation activation);

    Activation getActivation(int id);

    Activation getActivationByKey(String key);

    void removeActivation(Activation activation);
}
