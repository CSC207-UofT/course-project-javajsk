package entities.Regular.UnitTests;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendorTest {
    Shop shop;
    Menu menu;
    OrderBook orderBook;
    Vendor vendor;

    @BeforeEach
    void setUp() {
        ArrayList<Food> foods = new ArrayList<Food>();
        ArrayList<Addon> addons = new ArrayList<Addon>();
        ArrayList<Order> ordersList = new ArrayList<Order>();
        menu = new Menu(foods, addons);
        orderBook = new OrderBook(ordersList);
        shop = new Shop("12345", menu, orderBook, "UOFT", "JavaJSK", true);
        vendor = new Vendor("11111", "Username", "Password", shop);
    }

    @Test
    void getShop() {
       Shop new_shop = vendor.getShop();
       assertEquals(new_shop, shop);
    }

    @Test
    void setShop() {
        Shop new_shop = new Shop("12121", menu, orderBook, "Bay Street", "NewTruck", true);
        vendor.setShop(new_shop);
        assertEquals(vendor.getShop(), new_shop);
    }
}