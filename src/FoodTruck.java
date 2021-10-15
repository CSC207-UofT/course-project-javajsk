import Interfaces.*;

import java.util.ArrayList;

public class FoodTruck implements Shop {
    /**
     * A food truck class.
     *
     * This food truck class is supposed to represent a food truck (specifically ones found on campus at University of
     * Toronto, st. george campus.
     *
     * @param shopName is the Name of this foodTruck
     * @param location is the current location of the foodTruck
     * @param isOpen is a boolean value that stores whether or not the foodTruck is open.
     * @param menu is an instance of a Menu object that stores this foodTruck's menu.
     * @param orderbook is an orderBook object that allows orders to be managed for this foodTruck.
     */
    String shopName;
    String location;
    boolean isOpen;
    Menu menu;
    OrderBook orderBook;


    /**
     * A function that gets the shop's name in plaintext.
     * @return The shops name
     */
    @Override
    public String getShopName() {

        return shopName;

    }


    /**
     * A function that replaces the name of the foodTruck.
     * @param newName The new name of the shop in plaintext which will be set for this foodTruck.
     */
    @Override
    public void changeShopName(String newName) {
        this.shopName = newName;
    }

    /**
     * A function to get the current location of the foodTruck
     * @return the location of the foodTruck.
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * This function is a setter for the location of the foodTruck
     * @param location the new location to move the foodTruck to.
     */
    @Override
    public void changeLocation(String location) {
        this.location = location;
    }

    /**
     * A function to get the current status of the shop (i.e. is it open or closed)
     * @return a boolean value, true = open, false = closed.
     */
    @Override
    public boolean getShopStatus() {
        return isOpen;
    }


    /**
     * A function that sets the current shop's open/closed status.
     * @param status the status to set the foodTruck to.
     */
    @Override
    public void setShopStatus(boolean status) {
        isOpen = status;
    }

    /**
     * Add a item to the foodTruck's menu.
     * @param item the item to be added to the foodTruck's menu.
     * @return the id of the menu item that has been added. Null if menu item failed to be added.
     */
    @Override
    public String addMenuItem(Sellable item) {
        return menu.addItem(item);
    }

    /**
     * A setter to get an item from this foodTruck's Menu.
     * @param id The id of the menu item to get
     * @return the menu item if found, null if not found.
     */
    @Override
    public Sellable getMenuItem(String id) {
        return menu.getItem(id);
    }

    /**
     * A function to remove an item from this shop's menu if possible.
     * @param id the id by which to remove an item from the menu
     * @return a boolean value representing if the removal succeeded.
     */
    @Override
    public boolean removeMenuItem(String id) {
        return menu.removeItem(id);
    }

    /**
     * A simple getter to get the price of an item on the menu by its id.
     * @param id the menu item's id
     * @return the price of the respective menu item, if found. Null if not found.
     */
    @Override
    public float getMenuItemPrice(String id) {
        return menu.getItemPrice(id);
    }

    /**
     * A function to set the price of an item on the foodTruck's menu.
     * @param id the menu item's id
     * @param price the new price to set the respective item to
     * @return a boolean value that describes if the setting succeeded or not.
     */
    @Override
    public boolean setMenuItemPrice(String id, float price) {
        return menu.setItemPrice(id, price);
    }

    /**
     * Getter to get the availability of a given menu item.
     * @param id the id of the menu item that is to be checked
     * @return a boolean value that is true if the item is available. (False if item is not found)
     */
    @Override
    public boolean getMenuItemAvailability(String id) {
        return  menu.getItemAvailability(id);
    }

    /**
     * Setter for the availability of a specific item on the menu.
     * @param id the id of the menu item that is to be modified.
     * @param available a boolean value that states if the item is available, true == available.
     * @return if the modification was successful.
     */
    @Override
    public boolean setMenuItemAvailability(String id, boolean available) {
        return menu.setItemAvailability(id, available);
    }

    /**
     * Change the description of an item on the menu of the foodTruck.
     * @param id the menu item whose description is to be modified.
     * @param newDesc the new description to be set
     * @return a boolean that is true if the modification succeeded (Null if no such item is found).
     */
    @Override
    public boolean setMenuItemDescription(String id, String newDesc) {
        return menu.setItemDescription(id, newDesc);
    }

    /**
     * Getter for a given menu item's description
     * @param id the id of the item in the menu
     * @return the description if found (null if not found)
     */
    @Override
    public String getMenuItemDescription(String id) {
        return menu.getItemDescription(id);
    }

    /**
     * Get the name of an item on the menu
     * @param id the id of the menu item
     * @return the plaintext name of the menu item if found (Null if not found).
     */
    @Override
    public String getMenuItemName(String id) {
        return menu.getItemName(id);
    }

    /**
     * Getter for a given menu item's discount rate
     * @param id the menu item
     * @return the discount in percentage as a float (null if no such menu item exists)
     */
    @Override
    public Float getMenuItemDiscount(String id) {
        return menu.getDiscount(id);
    }

    /**
     * Setting the discount of a given menu item.
     * @param id the menu item to modify
     * @param discount the new discount to set for the given menu item
     * @return if the modification succeeded or not (null if no such item was found)
     */
    @Override
    public boolean setMenuItemDiscount(String id, float discount) {
        return menu.setDiscount(id, discount);
    }

