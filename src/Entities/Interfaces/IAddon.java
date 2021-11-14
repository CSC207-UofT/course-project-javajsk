package Entities.Interfaces;

import java.util.ArrayList;

/**
 * The Addon Interface
 *
 * This is an abstract interface that allows for the name, description, price, and add-on type to be
 * accessed using getter methods.
 */
public interface IAddon {
    /**
     * A method that returns the name of the Addon
     * @return name of addon
     */
    String getName();

    /**
     * A method that returns the description of the Addon
     * @return description of Addon
     */
    String getDescription();

    /**
     * A method that returns the price of the Addon
     * @return price of Addon
     */
    float getPrice();

    /**
     * A method that sets the price of the Addon
     * @param newPrice price of Addon
     */
    void setPrice(float newPrice);

    /**
     * A method that returns an arraylist of the types the addon is, where each type/category is
     * represented by an integer
     * @return arraylist of addon types
     */
    ArrayList<Integer> getTypes();

    /**
     * A method that returns whether the addon is available
     * @return whether addon is available
     */
    boolean getAvailability();

    /**
     * A method that sets the availability of the Addon
     * @param newAvail whether addon is available
     */
    void setAvailability(boolean newAvail);
}
