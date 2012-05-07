package com.teamdev.webapp1.model.user;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Expose
    private Integer id;

    @Column(name = "LOGIN", unique = true)
    @Expose
    @Size(min = 2, max = 12)
    private String login;

    @Column(name = "PASSWORD")
    @Expose
    @Size(min = 6, max = 12)
    private String password;

    @Column(name = "EMAIL", unique = true)
    @Expose
    @Email
    private String email;

    @Column(name = "ENABLED")
    @Expose
    private Boolean enabled;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
