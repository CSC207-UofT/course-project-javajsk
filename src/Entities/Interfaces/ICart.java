package Entities.Interfaces;

public interface ICart {

    public float getTotalPrice();

    // Remove an item by its value e.g. if cart contains an item == item, then
    // remove that item

    // Note boolean values to assert if successful.
    public boolean removeItem(IFood item);

    public boolean removeItem(int index); // Remove an item by index

    public boolean setItemQuantity(IFood item, int quantity);

    // Modify item quantity by index
    public boolean setItemQuantity(int index, int quantity);

    public boolean addItem(IFood item, int quantity, IAddon[] addons);

    public boolean setAddons(IFood item, IAddon[] addons);



}
