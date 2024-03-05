package com.voongc.service.entities;

import com.google.gwt.user.client.rpc.IsSerializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cup implements IsSerializable {
    @Id
    private float value;
    private int balance;
}