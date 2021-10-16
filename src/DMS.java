import Interfaces.Orderable;
import Interfaces.Sellable;
import Interfaces.Shop;
import Interfaces.DataSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class DMS implements DataSystem {
    HashMap<String, Shop> shops = new HashMap<>();

    /**
     * Search menus for a Sellable object with the specified key, and
     * return that object.
     *
     * @param key key of the Sellable object being searched
     * @return    the Sellable object with the specified key, or null if it doesn't exist.
     */
    @Override
    public Sellable searchMenus(String key) {
        return null;
    }

    /**
     * Get the Shop object with the specified ID
     *
     * @param shopId the ID of the Shop being searched
     * @return       the Shop with the specified shopID, or null if it doesn't exist.
     */
    @Override
    public Shop getShop(String shopId) {
        return shops.get(shopId);
    }


    /** TODO: FINISH THIS FUNC **/
    public String generateKey(Shop shop){
        return "";
    }

    /**
     * add a new Shop
     *
     * @param shop the Shop being added
     * @return     return true if shop has been added successfully and false otherwise.
     */
    @Override
    public boolean addShop(Shop shop) {
        if(shops.values().contains(shop)){
            return false;
        }


        shops.put(generateKey(shop), shop);
        return true;
    }

    /**
     * remove a Shop
     *
     * @param shopId the ID of the Shop being removed
     * @return       return true if shop has been removed successfully and false otherwise.
     */
    @Override
    public boolean removeShop(String shopId) {
        Shop removedShop =  shops.remove(shopId);
        return removedShop != null;
    }

    /**
     * Get the name of the Shop with ID shopId in plaintext.
     *
     * @param shopId ID of the specified Shop
     * @return       return the name of the specified shop in plaintext.
     */
    @Override
    public String getShopName(String shopId) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getShopName();
        }
        return null;
    }

    /**
     * Get the location of the Shop with ID shopID
     *
     * @param shopId ID of the specified Shop
     * @return       return the location of the specified Shop.
     */
    @Override
    public String getLocation(String shopId) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getLocation();
        }
        return null;
    }

    /**
     * Get the status of the Shop with ID shopID
     *
     * @param shopId ID of the specified Shop
     * @return       return the status of the specified Shop.
     */
    @Override
    public boolean getShopStatus(String shopId) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getShopStatus();
        }
        System.out.println("This is fake.");
        return false;
    }

    /**
     * Get a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the menu item as a Sellable object.
     */
    @Override
    public Sellable getMenuItem(String shopId, String id) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItem(id);
        }
        return null;
    }

    /**
     * Get the price of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the price of the menu item.
     */
    @Override
    public float getMenuItemPrice(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemPrice(id);
        }
        return -404;
    }

    /**
     * Get the availability of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return true if the menu item is available, and false if not.
     */
    @Override
    public boolean getMenuItemAvailability(String shopId, String id) {

        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemAvailability(id);
        }
        System.out.println("Does not work.");
        return false;
    }

    /**
     * Get the item description of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the item description of the menu item.
     */
    @Override
    public String getMenuItemDescription(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemDescription(id);
        }
        return null;
    }

    /**
     * Get the item name of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the item name of the menu item.
     */
    @Override
    public String getMenuItemName(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemName(id);
        }
        return null;
    }

    /**
     * Get the discount amount of a menu item from the specified shop with ID shopId
     * and individual ID id.
     *
     * @param shopId ID of the specified Shop
     * @param id     ID of the specified item within the specified Shop
     * @return       return the discount amount of the menu item.
     */
    @Override
    public Float getMenuDiscount(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getMenuItemDiscount(id);
        }
        return null;
    }

    /**
     * Get all the menu items from the specified shop with ID shopId
     *
     * @param shopId ID of the specified Shop
     * @return       return an ArrayList containing all Sellable items in the Shop
     */
    @Override
    public ArrayList<Sellable> getAllMenuItems(String shopId) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getAllMenuItems();
        }
        return null;
    }

    /**
     * Get all the available menu items from the specified shop with ID shopId
     *
     * @param shopId ID of the specified Shop
     * @return       return an ArrayList containing all available Sellable items in the Shop
     */
    @Override
    public ArrayList<Sellable> getAvailableMenuItems(String shopId) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getAvailableMenuItems();
        }
        return null;
    }

    /**
     * Get the order from the shop with ID shopId,
     * and with ID id.
     *
     * @param shopId ID of the specified shop
     * @param id     ID of the specified order
     * @return       return the order with ID id.
     */
    @Override
    public Orderable getOrder(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrder(id);
        }
        return null;
    }

    /**
     * Add an order to the specified shop
     *
     * @param shopId ID of the specified shop
     * @param item   the order to be added
     */
    @Override
    public void addOrder(String shopId, Orderable item) {
        if(shops.containsKey(shopId)){
            shops.get(shopId).addOrder(item);
        }
        System.out.println("Fake.");
    }

    /**
     * remove an order from the specified shop
     *
     * @param shopId ID of the specified shop
     * @param id     ID of the item to be removed
     * @return       return true if the item is removed, false otherwise
     */
    @Override
    public boolean removeOrder(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).removeOrder(id);
        }
        return false;
    }

    /**
     * Get an ArrayList containing all Sellable order items of a specified shop
     *
     * @param shopId ID of the specified shop
     * @param id     ID of the items
     * @return       return ArrayList containing all Sellable order items
     */
    @Override
    public ArrayList<Sellable> getOrderItems(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrderItems(id);
        }
        return null;
    }

    /**
     * Get the order status of the specified order
     *
     * @param shopId ID of the shop containing the order
     * @param id     ID of the order
     * @return       return an int representing the order status
     */
    @Override
    public int getOrderStatus(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrderStatus(id);
        }
        return -404;
    }

    /**
     * Get the tatal price of a specified order
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @return       return the price of the specified order
     */
    @Override
    public float getTotalPriceOfOrder(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getTotalPriceOfOrder(id);
        }
        return -404;
    }

    /**
     * Check if the specified order is cancelled
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @return       return true if the order is cancelled, false otherwise.
     */
    @Override
    public boolean isOrderCancelled(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).isOrderCancelled(id);
        }
        System.out.println("s");
        return false;
    }

    /**
     * Set the discount percentage of a specified order
     *
     * @param shopId     ID of the shop that contains the order
     * @param id         ID of the specified order
     * @param percentage The new discount percentage
     */
    @Override
    public void setOrderDiscount(String shopId, String id, float percentage) {
        if(shops.containsKey(shopId)) {
            shops.get(shopId).setOrderDiscount(id, percentage);
        }
    }

    /**
     * Get the current discount of a specified order
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @return       return the current discount of the specified order
     */
    @Override
    public float getOrderDiscount(String shopId, String id) {
        if(shops.containsKey(shopId)){
            return shops.get(shopId).getOrderDiscount(id);
        }
        return -404;
    }

    /**
     * Update the ETA of a specified order
     *
     * @param shopId ID of the specified shop containing the order
     * @param id     ID of the specified order
     * @param time   The new ETA of the order
     */
    @Override
    public void updateOrderETA(String shopId, String id, float time) {
        if(shops.containsKey(shopId)){
            shops.get(shopId).updateOrderETA(id, time);
        }
    }
}
