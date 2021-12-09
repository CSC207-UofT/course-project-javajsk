package UseCasesTest.Order;

import UseCasesTest.TestBoundaries.RAMOrderObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMOrderRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.usecases.GetUserPastOrdersInteractor;
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

class GetUserPastOrdersInteractorTest {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    ObjectBoundary<Order> orderObjectBoundary;
    GetUserPastOrdersInteractor getUserPastOrdersInteractor;
    Customer start_customer;
    Order new_order;

    @BeforeEach
    void setUp() {
        start_customer = new Customer("10000", "Username1", "Password1");
        customerRepository = new RAMCustomerRepository(start_customer);
        Customer new_customer = new Customer("20000", "Username2", "Password2");
        customerRepository.create(new_customer);
        new_order = createNewOrder();
        repositoryBoundary = new RAMRepositoryBoundary();
        orderRepository = new RAMOrderRepository();
        orderRepository.create(new_order);
        Order new_order2 = createNewOrder2();
        orderRepository.create(new_order2);
        orderObjectBoundary = new RAMOrderObjectBoundary();
        getUserPastOrdersInteractor = new GetUserPastOrdersInteractor(customerRepository, repositoryBoundary,
                orderRepository, orderObjectBoundary);
    }

    @Test
    void noUser() {
        ResponseObject responseObject = getUserPastOrdersInteractor.getUserPastOrders("30000");
        assertEquals("No such user found.", responseObject.getMessage());
    }

    @Test
    void getUserPastOrders() {
        ResponseObject responseObject = getUserPastOrdersInteractor.getUserPastOrders("10000");
        List<Order> orderList = (List<Order>) responseObject.getContents();
        assertEquals(1, orderList.size());
        assertTrue(orderList.contains(new_order));
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

    public Order createNewOrder2() {
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
        Order order = new Order("00001", cart, "86784", "20000", Order.Status.IN_PROGRESS,
                timePlaced, timeModified);

        return order;
    }
}