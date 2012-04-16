package com.teamdev.webapp1.model;

import javax.persistence.*;

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

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name="NAME")
    private String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Role(String name){
        this.name = name;
    }

    public Role() {
    }

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
