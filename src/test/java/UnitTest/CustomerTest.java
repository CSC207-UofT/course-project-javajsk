package UnitTest;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    OrderBook orderHistory;
    Cart currentCart;
    Customer customer;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "12345");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "Cheeseburger",
                "A burger with cheese", type, selection, true, "shop1");
        selections.add(selection);
        Food burger = new Food("123", "Cheeseburger combo",
                "A cheeseburger with pop and fries", 15, new Singleton[]{singleton}, "shop1");
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        HashMap<Food, List<Selection[]>> contents = new HashMap<Food, List<Selection[]>>();
        contents.put(burger,selectionList);
        currentCart =  new Cart("cart1", "shop1", contents);
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
        orderHistory = new OrderBook(orderlist);
        customer = new Customer("00000", "Username", "Password",
                orderHistory, currentCart);
    }

    @Test
    void getOrderHistory() {
        OrderBook actual_orderHistory = customer.getOrderHistory();
        assertEquals(orderHistory, actual_orderHistory);
    }

    @Test
    void setOrderHistory() {
        OrderBook new_orderHistory = createNewOrderHistory();
        customer.setOrderHistory(new_orderHistory);

        assertEquals(new_orderHistory, customer.getOrderHistory());
    }

    @Test
    void getCurrentCart() {
        Cart actual_currentCart = customer.getCurrentCart();
        assertEquals(currentCart, actual_currentCart);
    }

    @Test
    void setCurrentCart() {
        Cart cart = createNewCart();
        customer.setCurrentCart(cart);

        assertEquals(cart, customer.getCurrentCart());

    }

    @Test
    void emptyCart() {
        customer.emptyCart();
        assertNull(customer.getCurrentCart());
    }

    private OrderBook createNewOrderHistory(){
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Date time_placed1 = new Date(2021, 12, 13, 8, 0, 3);
        Date time_modified1 = new Date(2021, 12, 13, 12, 2, 3);
        Date time_placed2 = new Date(2022, 1, 12, 8, 4, 0);
        Date time_modified2 = new Date(2022, 1, 12, 8, 4, 0);
        Order order1 = new Order("00012", cart1, "00030", "00000", Order.Status.COMPLETED,
                time_placed1, time_modified1);
        Order order2 = new Order("00013", cart2, "00004", "00000", Order.Status.COMPLETED,
                time_placed2, time_modified2);
        List<Order> orderlist = new ArrayList<Order>();
        orderlist.add(order1);
        orderlist.add(order2);
        OrderBook new_orderHistory = new OrderBook(orderlist);

        return new_orderHistory;
    }

    private Cart createNewCart(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "12345");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "burger ",
                "A burger", type, selection, true, "shop1");
        selections.add(selection);
        Food burger = new Food("123", "burger combo",
                "A burger combo", 15, new Singleton[]{singleton}, "shop1");
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        HashMap<Food, List<Selection[]>> contents = new HashMap<Food, List<Selection[]>>();
        contents.put(burger,selectionList);
        Cart cart =  new Cart("cart1", "shop1", contents);

        return cart;
    }
}