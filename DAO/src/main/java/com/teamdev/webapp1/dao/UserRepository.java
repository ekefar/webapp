package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 19.04.12
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */

public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

    User findByLogin(String login);

    User findByEmail(String email);

    @Override
    Page<User> findAll(Pageable pageable);

    List<User> findAll();
}
