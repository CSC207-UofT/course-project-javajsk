package Interfaces;

import java.util.ArrayList;

public interface Menu {

    String addItem(Sellable item);

    Sellable getItem(String id);

    boolean removeItem(String id);

    float getItemPrice(String id);

    boolean setItemPrice(String id, double price);

    boolean getItemAvailability(String id);

    boolean setItemAvailability(String id, boolean available);

    boolean setItemDescription(String id, String newDesc);

    String getItemDescription(String id);

    String getItemName(String id);

    Float getDiscount(String id);

    boolean setDiscount(String id, float discount);

    boolean setItemName(String id, String newName);

    ArrayList<Sellable> getAllItems();

    ArrayList<Sellable> getAvailableItems();

    boolean hasItem(String id);


}
