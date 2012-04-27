package com.teamdev.webapp1.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 02.04.12
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "ROLES")
public class Role {

    public Role() {
    }

    public Role(Roles roles) {
        name = roles.name();
        id = roles.getId();
    }

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
