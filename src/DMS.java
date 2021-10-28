import Interfaces.Orderable;
import Interfaces.Sellable;
import Interfaces.Shop;
import Interfaces.DataSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class DMS implements DataSystem {
    HashMap<String, Shop> shops = new HashMap<>();

    /**
     * Search menus for a Sellable object with the specified key, and
     * return that object.
     *
     * @param key key of the Sellable object being searched
     * @return the Sellable object with the specified key, or null if it doesn't exist.
     */
    @Override
    public Sellable searchMenus(String key) {
        return null;
    }

    /**
     * Get the Shop object with the specified ID
     *
     * @param shopId the ID of the Shop being searched
     * @return the Shop with the specified shopID, or null if it doesn't exist.
     */
    @Override
    public Shop getShop(String shopId) {
        return shops.get(shopId);
    }


    /**
     * TODO: FINISH THIS FUNC
     **/
    public String generateKey(Shop shop) {
        return "";
    }

    /**
     * add a new Shop
     *
     * @param shop the Shop being added
     * @return return true if shop has been added successfully and false otherwise.
     */
    @Override
    public boolean addShop(Shop shop) {
        if (shops.values().contains(shop)) {
            return false;
        }


        shops.put(generateKey(shop), shop);
        return true;
    }

    /**
     * remove a Shop
     *
     * @param shopId the ID of the Shop being removed
     * @return return true if shop has been removed successfully and false otherwise.
     */
    @Override
    public boolean removeShop(String shopId) {
        Shop removedShop = shops.remove(shopId);
        return removedShop != null;
    }
}
