package UseCasesTest.Customer;

import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMShopRepository;
import businessrules.customer.inputboundaries.CustomerLogin;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerLoginInteractorTest {
    RAMCustomerRepository customerRepository;
    RAMShopRepository shopRepository;

    public Customer setNewCustomer() {
        Cart currentCart =  new Cart();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Date time_placed1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_modified1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_placed2 = new Date(2021, 6, 12, 8, 4, 0);
        Date time_modified2 = new Date(2021, 6, 12, 8, 20, 10);
        Order order1 = new Order("00001", cart1, "00002", "00000", Order.Status.COMPLETED,
                time_placed1, time_modified1);
        Order order2 = new Order("00003", cart2, "00004", "00000", Order.Status.COMPLETED,
                time_placed2, time_modified2);
        List<Order> orderlist = new ArrayList<Order>();
        orderlist.add(order1);
        orderlist.add(order2);
        OrderBook orderHistory = new OrderBook(orderlist);
        Customer customer = new Customer("00000", "Username", "Password",
                orderHistory, currentCart);

        return customer;
    }

    @Test
    void login() {
        Customer test_customer = setNewCustomer();

    }
}