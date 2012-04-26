package com.teamdev.webapp1.model.product;

import javax.persistence.*;

/**
 * Author: Alexander Serebriyan
 * Date: 26.04.12
 */

@Embeddable
public class Producer {

    @Column(name = "PRODUCER")
    private String name;

    public Producer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
