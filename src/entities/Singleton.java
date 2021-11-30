package entities;

import java.util.List;

/**
 * The type Singleton.
 */
public class Singleton {
    /**
     * The Id.
     */
    public String id;
    /**
     * The Price.
     */
    protected float price;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Allowed addon types.
     */
    protected List<Integer> allowedAddonTypes;
    /**
     * The Default selection.
     */
    protected List<Addon> defaultSelection;
    /**
     * The Availability.
     */
    protected boolean availability;


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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets allowed addon types.
     *
     * @return the allowed addon types
     */
    public List<Integer> getAllowedAddonTypes() {
        return allowedAddonTypes;
    }

    /**
     * Sets allowed addon types.
     *
     * @param allowedAddonTypes the allowed addon types
     */
    public void setAllowedAddonTypes(List<Integer> allowedAddonTypes) {
        this.allowedAddonTypes = allowedAddonTypes;
    }

    /**
     * Gets default selection.
     *
     * @return the default selection
     */
    public List<Addon> getDefaultSelection() {
        return defaultSelection;
    }

    /**
     * Sets default selection.
     *
     * @param defaultSelection the default selection
     */
    public void setDefaultSelection(List<Addon> defaultSelection) {
        this.defaultSelection = defaultSelection;
    }

    /**
     * Is availability boolean.
     *
     * @return the boolean
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * Sets availability.
     *
     * @param availability the availability
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


}
