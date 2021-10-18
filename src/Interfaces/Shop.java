package Interfaces;

import java.util.ArrayList;

/** The shop interface
 *
 * This interface allows vendors and users to interact with a shop. The shop is an abstract interface
 * which allows modification of menu items, order placing and order updating and certain getters and setters
 * related directly to shops (e.g. Shop name, shop location and shop status).
 *
 */
public interface Shop {
    /** The following methods are for shop attributes **/


    /**
     * A function that gets the shop's name in plaintext.
     * @return The shops name
     */
    String getShopName();

    /**
     * Function to set the shop's name
     * @param newName the new name to set the shop to in plaintext.
     */
    void setShopName(String newName);

    /**
     * A function to get the current location of the shop
     * @return the location of the shop.
     */
    String getLocation();

    /**
     * This function is a setter for the location of the shop
     * @param location the new location to move the shop to.
     */
    void setLocation(String location);

    /**
     * A function to get the current status of the shop (i.e. is it open or closed)
     * @return a boolean value, true = open, false = closed.
     */
    boolean getShopStatus();

    /**
     * A function that sets the current shop's open/closed status.
     * @param status the status to set the shop to.
     */
    void setShopStatus(boolean status);

    /** The following methods are related to the Menu of the shop**/

    /**
     * Add a item to the shop's menu.
     * @param item the item to be added to the shop's menu.
     * @return the id of the menu item that has been added. Null if menu item failed to be added.
     */
    String addMenuItem(Sellable item);

    /**
     * A setter to get an item from this shop's Menu.
     * @param id The id of the menu item to get
     * @return the menu item if found, null if not found.
     */
    Sellable getMenuItem(String id);

    /**
     * A function to remove an item from this shop's menu if possible.
     * @param id the id by which to remove an item from the menu
     * @return a boolean value representing if the removal succeeded.
     */
    boolean removeMenuItem(String id);

    /**
     * A simple getter to get the price of an item on the menu by its id.
     * @param id the menu item's id
     * @return the price of the respective menu item, if found. Null if not found.
     */
    float getMenuItemPrice(String id);


    /**
     * A function to set the price of an item on the shop's menu.
     * @param id the menu item's id
     * @param price the new price to set the respective item to
     * @return a boolean value that describes if the setting succeeded or not.
     */
    boolean setMenuItemPrice(String id, float price);


    /**
     * Getter to get the availability of a given menu item.
     * @param id the id of the menu item that is to be checked
     * @return a boolean value that is true if the item is available. (False if item is not found)
     */
    boolean getMenuItemAvailability(String id);


    /**
     * Setter for the availability of a specific item on the menu.
     * @param id the id of the menu item that is to be modified.
     * @param available a boolean value that states if the item is available, true == available.
     * @return if the modification was successful.
     */
    boolean setMenuItemAvailability(String id, boolean available);


    /**
     * Change the description of an item on the menu of the shop.
     * @param id the menu item whose description is to be modified.
     * @param newDesc the new description to be set
     * @return a boolean that is true if the modification succeeded (Null if no such item is found).
     */
    boolean setMenuItemDescription(String id, String newDesc);


    /**
     * Getter for a given menu item's description
     * @param id the id of the item in the menu
     * @return the description if found (null if not found)
     */
    String getMenuItemDescription(String id);


    /**
     * Get the name of an item on the menu
     * @param id the id of the menu item
     * @return the plaintext name of the menu item if found (Null if not found).
     */
    String getMenuItemName(String id);


    /**
     * Set the name of an item on the menu
     * @param id menu item's id
     * @param newName the new name to set the item to
     * @return a boolean value that is true if the modification succeeded (Null if item not found)
     */
    boolean setMenuItemName(String id, String newName);

    /**
     * Getter for a given menu item's discount rate
     * @param id the menu item
     * @return the discount in percentage as a float (null if no such menu item exists)
     */
    Float getMenuItemDiscount(String id);

    /**
     * Setting the discount of a given menu item.
     * @param id the menu item to modify
     * @param discount the new discount to set for the given menu item
     * @return if the modification succeeded or not (null if no such item was found)
     */
    boolean setMenuItemDiscount(String id, float discount);



