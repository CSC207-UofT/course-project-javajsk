package entities.Regular.UnitTests;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
       assertEquals(shop, new_shop);
    }

    @Test
    void setShop() {
        Shop new_shop = new Shop("12121", menu, orderBook, "Bay Street", "NewTruck", true);
        vendor.setShop(new_shop);
        assertEquals(new_shop, vendor.getShop());
    }

    @Test
    void getId() {
       String id = vendor.getId();
       assertEquals("11111", id);
    }

    @Test
    void setId() {
        vendor.setId("99999");
        assertEquals("99999", vendor.id);
    }

    @Test
    void getUserName() {
        String username = vendor.getUserName();
        assertEquals("Username", username);
    }

    @Test
    void setUserName() {
        vendor.setUserName("new_username");
        assertEquals("new_username", vendor.getUserName());
    }

    @Test
    void getHashedPassword() {
        String password = vendor.getHashedPassword();
        assertEquals("Password", password);
    }

    @Test
    void setHashedPassword() {
        vendor.setHashedPassword("new_password");
        assertEquals("new_password", vendor.getHashedPassword());
    }
}