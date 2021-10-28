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
