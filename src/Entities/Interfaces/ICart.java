package Entities.Interfaces;

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

    boolean addItem(IFood item, List<ISelection> addons);

    boolean setAddons(IFood item, int index, List<ISelection> addons);

    int getQuantity(IFood item);

    List<ISelection> getAddons(IFood item , int index);

    IFood[] getFoods();

    boolean isEmpty();



}
