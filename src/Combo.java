import Interfaces.Sellable;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Sellable {
    float price;
    String name;
    String id;
    ArrayList<Sellable> MainItems;
    ArrayList<Sellable> SideItems;
    ArrayList<Sellable> DrinkItems;
    String description;
    float Discount;
    boolean availability;

    public Combo(ArrayList<Sellable> mainitem, ArrayList<Sellable> sideitem, ArrayList<Sellable> drinkitem,
                 String ID, String name, float discount, String description){
        this.MainItems = mainitem;
        this.SideItems = sideitem;
        this.DrinkItems = drinkitem;
        this.name = name;
        this.Discount = discount;
        float mainsprice = totalPrice(mainitem);
        float sidesprice = totalPrice(sideitem);
        float drinksprice = totalPrice(drinkitem);
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
    public float totalPrice(ArrayList<Sellable> items){
        // This function will take on an array of food items and return the price of all the items in the array.
        // This function uses a for loop accumulator to add up the total price of all the foods.
        float accumulator = 0;
        for (Sellable item : items){
            accumulator = accumulator + item.getPrice();
        }
        return accumulator;
    }

    @Override
    public String getId() { // get function for the combo's id.
        return this.id;
    }

    @Override
    public float getPrice() { // get function for the combo's price.
        return this.price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String getName() { // get function for the combo's name.
        return this.name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    @Override
    public boolean isAvailable() {
        return this.availability;
    }

    @Override
    public float getDiscount() {
        return this.Discount;
    }

    @Override
    public void setDiscount(float discount) {
        this.Discount = discount;
    }

    @Override
    public void setAvailability(boolean available) {
        this.availability = available;
    }

    public boolean totalAvailability(ArrayList<Sellable> items) {
        // loops through all elements in the given array to check if all items are available.
        for (Sellable item : items){
            if (!item.isAvailable()){
                return false;
            }
        }
        return true;
    }

    public List<Sellable> getItems() {
        // Returns all the items in the combo in a single list.
        List<Sellable> items = new ArrayList<>();
        items.addAll(this.MainItems);
        items.addAll(this.SideItems);
        items.addAll(this.DrinkItems);

        return items;
    }

}
