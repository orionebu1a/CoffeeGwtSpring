package com.voongc.web.controller;


import com.voongc.entities.Cup;
import com.voongc.entities.Good;
import com.voongc.entities.Grade;
import com.voongc.entities.Type;
import com.voongc.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping("/type/add")
    public ResponseEntity<Type> createGrade(
            @RequestBody Type type
    ) {
        Type createdType;
        try{
            createdType = coffeeService.addType(type);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdType);
    }

    @PostMapping("/type/remove")
    public ResponseEntity<String> removeType(
            @RequestParam String typeName
    ) {
        try{
            coffeeService.removeType(typeName);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cup/refill")
    public ResponseEntity<Cup> cupRefill(
            @RequestParam float value,
            @RequestParam int amount
    ) {
        Cup cup;
        try{
            cup = coffeeService.refillCup(value, amount);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cup);
    }

    @PostMapping("/cup/add")
    public ResponseEntity<Cup> createCup(
            @RequestBody Cup cup
    ) {
        Cup createdCup;
        try{
            createdCup = coffeeService.addCup(cup);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdCup);
    }

    @PostMapping("/cup/remove")
    public ResponseEntity<String> removeCup(
            @RequestParam float value
    ) {
        try{
            coffeeService.removeCup(value);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/grade/refill")
    public ResponseEntity<Grade> gradeRefill(
            @RequestParam String gradeName,
            @RequestParam int amount
    ) {
        Grade grade;
        try{
            grade = coffeeService.refillGrade(gradeName, amount);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    @PostMapping("/grade/add")
    public ResponseEntity<Grade> createGrade(
            @RequestBody Grade grade
    ) {
        Grade createdGrade;
        try{
            createdGrade = coffeeService.addGrade(grade);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdGrade);
    }

    @PostMapping("/grade/remove")
    public ResponseEntity<String> removeGrade(
            @RequestParam String gradeName
    ) {
        try{
            coffeeService.removeGrade(gradeName);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/good/refill")
    public ResponseEntity<Good> goodRefill(
            @RequestParam String goodName,
            @RequestParam int amount
    ) {
        Good good;
        try{
            good = coffeeService.refillGood(goodName, amount);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(good);
    }

    @PostMapping("/good/add")
    public ResponseEntity<Good> createGood(
            @RequestBody Good good
    ) {
        Good createdGood;
        try{
            createdGood = coffeeService.addGood(good);
        }
        catch (IllegalAccessError e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdGood);
    }

    @PostMapping("/good/remove")
    public ResponseEntity<String> removeGood(
            @RequestParam String goodName
    ) {
        try{
            coffeeService.removeGood(goodName);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
