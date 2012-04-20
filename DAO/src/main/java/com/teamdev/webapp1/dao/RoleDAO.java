package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.Role;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 15.04.12
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDAO {

    void addRole(Role role);

    List<Role> listRoles();

    Role getUserRole();

    Role getAdminRole();
}
