package entities;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
class MenuTest {
    Menu menu;

    /**
     *
     */

    Menu setMenu(){
        ArrayList<Integer> type = new ArrayList<Integer>();
        type.add(1);
        Addon addon = new Addon("addon1", "addon", 15,null, true, "shop1");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection1 = new Selection(SingletonSelection);
        List<Selection> selections = new ArrayList<Selection>();
        Singleton singleton = new Singleton("3", 10, "fries", "Fries", type, selection1, true, "shop1");
        selections.add(selection1);
        Singleton[] singletons = new Singleton[1];
        singletons[0] = singleton;
         Food burger = new Food("123", "burger combo", "A burger combo", 20, singletons, "shop1");
        List<Food> foods = new ArrayList<Food>();
        foods.add(burger);
        List<Addon> addons = new ArrayList<Addon>();
        addons.add(addon);
        return new Menu(foods, addons);
    }

    @Test
    void getAddons() {
        menu = setMenu();
        Addon addon = new Addon("addon3", "addon", 15,null, true, "shop1");
        menu.addAddon(addon);
        assertEquals("addon1", menu.getAddons().get(0).getId());
        assertEquals("addon3", menu.getAddons().get(1).getId());

    }


    @Test
    void getFoods() {
        menu = setMenu();
        assertSame("123", menu.getFoods().get(0).getId());
    }

    @Test
    void addAddon() {
        menu = setMenu();
        Addon newaddon = new Addon("test", null, 10, null,true,"shop1");
        menu.addAddon(newaddon);
        assertEquals(menu.getAddons().get(1), newaddon);
        assertTrue(menu.getAddons().contains(newaddon));
    }

    @Test
    void updateAddon() {
        menu = setMenu();
        Addon addon = new Addon("10101", "NewAddon", 25, null, true, "shop1");
        Addon newaddon = new Addon("test", null, 10, null,true,"shop1");
        menu.addAddon(newaddon);
        menu.updateAddon("test", addon);
        assertEquals("NewAddon", menu.getAddon(addon).getName());
    }

    @Test
    void deleteAddon() {
        menu = setMenu();
        Addon newaddon = new Addon("test", null, 10, null,true,"shop1");
        menu.addAddon(newaddon);
        assertEquals(menu.getAddons().get(1), newaddon);
        menu.deleteAddon(newaddon);
        assertFalse(menu.getAddons().contains(newaddon));


    }

    @Test
    void updateSingleton() {
        menu = setMenu();
        Addon addon = new Addon("addon1", "addon", 15,null, true, "shop1");
        HashMap<Addon, Integer> SingletonSelection = new HashMap<Addon, Integer>();
        SingletonSelection.put(addon ,10);
        Selection selection1 = new Selection(SingletonSelection);
        Singleton singleton = new Singleton("id1", 20, null, null, null, selection1, true, "shop1");
        Singleton newsingleton = new Singleton("id2", 25,null, null, null, selection1, true, "shop1");
        Singleton[] components = new Singleton[1];
        components[0] = singleton;
        Food food1 = new Food("id3", null, null, 23,components ,"shop1");
        menu.addFood(food1);
        assertTrue(menu.updateSingleton(singleton.getId(), newsingleton));
        assertEquals(menu.getFood(food1).getComponents()[0].getId(), newsingleton.getId());
        //assertTrue(menu.getFood(food1).getComponents()[0] != components[0]);


    }

    @Test
    void addFood() {
        menu = new Menu();
        Food food1 = new Food("id3", null, null, 23, null,"shop1");
        menu.addFood(food1);
        assertTrue(menu.getFoods().contains(food1));
    }

    @Test
    void deleteFood() {
        menu = setMenu();
        Food food1 = new Food("id3", null, null, 23, null,"shop1");
        menu.addFood(food1);
        assertTrue(menu.foods.contains(food1));
        menu.deleteFood(food1);
        assertFalse(menu.foods.contains(food1));

    }

    @Test
    void setSingletonAvailability() {
        menu = setMenu();
        Singleton[] components = new Singleton[1];
        components[0] = new Singleton("id333", 12, "name1", null, null, null, false, "shop1");
        Food food1 = new Food("id3", null, null, 23, components,"shop1");
        menu.addFood(food1);
        assertFalse(menu.getFood(food1).getComponents()[0].isAvailable());
        menu.setSingletonAvailability(components[0], true);
        assertTrue(menu.getFood(food1).getComponents()[0].isAvailable());
    }

    @Test
    void getAvailableAddons() {
        menu = setMenu();
        Addon addon1 = new Addon("id2", "addon", 12, null, true, "shop1");
        menu.addAddon(addon1);
        assertSame(addon1, menu.getAvailableAddons().get(1));
        Addon addon2 = new Addon("id66", "addon11", 12, null, false, "shop1");
        menu.addAddon(addon2);
        assertFalse(menu.getAvailableAddons().contains(addon2));
    }

    @Test
    void getAvailableFoods() {
        Menu menu = new Menu();
        Singleton[] components = new Singleton[1];
        components[0] = new Singleton(null, 12, null,null, null,null,true,"shop1");
        Food food = new Food("id1", "food", null, 12, components, "shop1");
        menu.addFood(food);
        assertTrue(menu.getAvailableFoods().contains(food));

    }
}