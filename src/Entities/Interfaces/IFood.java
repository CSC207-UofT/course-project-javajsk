package Entities.Interfaces;

import java.util.HashMap;
import java.util.List;

/**
 * The Food Interface
 *
 * This is an abstract interface that allows for the name, description, price, and components
 * of a RegularFood object to be accessed using getter methods.
 */
public interface IFood {
    /**
     *  A method that returns the name of this RegularFood object
     */
    public String getName();

    /**
     * A method that returns the description of this RegularFood object
     */
    public String getDescription();

    /**
     * A method that returns the price of this RegularFood object
     */
    public float getPrice();

    /**
     * A method that returns the components of this RegularFood object
     */
    public List<ISingleton> getComponents();

    public boolean isValidAddons(List<HashMap<IAddon, Integer>> addons);

    public List<List<IAddon>> getAllowedAddons();
}
