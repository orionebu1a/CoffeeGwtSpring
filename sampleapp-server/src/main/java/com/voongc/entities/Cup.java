package com.voongc.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Cup implements IsSerializable {
    @Id
    private float value;
    private int balance;

    public Cup() {
    }

    // All-args constructor
    public Cup(float value, int balance) {
        this.value = value;
        this.balance = balance;
    }

    // Getters and Setters

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
