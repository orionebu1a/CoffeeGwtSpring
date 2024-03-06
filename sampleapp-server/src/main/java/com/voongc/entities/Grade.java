package com.voongc.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Grade implements IsSerializable {
    @Id
    private String name;
    private int roast;
    private int balance;
    public Grade() {
    }

    // All-args constructor
    public Grade(String name, int roast, int balance) {
        this.name = name;
        this.roast = roast;
        this.balance = balance;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoast() {
        return roast;
    }

    public void setRoast(int roast) {
        this.roast = roast;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

