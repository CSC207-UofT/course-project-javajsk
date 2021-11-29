package Entities.Interfaces;

import java.util.List;

/**
 * The ICart Interface
 */
public interface ICart {
    /**
     * A method that returns the id of the cart
     * @return id of cart
     */
    String getId();

    /**
     * A method that returns the related shopId of the cart
     * @return id of shop
     */
    String getShopId();

    /**
     * Get the quantity of a given item in the cart.
     * @param item the item being looked for
     * @return how many of type "item" are in the cart.
     */
    int getQuantity(IFood item);

    /**
     * Get an array of unique food types that have been ordered into the cart.
     * @return the array of food types.
     */
    IFood[] getFoods();

    /**
     * Get the addons of a food at the given position.
     * @param item the food for which to get the addons set
     * @param index the index at which to get the addons set.
     * @return list of selections
     */
    List<ISelection> getAddons(IFood item , int index);

    boolean setAddons(IFood item, int index, List<ISelection> addons);

    /**
     * Set the number of one specific food in a cart. e.g. if there is 1 burger, but you want 3. Use this method.
     * @param item the item for which to change the quantity
     * @param quantity the new expected quantity (can be greater, equal to
     *                or less than the current quantity, but not less than 0)
     * @return if the setting succeeded or not
     */
    boolean setItemQuantity(IFood item, int quantity);

    /**
     * A method that removes the given food item from the cart and returns
     * whether the item was successfully removed.
     * @param item food item to remove
     * @param index which instance of the food item in cart to remove (index = 0 if there is only one in cart)
     * @return whether item was successfully removed
     */
    boolean removeItem(IFood item, int index);

    /**
     * Add an item to the order
     * @param item the food item to be added
     * @param addons the list of addon information for the given food
     *               i.e. if food has 3 components, the first element of the addons corresponds to the first
     *               singleton of item.
     * @return if adding the item succeeded
     *
     * Note: This function will simply add to the info of the cart if the item is already present.
     */
    boolean addItem(IFood item, List<ISelection> addons);

    /**
     * A method that returns whether there are any items in the cart (in contents)
     * @return whether contents is empty
     */
    boolean isEmpty();



}
