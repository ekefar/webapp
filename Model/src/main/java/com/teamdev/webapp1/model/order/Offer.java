package com.teamdev.webapp1.model.order;

import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.model.user.User;

import javax.persistence.*;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Entity
@Table(name = "OFFERS")
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;

    @Column(name = "PRICE", nullable = false)
    private double price;

    public Offer() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
