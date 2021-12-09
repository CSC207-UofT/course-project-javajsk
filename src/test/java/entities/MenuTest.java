package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
class MenuTest {
    Menu menu;

    /**
     *
     */
    @BeforeEach
    void setMenu(){
        List<Food> foods = new ArrayList<Food>();
        Food burger = new FoodTest().burger;
        foods.add(burger);
        List<Addon> addons = new ArrayList<Addon>();
        Addon addon = new AddonTest().addon;
        addons.add(addon);
        menu = new Menu(foods, addons);
    }

    @Test
    void getAddons() {
        List<Addon> addons = new ArrayList<Addon>();
        Addon addon = new AddonTest().addon;
        addons.add(addon);
        assertEquals(menu.getAddons(), addons);
    }


    @Test
    void getFoods() {
        List<Food> foods = new ArrayList<Food>();
        Food burger = new FoodTest().burger;
        foods.add(burger);
        assertEquals(menu.getFoods(), foods);
    }

    @Test
    void addAddon() {
        Addon newaddon = new Addon("test", null, 10, null,true,"shop1");
        menu.addAddon(newaddon);
        assertEquals(menu.getAddons().get(1), newaddon);
    }

    @Test
    void updateAddon() {
        Addon addon = new Addon("10101", "NewAddon", 25, null, true, "shop1");
        Addon newaddon = new Addon("test", null, 10, null,true,"shop1");
        menu.addAddon(newaddon);
        menu.updateAddon("test", addon);
        assertEquals("NewAddon", menu.getAddon(addon).getName());
    }

    @Test
    void deleteAddon() {
        Addon newaddon = new Addon("test", null, 10, null,true,"shop1");
        menu.addAddon(newaddon);
        assertEquals(menu.getAddons().get(1), newaddon);
        menu.deleteAddon(newaddon);
        assertFalse(menu.getAddons().contains(newaddon));
    }

    @Test
    void updateSingleton() {
        Singleton singleton = new Singleton("id1", 20, null, null, null, null, true, "shop1");
        Singleton newsingletone = new Singleton("id2", 25,null, null, null, null, true, "shop1");
        Food food1 = new Food("id3", null, null, 23, null,"shop1");
        menu.addFood(food1);
        menu.updateSingleton("id3", singleton);
        assertSame(menu.foods.get(1).id, singleton.id);

    }

    @Test
    void addFood() {
        Food food1 = new Food("id3", null, null, 23, null,"shop1");
        menu.addFood(food1);
        assertTrue(menu.foods.contains(food1));
    }

    @Test
    void deleteFood() {
        Food food1 = new Food("id3", null, null, 23, null,"shop1");
        menu.addFood(food1);
        assertTrue(menu.foods.contains(food1));
        menu.deleteFood(food1);
        assertFalse(menu.foods.contains(food1));
    }

    @Test
    void setSingletonAvailability() {
    }

    @Test
    void getAvailableAddons() {
        Addon addon1 = new AddonTest().addon;
        assertSame(addon1, menu.addons.get(0));
    }

    @Test
    void getAvailableFoods() {
        Food food2 = new FoodTest().burger;
        assertSame(food2, menu.foods.get(0));
    }
}