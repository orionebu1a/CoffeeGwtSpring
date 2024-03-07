package com.voongc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.voongc.DTO.*;


public class GwtCoffeeService {
    public interface CoffeeCallback {
        void onSuccess(CoffeeDto coffeeDto);

        void onFailure(Throwable throwable);
    }

    public interface RemoveCallback {
        void onSuccess(JSONObject jsonValue);

        void onFailure(Throwable throwable);
    }

    public interface AddCallback {
        void onSuccess();

        void onFailure(Throwable throwable);
    }

    public interface RefillCallback {
        void onSuccess(JSONObject jsonValue);

        void onFailure(Throwable throwable);
    }

    public void refill(String refill, int amount, RefillCallback callback) {
        String path = "api/staff/" + refill + "/refill";
        StringBuilder urlBuilder = new StringBuilder(GWT.getHostPageBaseURL() + path);
        urlBuilder.append("?amount=").append(URL.encodeQueryString(String.valueOf(amount)));
        String actionUrl = urlBuilder.toString();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, actionUrl);
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if(response.getStatusCode() == 200){
                        String jsonResponse = response.getText();
                        JSONObject jsonValue = JSONParser.parseStrict(jsonResponse).isObject();
                        callback.onSuccess(jsonValue);
                    } else {
                        callback.onFailure(new RuntimeException("HTTP error: " + response.getStatusCode()));
                    }
                }
                @Override
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(exception);
                }
            });
        } catch (RequestException ex) {
            callback.onFailure(ex);
        }
    }



    public void addType(TypeDto type, AddCallback callback) {
        String path = "api/staff/type/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", new JSONString(type.name));
        addingRequest(callback, path, jsonObject);
    }

    public void addCup(CupDto cup, AddCallback callback) {
        String path = "api/staff/cup/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", new JSONString(String.valueOf(cup.value)));
        jsonObject.put("balance", new JSONString(String.valueOf(cup.balance)));
        addingRequest(callback, path, jsonObject);
    }

    private void addingRequest(AddCallback callback, String path, JSONObject jsonObject) {
        String actionUrl = GWT.getHostPageBaseURL() + path;
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, actionUrl);
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
            requestBuilder.sendRequest(jsonObject.toString(), new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if(response.getStatusCode() == 200){
                        callback.onSuccess();
                    } else {
                        callback.onFailure(new RuntimeException("HTTP error: " + response.getStatusCode()));
                    }
                }
                @Override
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(exception);
                }
            });
        } catch (RequestException ex) {
            callback.onFailure(ex);
        }
    }

    public void addGrade(GradeDto grade, AddCallback callback) {
        String path = "api/staff/grade/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", new JSONString(grade.name));
        jsonObject.put("roast", new JSONString(String.valueOf(grade.roast)));
        jsonObject.put("balance", new JSONString(String.valueOf(grade.balance)));
        addingRequest(callback, path, jsonObject);
    }

    public void addGood(GoodDto good, AddCallback callback) {
        String path = "api/staff/good/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", new JSONString(good.name));
        jsonObject.put("balance", new JSONString(String.valueOf(good.balance)));
        addingRequest(callback, path, jsonObject);
    }

    public void remove(String remove, String name, RemoveCallback callback) {

        String path = "api/staff/" + remove + "/remove";
        StringBuilder urlBuilder = new StringBuilder(GWT.getHostPageBaseURL() + path);
        urlBuilder.append("?name=").append(URL.encodeQueryString(name));
        String actionUrl = urlBuilder.toString();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, actionUrl);
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if(response.getStatusCode() == 200){
                        String jsonResponse = response.getText();
                        JSONObject jsonValue = JSONParser.parseStrict(jsonResponse).isObject();
                        callback.onSuccess(jsonValue);
                    } else {
                        callback.onFailure(new RuntimeException("HTTP error: " + response.getStatusCode()));
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(exception);
                }
            });
        } catch (RequestException ex) {
            callback.onFailure(ex);
        }
    }

    public void makeCoffee(String type, String grade, String size, int sugarAmount, CoffeeCallback callback){
        String path = "api/client/make";
        StringBuilder urlBuilder = new StringBuilder(GWT.getHostPageBaseURL() + path);
        urlBuilder.append("?type=").append(URL.encodeQueryString(type));
        urlBuilder.append("&grade=").append(URL.encodeQueryString(grade));
        urlBuilder.append("&size=").append(URL.encodeQueryString(size));
        urlBuilder.append("&sugarAmount=").append(sugarAmount);

        String actionUrl = urlBuilder.toString();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, actionUrl);
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if(response.getStatusCode() == 200){
                        String jsonResponse = response.getText();
                        JSONObject jsonValue = JSONParser.parseStrict(jsonResponse).isObject();
                        String type = jsonValue.get("type").isString().stringValue();
                        String grade = jsonValue.get("grade").isString().stringValue();
                        String size = jsonValue.get("size").isString().stringValue();
                        int sugar = (int)(jsonValue.get("sugar").isNumber().doubleValue());
                        CoffeeDto coffeeDto = new CoffeeDto(type, grade, size, sugar);
                        callback.onSuccess(coffeeDto);
                    } else {
                        callback.onFailure(new RuntimeException("HTTP error: " + response.getStatusCode()));
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(exception);
                }
            });
        } catch (RequestException ex) {
            callback.onFailure(ex);
        }
    }


}
