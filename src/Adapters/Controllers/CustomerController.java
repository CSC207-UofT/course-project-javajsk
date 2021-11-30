package Adapters.Controllers;

import Adapters.JSONParser;
import UseCases.Customer.GetOrderHistoryInputBoundary;
import org.json.JSONObject;

public class CustomerController {
    GetOrderHistoryInputBoundary getOrderHistoryInputBoundary;
    JSONParser parser;

    public CustomerController(GetOrderHistoryInputBoundary getOrderHistoryInputBoundary, JSONParser parser){
        this.getOrderHistoryInputBoundary = getOrderHistoryInputBoundary;
        this.parser = parser;
    }

    public void runGetOrderHistory(String input){
        JSONObject data = this.parser.parse(input);
        String customerId = data.getString("customerId");
        this.getOrderHistoryInputBoundary.getOrderHistory(customerId);
    }
}
