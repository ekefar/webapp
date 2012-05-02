package com.teamdev.webapp1.model.user;

import com.teamdev.webapp1.model.order.Offer;

import javax.persistence.*;


/**
 * Author: Alexander Serebriyan
 * Date: 02.05.12
 */

@Entity
@Table(name = "CART_DETAILS")
public class CartDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Offer offer;

    @Column(name = "AMOUNT")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public CartDetails() {
    }

    public CartDetails(Offer offer, int amount) {
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
