package entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class AddonTest {
    Addon addon;
    ArrayList<Integer> type;

    @BeforeEach
    void SetAddon_Test() {
        type = new ArrayList<Integer>();
        type.add(1);
        addon = new Addon("1", "Ketchup", 12, type, true, "12345");
    }

    @Test
    void setId() {
        addon.setId("123");
        assertEquals("123", addon.id);
    }

    @Test
    void getShopId() {
        assertEquals("12345", addon.getShopId());
    }

    @Test
    void setShopId() {
        addon.setShopId("shopId1");
        assertEquals("shopId1", addon.getShopId());
    }

    @Test
    void getName() {
        assertEquals(addon.getName(), "Ketchup");
    }

    @Test
    void setName(){
        addon.setName("Mustard");
        assertEquals("Mustard", addon.getName());
    }

    @Test
    void getPrice() {
        assertEquals(addon.getPrice(), 12);
    }

    @Test
    void setPrice(){
        addon.setPrice(16f);
        assertEquals(16f, addon.getPrice());
    }

    @Test
    void getAddonTypes() {
        assertEquals(type, addon.getAddonTypes());
    }

    @Test
    void setAddonTypes() {
        ArrayList<Integer> type1 = new ArrayList<Integer>();
        type1.add(1);
        type1.add(12);
        addon.setAddonTypes(type1);
        assertEquals(type1, addon.getAddonTypes());
    }

    @Test
    void isAvailable() {
        assertTrue(addon.getAvailable());
    }

    @Test
    void setAvailable(){
        addon.setAvailable(false);
        assertFalse(addon.getAvailable());
    }

    @Test
    void getId() {
        String id1 = "falseID";
        assertFalse(addon.id.equalsIgnoreCase(id1));
        assertSame("1", addon.getId());
    }


}
