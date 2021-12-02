package Adapters.controllers;

import businessrules.order.inputboundaries.CreateOrderInputBoundary;
import businessrules.order.inputboundaries.DeleteOrderInputBoundary;
import businessrules.order.inputboundaries.ReadOrderInputBoundary;
import businessrules.order.inputboundaries.UpdateOrderInputBoundary;
import org.json.JSONObject;

public class OrderController {

    CreateOrderInputBoundary createOrderInputBoundary;
    DeleteOrderInputBoundary deleteOrderInputBoundary;
    UpdateOrderInputBoundary updateOrderInputBoundary;
    ReadOrderInputBoundary readOrderInputBoundary;

    public OrderController(CreateOrderInputBoundary createOrderInputBoundary,
                          DeleteOrderInputBoundary deleteOrderInputBoundary,
                          UpdateOrderInputBoundary updateOrderInputBoundary,
                          ReadOrderInputBoundary readOrderInputBoundary){
        this.createOrderInputBoundary = createOrderInputBoundary;
        this.deleteOrderInputBoundary = deleteOrderInputBoundary;
        this.readOrderInputBoundary = readOrderInputBoundary;
        this.updateOrderInputBoundary = updateOrderInputBoundary;
    }

    public void runUpdateOrder(String input){
        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("orderId") &&
                update_data.has("orderObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String orderId = update_data.getString("orderID");
        JSONObject orderObject = update_data.getJSONObject("orderObject");


        this.updateOrderInputBoundary.updateOrder(vendorToken, orderId,orderObject);
    }

    public void runCreateOrder(String input){
        JSONObject create_data = new JSONObject(input);
        if(!create_data.has("orderID") && create_data.has("orderObject")){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_order = create_data.getJSONObject("orderObject");


        this.createOrderInputBoundary.createOrder(vendorToken,new_order);
    }


    public void runDeleteOrder(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!delete_data.has("orderID") && delete_data.has("vendorToken")){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String orderId = delete_data.getString("orderId");


        this.deleteOrderInputBoundary.deleteOrder(vendorToken,orderId);
    }

    public void runReadOrder(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("orderId")){

            //TODO:Call presenter with error message
        }
        String orderId = read_data.getString("orderId");


        this.readOrderInputBoundary.readOrder(orderId);
    }
}