    /**
     * Getter for all items on the menu
     * @return An arrayList of sellable objects.
     */
    ArrayList<Sellable> getAllMenuItems();


    /**
     * A function similar to getAllMenuItems() but only returns available items.
     * @return an arrayList of *available* items.
     */
    ArrayList<Sellable> getAvailableMenuItems();


    /** The following methods are related to the orderbook of the shop**/

    /**
     * Getter to get the next order in the orderBook
     * @return the next non-cancelled order.
     */
    Orderable getNextOrder();

    /**
     * Getter for orders in the orderBook by id
     * @param id the id of the order
     * @return the order (Null if no such order is found)
     */
    Orderable getOrder(String id);


    /**
     * Add order to the shop's orderbook.
     * @param item the item to be added to the orderbook.
     */
    void addOrder(Orderable item);


    /**
     * Completely remove an order from the shop. This should not be used, instead use setOrderStatus and set the
     * specified order to cancelled.
     * @param id the id of the order to remove
     * @return if the order was successfully removed (false if no such order was found)
     */
    boolean removeOrder(String id);


    /**
     * Set an order to completed (exactly the same as calling:
     * @<code>
     *     setOrderStatus(id, Orderable.COMPLETED)
     * </code>
     * @param id the id of the order to set as complete
     * @return if the order was successfully set as completed (False if no such order is found)
     */
    boolean completeOrder(String id);


    /**
     * Sets the status of a given order, please use the interface constants when calling this function. E.g.:
     * @<code>
     *     setOrderStatus(id, Orderable.COMPLETED)
     * </code>
     * @param id The id of the order
     * @param status the new status of the order
     * @return if the order's status was successfully changed. (False if no such order is found).
     */
    boolean setOrderStatus(String id, int status);


    /**
     * Get the items in an order
     * @param id the order id
     * @return the ArrayList of items in the order, null if no such order exists
     */
    ArrayList<Sellable> getOrderItems(String id);


    /**
     * Add an item to an order
     * @param id the order to add items to
     * @param item the item to add to the order
     * @param quantity the number of times to add the given item to the order
     * @return if the item was successfully added to the order.
     */
    boolean addItemToOrder(String id, Sellable item, int quantity);


    /**
     * Modify the shop's order at a specific index
     * @param id the id of the order to modify
     * @param index the index of the item in the order to modify
     * @param item the replacement item for the order
     * @return a boolean explaining if the modification was successful
     */
    boolean modifyOrder(String id, int index, Sellable item);


    /**
     * Remove an item from the shops given order at a given index.
     * @param id the id of the order
     * @param index the index at which to remove an item
     * @return if the removal of item from order succeeded.
     */
    boolean removeItemFromOrder(String id, int index);


    /**
     * Get the status of a given order
     * @param id id of the order
     * @return the status of the order (null if no such order is found).
     */
    int getOrderStatus(String id);


    /**
     * Get the total price of a given order
     * @param id the order's id
     * @return the price of the order as float if found, (null if no order is found).
     */
    float getTotalPriceOfOrder(String id);


    /**
     * Check if an order is canclled. This is the same as calling:
     * @<code>
     *     getOrderStatus(id) == Orderable.CANCELLED
     * </code>
     * @param id the id of the order to check.
     * @return if the order is cancelled or not (false if no such order is found)
     */
    boolean isOrderCancelled(String id);


    /**
     * Set the discount on a given order
     * @param id the id of the order
     * @param percentage the percentage of discount to set as a float.
     *                   TODO: ADD RETURN VALUE AS BOOLEAN
     */
    void setOrderDiscount(String id, float percentage);


    /**
     * Get the discount for a given order
     * @param id the id of the order
     * @return float representing the discount of the order if found (null if not found).
     */
    float getOrderDiscount(String id);


    /**
     * Update the time remaining for an order to be processed
     * @param id the id of the order
     * @param time the amount of time remaining on the order (?)
     *             TODO: SHOULD THIS BE A TIMESTAMP OR AN ETA
     */
    void updateOrderETA(String id,float time);
    
}
