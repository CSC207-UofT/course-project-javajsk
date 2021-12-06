package UnitTests;
import entities.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {
    Cart setCart(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);

        Addon addon = new Addon("101", "Ketchup", 12,type, true);
        List<Addon> add_ons = new ArrayList<Addon>();
        add_ons.add(addon);
        Singleton singleton = new Singleton("3", 10, "burger ", "A burger", type, add_ons, true);
        List<Singleton> components = new ArrayList<Singleton>();
        components.add(singleton);
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        selections.add(selection);
        Food burger = new Food("123", "burger combo", "A burger combo", 15, new Singleton[]{singleton});
        List<Selection[]> selectionList = new ArrayList<Selection[]>();
        selectionList.add(selections.toArray(new Selection[0]));
        HashMap<Food, List<Selection[]>> contents = new HashMap<Food, List<Selection[]>>();
        contents.put(burger,selectionList);
        return new Cart("cart1", "shop1", contents);

    }

    @Test
    public void getId() {
        Cart new_cart = setCart();
        assertEquals(new_cart.id, "cart1");


    }

    @Test
    public void getShopId() {
        Cart new_cart = setCart();
        assertEquals(new_cart.getShopId(), "shop1");
    }

    @Test
    public void setShopId() {
        Cart new_cart = setCart();
        new_cart.setShopId("shop2");
        assertSame("shop2", new_cart.getShopId());
    }

    @Test
    public void getContents() {
        Cart new_cart = setCart();


    }
}