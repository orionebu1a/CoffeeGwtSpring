package com.voongc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.voongc.DTO.CoffeeDto;


public class GwtCoffeeService {
    public interface CoffeeCallback {
        void onSuccess(CoffeeDto coffeeDto);

        void onFailure(Throwable throwable);
    }

    public void makeCoffee(String type, String grade, String size, int sugarAmount, CoffeeCallback callback){
        Response gwtResponse;
        String path = "api/client/make";
        StringBuilder urlBuilder = new StringBuilder(GWT.getHostPageBaseURL() + path);
        urlBuilder.append("?type=").append(URL.encodeQueryString(type));
        urlBuilder.append("&grade=").append(URL.encodeQueryString(grade));
        urlBuilder.append("&size=").append(URL.encodeQueryString(size));
        urlBuilder.append("&sugarAmount=").append(sugarAmount);

        String actionUrl = urlBuilder.toString();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, actionUrl);
        requestBuilder.setHeader("Content-Type", "application/json");
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

    private void sendHttpRequestByGWT(String json, String path) {

    }
}
