package entities;

import java.util.List;

/**
 * The type Vendor.
 */
public class Vendor extends User{
    /**
     * The Shop.
     */
    protected Shop shop;

    /**
     * Instantiates a new Vendor.
     *
     * @param shop the shop
     */
    public Vendor(String id, String username, String password, Shop shop) {
        super(id, username, password);
        this.shop = shop;
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
}
