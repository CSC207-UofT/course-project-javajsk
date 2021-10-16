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

    String addItem(Sellable item);

    Sellable getItem(String id);

    boolean removeItem(String id);

    float getItemPrice(String id);

    boolean setItemPrice(String id, float price);

    boolean getItemAvailability(String id);

    boolean setItemAvailability(String id, boolean available);

    String getItemDescription(String id);

    boolean setItemDescription(String id, String newDesc);

    String getItemName(String id);

    boolean setItemName(String id, String newName);

    Float getDiscount(String id);

    boolean setDiscount(String id, float discount);

    ArrayList<Sellable> getAllItems();

    ArrayList<Sellable> getAvailableItems();

    boolean hasItem(String id);


}
