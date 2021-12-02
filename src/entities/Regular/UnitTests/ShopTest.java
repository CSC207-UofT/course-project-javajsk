package entities.Regular.UnitTests;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Menu menu;
    OrderBook orderBook;
    Shop shop;

    @BeforeEach
    void setUp(){
        ArrayList<Food> foods = new ArrayList<Food>();
        ArrayList<Addon> addons = new ArrayList<Addon>();
        ArrayList<Order> ordersList = new ArrayList<Order>();
        HashMap<Food, List<Selection[]>> contents = new HashMap<>();
        Cart new_cart = new Cart("17235", "12345", contents);
        Order new_order = new Order("10923", new_cart, "12345", "00000", "In Progress",
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2),
                new Date(2020, Calendar.SEPTEMBER, 12, 15, 12, 2));
        ordersList.add(0, new_order);
        menu = new Menu(foods, addons);
        orderBook = new OrderBook(ordersList);
        shop = new Shop("12345", menu, orderBook, "UOFT", "JavaJSK", true);
    }

    @Test
    void getId() {
        String id = shop.getId();
        assertEquals("12345", id);
    }

    @Test
    void setId() {
        shop.setId("00000");
        assertEquals("00000", shop.id);
    }

    @Test
    void getMenu() {
        Menu new_menu = shop.getMenu();
        assertEquals(menu, new_menu);
    }

    @Test
    void setMenu() {
        ArrayList<Food> foods = new ArrayList<Food>();
        ArrayList<Addon> addons = new ArrayList<Addon>();
        ArrayList<Order> ordersList = new ArrayList<Order>();
        Menu new_menu = new Menu(foods, addons);
        shop.setMenu(new_menu);

        assertEquals(new_menu, shop.getMenu());
    }

    @Test
    void getOrderBook() {
        OrderBook new_orderBook = shop.getOrderBook();
        assertEquals(orderBook, new_orderBook);
    }

    @Test
    void setOrderBook() {
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

    @Test
    void getLocation() {
        String location = shop.getLocation();
        assertEquals("UOFT", location);
    }

    @Test
    void setLocation() {
        shop.setLocation("Toronto");
        assertEquals("Toronto", shop.getLocation());
    }

    @Test
    void getName() {
        String name = shop.getName();
        assertEquals("JavaJSK", name);
    }

    @Test
    void setName() {
        shop.setName("new_shop");
        assertEquals("new_shop", shop.getName());
    }

    @Test
    void isOpen() {
        boolean open = shop.isOpen();
        assertTrue(open);
    }

    @Test
    void setOpen() {
        shop.setOpen(false);
        assertFalse(shop.isOpen());
    }

    @Test
    void jsonify() {
    }
}