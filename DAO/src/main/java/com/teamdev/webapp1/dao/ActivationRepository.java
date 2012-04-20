package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.Activation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 20.04.12
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public interface ActivationRepository extends CrudRepository<Activation, Integer> {
    Activation findByActivationKey(String key);
}
