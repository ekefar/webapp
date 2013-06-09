package com.teamdev.webapp1.model.order;

import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.model.validation.AmountAcceptable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Entity
@Table(name = "OFFERS")
@JsonAutoDetect
@AmountAcceptable
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(name = "AMOUNT", nullable = false)

    @NotNull
    private Integer amount;

    @Column(name = "PRICE", nullable = false)
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private OfferStates state;

    public Offer() {
    }

    public Offer(int id) {
        this.id = id;
    }

    public Offer(User user, Product product, Integer amount, BigDecimal price, String description) {
        this.user = user;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferStates getState() {
        return state;
    }

    public void setState(OfferStates state) {
        this.state = state;
    }
}
