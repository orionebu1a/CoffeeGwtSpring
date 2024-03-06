package com.voongc.web.controller;

import com.voongc.DTO.CoffeeDto;
import com.voongc.entities.Coffee;
import com.voongc.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/make")
    public ResponseEntity<CoffeeDto> prepareCoffee(
            @RequestParam(defaultValue = "any") String type,
            @RequestParam(defaultValue = "any") String grade,
            @RequestParam(defaultValue = "any") String size,
            @RequestParam(defaultValue = "0") int sugarAmount
    ) {
        CoffeeDto coffee;
        try{
            coffee = coffeeService.makeCoffee(type, grade, size, sugarAmount);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coffee);
    }
}
