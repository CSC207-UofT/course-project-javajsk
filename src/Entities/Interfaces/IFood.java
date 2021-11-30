package Entities.Interfaces;

import java.util.List;

/**
 * The Food Interface
 *
 * This is an abstract interface that allows for the name, description, price, and components
 * of a RegularFood object to be accessed using getter methods.
 */
public interface IFood {
    /**
     * A method that return the id of this food object
     */
    String getId();

    /**
     *  A method that returns the name of this food object
     */
    String getName();

    /**
     * A method that returns the description of this food object
     */
    String getDescription();

    /**
     * A method that returns the components of this food object
     */
    List<ISingleton> getComponents();

    /**
     * A method that sets the id of the food item
     * @param newId id of food item
     */
    void setId(String newId);

    /**
     * A method that sets the name of the food item
     * @param name of food item
     */
    void setName(String name);

    /**
     * A method that sets the description of the food item
     * @param description description of food item
     */
    void setDescription(String description);

    /**
     * A method that sets the components of the food item
     * @param components list of singletons
     */
    void setComponents(List<ISingleton> components);

    /**
     * A method that returns whether a given list of addon selections
     * are valid with this food item
     * @param addons list of selections
     * @return whether given list has valid addons
     */
    boolean isValidAddons(List<ISelection> addons);

    /**
     * A method that returns a list of allowed addons for each singleton in the food
     * item, organized as a list.
     * So each element (list of addons) in the list corresponds to one singleton
     * in the components of food
     * @return list of list of allowed addons
     */
    List<List<IAddon>> getAllowedAddons();
}
