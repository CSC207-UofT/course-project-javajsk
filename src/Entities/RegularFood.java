package Entities;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Public class representing a Food object to be sold by a shop.
 *
 * Implements the IFood interface.
 *
 */

public class RegularFood implements IFood {
    String id;
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
    public RegularFood(String id, String name, String description, float price, List<ISingleton> components) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.components = components;
    }

    /**
     * Method returns the id of this RegularFood object
     *
     * @return id of food object
     */
    @Override
    public String getId(){ return this.id; }


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
        if(this.price == -1){
            float sum = 0;
            for (ISingleton single: this.components) {
                sum += single.getPrice();
            }
            return sum;
        }
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void setComponents(List<ISingleton> components) {
        this.components = components;
    }

    @Override
    public boolean isValidAddons(List<HashMap<IAddon, Integer>> addons) {
        if(addons.size() != components.size()){
            return false;
        }
        List<List<IAddon>> allowedTypes = this.getAllowedAddons();
        for(int i =0; i < addons.size(); i++){
            List<IAddon> allowedForSingleton = allowedTypes.get(i);
            for(IAddon selectedAddon: addons.get(i).keySet()){
                if(!allowedForSingleton.contains(selectedAddon)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<List<IAddon>> getAllowedAddons() {
        List<List<IAddon>> addons = new ArrayList<>();
        for(ISingleton item: components){
            addons.add(item.getAllowedAddonTypes());
        }
        return addons;
    }
}
