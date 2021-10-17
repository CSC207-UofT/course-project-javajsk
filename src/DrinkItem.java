import Interfaces.Sellable;

/**
 * public class representing a Drink Item to be sold as a sellable item.
 *
 * Implements the Sellable interface
 *
 * NOTE FROM JEFFREY: As of right now, there is virtually no difference between DrinkItem,
 * SideItem, and MainItem, but changes will be added in the future to differentiate them.
 */
public class DrinkItem implements Sellable {
    boolean isAvailable;
    String id;
    float price;
    String name;
    String description;
    float discount;

    /**
     * Construct an instance of a MainItem, which is a main item sold by a food truck vendor.
     *
     * @param id          ID of the item
     * @param name        Name of the item
     * @param price       Price of the item
     * @param description Brief description of the item
     * @param available   Availability of the item
     * @param discount    Discount percentage of the item
     */
    public DrinkItem(String id, String name, float price, String description, boolean available, float discount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.isAvailable = available;
        this.discount = discount;

    }

    /**
     * Get the ID of this item
     *
     * @return Return the ID of this item
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Get the price of this item
     *
     * @return Return the price of this item
     */
    @Override
    public float getPrice() {
        return this.price * this.discount;
    }

    /**
     * Set the price of this item
     *
     * @param price The new price to be set
     */
    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the name of this item
     *
     * @return Return the name of this item
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Set the Name of this item
     *
     * @param newName The new name to be set
     */
    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Get the description of this item
     *
     * @return Return the description of this item
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description of this item
     *
     * @param newDesc The new description to be set
     */
    @Override
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Return true if this item is available, false otherwise.
     *
     * @return the availability of this item.
     */
    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Get the discount of this item
     *
     * @return Return the discount percentage of this item
     */
    @Override
    public float getDiscount() {
        return this.discount;
    }

    /**
     * Set the discount of this item
     *
     * @param discount The new discount percentage to be set
     */
    @Override
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * Set the availability of this item
     *
     * @param available The new availability to be set
     */
    @Override
    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }
}
