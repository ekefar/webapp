package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
