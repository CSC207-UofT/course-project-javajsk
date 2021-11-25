package Entities;

import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;

import java.util.HashMap;

public class Menu {

    private HashMap<IFood, Object[]> menu;

    /**
     * A method for adding a new food item to a menu
     *
     * @param food the new food item
     * @param price the price for the food item
     * @param availability the availability of the food item
     */
    public void addFood(IFood food, float price, boolean availability){
        Object[] foodInfo = {price, availability};
        this.menu.put(food, foodInfo);
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
     * A method for modifying a food item to a menu
     *
     * @param food the  food item
     * @param price the new price for the food item
     * @param availability the updated availability of the food item
     */
    public void setFood(IFood food, float price, boolean availability){
        Object[] foodInfo = this.menu.get(food);
        foodInfo[0] = price;
        foodInfo[1] = availability;
    }



    /**
     * A method for getting the food items in a menu
     * @return the items of the menu in  hashmap
     */
    public HashMap<IFood, Object[]> getContents() {
        return menu;
    }

    /**
     * A method for getting the price of a food item
     * @param item the food item we want the price of
     * @return the price of the food item
     */
    public float getPrice(IFood item) {
        // if price is -1, then calculate price from the components
        if (this.menu.containsKey(item)) {
            return (float) this.menu.get(item)[0];
        } else
            return 0;
    }

    /**
     *A method for getting the components of a food item if it has any
     * @param item the food item we want the description
     * @return the description of the food item as a string
     */
    public String getDescription(IFood item) {
        if (menu.containsKey(item)) {
            StringBuilder description = new StringBuilder();
            for(ISingleton component: item.getComponents()){
                description.append(component.getDescription());
                description.append(" ");}
            return description.toString();
        }
        return null;
    }


    /**
     * A method to check the availability of a given food item
     * @param item the food item
     * @return true if the food item is available
     */
    public boolean isFoodAvailable(IFood item){
        if(this.menu.containsKey(item)){
            Object[] foodInfo = this.menu.get(item);
            return (boolean) foodInfo[1];
        }
        else{
            //TODO give alert that the given item is not in the menu?
            return false;
        }
    }



}
// incomplete



