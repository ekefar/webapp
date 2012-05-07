package com.teamdev.webapp1.model.product;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Entity
@Table(name = "UNITS")
public class Unit {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true)
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    public Unit() {
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

