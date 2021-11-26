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
     * A method that returns the price of this food object
     */
    float getPrice();

    /**
     * A method that returns the components of this food object
     */
    List<ISingleton> getComponents();

    void setName(String name);

    void setDescription(String description);

    void setPrice(float price);

    void setComponents(List<ISingleton> components);

    boolean isValidAddons(ISelection addons);

    List<List<IAddon>> getAllowedAddons();
}
