package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    Singleton singleton;
    List<Integer> allowedAddonTypes;
    Selection defaultSelection;

    @BeforeEach
    void setUp(){
        allowedAddonTypes = new ArrayList<Integer>();
        allowedAddonTypes.add(1);
        allowedAddonTypes.add(2);
        Addon addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");
        HashMap<Addon, Integer> singletonSelection = new HashMap<Addon, Integer>();
        singletonSelection.put(addon1, 1);
        singletonSelection.put(addon2, 2);
        defaultSelection = new Selection(singletonSelection);
        singleton = new Singleton("12345", 15f, "Cheeseburger", "Burger with cheese",
                allowedAddonTypes, defaultSelection, true, "00001");
    }

    @Test
    void getId() {
        String id = singleton.getId();
        assertEquals("12345", id);
    }

    @Test
    void setId() {
        singleton.setId("90909");
        assertEquals("90909", singleton.getId());
    }

    @Test
    void getPrice() {
        float price = singleton.getPrice();
        assertEquals(15f, price);
    }

    @Test
    void setPrice() {
        singleton.setPrice(16f);
        assertEquals(16f, singleton.getPrice());
    }

    @Test
    void getName() {
        String name = singleton.getName();
        assertEquals("Cheeseburger", name);
    }

    @Test
    void setName() {
        singleton.setName("New Cheeseburger");
        assertEquals("New Cheeseburger", singleton.getName());
    }

    @Test
    void getDescription() {
        String description = singleton.getDescription();
        assertEquals("Burger with cheese", description);
    }

    @Test
    void setDescription() {
        singleton.setDescription("All new cheeseburger!");
        assertEquals("All new cheeseburger!", singleton.getDescription());
    }

    @Test
    void getAllowedAddonTypes() {
        List<Integer> actual_allowedAddonTypes = singleton.getAllowedAddonTypes();
        assertEquals(allowedAddonTypes, actual_allowedAddonTypes);
    }

    @Test
    void setAllowedAddonTypes() {
        List<Integer> new_allowedAddonTypes = new ArrayList<Integer>();
        new_allowedAddonTypes.add(3);
        new_allowedAddonTypes.add(4);
        new_allowedAddonTypes.add(5);
        singleton.setAllowedAddonTypes(new_allowedAddonTypes);
        assertEquals(new_allowedAddonTypes, singleton.getAllowedAddonTypes());
    }

    @Test
    void getDefaultSelection() {
        Selection actual_defaultSelection = singleton.getDefaultSelection();
        assertEquals(defaultSelection, actual_defaultSelection);
    }

    @Test
    void setDefaultSelection() {
        Selection new_defaultSelection = createNewDefaultSelection();
        singleton.setDefaultSelection(new_defaultSelection);
        assertEquals(new_defaultSelection, singleton.getDefaultSelection());
    }

    @Test
    void isAvailable() {
        boolean availability = singleton.isAvailable();
        assertTrue(availability);
    }

    @Test
    void setAvailability() {
        singleton.setAvailability(false);
        assertFalse(singleton.isAvailable());
    }

    @Test
    void replace() {
        List<Integer> new_allowedAddonTypes = new ArrayList<Integer>();
        new_allowedAddonTypes.add(3);
        new_allowedAddonTypes.add(4);
        Addon addon1 = new Addon("00000", "Mushrooms", 2f,
                new_allowedAddonTypes, true, "00002");
        Addon addon2 = new Addon("00010", "Sauce", 0.6f,
                new_allowedAddonTypes, true, "00002");
        HashMap<Addon, Integer> singletonSelection = new HashMap<Addon, Integer>();
        singletonSelection.put(addon1, 3);
        singletonSelection.put(addon2, 4);
        Selection new_defaultSelection = new Selection(singletonSelection);
        Singleton new_singleton = new Singleton("10000", 16f,
                "Mushroomburger", "Burger with Mushrooms",
                new_allowedAddonTypes, new_defaultSelection, false, "00002");
        singleton.replace(new_singleton);
        assertEquals("10000", singleton.getId());
        assertEquals(16f, singleton.getPrice());
        assertEquals("Mushroomburger", singleton.getName());
        assertEquals("Burger with Mushrooms", singleton.getDescription());
        assertEquals(new_allowedAddonTypes, singleton.getAllowedAddonTypes());
        assertEquals(new_defaultSelection, singleton.getDefaultSelection());
        assertFalse(singleton.isAvailable());
        assertEquals("00002", singleton.getShopId());
    }

    @Test
    void getShopId() {
       String shopId = singleton.getShopId();
       assertEquals("00001", shopId);
    }

    @Test
    void setShopId() {
        singleton.setShopId("11111");
        assertEquals("11111", singleton.getShopId());
    }

    @Test
    void isValidSelection() {
        boolean validSelection = singleton.isValidSelection(defaultSelection);
        assertTrue(validSelection);
    }

    private Selection createNewDefaultSelection(){
        Addon addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon3 = new Addon("00010", "Sauce", 0.6f,
                allowedAddonTypes, true, "00001");
        HashMap<Addon, Integer> singletonSelection = new HashMap<Addon, Integer>();
        singletonSelection.put(addon1, 1);
        singletonSelection.put(addon2, 2);
        singletonSelection.put(addon3, 3);
        Selection new_defaultSelection = new Selection(singletonSelection);

        return new_defaultSelection;
    }

}