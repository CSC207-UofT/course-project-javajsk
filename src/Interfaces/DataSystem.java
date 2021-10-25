package Interfaces;

import java.util.ArrayList;

public interface DataSystem {
    /** The following methods are for shop attributes **/

    /**
     * Search menus for a Sellable object with the specified key, and
     * return that object.
     *
     * @param key key of the Sellable object being searched
     * @return    the Sellable object with the specified key, or null if it doesn't exist.
     */
    Sellable searchMenus(String key);

    /**
     * Get the Shop object with the specified ID
     *
     * @param shopId the ID of the Shop being searched
     * @return       the Shop with the specified shopID, or null if it doesn't exist.
     */
    Shop getShop(String shopId);

    /**
     * add a new Shop
     *
     * @param shop the Shop being added
     * @return     return true if shop has been added successfully and false otherwise.
     */
    boolean addShop(Shop shop);

    /**
     * remove a Shop
     *
     * @param shopId the ID of the Shop being removed
     * @return       return true if shop has been removed successfully and false otherwise.
     */
    boolean removeShop(String shopId);

}
