package com.voongc.service.entities;


import com.google.gwt.user.client.rpc.IsSerializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Coffee implements IsSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "cup_value")
    private Cup cup;

    @OneToOne
    @JoinColumn(name = "grade_name")
    private Grade grade;

    @OneToOne
    @JoinColumn(name = "type_name")
    private Type type;

    private int sugar;

    private int time;
}
