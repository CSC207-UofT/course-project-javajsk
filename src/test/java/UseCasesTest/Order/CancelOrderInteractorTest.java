package UseCasesTest.Order;

import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.TestBoundaries.RAMOrderObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMOrderRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.usecases.CancelOrderInteractor;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancelOrderInteractorTest {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Order> orderObjectBoundary;
    CancelOrderInteractor cancelOrderInteractor;
    Customer start_customer;
    Order new_order;

    @BeforeEach
    void setUp() {
        start_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(start_customer);
        Customer new_customer = new Customer("20000", "Username2", "Password2");
        customerRepository.create(new_customer);
        repositoryBoundary = new RAMRepositoryBoundary();
        orderRepository = new RAMOrderRepository();
        new_order = createNewOrder();
        orderRepository.create(new_order);
        customerBoundary = new RAMCustomerBoundary();
        orderObjectBoundary = new RAMOrderObjectBoundary();
        cancelOrderInteractor = new CancelOrderInteractor(customerRepository, repositoryBoundary, orderRepository,
                customerBoundary, orderObjectBoundary);
    }


    @Test
    void successfulCancelOrder() {
        ResponseObject responseObject = cancelOrderInteractor.cancelOrder("10000", "00001");
        assertEquals(Order.Status.CANCELLED, new_order.getStatus());
    }

    @Test
    void noUserCancelOrder() {
        ResponseObject responseObject = cancelOrderInteractor.cancelOrder("00000", "00001");
        assertEquals("No such customer found.", responseObject.getMessage());
    }

    @Test
    void dontOwnOrder() {
        ResponseObject responseObject = cancelOrderInteractor.cancelOrder("20000", "00001");
        assertEquals("You do not own this order.", responseObject.getMessage());
    }

    @Test
    void noOrderFound() {
        ResponseObject responseObject = cancelOrderInteractor.cancelOrder("10000", "00000");
        assertEquals("No such order found.", responseObject.getMessage());
    }

    public Order createNewOrder() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "86784");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon, 10);
        Selection selection = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "burger ",
                "A burger", type, selection, true, "86784");
        selections.add(selection);
        Food burger = new Food("123", "burger combo",
                "A burger combo", 15, new Singleton[]{singleton}, "86784");
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        HashMap<Food, List<Selection[]>> contents = new HashMap<Food, List<Selection[]>>();
        contents.put(burger, selectionList);
        Cart cart = new Cart("cart1", "86784", contents);
        Date timePlaced = new Date(2020, 8, 15, 12, 2, 3);
        Date timeModified = new Date(2020, 8, 15, 13, 2, 3);
        Order order = new Order("00001", cart, "86784", "10000", Order.Status.IN_PROGRESS,
                timePlaced, timeModified);

        return order;
    }
}