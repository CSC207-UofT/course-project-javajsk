package Interfaces;

import java.util.ArrayList;

public interface DataSystem {
    /** The following methods are for shop attributes **/

    /**
     * Search menus for a Sellable object with the specified key, and
     * return that object.
     *
     * @param key key of the Sellable object being searched
     * @return    the Sellable object with the specified key, or null if it doesn't exist.
     */
    Sellable searchMenus(String key);

    /**
     * Get the Shop object with the specified ID
     *
     * @param shopId the ID of the Shop being searched
     * @return       the Shop with the specified shopID, or null if it doesn't exist.
     */
    Shop getShop(String shopId);

    /**
     * add a new Shop
     *
     * @param shop the Shop being added
     * @return     return true if shop has been added successfully and false otherwise.
     */
    boolean addShop(Shop shop);

    /**
     * remove a Shop
     *
     * @param shopId the ID of the Shop being removed
     * @return       return true if shop has been removed successfully and false otherwise.
     */
    boolean removeShop(String shopId);

    /**
     * Get the name of the Shop with ID shopId in plaintext.
     *
     * @param shopId ID of the specified Shop
     * @return       return the name of the specified shop in plaintext.
     */
    String getShopName(String shopId);

    /**
     * Get the location of the Shop with ID shopID
     *
     * @param shopId ID of the specified Shop
     * @return       return the location of the specified Shop.
     */
    String getLocation(String shopId);

    /**
     * Get the status of the Shop with ID shopID
     *
     * @param shopId ID of the specified Shop
     * @return       return the status of the specified Shop.
     */
    boolean getShopStatus(String shopId);


    /** The following methods are related to the Menu of the shop**/

    /**
     * Get a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the menu item as a Sellable object.
     */
    Sellable getMenuItem(String shopId, String id);

    /**
     * Get the price of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the price of the menu item.
     */
    float getMenuItemPrice(String shopId, String id);

    /**
     * Get the availability of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return true if the menu item is available, and false if not.
     */
    boolean getMenuItemAvailability(String shopId, String id);

    /**
     * Get the item description of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the item description of the menu item.
     */
    String getMenuItemDescription(String shopId, String id);

    /**
     * Get the item name of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the item name of the menu item.
     */
    String getMenuItemName(String shopId, String id);

    /**
     * Get the discount amount of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the discount amount of the menu item.
     */
    Float getMenuDiscount(String shopId, String id);

    /**
     * Get all the menu items from the specified shop with ID shopId
     *
     * @param shopId ID of the specified Shop
     * @return       return an ArrayList containing all Sellable items in the Shop
     */
    ArrayList<Sellable> getAllMenuItems(String shopId);

    /**
     * Get all the available menu items from the specified shop with ID shopId
     *
     * @param shopId ID of the specified Shop
     * @return       return an ArrayList containing all available Sellable items in the Shop
     */
    ArrayList<Sellable> getAvailableMenuItems(String shopId);


    /** The following methods are related to the orderbook of the foodTrucks**/

    /**
     * Get the order from the shop with ID shopId,
     * and with ID id.
     *
     * @param shopId ID of the specified shop
     * @param id     ID of the specified order
     * @return       return the order with ID id.
     */
    Orderable getOrder(String shopId, String id);

    /**
     * Add an order to the specified shop
     *
     * @param shopId ID of the specified shop
     * @param item   the order to be added
     */
    void addOrder(String shopId, Orderable item);

    /**
     * remove an order from the specified shop
     *
     * @param shopId ID of the specified shop
     * @param id     ID of the item to be removed
     * @return       return true if the item is removed, false otherwise
     */
    boolean removeOrder(String shopId, String id);

    /**
     * Get an ArrayList containing all Sellable order items of a specified shop
     *
     * @param shopId ID of the specified shop
     * @param id     ID of the items
     * @return       return ArrayList containing all Sellable order items
     */
    ArrayList<Sellable> getOrderItems(String shopId, String id);


    /**
     * Should i have the following setters for the user end?
     void addItemToOrder(String foodTruckId, String id, Sellable item);

     void modifyOrder(String foodTruckId, String id, int index, Sellable item);

     boolean removeItemFromOrder(String foodTruckId, String id, int index);

     boolean removeItemFromOrder(String foodTruckId, String orderId, String itemId);

     **/

    /**
     * Get the order status of the specified order
     *
     * @param shopId ID of the shop containing the order
     * @param id     ID of the order
     * @return       return an int representing the order status
     */
    int getOrderStatus(String shopId, String id);

    /**
     * Get the tatal price of a specified order
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @return       return the price of the specified order
     */
    float getTotalPriceOfOrder(String shopId, String id);

    /**
     * Check if the specified order is cancelled
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @return       return true if the order is cancelled, false otherwise.
     */
    boolean isOrderCancelled(String shopId, String id);

    /**
     * Set the discount percentage of a specified order
     *
     * @param shopId     ID of the shop that contains the order
     * @param id         ID of the specified order
     * @param percentage The new discount percentage
     */
    void setOrderDiscount(String shopId, String id, float percentage);

    /**
     * Get the current discount of a specified order
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @return       return the current discount of the specified order
     */
    float getOrderDiscount(String shopId, String id);

    /**
     * Update the ETA of a specified order
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @param time   The new ETA of the order
     */
    void updateOrderETA(String shopId, String id,float time);
}
