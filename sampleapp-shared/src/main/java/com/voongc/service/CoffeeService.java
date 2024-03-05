package com.voongc.service;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voongc.service.entities.*;

@RemoteServiceRelativePath("greet")
public interface CoffeeService extends RemoteService {

    Grade refillGrade(String gradeName, int count);

    Cup refillCup(float cupValue, int count);

    Good refillGood(String goodName, int count);

    Type addType(Type type);

    Cup addCup(Cup cup);

    Grade addGrade(Grade grade);

    Good addGood(Good good);

    Type findOrAnyType(String typeName);

    Grade findOrAnyGrade(String gradeName);

    Cup findOrAnyCup(String cupName);

    Coffee makeCoffee(String typeName, String gradeName, String cupName, int sugarAmount);

    void removeGood(String goodName);

    void removeGrade(String gradeName);

    void removeCup(float value);

    void removeType(String typeName);
}
