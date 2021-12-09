package entities;

import org.json.JSONObject;

/**
 * The Vendor entity class.
 */
public class Vendor extends User {
    /**
     * The Shop.
     */
    protected Shop shop;

    /**
     * Instantiates a new Vendor.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param shop     the shop
     */
    public Vendor(String id, String username, String password, Shop shop) {
        super(id, username, password);
        this.shop = shop;
    }

    /**
     * Instantiates a new Vendor.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param shopName the shop name
     * @param shopLoc  the shop loc
     */
    public Vendor(String id, String username, String password, String shopName, String shopLoc) {
        super(id, username, password);
        this.shop = new Shop(shopName, shopLoc);
    }

    /**
     * Gets shop.
     *
     * @return the shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets shop.
     *
     * @param shop the shop
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Delete shop boolean.
     *
     * @param shopId the shop id
     * @return the boolean
     */
    public boolean deleteShop(String shopId) {
        if (this.shop.getId().equals(shopId)) {
            this.shop = null;
            return true;
        }
        return false;
    }

    /**
     * Method returns vendor as a string representation
     *
     * @return string representation of vendor
     */
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("shops", new JSONObject(this.shop.toString()));

        return jsonObject.toString();
    }

}
