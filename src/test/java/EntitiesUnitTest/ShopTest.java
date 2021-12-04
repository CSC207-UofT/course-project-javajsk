package EntitiesUnitTest;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Menu menu;
    OrderBook orderBook;
    Shop shop;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        orderBook = new OrderBook();
        shop = new Shop("00001", "JavaJShop", "Bay Street", true, menu, orderBook);
    }

    @Test
    void getId() {
        String id = shop.getId();
        assertEquals("00001", id);
    }

    @Test
    void setId() {
        shop.setId("11111");
        assertEquals("11111", shop.getId());
    }

    @Test
    void getMenu() {
       Menu actual_menu = shop.getMenu();
       assertEquals(menu, actual_menu);
    }

    @Test
    void setMenu() {
        Food burger = createFood();
        List<Integer> allowedAddonTypes = new ArrayList<Integer>();
        allowedAddonTypes.add(1);
        allowedAddonTypes.add(2);
        Addon addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");

        List<Food> foods = new ArrayList<Food>();
        foods.add(burger);
        List<Addon> addons = new ArrayList<Addon>();
        addons.add(addon1);
        addons.add(addon2);

        Menu new_menu = new Menu(foods, addons);

        shop.setMenu(new_menu);

        assertEquals(new_menu, shop.getMenu());
    }

    @Test
    void getOrderBook() {
        OrderBook actual_orderbook = shop.getOrderBook();
        assertEquals(orderBook, actual_orderbook);
    }

    @Test
    void setOrderBook() {
        OrderBook new_orderBook = createNewOrderBook();
        shop.setOrderBook(new_orderBook);

        assertEquals(new_orderBook, shop.getOrderBook());
    }

    @Test
    void getLocation() {
        String location = shop.getLocation();
        assertEquals("Bay Street", location);
    }

    @Test
    void setLocation() {
        shop.setLocation("Bahen");
        assertEquals("Bahen", shop.getLocation());
    }

    @Test
    void getName() {
        String name = shop.getName();
        assertEquals("JavaJShop", name);
    }

    @Test
    void setName() {
        shop.setName("JavaTruck");
        assertEquals("JavaTruck", shop.getName());
    }

    @Test
    void isOpen() {
        boolean availability = shop.isOpen();
        assertTrue(availability);
    }

    @Test
    void setOpen() {
        shop.setOpen(false);
        assertFalse(shop.isOpen());
    }

    private Food createFood(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("1", "Ketchup", 12, type, true, "12345");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection = new Selection(SingletonSelection);
        Singleton singleton = new Singleton("3", 10, "Cheeseburger",
                "A burger with cheese", type, selection, true, "shop1");
        Food burger = new Food("123", "Cheeseburger combo",
                "A cheeseburger with pop and fries", 15, new Singleton[]{singleton}, "shop1");

        return burger;
    }

    private OrderBook createNewOrderBook(){
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Date time_placed1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_modified1 = new Date(2020, 8, 15, 12, 2, 3);
        Date time_placed2 = new Date(2021, 6, 12, 8, 4, 0);
        Date time_modified2 = new Date(2021, 6, 12, 8, 20, 10);
        Order order1 = new Order("00001", cart1, "00002", "00000", Order.Status.COMPLETED,
                time_placed1, time_modified1);
        Order order2 = new Order("00003", cart2, "00004", "00000", Order.Status.IN_PROGRESS,
                time_placed2, time_modified2);
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderList.add(order1);
        orderList.add(order2);
        OrderBook new_orderBook = new OrderBook(orderList);
        return  new_orderBook;
    }

}