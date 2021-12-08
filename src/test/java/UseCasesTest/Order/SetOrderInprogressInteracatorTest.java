package UseCasesTest.Order;

import UseCasesTest.TestBoundaries.RAMOrderObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.TestBoundaries.RAMVendorBoundary;
import UseCasesTest.daitesters.RAMOrderRepository;
import UseCasesTest.daitesters.RAMVendorRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.order.usecases.SetOrderInprogressInteracator;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetOrderInprogressInteracatorTest {
    VendorRepository vendorRepository;
    Repository<Order> orderRepository;
    RepositoryBoundary repositoryBoundary;
    VendorBoundary vendorBoundary;
    ObjectBoundary<Order> orderObjectBoundary;
    SetOrderInprogressInteracator setOrderInprogressInteracator;
    Vendor vendor;
    Order new_order;

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
        orderRepository = new RAMOrderRepository();
        new_order = createNewOrder();
        orderRepository.create(new_order);
        repositoryBoundary = new RAMRepositoryBoundary();
        vendorBoundary = new RAMVendorBoundary();
        orderObjectBoundary = new RAMOrderObjectBoundary();
        setOrderInprogressInteracator = new SetOrderInprogressInteracator(vendorRepository, orderRepository,
                repositoryBoundary, vendorBoundary, orderObjectBoundary);
    }

    @Test
    void noVendor(){
        ResponseObject responseObject = setOrderInprogressInteracator.setOrderInprogress("90000",
                "00001");
        assertEquals("No such vendor found.", responseObject.getMessage());

    }

    @Test
    void noOrder() {
        ResponseObject responseObject = setOrderInprogressInteracator.setOrderInprogress("12345",
                "90000");
        assertEquals("No such order found.", responseObject.getMessage());
    }

    @Test
    void dontOwnOrder() {
        ResponseObject responseObject = setOrderInprogressInteracator.setOrderInprogress("90909",
                "00001");
        assertEquals("You do not own this order.", responseObject.getMessage());
    }


    @Test
    void SetOrderInProgress() {
        ResponseObject responseObject = setOrderInprogressInteracator.setOrderInprogress("12345",
                "00001");
        assertEquals(Order.Status.IN_PROGRESS, new_order.getStatus());
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
        Order order = new Order("00001", cart, "86784", "10000", Order.Status.PLACED,
                timePlaced, timeModified);

        return order;
    }
}