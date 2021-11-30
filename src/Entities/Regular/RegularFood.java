package Entities.Regular;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.IFood;
import Entities.Interfaces.ISelection;
import Entities.Interfaces.ISingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Public class representing a Food object to be sold by a shop.
 *
 * Implements the IFood interface.
 */

public class RegularFood implements IFood {
    String id;
    String name;
    String description;
    float price;
    boolean isAvailable;
    List<ISingleton> components;
    String shopId;

    /**
     * Construct an instance of a RegularFood, which is an object sold by a food truck vendor.
     *
     * @param id the id of the food item
     * @param name Name of the item
     * @param price price of food item
     * @param avail whether food item is available
     * @param components The singleton entities that make up this RegularFood object.
     */
    public RegularFood(String id, String name, float price, boolean avail,
                       List<ISingleton> components) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = avail;
        this.components = components;
        setDefaultDescription();
    }

    /**
     * A method that sets a default description for the food item based on its components
     */
    private void setDefaultDescription(){
        StringBuilder description = new StringBuilder();
        for(ISingleton component: this.components){
            description.append(component.getName() );
        }
        this.description = description.toString();
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
     * Get the components of this object
     *
     * @return Return a list of components of this object
     */
    @Override
    public List<ISingleton> getComponents() {
        return this.components;
    }

    /**
     * A method that returns the price of this food object
     * @return price of food
     */
    @Override
    public float getPrice() { return this.price; }

    /**
     * A method that returns whether the food is available for order
     * @return whether food is available
     */
    @Override
    public boolean getAvailability() {
        return false;
    }

    /**
     * A method that returns the shopId that the food is for
     * @return shopId of food
     */
    @Override
    public String getShopId(){ return this.shopId; }

    /**
     * A method that sets the price of this food object
     * @param newPrice price of food
     */
    @Override
    public void setPrice(float newPrice) { this.price = newPrice; }

    /**
     * A method that sets the shopId of the food object
     * @param newShopId shopId of food
     */
    @Override
    public void setShopId(String newShopId) { this.shopId = newShopId; }

    /**
     * A method that sets the id of the food item
     * @param newId id of food item
     */
    @Override
    public void setId(String newId){ this.id = newId; }

    /**
     * A method that sets the name of the food item
     * @param name of food item
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method that sets the description of the food item
     * @param description description of food item
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A method that sets the components of the food item
     * @param components list of singletons
     */
    @Override
    public void setComponents(List<ISingleton> components) {
        this.components = components;
    }

    /**
     * A method that returns whether a given list of addon selections
     * are valid with this food item
     * @param addons list of selections
     * @return whether given list has valid addons
     */
    @Override
    public boolean isValidAddons(List<ISelection> addons) {
        if(addons.size() != components.size()){
            return false;
        }
        List<List<IAddon>> allowedTypes = this.getAllowedAddons();
        for(int i =0; i < addons.size(); i++){
            List<IAddon> allowedForSingleton = allowedTypes.get(i);
            for (IAddon selectedAddon : addons.get(i).getUsedAddons()) {
                if (!allowedForSingleton.contains(selectedAddon)) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * A method that returns a list of allowed addons for each singleton in the food
     * item, organized as a list.
     * So each element (list of addons) in the list corresponds to one singleton
     * in the components of food
     * @return list of list of allowed addons
     */
    @Override
    public List<List<IAddon>> getAllowedAddons() {
        List<List<IAddon>> addons = new ArrayList<>();
        for(ISingleton item: components){
            addons.add(item.getAllowedAddonTypes());
        }
        return addons;
    }
}
