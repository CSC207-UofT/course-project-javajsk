package Entities;

import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;

import java.util.List;

/**
 * Public class representing a Food object to be sold by a shop.
 *
 * Implements the IFood interface.
 *
 */

public class RegularFood implements IFood {
    String name;
    String description;
    float price;
    List<ISingleton> components;

    /**
     * Construct an instance of a RegularFood, which is an object sold by a food truck vendor.
     *
     * @param name        Name of the item
     * @param price       Price of the item
     * @param description Brief description of the item
     * @param components   The singleton entities that make up this RegularFood object.
     */
    public RegularFood(String name, String description, float price,
                       List<ISingleton> components) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.components = components;
    }

    /**
     * Get the name of this object
     *
     * @return Return the name of this object
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the description of this object
     *
     * @return Return the description of this object
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the price of this object
     *
     * @return Return the price of this object
     */
    @Override
    public float getPrice() {
        return this.price;
    }

    /**
     * Get the components of this object
     *
     * @return Return a list of components of this object
     */
    @Override
    public List<ISingleton> getComponents() {
        return this.components;
    }
}
