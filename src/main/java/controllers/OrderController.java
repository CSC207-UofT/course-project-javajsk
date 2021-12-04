package controllers;

import businessrules.order.inputboundaries.*;
import businessrules.outputboundaries.ResponseObject;
import entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class OrderController {
    CancelOrder cancelOrder;
    CompleteOrder completeOrder;
    GetNextOrder getNextOrder;
    GetShopOrders getShopOrders;
    GetUserPastOrders getUserPastOrders;
    PlaceOrder placeOrder;
    SetOrderInprogress setOrderInprogress;

    public OrderController(CancelOrder cancelOrder, CompleteOrder completeOrder,
                           GetNextOrder getNextOrder, GetShopOrders getShopOrders,
                           GetUserPastOrders getUserPastOrders, PlaceOrder placeOrder,
                           SetOrderInprogress setOrderInprogress) {
        this.cancelOrder = cancelOrder;
        this.completeOrder = completeOrder;
        this.getNextOrder = getNextOrder;
        this.getShopOrders = getShopOrders;
        this.getUserPastOrders = getUserPastOrders;
        this.placeOrder = placeOrder;
        this.setOrderInprogress = setOrderInprogress;
    }
    @PutMapping("/CancelOrder/{userToken}/{orderId}")
    public Order runCancelOrder(@PathVariable String orderId, @PathVariable String userToken){
        ResponseObject response = cancelOrder.cancelOrder(userToken, orderId);
        return (Order) response.getContents();
    }

    @PutMapping("/CompleteOrder/{vendorToken}/{orderId}")
    public Order runCompleteOrder(@PathVariable String orderId, @PathVariable String vendorToken){
        ResponseObject response = completeOrder.completeOrder(vendorToken, orderId);
        return (Order) response.getContents();
    }

    @GetMapping("/GetNextOrder/{vendorToken}")
    public Order runCompleteOrder(@PathVariable String vendorToken){
        ResponseObject response = getNextOrder.getNextOrder(vendorToken);
        return (Order) response.getContents();
    }

    @GetMapping("/GetShopOrders/{vendorToken}")
    public Order runGetShopOrders(@PathVariable String vendorToken){
        ResponseObject response = getShopOrders.getShopOrders(vendorToken);
        return (Order) response.getContents();
    }

    @GetMapping("/GetUsersPastOrders/{userToken}")
    public Order runGetUsersPastOrders(@PathVariable String userToken){
        ResponseObject response = getUserPastOrders.getUserPastOrders(userToken);
        return (Order) response.getContents();
    }

    @PostMapping("/PlaceOrder/{userToken}")
    public Order runPlaceOrder(@PathVariable String userToken){
        ResponseObject response = placeOrder.placeOrder(userToken);
        return (Order) response.getContents();
    }

    @PostMapping("/SetOrderInProgress/{vendorToken}/{orderId}")
    public Order runPlaceOrder(@PathVariable String vendorToken, @PathVariable String orderId){
        ResponseObject response = setOrderInprogress.setOrderInprogress(vendorToken, orderId);
        return (Order) response.getContents();
    }

}
