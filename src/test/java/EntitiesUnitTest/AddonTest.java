package EntitiesUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class AddonTest {
    Addon addon;

    @BeforeEach
    void SetAddon_Test() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        addon = new Addon("1", "Ketchup", 12, type, true, "12345");
    }

    @Test
    void getName() {
        assertEquals(addon.getName(), "Ketchup");
    }

    @Test
    void getPrice() {
        assertEquals(addon.price, 12);
    }

    @Test
    void getAddonTypes() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(4);
        assertFalse(Arrays.equals(addon.getAddonTypes().toArray(), arr.toArray()));
    }

    @Test
    void isAvailable() {
        assertTrue(addon.isAvailable());
    }

    @Test
    void getId() {
        String id1 = "falseID";
        assertFalse(addon.id.equalsIgnoreCase(id1));
        assertSame("1", addon.id);
    }

    @Test
    void getShopId() {
        assertSame("1234", addon.shopId);
    }
}
