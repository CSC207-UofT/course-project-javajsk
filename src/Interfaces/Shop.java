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
    String getShopName();

    void setShopName(String newName);

    String getLocation();

    void setLocation(String location);

    boolean getShopStatus();

    void setShopStatus(boolean status);

    /** The following methods are related to the Menu of the shop**/
    String addMenuItem(Sellable item);

    Sellable getMenuItem(String id);

    boolean removeMenuItem(String id);

    float getMenuItemPrice(String id);

    boolean setMenuItemPrice(String id, float price);

    boolean getMenuItemAvailability(String id);

    boolean setMenuItemAvailability(String id, boolean available);

    boolean setMenuItemDescription(String id, String newDesc);

    String getMenuItemDescription(String id);

    String getMenuItemName(String id);

    boolean setMenuItemName(String id, String newName);

    Float getMenuItemDiscount(String id);

    boolean setMenuItemDiscount(String id, float discount);

    ArrayList<Sellable> getAllMenuItems();

    ArrayList<Sellable> getAvailableMenuItems();


    /** The following methods are related to the orderbook of the shop**/
    Orderable getNextOrder();

    Orderable getOrder(String id);

    void addOrder(Orderable item);

    boolean removeOrder(String id);

    boolean completeOrder(String id);

    boolean setOrderStatus(String id, int status);

    ArrayList<Sellable> getOrderItems(String id);

    boolean addItemToOrder(String id, Sellable item, int quantity);

    boolean modifyOrder(String id, int index, Sellable item);

    boolean removeItemFromOrder(String id, int index);

    int getOrderStatus(String id);

    float getTotalPriceOfOrder(String id);

    boolean isOrderCancelled(String id);

    void setOrderDiscount(String id, float percentage);

    float getOrderDiscount(String id);

    void updateOrderETA(String id,float time);


}
