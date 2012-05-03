package com.teamdev.webapp1.model.order;

import com.google.gson.annotations.Expose;
import com.teamdev.webapp1.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Entity
@Table(name="ORDERS")
public class Order {


    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private Integer id;

    @ManyToOne
    @Expose
    private User customer;

    @Column(name = "CREATION_DATE")
    @Expose
    private Date creationDate;

    @ManyToOne
    private Offer offer;

    @Column(name = "AMOUNT")
    private Integer amount;

    public Order() {
    }

    public Order(Offer offer, Integer amount) {
        this.offer = offer;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
