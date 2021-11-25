package Entities.Interfaces;

import java.util.HashMap;
import java.util.List;

public interface ICart {
    // Consider replacing with an abstract class?


    float getTotalPrice();

    // Remove an item by its value e.g. if cart contains an item == item, then
    // remove that item
    // Note boolean values to assert if successful.
    boolean removeItem(IFood item, int index);

    String getId();

    boolean setItemQuantity(IFood item, int quantity);

    boolean addItem(IFood item, ISelection addons);

    boolean setAddons(IFood item, int index, ISelection addons);

    int getQuantity(IFood item);

    ISelection getAddons(IFood item , int index);

    IFood[] getFoods();

    boolean isEmpty();



}
