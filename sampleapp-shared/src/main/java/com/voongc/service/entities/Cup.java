package com.voongc.service.entities;

import com.google.gwt.user.client.rpc.IsSerializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
