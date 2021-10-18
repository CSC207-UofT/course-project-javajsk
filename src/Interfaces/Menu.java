package Interfaces;

import java.util.ArrayList;

/** The Menu Interface
 *
 * This interface allows foodTrucks and sellable items to modify or view items on a menu.
 * This is an abstract interface that allows for different types of menus and uses getter and setter methods
 * to modify items on a menu (add items, change item prices availibility, description, discounts, name).
 *
 **/
public interface Menu {

    /**
     * A method that adds an item to the menu
     *
     * @param item item to be added
     * @return id of the item added (the corresponding key)
     */
    String addItem(Sellable item);

    /**
     * A method that returns an item based on given id
     * @param id key of the item to get
     * @return item in menu
     */
    Sellable getItem(String id);

    /**
     * A method that removes an item from Menu
     *
     * @param id key of item to be removed
     * @return whether the item was successfully removed
     */
    boolean removeItem(String id);

    /**
     * A method that returns the price of a given item
     * @param id key of item
     * @return price of item as a float
     */
    float getItemPrice(String id);

    /**
     * A method that sets the price of a given item with the given price
     * @param id key of item to change price of
     * @param price price of item
     * @return whether the item price was successfully updated
     */
    boolean setItemPrice(String id, float price);

    /**
     * A method that returns whether the item is available
     * @param id key of item to check
     * @return whether item is available
     */
    boolean getItemAvailability(String id);

    /**
     * A method that sets the availability of a given item
     * @param id key of item
     * @param available whether item is available
     * @return whether availability was successfully set
     */
    boolean setItemAvailability(String id, boolean available);

    /**
     * A method that returns the description of a given item
     * @param id key of item
     * @return item description
     */
    String getItemDescription(String id);

    /**
     * A method that sets the item desciption to given descripition
     * @param id key of item
     * @param newDesc description of item
     * @return whether description was successfully updated
     */
    boolean setItemDescription(String id, String newDesc);

    /**
     * A method that returns the name of an item
     * @param id key of item
     * @return name of item
     */
    String getItemName(String id);

    /**
     * A method that sets the name of an item
     * @param id key of item
     * @param newName name of item
     * @return whether name was successfully updated
     */
    boolean setItemName(String id, String newName);

    /**
     * A method that returns the discount of an item
     * @param id key of item
     * @return discount of item
     */
    Float getDiscount(String id);

    /**
     * A method that sets the discount of an item
     * @param id key of item
     * @param discount discount of item
     * @return whether discount was successfully set
     */
    boolean setDiscount(String id, float discount);

    /**
     * A method that returns all the items on the menu
     * @return list of items on menu
     */
    ArrayList<Sellable> getAllItems();

    /**
     * A method that returns all the available items on the menu
     * @return list of available items on menu
     */
    ArrayList<Sellable> getAvailableItems();

    /**
     * A method that returns whether the item in the menu
     * @param id key of item
     * @return whether the item is in the menu
     */
    boolean hasItem(String id);


}
