package Interfaces;

import java.util.ArrayList;

public interface OrderBook {
    Orderable getNextOrder();

    Orderable getOrder(String id);

    /** Adds an item to an existing order
     *
     * @param item to be added to an order
     */
    void addOrder(Orderable item);

    /** Removes an order
     *
     * @param id of the order to be removed
     * @return true if order was removed
     */
    boolean removeOrder(String id);

    /** Updates order status to Complete and remove it from the order queue
     *
     * @param id of order
     * @return true when order is competed
     */
    boolean completeOrder(String id);

    /** Updates the progress of an order
     *
     * @param id of order
     * @param status to update to
     * @return true of order has been updated
     */
    boolean setOrderStatus(String id, int status);

    /** Gets all item in an order
     *
     * @param id of the order
     * @return A list of all items in an order
     */
    ArrayList<Sellable> getOrderItems(String id);

    /** Adds an item to order
     *
     * @param id of order
     * @param item to be added
     * @param quantity of items
     * @return true if item was added
     */
    boolean addItemToOrder(String id, Sellable item, int quantity);

    /** Changes an order
     *
     * @param id of order
     * @param index of item in an order
     * @param item to be changed
     * @return true if order is changed
     */
    boolean modifyOrder(String id, int index, Sellable item);

    /** Removes item from order
     *
     * @param id of order
     * @param index of item to be removed
     * @return true if item has been removed
     */
    boolean removeItemFromOrder(String id, int index);

    /**
     *  Gets the status of an order
     * @param id of order
     * @return integer value corresponding to the status of the order
     */
    int getOrderStatus(String id);

    /**
     * Gets the total price of an order
     * @param id of order
     * @return the total price of an order
     */
    float getTotalPriceOfOrder(String id);

    /**
     * Checks if order is cancelled
     * @param id of order
     * @return true if order is cancelled
     */
    boolean isOrderCancelled(String id);

    /**
     * Sets a discount for a certain order
     * @param id of order
     * @param percentage of discount
     * @return true if discount is applied to an order
     */
    boolean setOrderDiscount(String id, float percentage);

    /**
     * Gets the discount associated with an order
     * @param id of order
     * @return the discount percentage, if any
     */
    float getOrderDiscount(String id);

    /**
     * Changes the time an order should be ready for pickup
     * @param id of order
     * @param time to be changed to
     * @return true if order time has been updated
     */
    boolean updateOrderETA(String id, float time); 
}

