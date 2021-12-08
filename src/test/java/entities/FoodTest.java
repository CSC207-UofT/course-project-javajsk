package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    Food burger;

    @BeforeEach
    void setFood(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new AddonTest().addon;
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "fries", "Fries", type, selection1, true, "shop1");
        selections.add(selection1);
        Singleton[] singletons = new Singleton[1];
        singletons[0] = singleton;
        burger = new Food("123", "burger combo", "A burger combo", 20f, singletons, "shop1");
    }

    @Test
    void getId() {
        assertEquals(burger.getId(), "123");
    }

    @Test
    void getName() {
        assertTrue(burger.getName().equalsIgnoreCase("burger combo"));
    }

    @Test
    void getComponents() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new AddonTest().addon;
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "fries", "Fries", type, selection1, true, "shop1");
        Singleton[] lst = burger.getComponents();
        ArrayList<Singleton> accumulator = new ArrayList<>();
        accumulator.addAll(Arrays.asList(lst));
        assertSame(accumulator.get(0).name, "fries");
    }

    @Test
    void setComponents() {
        // TODO
    }

    @Test
    void setName() {
        burger.setName("cheeseburger");
        assertEquals("cheeseburger", burger.getName());
    }

    @Test
    void getDescription() {
        String description = burger.getDescription();
        assertEquals("A burger combo", description);
    }

    @Test
    void setDescription(){
        burger.setDescription("A new burger combo");
        assertEquals("A new burger combo", burger.getDescription());
    }

    @Test
    void getPrice() {
        float price = burger.getPrice();
        assertEquals(20f, price);
    }

    @Test
    void setPrice() {
        burger.setPrice(16f);
        assertEquals(16f, burger.getPrice());
    }

    @Test
    void setId(){
        burger.setId("00001");
        assertEquals("00001", burger.id);
    }

    @Test
    void getShopId() {
        assertEquals(burger.getShopId(), "shop1");
    }

    @Test
    void setShopId() {
        burger.setShopId("00004");
        assertEquals("00004", burger.getShopId());
    }


    @Test
    void addSingleton() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new AddonTest().addon;
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("5", 16, "Poutine", "Poutine", type, selection1, true, "shop1");
        burger.addSingleton(singleton);
        assertSame(burger.components[1], singleton);

    }

    @Test
    void isValidSelections(){
        //TODO
    }

    @Test
    void isAvailable() {
        assertTrue(burger.isAvailable());
    }

    @Test
    void foodToString(){

    }
}