package com.voongc.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Good implements IsSerializable {
    @Id
    private String name;
    private int balance;
    public Good() {
    }

    // All-args constructor
    public Good(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
