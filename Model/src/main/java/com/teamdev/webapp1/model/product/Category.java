package com.teamdev.webapp1.model.product;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true)
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;


    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
