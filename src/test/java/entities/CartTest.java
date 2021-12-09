package entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart cart;
    HashMap<Food, List<Selection[]>> contents;


    @BeforeEach
    void setCart(){

        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new AddonTest().addon;
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "burger ", "A burger", type, selection1, true, "shop1");
        selections.add(selection1);
        Food burger = new Food("123", "burger combo", "A burger combo", 15, new Singleton[]{singleton}, "shop1");
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        contents = new HashMap<Food, List<Selection[]>>();
        contents.put(burger,selectionList);
        cart =  new Cart("cart1", "shop1", contents);

    }

    @Test
    void getId() {
        assertEquals(cart.getId(), "cart1");
    }

    @Test
    void getShopId() {
        assertEquals(cart.getShopId(), "shop1");
    }

    @Test
    void getContents() {
        HashMap<Food, List<Selection[]>> contents1 = new HashMap<Food, List<Selection[]>>();
        Selection def_selection = new  Selection(new HashMap<Addon, Integer>());
        ArrayList<Selection[]> sels = new ArrayList<Selection[]>();
        Selection[] defsel = new Selection[0];
        sels.add(defsel);
        Singleton[] singletons = new Singleton[0];
        Food food = new Food("101", "notaburger", "imposterburger", 10000, singletons, "shop999" );
        contents1.put(food,sels);
        //ArrayList<Integer> intlist = new ArrayList<Integer>();
        //intlist.add(1);
        //Singleton singleton = new Singleton("3", 10, "burger ", "A burger",intlist, def_selection, true, "shop1");
        //Food burger = new Food("123", "burger combo", "A burger combo", 15, new Singleton[]{singleton}, "shop1");
        assertNotEquals(cart.getContents(), contents1);
        assertEquals(contents, cart.getContents());

    }

    @Test
    void setContents() {
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new AddonTest().addon;
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "burger ", "A burger", type, selection, true, "shop1");
        selections.add(selection);
        Food burger = new Food("123", "burger combo", "A burger combo", 15, new Singleton[]{singleton}, "shop1");
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        HashMap<Food, List<Selection[]>> contents1 = new HashMap<Food, List<Selection[]>>();
        contents1.put(burger,selectionList);
        cart.setContents(contents1);
        assertEquals(cart.getContents(), contents1);
    }

    @Test
    void addItem() {
     Food Shawarma = new Food("1001", "Shawarma", "A regular Shawarma", 15, null, "shop1");
     HashMap<Addon, Integer> hash = new HashMap<Addon, Integer>();
     Selection selection = new Selection(hash);
     Selection[] selections = new Selection[0];
     assertTrue(cart.addItem(Shawarma, selections));
     assertTrue(cart.getContents().containsKey(Shawarma));
    }

    @Test
    void removeItem() {
        Food Shawarma = new Food("1001", "Shawarma", "A regular Shawarma", 15, null, "shop1");
        HashMap<Addon, Integer> hash = new HashMap<Addon, Integer>();
        Selection selection = new Selection(hash);
        Selection[] selections = new Selection[0];
        cart.removeItem(Shawarma, selections);
        assertFalse(cart.getContents().containsKey(Shawarma));
        assertNotEquals(0, cart.getContents().size());
    }

    @Test
    void isEmpty() {
        assertFalse(cart.isEmpty());
    }

    @Test
    void modifySelection() {
        Food Shawarma = new Food("1001", "Shawarma", "A regular Shawarma", 15,
                null, "shop1");
        HashMap<Addon, Integer> hash = new HashMap<Addon, Integer>();
        Selection selection = new Selection(hash);
        Selection[] selections = new Selection[1];
        cart.addItem(Shawarma, selections);
        Addon addon = new AddonTest().addon;
        hash.put(addon, 10);
       Selection[] newSel = null;
        cart.modifySelection(Shawarma, selections, newSel);
        List<Selection[]> nullmap = new ArrayList<>();
        nullmap.add(null);
        assertEquals(nullmap, cart.getContents().get(Shawarma));





    }
}