    /**
     * Set the name of an item on the menu
     * @param id menu item's id
     * @param newName the new name to set the item to
     * @return a boolean value that is true if the modification succeeded (Null if item not found)
     */
    @Override
    public boolean setMenuItemName(String id, String newName) {
        return menu.setItemName(id, newName);
    }

    /**
     * Getter for all items on the menu
     * @return An arrayList of sellable objects.
     */
    @Override
    public ArrayList<Sellable> getAllMenuItems() {
        return menu.getAllItems();
    }

    /**
     * A function similar to getAllMenuItems() but only returns available items.
     * @return an arrayList of *available* items.
     */
    @Override
    public ArrayList<Sellable> getAvailableMenuItems() {
        return menu.getAvailableItems();
    }

    /**
     * Getter to get the next order in the orderBook
     * @return the next non-cancelled order.
     */
    @Override
    public Orderable getNextOrder() {
        return orderBook.getNextOrder();
    }

    /**
     * Getter for orders in the orderBook by id
     * @param id the id of the order
     * @return the order (Null if no such order is found)
     */
    @Override
    public Orderable getOrder(String id) {
        return orderBook.getOrder(id);
    }

    /**
     * Add order to the foodTruck's orderbook.
     * @param item the item to be added to the orderbook.
     */
    @Override
    public void addOrder(Orderable item) {
        orderBook.addOrder(item);
    }


    /**
     * Completely remove an order from the foodTruck. This should not be used, instead use setOrderStatus and set the
     * specified order to cancelled.
     * @param id the id of the order to remove
     * @return if the order was successfully removed (false if no such order was found)
     */
    @Override
    public boolean removeOrder(String id) {
        return orderBook.removeOrder(id);
    }

    /**
     * Set an order to completed (exactly the same as calling:
     * @<code>
     *     setOrderStatus(id, Orderable.COMPLETED)
     * </code>
     * @param id the id of the order to set as complete
     * @return if the order was successfully set as completed (False if no such order is found)
     */
    @Override
    public boolean completeOrder(String id) {
        return orderBook.completeOrder(id);
    }

    /**
     * Sets the status of a given order, please use the interface constants when calling this function. E.g.:
     * @<code>
     *     setOrderStatus(id, Orderable.COMPLETED)
     * </code>
     * @param id The id of the order
     * @param status the new status of the order
     * @return if the order's status was successfully changed. (False if no such order is found).
     */
    @Override
    public boolean setOrderStatus(String id, int status) {
        return orderBook.setOrderStatus(id, status);
    }

    /**
     * Get the items in an order
     * @param id the order id
     * @return the ArrayList of items in the order, null if no such order exists
     */
    @Override
    public ArrayList<Sellable> getOrderItems(String id) {
        return orderBook.getOrderItems(id);
    }

    /**
     * Add an item to an order
     * @param id the order to add items to
     * @param item the item to add to the order
     * @param quantity the number of times to add the given item to the order
     * @return if the item was successfully added to the order.
     */
    @Override
    public boolean addItemToOrder(String id, Sellable item, int quantity) {
        return orderBook.addItemToOrder(id, item, quantity);
    }

    /**
     * Modify the foodTruck's order at a specific index
     * @param id the id of the order to modify
     * @param index the index of the item in the order to modify
     * @param item the replacement item for the order
     * @return a boolean explaining if the modification was successful
     */
    @Override
    public boolean modifyOrder(String id, int index, Sellable item) {
        return orderBook.modifyOrder(id, index, item);
    }

    /**
     * Remove an item from the foodTrucks given order at a given index.
     * @param id the id of the order
     * @param index the index at which to remove an item
     * @return if the removal of item from order succeeded.
     */
    @Override
    public boolean removeItemFromOrder(String id, int index) {
        return orderBook.removeItemFromOrder(id, index);
    }

    /**
     * Get the status of a given order
     * @param id id of the order
     * @return the status of the order (null if no such order is found).
     */
    @Override
    public int getOrderStatus(String id) {
        return orderBook.getOrderStatus(id);
    }

    /**
     * Get the total price of a given order
     * @param id the order's id
     * @return the price of the order as float if found, (null if no order is found).
     */
    @Override
    public float getTotalPriceOfOrder(String id) {
        return orderBook.getTotalPriceOfOrder(id);
    }

    /**
     * Check if an order is canclled. This is the same as calling:
     * @<code>
     *     getOrderStatus(id) == Orderable.CANCELLED
     * </code>
     * @param id the id of the order to check.
     * @return if the order is cancelled or not (false if no such order is found)
     */
    @Override
    public boolean isOrderCancelled(String id) {
        return orderBook.isOrderCancelled(id);
    }

    /**
     * Set the discount on a given order
     * @param id the id of the order
     * @param percentage the percentage of discount to set as a float.
     *                   TODO: ADD RETURN VALUE AS BOOLEAN
     */
    @Override
    public void setOrderDiscount(String id, float percentage) {
        orderBook.setOrderDiscount(id, percentage);
    }

    /**
     * Get the discount for a given order
     * @param id the id of the order
     * @return float representing the discount of the order if found (null if not found).
     */
    @Override
    public float getOrderDiscount(String id) {
        return orderBook.getOrderDiscount(id);
    }

    /**
     * Update the time remaining for an order to be processed
     * @param id the id of the order
     * @param time the amount of time remaining on the order (?)
     *             TODO: SHOULD THIS BE A TIMESTAMP OR AN ETA
     */
    @Override
    public void updateOrderETA(String id, float time) {
        orderBook.updateOrderETA(id, time);
    }
}
