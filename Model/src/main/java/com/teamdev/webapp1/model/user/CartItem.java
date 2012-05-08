package com.teamdev.webapp1.model.user;

import com.google.gson.annotations.Expose;
import com.teamdev.webapp1.model.validation.AmountAcceptable;
import com.teamdev.webapp1.model.order.Offer;

import javax.persistence.*;
import javax.validation.constraints.Min;


/**
 * Author: Alexander Serebriyan
 * Date: 02.05.12
 */

@Entity
@Table(name = "CART_ITEMS")
@AmountAcceptable
public class CartItem {

    @Id
    @GeneratedValue
    @Expose
    private Integer id;

    @OneToOne
    @Expose
    private Offer offer;

    @Column(name = "AMOUNT")
    @Expose
    @Min(1)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @Expose
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Offer offer, int amount) {
        this.offer = offer;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
