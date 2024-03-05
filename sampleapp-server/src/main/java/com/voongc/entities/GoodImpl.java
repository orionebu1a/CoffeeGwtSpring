package com.voongc.entities;

import com.test.testTask.shared.domain.Good;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class GoodImpl implements Good {
    @Id
    private String name;
    private int balance;
}
