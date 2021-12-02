package entities.Regular.UnitTests;

import entities.Addon;
import entities.Singleton;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    Singleton singleton;

    @BeforeEach
    void setUp(){
        ArrayList<Integer> addonTypes = new ArrayList<Integer>();
        Addon example_addon = new Addon("12345", "Cheese", 1.50f, addonTypes, true);
        ArrayList<Integer> allowedAddonTypes = new ArrayList<Integer>();
        allowedAddonTypes.add(0, 1);
        ArrayList<Addon> defaultSelection = new ArrayList<Addon>();
        defaultSelection.add(example_addon);
        singleton = new Singleton(
                "00001",
                12.5f,
                "Cheeseburger",
                "Burger with cheese",
                allowedAddonTypes,
                defaultSelection,
                true
        );
    }

    @Test
    void getId() {
        String id = singleton.getId();
        assertEquals("00001", id);
    }

    @Test
    void setId() {
        singleton.setId("45454");
        assertEquals("45454", singleton.id);
    }

    @Test
    void getPrice() {
        float price = singleton.getPrice();
        assertEquals(12.5f, price);
    }

    @Test
    void setPrice() {
        singleton.setPrice(15f);
        assertEquals(15f, singleton.getPrice());
    }

    @Test
    void getName() {
        String name = singleton.getName();
        assertEquals("Cheeseburger", name);
    }

    @Test
    void setName() {
        singleton.setName("new_cheeseburger");
        assertEquals("new_cheeseburger", singleton.getName());
    }

    @Test
    void getDescription() {
        String description = singleton.getDescription();
        assertEquals("Burger with cheese", description);
    }

    @Test
    void setDescription() {
        singleton.setDescription("New burger with cheese");
        assertEquals("New burger with cheese", singleton.getDescription());
    }

    @Test
    void getAllowedAddonTypes() {
        List<Integer> allowedAddonTypes = singleton.getAllowedAddonTypes();
        ArrayList<Integer> AddonTypes = new ArrayList<Integer>();
        AddonTypes.add(0, 1);
        assertEquals(AddonTypes, allowedAddonTypes);
    }

    @Test
    void setAllowedAddonTypes() {
        ArrayList<Integer> allowedAddonTypes = new ArrayList<Integer>();
        allowedAddonTypes.add(0, 1);
        allowedAddonTypes.add(1, 4);
        allowedAddonTypes.add(2, 5);

        singleton.setAllowedAddonTypes(allowedAddonTypes);

        assertEquals(allowedAddonTypes, singleton.getAllowedAddonTypes());
    }

    @Test
    void getDefaultSelection() {
        ArrayList<Integer> addonTypes = new ArrayList<Integer>();
        Addon example_addon = new Addon("12345", "Cheese", 1.50f, addonTypes, true);
        ArrayList<Addon> expected_defaultSelection = new ArrayList<Addon>();
        expected_defaultSelection.add(example_addon);

        List<Addon> defaultSelection = singleton.getDefaultSelection();

        assertEquals(expected_defaultSelection, defaultSelection);
    }

    @Test
    void setDefaultSelection() {
        ArrayList<Integer> addonTypes = new ArrayList<Integer>();
        Addon example_addon = new Addon("12345", "Cheese", 1.50f, addonTypes, true);
        Addon example_addon2 = new Addon("10101", "Sauce", 0.50f, addonTypes, true);
        ArrayList<Addon> defaultSelection = new ArrayList<Addon>();
        defaultSelection.add(example_addon);
        defaultSelection.add(example_addon2);

        singleton.setDefaultSelection(defaultSelection);

        assertEquals(defaultSelection, singleton.getDefaultSelection());
    }

    @Test
    void isAvailability() {
        boolean availability = singleton.isAvailability();
        assertTrue(availability);
    }

    @Test
    void setAvailability() {
        singleton.setAvailability(false);
        assertFalse(singleton.isAvailability());
    }

    @Test
    void jsonify(){
        JSONObject jsonObject = singleton.jsonify();
        JSONObject new_json = new JSONObject();
        new_json.put("id", singleton.getId());
        new_json.put("price", singleton.getPrice());
        new_json.put("name", singleton.getName());
        new_json.put("description", singleton.getDescription());
        new_json.put("allowedAddonTypes", singleton.getAllowedAddonTypes());
        new_json.put("defaultSelection", singleton.getDefaultSelection());
        new_json.put("availability", singleton.isAvailability());

        assertEquals(new_json, jsonObject);
    }
}