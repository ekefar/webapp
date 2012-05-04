package com.teamdev.webapp1.model.product;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private int id;

    @Column(name = "NAME")
    @Expose
    private String name;

    @Lob
    @Column(name = "ICON")
    @Expose
    private byte[] icon;

    @ManyToOne
    @Expose
    private Unit unit;

    @ManyToOne
    @Expose
    private Category category;




    public Product() {
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

}
