package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {
    HashMap<Addon, Integer> singletonSelection;
    Selection selection;
    Addon addon1;
    Addon addon2;

    @BeforeEach
    void setUp() {
        List<Integer> allowedAddonTypes = new ArrayList<Integer>();
        allowedAddonTypes.add(1);
        allowedAddonTypes.add(2);
        addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");
        singletonSelection = new HashMap<Addon, Integer>();
        singletonSelection.put(addon1, 1);
        singletonSelection.put(addon2, 2);
        selection = new Selection(singletonSelection);
    }

    @Test
    void getSingletonSelection() {
        HashMap<Addon, Integer> actual_singleSelection = selection.getSingletonSelection();
        assertEquals(singletonSelection, actual_singleSelection);
    }

    @Test
    void setSingletonSelection() {
        HashMap<Addon, Integer> new_singletonSelection = createNewSingletonSelection();

        selection.setSingletonSelection(new_singletonSelection);
        assertEquals(new_singletonSelection, selection.getSingletonSelection());
    }

    @Test
    void getSelectedAddons() {
        Set<Addon> selectedAddons = selection.getSelectedAddons();
        assertEquals(2, selectedAddons.size());
        assertTrue(selectedAddons.contains(addon1));
        assertTrue(selectedAddons.contains(addon2));
    }

    private HashMap<Addon, Integer> createNewSingletonSelection(){
        List<Integer> allowedAddonTypes = new ArrayList<Integer>();
        allowedAddonTypes.add(1);
        allowedAddonTypes.add(2);
        Addon addon1 = new Addon("00000", "Lettuce", 0.5f,
                allowedAddonTypes, true, "00001");
        Addon addon2 = new Addon("00003", "Tomato", 0.5f,
                allowedAddonTypes, true, "00001");
        HashMap<Addon, Integer> new_singletonSelection = new HashMap<Addon, Integer>();
        new_singletonSelection.put(addon1, 1);
        new_singletonSelection.put(addon2, 2);

        return new_singletonSelection;
    }
}