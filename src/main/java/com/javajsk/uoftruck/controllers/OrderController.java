package com.javajsk.uoftruck.controllers;

import adapters.dam.CartDB;
import adapters.dam.CustomerDB;
import adapters.dam.OrderDB;
import adapters.dam.VendorDB;
import adapters.presenters.CustomerPresenter;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.order.inputboundaries.*;
import businessrules.order.usecases.*;
import businessrules.outputboundaries.*;
import entities.Cart;
import entities.Order;
import framework.MongoDB;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for order use cases
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    /**
     * The Cancel order input boundary.
     */
    CancelOrder cancelOrder;
    /**
     * The Complete order input boundary.
     */
    CompleteOrder completeOrder;
    /**
     * The Get next order input boundary.
     */
    GetNextOrder getNextOrder;
    /**
     * The Get shop orders input boundary.
     */
    GetShopOrders getShopOrders;
    /**
     * The Get user past orders input boundary.
     */
    GetUserPastOrders getUserPastOrders;
    /**
     * The Place order input boundary.
     */
    PlaceOrder placeOrder;
    /**
     * The Set order in progress input boundary.
     */
    SetOrderInprogress setOrderInprogress;
    /**
     * The database.
     */
    MongoDB db;
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Order repository.
     */
    Repository<Order> orderRepository;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The cart repository;
     */
    Repository<Cart> cartRepository;
    /**
     * The Customer output boundary.
     */
    CustomerBoundary customerBoundary = new CustomerPresenter();
    /**
     * The vendor output output boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();
    /**
     * The Order object output boundary.
     */
    ObjectBoundary<Order> orderObjectBoundary = new ObjectPresenter<>();

    /**
     * Instantiates a new Order controller.
     */
    public OrderController() {
        this.db = new MongoDB();
        this.customerRepository = new CustomerDB(db);
        this.orderRepository = new OrderDB(db);
        this.vendorRepository = new VendorDB(db);
        this.cancelOrder = new CancelOrderInteractor(customerRepository, repositoryBoundary,
                orderRepository, customerBoundary, orderObjectBoundary);
        this.completeOrder = new CompleteOrderInteractor(vendorRepository, repositoryBoundary,
                orderRepository, vendorBoundary, orderObjectBoundary);
        this.getNextOrder = new GetNextOrderInteractor(vendorRepository, repositoryBoundary, orderObjectBoundary);
        this.getShopOrders = new GetShopOrdersInteractor(vendorRepository, repositoryBoundary, orderObjectBoundary);
        this.getUserPastOrders = new GetUserPastOrdersInteractor(customerRepository, repositoryBoundary,
                orderRepository, orderObjectBoundary);
        this.placeOrder = new PlaceOrderInteractor(customerRepository, repositoryBoundary, orderRepository,
                cartRepository, customerBoundary, orderObjectBoundary);
        this.setOrderInprogress = new SetOrderInprogressInteracator(vendorRepository, orderRepository,
                repositoryBoundary, vendorBoundary, orderObjectBoundary);
    }

    /**
     * Runs cancel order use case.
     *
     * @param orderId   the order id
     * @param userToken the user token
     * @return response object containing data to display to user
     */
    @PutMapping("/CancelOrder/{userToken}/{orderId}")
    public ResponseObject runCancelOrder(@PathVariable String orderId, @PathVariable String userToken) {
        return cancelOrder.cancelOrder(userToken, orderId);
    }

    /**
     * Runs complete order use case.
     *
     * @param orderId     the order id
     * @param vendorToken the vendor token
     * @return response object containing data to display to user
     */
    @PutMapping("/CompleteOrder/{vendorToken}/{orderId}")
    public ResponseObject runCompleteOrder(@PathVariable String orderId, @PathVariable String vendorToken) {
        return completeOrder.completeOrder(vendorToken, orderId);
    }

    /**
     * Runs get next order use case.
     *
     * @param vendorToken the vendor token
     * @return response object containing data to display to user
     */
    @GetMapping("/GetNextOrder/{vendorToken}")
    public ResponseObject runGetNextOrder(@PathVariable String vendorToken) {
        return getNextOrder.getNextOrder(vendorToken);
    }

    /**
     * Runs get shop orders use case.
     *
     * @param vendorToken the vendor token
     * @return response object containing data to display to user
     */
    @GetMapping("/GetShopOrders/{vendorToken}")
    public ResponseObject runGetShopOrders(@PathVariable String vendorToken) {
        return getShopOrders.getShopOrders(vendorToken);
    }

    /**
     * Runs get users past orders use case.
     *
     * @param userToken the user token
     * @return response object containing data to display to user
     */
    @GetMapping("/GetUsersPastOrders/{userToken}")
    public ResponseObject runGetUsersPastOrders(@PathVariable String userToken) {
        return getUserPastOrders.getUserPastOrders(userToken);
    }

    /**
     * Runs place order use case.
     *
     * @param userToken the user token
     * @return response object containing data to display to user
     */
    @PostMapping("/PlaceOrder/{userToken}")
    public ResponseObject runPlaceOrder(@PathVariable String userToken) {
        return placeOrder.placeOrder(userToken);
    }

    /**
     * Runs set order in progress use case.
     *
     * @param vendorToken the vendor token
     * @param orderId     the order id
     * @return response object containing data to display to user
     */
    @PostMapping("/SetOrderInProgress/{vendorToken}/{orderId}")
    public ResponseObject runSetOrderInProgress(@PathVariable String vendorToken, @PathVariable String orderId) {
        return setOrderInprogress.setOrderInprogress(vendorToken, orderId);
    }
}
