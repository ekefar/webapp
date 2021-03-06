package com.teamdev.webapp1.model.product;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PRODUCTS")
@JsonAutoDetect
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    @Size(min = 2)
    @NotEmpty
    private String name;

    @Lob
    @Column(name = "ICON")
    private byte[] icon;

    @ManyToOne
    @Valid
    private Unit unit;

    @ManyToOne
    @Valid
    private Category category;


    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Unit unit, Category category) {
        this.name = name;
        this.unit = unit;
        this.category = category;
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Product && this.getName().equals(((Product) obj).getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
