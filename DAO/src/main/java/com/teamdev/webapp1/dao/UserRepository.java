package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 19.04.12
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLogin(String login);
}
