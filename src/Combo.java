import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Sellable {
    /**
     * A public class representing a Combo, which is a combination of main, side, and drink items
     * in one class object.
     *
     * This class is meant to represent an object in a food truck's menu that contains multiple
     * food items.
     *
     */
    float price;
    String name;
    String id;
    ArrayList<Food> MainItems;
    ArrayList<Food> SideItems;
    ArrayList<Food> DrinkItems;
    String description;
    float Discount;
    boolean availability;


    /**
     * Construct an instance of a Combo, which is a combination of main, side,
     * and drink items sold by a food truck vendor.
     *
     * @param ID          ID of the combo
     * @param name        Name of the combo
     * @param description Brief description of the combo
     * @param discount    Discount percentage of the combo
     * @param mainitems   MainItems in the combo
     * @param sideitems   SideItems in the combo
     * @param drinkitems  DrinkItems in the combo
     */
    public Combo(ArrayList<Food> mainitems, ArrayList<Food> sideitems, ArrayList<Food> drinkitems,
                 String ID, String name, float discount, String description){
        this.MainItems = mainitems;
        this.SideItems = sideitems;
        this.DrinkItems = drinkitems;
        this.name = name;
        this.Discount = discount;
        float mainsprice = totalPrice(mainitems);
        float sidesprice = totalPrice(sideitems);
        float drinksprice = totalPrice(drinkitems);
        this.price = (mainsprice + sidesprice + drinksprice) * this.Discount; // the price of a combo is
        // the price of all the items added together multiplied by the discount that the people running
        // the food truck provides.
        this.id = ID;
        this.description = description;
        this.availability = totalAvailability(this.MainItems) &&
                totalAvailability(this.SideItems) &&
                totalAvailability(this.DrinkItems);
        // using the totalAvailability function to check if all elements in
        // this.MainItems, this.SideItems, and this.DrinkItems are available.
    }
    public float totalPrice(ArrayList<Food> items){
        // This function will take on an array of food items and return the price of all the items in the array.
        // This function uses a for loop accumulator to add up the total price of all the foods.
        float accumulator = 0;
        for (Food item : items){
            accumulator = accumulator + item.getPrice();
        }
        return accumulator;
    }

    /**
     * Get the ID of this combo
     *
     * @return Return the ID of this combo
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Get the price of this combo
     *
     * @return Return the price of this combo
     */
    @Override
    public float getPrice() {
        return this.price;
    }

    /**
     * Set the price of this combo
     *
     * @param price The new price to be set
     */
    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the name of this combo
     *
     * @return Return the name of this combo
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Set the Name of this combo
     *
     * @param newName The new name to be set
     */
    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Get the description of this combo
     *
     * @return Return the description of this combo
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description of this item
     *
     * @param newDesc The new description to be set
     */
    @Override
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Return true if this item is available, false otherwise.
     *
     * @return the availability of this item.
     */
    @Override
    public boolean isAvailable() {
        return this.availability;
    }

    /**
     * Get the discount of this combo
     *
     * @return Return the discount percentage of this combo
     */
    @Override
    public float getDiscount() { // get function for the discount for a combo.
        return this.Discount;
    }

    /**
     * Set the discount of this combo
     *
     * @param discount The new discount percentage to be set
     */
    @Override
    public void setDiscount(float discount) { // takes in a float to change the
        // discount attribute of a combo object.
        this.Discount = discount;
    }

    /**
     * Set the availability of this combo
     *
     * @param available The new availability to be set
     */
    @Override
    public void setAvailability(boolean available) { // takes in a boolean to change the
        // availability attribute of a combo object.
        this.availability = available;
    }

    /**
     * Returns whether all the items in the given list of items are available
     *
     * @param items A list of items, either main items, side items, and drink items.
     */
    public boolean totalAvailability(ArrayList<Food> items) {
        // loops through all elements in the given array to check if all items are available.
        for (Food item : items){
            if (!item.isAvailable()){
                return false;
            }
        }
        return true;
    }

    /**
     * Return false if any item is not available, true otherwise.
     *
     * @return Return whether all the items in the given list of items are available
     */
    public List<Food> getItems() {
        // Returns all the items in the combo in a single list.
        List<Food> items = new ArrayList<>();
        items.addAll(this.MainItems);
        items.addAll(this.SideItems);
        items.addAll(this.DrinkItems);

        return items;
    }

}
