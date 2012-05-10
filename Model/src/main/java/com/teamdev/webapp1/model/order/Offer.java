package com.teamdev.webapp1.model.order;

import com.google.gson.annotations.Expose;
import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.model.user.User;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Entity
@Table(name = "OFFERS")
@JsonAutoDetect
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    @Expose
    private Product product;

    @Column(name = "AMOUNT", nullable = false)
    @Expose
    @NotNull
    @Min(1)
    private Integer amount;

    @Column(name = "PRICE", nullable = false)
    @Expose
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    @Expose
    private String description;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    @Expose
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
