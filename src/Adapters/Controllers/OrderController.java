package Adapters.Controllers;

import Adapters.JSONParser;
import UseCases.Orders.*;
import org.json.JSONObject;

public class OrderController {
    CreateOrderInputBoundary createOrderInputBoundary;
    SetOrderStatusInputBoundary setOrderStatusInputBoundary;
    JSONParser parser;

    public OrderController(CreateOrderInputBoundary createOrderInputBoundary,
                           SetOrderStatusInputBoundary setOrderStatusInputBoundary, JSONParser parser){
        this.parser = parser;
        this.createOrderInputBoundary = createOrderInputBoundary;
        this.setOrderStatusInputBoundary = setOrderStatusInputBoundary;
    }

    public void runCreateOrder(String input){
        JSONObject data = this.parser.parse(input);
        JSONObject cart_json = data.getJSONObject("Cart");

        JSONObject customer_json = data.getJSONObject("Customer");
        String shopId = data.getString("shopId");
        //TODO: need to build cart and customer objects to use as parameters
        //this.createOrderInputBoundary.createOrder();
    }
}
