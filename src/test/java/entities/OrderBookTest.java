package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderBookTest {
    Order order2;
    List<Order> orderList;
    OrderBook orderBook;

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
        Cart cart1 = new Cart("cart1", "shop1", contents);
        Cart cart2 = new Cart();
        Date time_placed1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_modified1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_placed2 = new Date(2021, 6, 12, 8, 4, 0);
        Date time_modified2 = new Date(2021, 6, 12, 8, 20, 10);
        Order order1 = new Order("00001", cart1, "00002", "00000", Order.Status.COMPLETED,
                time_placed1, time_modified1);
        order2 = new Order("00003", cart2, "00004", "00000", Order.Status.IN_PROGRESS,
                time_placed2, time_modified2);
        orderList = new ArrayList<Order>();
        orderList.add(order1);
        orderList.add(order2);
        orderBook = new OrderBook(orderList);
    }

    @Test
    void getOrdersList() {
        List<Order> actual_orderList = orderBook.getOrdersList();
        assertEquals(orderList, actual_orderList);
    }

    @Test
    void setOrdersList() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Date time_placed1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_modified1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_placed2 = new Date(2021, 6, 12, 8, 4, 0);
        Date time_modified2 = new Date(2021, 6, 12, 8, 20, 10);
        Order order1 = new Order("00001", cart1, "00002", "00000", Order.Status.COMPLETED,
                time_placed1, time_modified1);
        List<Order> orderlist = new ArrayList<Order>();
        orderlist.add(order1);
        orderlist.add(order2);

        orderBook.setOrdersList(orderlist);

        assertEquals(orderlist, orderBook.getOrdersList());
    }

    @Test
    void getOrderIds() {
    }

    @Test
    void getIncompleteOrders() {
        List<Order> incomplete_order = orderBook.getIncompleteOrders();
        List<Order> expected = new ArrayList<Order>();
        expected.add(order2);
        assertEquals(expected, incomplete_order);
    }

    @Test
    void getNextOrder() {
       Order next_order = orderBook.getNextOrder();
       assertEquals(order2, next_order);
    }
}