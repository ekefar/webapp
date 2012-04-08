package com.teamdev.webapp1.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 08.04.12
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "USER_PROFILES")
public class UserProfile {



    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name = "AGE")
    private byte age;

    @Column(name = "SKYPE")
    private String skype;

    @Column(name = "HOBBY")
    private String hobby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
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
}
