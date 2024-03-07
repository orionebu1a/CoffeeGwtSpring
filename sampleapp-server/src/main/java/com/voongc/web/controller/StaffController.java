package com.voongc.web.controller;


import com.voongc.DTO.CupDto;
import com.voongc.DTO.GoodDto;
import com.voongc.DTO.GradeDto;
import com.voongc.DTO.TypeDto;
import com.voongc.entities.Cup;
import com.voongc.entities.Good;
import com.voongc.entities.Grade;
import com.voongc.entities.Type;
import com.voongc.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping("/type/add")
    public ResponseEntity<TypeDto> createGrade(
            @RequestBody TypeDto type
    ) {
        Type newType = new Type(type.name);
        Type createdType;
        try{
            createdType = coffeeService.addType(newType);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(type);
    }

    @Transactional
    @PostMapping("/type/remove")
    public ResponseEntity<String> removeType(
            @RequestParam String name
    ) {
        try{
            coffeeService.removeType(name);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cup/refill")
    public ResponseEntity<CupDto> cupRefill(
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
        return ResponseEntity.ok(new CupDto(cup.getValue(), cup.getBalance()));
    }

    @PostMapping("/cup/add")
    public ResponseEntity<CupDto> createCup(
            @RequestBody CupDto cup
    ) {
        Cup newCup = new Cup(cup.value, cup.balance);
        Cup createdCup;
        try{
            createdCup = coffeeService.addCup(newCup);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cup);
    }

    @Transactional
    @PostMapping("/cup/remove")
    public ResponseEntity<String> removeCup(
            @RequestParam String name
    ) {
        try{
            coffeeService.removeCup(name);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/grade/refill")
    public ResponseEntity<GradeDto> gradeRefill(
            @RequestParam String name,
            @RequestParam int amount
    ) {
        Grade grade;
        try{
            grade = coffeeService.refillGrade(name, amount);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GradeDto(grade.getName(), grade.getRoast(), grade.getBalance()));
    }

    @PostMapping("/grade/add")
    public ResponseEntity<GradeDto> createGrade(
            @RequestBody GradeDto grade
    ) {
        Grade newGrade = new Grade(grade.name, grade.roast, grade.balance);
        Grade createdGrade;
        try{
            createdGrade = coffeeService.addGrade(newGrade);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    @Transactional
    @PostMapping("/grade/remove")
    public ResponseEntity<String> removeGrade(
            @RequestParam String name
    ) {
        try{
            coffeeService.removeGrade(name);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/good/refill")
    public ResponseEntity<GoodDto> goodRefill(
            @RequestParam String name,
            @RequestParam int amount
    ) {
        Good good;
        try{
            good = coffeeService.refillGood(name, amount);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GoodDto(good.getName(), good.getBalance()));
    }

    @PostMapping("/good/add")
    public ResponseEntity<GoodDto> createGood(
            @RequestBody GoodDto good
    ) {
        Good newGood = new Good(good.name, good.balance);
        Good createdGood;
        try{
            createdGood = coffeeService.addGood(newGood);
        }
        catch (IllegalAccessError e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(good);
    }

    @Transactional
    @PostMapping("/good/remove")
    public ResponseEntity<String> removeGood(
            @RequestParam String name
    ) {
        try{
            coffeeService.removeGood(name);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
