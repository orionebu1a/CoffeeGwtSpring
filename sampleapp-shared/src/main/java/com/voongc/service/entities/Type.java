package com.voongc.service.entities;

import com.google.gwt.user.client.rpc.IsSerializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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


