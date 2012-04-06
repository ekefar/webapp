package com.teamdev.webapp1.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 01.04.12
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;

    @Column(name = "LOGIN", unique = true)
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name="ENABLED")
    private boolean enabled;

    @ManyToOne
    private Role role;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {
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
}
