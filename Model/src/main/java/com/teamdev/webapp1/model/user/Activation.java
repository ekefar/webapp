package com.teamdev.webapp1.model.user;

import javax.persistence.*;


@Entity
@Table(name = "ACTIVATIONS")
public class Activation {

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ACTIVATION_KEY")
    private String activationKey;

    @OneToOne
    private User user;

    public Activation() {
    }

    public Activation(String activationKey) {
        this.activationKey = activationKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }
}
