package Entities;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISingleton;

import java.util.List;

public class RegularSingleton implements ISingleton{
    String name;
    String description;
    float price;
    List<IAddon> add_ons;
    /**
     * Construct an instance of a RegularFood, which is an object sold by a food truck vendor.
     *
     * @param name        Name of the item
     * @param price       Price of the item
     * @param description Brief description of the item
     * @param add_ons   A list of 0 or more add_on entities that make up a Singleton object.
     */
    public RegularSingleton(String name, String description, float price, List<IAddon> add_ons){
        this.name = name;
        this.description = description;
        this.price = price;
        this.add_ons = add_ons;
    }

    /**
     * Get the price of this object
     *
     * @return Return the price of this object
     */
    public float getPrice() {
        return this.price;
    }
    /**
     * Get the description of this object
     *
     * @return Return the description of this object
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<IAddon> getAllowedAddonTypes() {
        return add_ons;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setAllowedAddonTypes(List<IAddon> allowedAddonTypes) {
        this.add_ons = add_ons;
    }

    /**
     * Get the name of this object
     *
     * @return Return the name of this object
     */
    public String getName() {
        return this.name;
    }
    /**
     * Get the list of add-ons of this object
     *
     * @return Return the list of add-ons of this object
     */
    public List<IAddon> getAdd_ons() {
        return this.add_ons;
    }
}
