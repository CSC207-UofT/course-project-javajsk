package Entities.Interfaces;

public interface ICart {

    public float getTotalPrice();

    // Remove an item by its value e.g. if cart contains an item == item, then
    // remove that item

    // Note boolean values to assert if successful.
    public boolean removeItem(IFood item);

    public boolean setItemQuantity(IFood item, int quantity);

    public boolean addItem(IFood item, IAddon[] addons, int[] addonQuantities);

    public boolean setAddons(IFood item,int index, IAddon[] addons, int[] addonQuantities);



}
