package com.teamdev.webapp1.model.order;

import com.google.gson.annotations.Expose;
import com.teamdev.webapp1.model.user.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Entity
@Table(name = "ORDERS")
public class Order {


    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private Integer id;

    @ManyToOne
    @Expose
    @Valid
    private User customer;

    @Column(name = "CREATION_DATE")
    @Expose
    private Date creationDate;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    @Expose
    private OrderStates state;

    @ManyToOne
    @Valid
    private Offer offer;

    @Column(name = "AMOUNT")
    @Min(1)
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public OrderStates getState() {
        return state;
    }

    public void setState(OrderStates state) {
        this.state = state;
    }
}
