package UseCasesTest.Order;

import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.TestBoundaries.RAMOrderObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMOrderRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.order.usecases.CancelOrderInteractor;
import businessrules.order.usecases.CompleteOrderInteractor;
import businessrules.outputboundaries.*;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompleteOrderInteractorTest {
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Order> orderObjectBoundary;
    CompleteOrderInteractor completeOrderInteractor;
    Customer start_customer;
    Order new_order;
    Vendor vendor;

    @BeforeEach
    void setUp() {
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop = new Shop("86784", "JavaJShop", "Bay Street", true, menu, orderBook);
        vendor = new Vendor("12345", "Username", "Password", shop);
        Menu menu1 = new Menu();
        OrderBook orderBook1 = new OrderBook();
        Shop shop1 = new Shop("67676", "UofTruck", "Bahen", true, menu1, orderBook1);
        Vendor vendor1 = new Vendor("90909", "Username1", "Password1", shop1);
        vendorRepository = new RAMVendorRepository(vendor);
        vendorRepository.create(vendor1);
        repositoryBoundary = new RAMRepositoryBoundary();
        orderRepository = new RAMOrderRepository();
        new_order = createNewOrder();
        orderRepository.create(new_order);
        vendorBoundary = new RAMVendorBoundary();
        orderObjectBoundary = new RAMOrderObjectBoundary();
        completeOrderInteractor = new CompleteOrderInteractor(vendorRepository, repositoryBoundary, orderRepository,
                vendorBoundary, orderObjectBoundary);
    }

    @Test
    void successfulCompleteOrder() {
        ResponseObject responseObject = completeOrderInteractor.completeOrder("12345", "00001");
        assertEquals(Order.Status.COMPLETED, new_order.getStatus());
    }

    @Test
    void noVendorCompleteOrder() {
        ResponseObject responseObject = completeOrderInteractor.completeOrder("vendorId", "00001");
        assertEquals("No such vendor found.", responseObject.getMessage());
    }

    @Test
    void noOrderCompleteOrder() {
        ResponseObject responseObject = completeOrderInteractor.completeOrder("12345", "00000");
        assertEquals("No such order found.", responseObject.getMessage());
    }

    @Test
    void dontOwnOrder() {
        ResponseObject responseObject = completeOrderInteractor.completeOrder("90909", "00001");
        assertEquals("You do not own this order.", responseObject.getMessage());
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