package com.voongc.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Type implements IsSerializable {
    @Id
    private String name;

    public Type() {
    }

    // All-args constructor
    public Type(String name) {
        this.name = name;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


