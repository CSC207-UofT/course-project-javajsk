package UnitTests;
import entities.Addon;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONObject;
import static org.junit.Assert.*;
public class AddonTest {
    Addon setAddon(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        return new Addon("1", "Ketchup", 12, type, true);
    }

    @Test
    public void getName() {
        Addon addon = setAddon();
        assertEquals(addon.getName(), "Ketchup");



    }
    @Test
    public void getPrice() {
        Addon addon = setAddon();
        assertEquals(12, addon.getPrice(), 0.0);

    }

    @Test
    public void getAddonTypes() {
        Addon addon = setAddon();
        assertEquals(addon.getAddonTypes(), "[1]");
    }

    @Test
    public void isAvailable() {
        Addon addon = setAddon();
        assertTrue(addon.isAvailable());

    }

    @Test
    public void getId() {
        Addon addon = setAddon();
        assert Objects.equals(addon.id, "1");
    }
}