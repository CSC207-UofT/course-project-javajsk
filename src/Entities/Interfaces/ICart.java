package Entities.Interfaces;

import java.util.HashMap;
import java.util.List;

public interface ICart {

    public float getTotalPrice();

    // Remove an item by its value e.g. if cart contains an item == item, then
    // remove that item

    // Note boolean values to assert if successful.
    public boolean removeItem(IFood item, int index);

    public String getId();

    public boolean setItemQuantity(IFood item, int quantity);

    public boolean addItem(IFood item, ISelection addons);

    public boolean setAddons(IFood item, int index, ISelection addons);

    public int getQuantity(IFood item);

    public ISelection getAddons(IFood item , int index);

    public IFood[] getFoods();

    public boolean isEmpty();



}
