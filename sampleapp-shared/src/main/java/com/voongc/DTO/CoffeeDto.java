package com.voongc.DTO;

public class CoffeeDto {
    public String type;
    public String grade;
    public String size;
    public int sugar;

    public CoffeeDto(String type, String grade, String size, int sugar) {
        this.type = type;
        this.grade = grade;
        this.size = size;
        this.sugar = sugar;
    }

    public CoffeeDto() {
    }
}
