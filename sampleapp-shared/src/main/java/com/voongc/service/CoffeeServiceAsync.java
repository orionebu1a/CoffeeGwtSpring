package com.voongc.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voongc.service.entities.*;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CoffeeServiceAsync {

    void refillGrade(String gradeName, int count, AsyncCallback<Grade> callback)throws IllegalArgumentException;

    void refillCup(float cupValue, int count, AsyncCallback<Cup> callback)throws IllegalArgumentException;

    void refillGood(String goodName, int count, AsyncCallback<Good> callback)throws IllegalArgumentException;

    void addType(Type type, AsyncCallback<Type> callback)throws IllegalArgumentException;

    void addCup(Cup cup, AsyncCallback<Cup> callback)throws IllegalArgumentException;

    void addGrade(Grade grade, AsyncCallback<Grade> callback)throws IllegalArgumentException;

    void addGood(Good good, AsyncCallback<Good> callback)throws IllegalArgumentException;

    void findOrAnyType(String typeName, AsyncCallback<Type> callback)throws IllegalArgumentException;

    void findOrAnyGrade(String gradeName, AsyncCallback<Grade> callback)throws IllegalArgumentException;

    void findOrAnyCup(String cupName, AsyncCallback<Cup> callback)throws IllegalArgumentException;

    void makeCoffee(String typeName, String gradeName, String cupName, int sugarAmount, AsyncCallback<Coffee> callback)throws IllegalArgumentException;

    void removeGood(String goodName, AsyncCallback<String> callback)throws IllegalArgumentException;

    void removeGrade(String gradeName, AsyncCallback<String> callback)throws IllegalArgumentException;

    void removeCup(float value, AsyncCallback<String> callback)throws IllegalArgumentException;

    void removeType(String typeName, AsyncCallback<String> callback)throws IllegalArgumentException;
}