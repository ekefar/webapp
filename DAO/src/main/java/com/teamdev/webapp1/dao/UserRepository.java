package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

    User findByLogin(String login);

    User findByEmail(String email);

    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where u.login like :login")
    Page<User> searchByLogin(@Param("login") String login, Pageable pageable);

    List<User> findAll();
}
