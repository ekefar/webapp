package com.teamdev.webapp1.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */

@NoRepositoryBean
public interface UpdatableRepository<T> extends Repository<T, Integer> {

    @Modifying
    T updateEntity(T entity);
}
