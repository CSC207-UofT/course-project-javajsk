package Entities;
import Entities.Interfaces.IAddon;
import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;

import java.util.HashMap;
import java.util.List;

public class Menu {


    private HashMap<IFood, ISingleton> menu;
    public Menu() {
        this.menu = new HashMap<>();
    }

    public void MenuItem(IFood[] foods, ISingleton[] singleton) {
        for (int i = 0; i < foods.length; i++) {
            menu.put(foods[i], singleton[i]);
        }
    }

    public float getPrice(IFood item) {
        if (menu.containsKey(item)) {
            return menu.get(item).getPrice();
        } else
            return 0;
    }

    public String getDescription(IFood item) {
        if (menu.containsKey(item)) {
            return menu.get(item).getDescription();
        } else
            return null;
    }

}
// incomplete



