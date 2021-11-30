package entities;

import java.util.List;

/**
 * The type Addon.
 */
public class Addon {
    public String id;
    protected String name;
    protected float price;
    protected List<Integer> addonTypes;
    protected boolean isAvailable;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets addon types.
     *
     * @return the addon types
     */
    public List<Integer> getAddonTypes() {
        return addonTypes;
    }

    /**
     * Sets addon types.
     *
     * @param addonTypes the addon types
     */
    public void setAddonTypes(List<Integer> addonTypes) {
        this.addonTypes = addonTypes;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }
}
