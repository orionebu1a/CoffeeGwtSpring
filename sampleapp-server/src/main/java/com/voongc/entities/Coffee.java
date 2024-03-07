package com.voongc.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;


@Entity
public class Coffee implements IsSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cup_value")
    private Cup cup;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grade_name")
    private Grade grade;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "type_name")
    private Type type;

    private int sugar;

    private int time;

    public Coffee() {
    }

    // All-args constructor
    public Coffee(long id, Cup cup, Grade grade, Type type, int sugar, int time) {
        this.id = id;
        this.cup = cup;
        this.grade = grade;
        this.type = type;
        this.sugar = sugar;
        this.time = time;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
