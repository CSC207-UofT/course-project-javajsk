package com.javajsk.uoftruck.controllers;

import businessrules.order.inputboundaries.*;
import businessrules.outputboundaries.ResponseObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    CancelOrder cancelOrder;
    CompleteOrder completeOrder;
    GetNextOrder getNextOrder;
    GetShopOrders getShopOrders;
    GetUserPastOrders getUserPastOrders;
    PlaceOrder placeOrder;
    SetOrderInprogress setOrderInprogress;

    public OrderController() {
//        this.cancelOrder = cancelOrder;
//        this.completeOrder = completeOrder;
//        this.getNextOrder = getNextOrder;
//        this.getShopOrders = getShopOrders;
//        this.getUserPastOrders = getUserPastOrders;
//        this.placeOrder = placeOrder;
//        this.setOrderInprogress = setOrderInprogress;
    }

    @PutMapping("/CancelOrder/{userToken}/{orderId}")
    public ResponseObject runCancelOrder(@PathVariable String orderId, @PathVariable String userToken){
        return cancelOrder.cancelOrder(userToken, orderId);
    }

    @PutMapping("/CompleteOrder/{vendorToken}/{orderId}")
    public ResponseObject runCompleteOrder(@PathVariable String orderId, @PathVariable String vendorToken){
        return completeOrder.completeOrder(vendorToken, orderId);
    }

    @GetMapping("/GetNextOrder/{vendorToken}")
    public ResponseObject runCompleteOrder(@PathVariable String vendorToken){
        return getNextOrder.getNextOrder(vendorToken);
    }

    @GetMapping("/GetShopOrders/{vendorToken}")
    public ResponseObject runGetShopOrders(@PathVariable String vendorToken){
        return getShopOrders.getShopOrders(vendorToken);
    }

    @GetMapping("/GetUsersPastOrders/{userToken}")
    public ResponseObject runGetUsersPastOrders(@PathVariable String userToken){
        return getUserPastOrders.getUserPastOrders(userToken);
    }

    @PostMapping("/PlaceOrder/{userToken}")
    public ResponseObject runPlaceOrder(@PathVariable String userToken){
        return placeOrder.placeOrder(userToken);
    }

    @PostMapping("/SetOrderInProgress/{vendorToken}/{orderId}")
    public ResponseObject runPlaceOrder(@PathVariable String vendorToken, @PathVariable String orderId){
        return setOrderInprogress.setOrderInprogress(vendorToken, orderId);
    }

}
