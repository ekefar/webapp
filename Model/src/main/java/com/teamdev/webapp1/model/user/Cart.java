package com.teamdev.webapp1.model.user;

import com.teamdev.webapp1.model.order.Offer;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private Integer id;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Offer, Integer> purchase;

    public Cart() {
        purchase = new HashMap<Offer, Integer>();
    }

    public Cart(Offer offer, Integer amount) {
        purchase = new HashMap<Offer, Integer>();
        purchase.put(offer, amount);
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

    public Map<Offer, Integer> getPurchase() {
        return purchase;
    }

    public void setPurchase(Map<Offer, Integer> purchase) {
        this.purchase = purchase;
    }

    public void add(Offer offer, Integer amount){
        purchase.put(offer, amount);
    }
}
