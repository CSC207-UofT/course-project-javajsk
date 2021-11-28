package Entities.Regular;

import Entities.Interfaces.IAddon;

import java.util.ArrayList;

/**
 * A public class that stores a certain addon that represents a food item that can be added to a singleton item
 * as desired by the user.
 * (ex. an extra topping on a pizza or hot dog)
 */
public class RegularAddon implements IAddon {
    /**
     * A RegularAddon class
     *
     * This is meant to represent a specific ingredient/food/topping that can be added to single food item
     * (a singleton)
     *
     * name is the name of the addon
     * description is the description of the addon
     * price is the price of the addon per 1 quantity
     * addonTypes is a list of integers corresponding to types that addon applies to
     * isAvailable indicates if the addon is currently available for purchase
     */
    public String name;
    public String description;
    public float price;
    public ArrayList<Integer> addonTypes;
    public boolean isAvailable;
    public String ID;

    /**
     * Construct an instance of RegularAddon
     *
     * @param addonName name of addon
     * @param addonDesc description of addon
     * @param addonPrice price of addon
     * @param types arraylist of types that addon is
     */
    public RegularAddon(String addonName, String addonDesc, float addonPrice,
                        ArrayList<Integer> types, boolean availability, String ID){
        this.name = addonName;
        this.description = addonDesc;
        this.price =  addonPrice;
        this.addonTypes = types;
        this.isAvailable = availability;
        this.ID = ID;
    }

    /**
     * A method that returns the name of the Addon
     * @return name of addon
     */

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * A method that returns the description of the Addon
     * @return description of Addon
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * A method that returns the price of the Addon
     * @return price of Addon
     */
    @Override
    public float getPrice() {
        return this.price;
    }
  
    /**
     * A method that sets the price of the Addon
     * @param newPrice price of Addon
     */
    @Override
    public void setPrice(float newPrice){
        this.price = newPrice;
    }

    /**
     * A method that returns an arraylist of the types the addon is, where each type/category is
     * represented by an integer
     * @return arraylist of addon types
     */
    @Override
    public ArrayList<Integer> getTypes() {
        return this.addonTypes;
    }

    /**
     * A method that returns whether the addon is available
     * @return whether addon is available
     */
    @Override
    public boolean getAvailability() {
        return this.isAvailable;
    }

    /**
     * A method that sets the availability of the Addon
     * @param newAvail whether addon is available
     */
    @Override
    public void setAvailability(boolean newAvail){
        this.isAvailable = newAvail;
    }

    @Override
    public String getID() {
        return this.ID;
    }
}
