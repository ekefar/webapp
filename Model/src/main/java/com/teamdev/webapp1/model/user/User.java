package com.teamdev.webapp1.model.user;

import com.google.gson.annotations.Expose;
import flexjson.JSON;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private int id;

    @Column(name = "LOGIN", unique = true)
    @Expose
    private String login;

    @Column(name = "PASSWORD")
    @Expose
    private String password;

    @Column(name = "EMAIL", unique = true)
    @Expose
    private String email;

    @Column(name = "ENABLED")
    @Expose
    private boolean enabled;

    @ManyToOne
    @Expose
    private Role role;

    @Column(name = "NAME")
    @Expose
    private String name;

    @Column(name = "DATE_OF_BIRTH")
    @Expose
    private Date dateOfBirth;

    @Column(name = "SKYPE")
    @Expose
    private String skype;

    @Column(name = "HOBBY")
    @Expose
    private String hobby;

    @Lob
    @Column(name = "AVATAR")
    @Expose
    private byte[] avatar;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @JSON(include = false)
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
