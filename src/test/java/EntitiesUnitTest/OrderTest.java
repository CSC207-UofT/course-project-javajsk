package EntitiesUnitTest;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;
    Cart cart;
    Date timePlaced;
    Date timeModified;


    @BeforeEach
    void setUp() {
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
        cart =  new Cart("cart1", "86784", contents);
        timePlaced = new Date(2020, 8, 15, 12, 2, 3);
        timeModified = new Date(2020, 8, 15, 13, 2, 3);
        order = new Order("00000", cart, "86784", "00001", Order.Status.IN_PROGRESS,
                timePlaced, timeModified);
    }

    @Test
    void getId() {
        String id = order.getId();
        assertEquals("00000", id);
    }

    @Test
    void setId() {
        order.setId("11115");
        assertEquals("11115", order.getId());
    }

    @Test
    void getCart() {
        Cart actual_cart = order.getCart();
        assertEquals(cart, actual_cart);
    }

    @Test
    void setCart() {
        Cart new_cart = createNewCart();
        order.setCart(new_cart);
        assertEquals(new_cart, order.getCart());
    }

    @Test
    void getShopId() {
        String shopId = order.getShopId();
        assertEquals("86784", shopId);
    }

    @Test
    void setShopId() {
        order.setShopId("50000");
        assertEquals("50000", order.getShopId());
    }

    @Test
    void getCustomerId() {
        String customerId = order.getCustomerId();
        assertEquals("00001", customerId);
    }

    @Test
    void setCustomerId() {
        order.setCustomerId("00005");
        assertEquals("00005", order.getCustomerId());
    }

    @Test
    void getStatus() {
        Order.Status status = order.getStatus();
        assertEquals(Order.Status.IN_PROGRESS, status);
    }

    @Test
    void setStatus() {
        order.setStatus(Order.Status.COMPLETED);
        assertEquals(Order.Status.COMPLETED, order.getStatus());
    }

    @Test
    void getTimePlaced() {
        Date actual_timePlaced = order.getTimePlaced();
        assertEquals(timePlaced, actual_timePlaced);
    }

    @Test
    void setTimePlaced() {
        Date time_placed1 = new Date(2021, 11, 21, 12, 2, 3);
        order.setTimePlaced(time_placed1);
        assertEquals(time_placed1, order.getTimePlaced());
    }

    @Test
    void getTimeStatusModified() {
        Date actual_timeModified = order.getTimeStatusModified();
        assertEquals(timeModified, actual_timeModified);
    }

    @Test
    void setTimeStatusModified() {
        Date time_modified1 = new Date(2021, 11, 21, 13, 2, 3);
        order.setTimeStatusModified(time_modified1);
        assertEquals(time_modified1, order.getTimeStatusModified());
    }

    private Cart createNewCart() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("10101", "Mustard", 12f, type, true, "90000");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10f, "Cheeseburger",
                "A burger with cheese", type, selection, true, "90000");
        selections.add(selection);
        Food burger = new Food("123", "Cheeseburger combo",
                "A cheeseburger with pop and fries", 15f, new Singleton[]{singleton}, "90000");
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        HashMap<Food, List<Selection[]>> contents = new HashMap<Food, List<Selection[]>>();
        contents.put(burger,selectionList);
        Cart new_Cart =  new Cart("cart1", "90000", contents);

        return new_Cart;
    }
}