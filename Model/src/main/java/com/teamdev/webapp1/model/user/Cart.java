package com.teamdev.webapp1.model.user;

import com.google.gson.annotations.Expose;
import com.teamdev.webapp1.model.order.Offer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 30.04.12
 */

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private Integer id;

    @Column(name = "CREATION_DATE")
    @Expose
    private Date creationDate;

    @OneToMany(
            targetEntity = CartItems.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy = "cart")
    private List<CartItems> items;

    public Cart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }

    public void add(Offer offer, Integer amount) {
        items.add(new CartItems(offer, amount));
    }

    public void add(CartItems cartItems) {
        items.add(cartItems);
    }
}
