package UseCasesTest.Order;

import UseCasesTest.TestBoundaries.RAMCustomerBoundary;
import UseCasesTest.TestBoundaries.RAMOrderObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.RAMCartRepository;
import UseCasesTest.daitesters.RAMCustomerRepository;
import UseCasesTest.daitesters.RAMOrderRepository;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.order.usecases.PlaceOrderInteractor;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOrderInteractorTest {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Order> orderRepository;
    Repository<Cart> cartRepository;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Order> orderObjectBoundary;
    PlaceOrderInteractor placeOrderInteractor;
    Cart cuurentCart;

    @BeforeEach
    void setUp() {
        Customer start_customer = new Customer("10000", "Username1", "Password1");
        cuurentCart = createNewCart();
        start_customer.setCurrentCart(cuurentCart);
        customerRepository = new RAMCustomerRepository(start_customer);
        repositoryBoundary = new RAMRepositoryBoundary();
        customerBoundary = new RAMCustomerBoundary();
        orderObjectBoundary = new RAMOrderObjectBoundary();
        orderRepository = new RAMOrderRepository();
        cartRepository = new RAMCartRepository();
        placeOrderInteractor = new PlaceOrderInteractor(customerRepository, repositoryBoundary, orderRepository,
                cartRepository, customerBoundary, orderObjectBoundary);
    }

    @Test
    void successfulPlaceOrder() {

    }

    public Cart createNewCart() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "86784");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
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
        contents.put(burger,selectionList);
        Cart cart =  new Cart("cart1", "86784", contents);
        Date timePlaced = new Date(2020, 8, 15, 12, 2, 3);
        Date timeModified = new Date(2020, 8, 15, 13, 2, 3);
        Order order = new Order("00000", cart, "86784", "00001", Order.Status.IN_PROGRESS,
                timePlaced, timeModified);

        return cart;
    }
}