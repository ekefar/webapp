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

    @Column(name = "DESCRIPTION")
    private String description;

    public Offer() {
    }

    public Offer(int id) {
        this.id = id;
    }

    public Offer(User user, Product product, int amount, double price, String description) {
        this.user = user;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
