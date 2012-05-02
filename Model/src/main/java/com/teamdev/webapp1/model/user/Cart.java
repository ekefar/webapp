package com.teamdev.webapp1.model.user;

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
    private Integer id;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @OneToMany(targetEntity=CartDetails.class, fetch=FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy = "cart")
    private List<CartDetails> details;

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

    public List<CartDetails> getDetails() {
        return details;
    }

    public void setDetails(List<CartDetails> details) {
        this.details = details;
    }

    public void add(Offer offer, Integer amount){
        details.add(new CartDetails(offer, amount));
    }

    public void add(CartDetails cartDetails){
        details.add(cartDetails);
    }
}
