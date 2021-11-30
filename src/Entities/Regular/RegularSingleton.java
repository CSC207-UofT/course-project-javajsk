package Entities.Regular;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISelection;
import Entities.Interfaces.ISingleton;

import java.util.List;

public class RegularSingleton implements ISingleton{
    String id;
    String name;
    float price;
    String description;
    List<IAddon> addons;
    ISelection defaultSelection;

    /**
     * Creates a RegularSingleton object
     *
     * @param id id of the singleton
     * @param name name of the singleton
     * @param price price of the item
     * @param description brief description of the addons it has
     * @param add_ons a list of 0 or more addon entities that make up a Singleton object.
     */
    public RegularSingleton(String id, String name, String description, float price, List<IAddon> add_ons,
                            ISelection defaultSelect){
        this.id = id;
        this.name = name;
        this.description = description;
        this.rpice = price;
        this.addons = add_ons;
        this.defaultSelection = defaultSelect;
    }

    /**
     * Get the id of this Singleton
     *
     * @return return the id of this Singleton
     */
    public String getId() { return this.id; }

    /**
     * Method for setting the id of a singleton
     * @param id the id of the singleton
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    
    /**
     * Get the price of this Singleton
     *
     * @return the price of this Singleton
     */
    public String getPrice() { return this.price; }

    /**
     * Method for setting the price of a singleton
     * @param id the price of the singleton
     */
    @Override
    public void setPrice(float price) {
        this.price = price;
    }
    

    /**
     * Get the description of this object
     *
     * @return Return the description of this object
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A method for getting the default selection of a singleton
     * @return the selection of the singleton
     */
    @Override
    public ISelection getDefaultSelection() {
        return defaultSelection;
    }

    /**
     * A method for getting the allowed addons of a singleton
     * @return the list of allowed addons
     */
    @Override
    public List<IAddon> getAllowedAddonTypes() {
        return addons;
    }

    /**
     * Method for setting the singleton name
     * @param name the new name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting the singleton description
     * @param description the new description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method for setting the allowed addons type for a singleton
     * @param allowedAddonTypes the list of allowed addon types
     */
    @Override
    public void setAllowedAddonTypes(List<IAddon> allowedAddonTypes) {
        this.addons = allowedAddonTypes;
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
    public List<IAddon> getAddons() {
        return this.addons;
    }
}
