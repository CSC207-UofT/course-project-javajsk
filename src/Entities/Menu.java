package Entities;

import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;

import java.util.List;

public class Menu {

    private List<IFood> menu;

    /**
     * The constructor for creating a new menu
     * @param menu the contents of the new menu
     */
    public Menu(List<IFood> menu) { this.menu = menu; }

    /**
     * A method for adding a new food item to a menu
     *
     * @param food the new food item
     */
    public void addFood(IFood food){
        this.menu.add(food);
    }

    /**
     * A method for removing a food item to a menu
     *
     * @param food the new food item
     */
    public void removeFood(IFood food) {
        this.menu.remove(food);
    }

    /**
     * A method for getting the food items in a menu
     * @return the items of the menu in  hashmap
     */
    public List<IFood> getMenu() {
        return menu;
    }

    /**
     * A method that returns whether the menu contains a given food item
     * @param item the food item to check for
     * @return whether item is in the menu
     */
    public boolean hasFood(IFood item){
       return this.menu.contains(item);
    }

    /**
     * A method that modifies the food item with given id with
     * the new food item
     * @param foodId id of food item to modify
     * @param food food item to set it to
     * @return whether food was successfully modified on menu
     */
    public boolean setFood(String foodId, IFood food){
        for(int i=0; i < this.menu.size(); i++){
            if(this.menu.get(i).getId().equals(foodId)){
                this.menu.set(i, food);
                return true;
            }
        }
        return false;
    }
}




