package com.teamdev.webapp1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */

@NoRepositoryBean
public class UpdatableRepositoryImpl<T> implements UpdatableRepository<T> {

    private final EntityManager entityManager;

    @Autowired
    public UpdatableRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T updateEntity(T entity) {
       return entityManager.merge(entity);
    }
}
