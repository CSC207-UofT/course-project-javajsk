package entities.Regular.UnitTests;


import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderBookTest {
    OrderBook orderBook;

    @BeforeEach
    void setUp(){
        HashMap<Food, List<Selection[]>> contents = new HashMap<>();
        ArrayList<Order> ordersList = new ArrayList<Order>();
        Cart new_cart = new Cart("17235", "12345", contents);
        Order new_order = new Order("10923", new_cart, "12345", "00000", "In Progress",
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2),
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2));
        ordersList.add(0, new_order);

        orderBook = new OrderBook(ordersList);
    }

    @Test
    void getOrdersList() {
        HashMap<Food, List<Selection[]>> contents = new HashMap<>();
        ArrayList<Order> expected_ordersList = new ArrayList<Order>();
        Cart new_cart = new Cart("17235", "12345", contents);
        Order new_order = new Order("10923", new_cart, "12345", "00000", "In Progress",
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2),
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2));
        expected_ordersList.add(0, new_order);

        List<Order> orderList = orderBook.getOrdersList();

        assertTrue(expected_ordersList == orderList);
    }

    @Test
    void setOrdersList() {
        HashMap<Food, List<Selection[]>> contents = new HashMap<>();
        ArrayList<Order> ordersList = new ArrayList<Order>();
        Cart new_cart = new Cart("17235", "12345", contents);
        Order new_order = new Order("10923", new_cart, "12345", "00000", "In Progress",
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2),
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2));
        Order new_order2 = new Order("109245", new_cart, "12345", "00000", "In Progress",
                new Date(2021, Calendar.SEPTEMBER, 13, 15, 14, 2),
                new Date(2021, Calendar.SEPTEMBER, 13, 15, 14, 2));
        ordersList.add(0, new_order);
        ordersList.add(1, new_order2);

        orderBook.setOrdersList(ordersList);

        assertEquals(ordersList, orderBook.getOrdersList());
    }
}