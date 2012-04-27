package com.teamdev.webapp1.model.user;

/**
 * Created with IntelliJ IDEA.
 * User: alexander.serebriyan
 * Date: 24.04.12
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */


public enum Roles {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private int id;

    private Roles(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
