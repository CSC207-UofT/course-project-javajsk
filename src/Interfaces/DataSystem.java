package Interfaces;

import java.util.ArrayList;

public interface DataSystem {
    /** The following methods are for shop attributes **/
    Sellable searchMenus(String key);

    Shop getShop(String shopId);

    boolean addShop(Shop shop);

    boolean removeShop(String shopId);

    String getShopName(String shopId);

    String getLocation(String shopId);

    boolean getShopStatus(String shopId);


    /** The following methods are related to the Menu of the shop**/
    Sellable getMenuItem(String shopId, String id);

    float getMenuItemPrice(String shopId, String id);

    boolean getMenuItemAvailability(String shopId, String id);

    String getMenuItemDescription(String shopId, String id);

    String getMenuItemName(String shopId, String id);

    Float getMenuDiscount(String shopId, String id);

    ArrayList<Sellable> getAllMenuItems(String shopId);

    ArrayList<Sellable> getAvailableMenuItems(String shopId);


    /** The following methods are related to the orderbook of the foodTrucks**/
    Orderable getOrder(String shopId, String id);

    void addOrder(String shopId, Orderable item);

    boolean removeOrder(String shopId, String id);

    ArrayList<Sellable> getOrderItems(String shopId, String id);


    /**
     * Should i have the following setters for the user end?
    void addItemToOrder(String foodTruckId, String id, Sellable item);

    void modifyOrder(String foodTruckId, String id, int index, Sellable item);

    boolean removeItemFromOrder(String foodTruckId, String id, int index);

    boolean removeItemFromOrder(String foodTruckId, String orderId, String itemId);

     **/

    int getOrderStatus(String shopId, String id);

    float getTotalPriceOfOrder(String shopId, String id);

    boolean isOrderCancelled(String shopId, String id);

    void setOrderDiscount(String shopId, String id, float percentage);

    float getOrderDiscount(String shopId, String id);

    void updateOrderETA(String shopId, String id,float time);




}
