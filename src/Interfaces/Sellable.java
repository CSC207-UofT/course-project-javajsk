package Interfaces;

/** The Sellable Interface
 *
 * This is an abstract interface that allows different types of menu items to all be on a food truck's menu and
 * allows modification of price, item name, description, discounts, and availability through getter and
 * setter methods.
 *
 **/
public interface Sellable {

    /**
     * A method that returns ID of sellable item
     * @return ID
     */
    String getId();

    /**
     * A method that returns price
     * @return Price
     */
    float getPrice();

    /**
     * A method that sets price to price
     * @param price price of sellable item
     */
    void setPrice(float price);

    /**
     * A method that gets the name of sellable item
     * @return Name
     */
    String getName();

    /**
     * A method that sets the name of sellable item
     * @param newName desired name of sellable item
     */
    void setName(String newName);

    /**
     * A method that gets the description of the sellable item
     * @return Description
     */
    String getDescription();

    /**
     * A method that sets the description of the sellable item
     * @param newDesc desired description of sellable item
     */
    void setDescription(String newDesc);

    /**
     * A method that checks if the sellable item is available
     * @return whether item is available
     */
    boolean isAvailable();

    /**
     * A method that gets the discount for the sellable item
     * @return discount
     */
    float getDiscount();

    /**
     * A method to set the discount for the sellable item
     * @param discount desired discount for the sellable item
     */
    void setDiscount(float discount);

    /**
     * A method to set the availability
     * @param available whether sellable item is available or not
     */
    void setAvailability(boolean available);

}
