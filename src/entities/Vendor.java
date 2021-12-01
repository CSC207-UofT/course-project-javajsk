package entities;

import org.json.JSONObject;

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

    @Override
    public JSONObject jsonify(){
        JSONObject userData = new JSONObject();
        userData.put("id", this.id);
        userData.put("userName", this.userName);
        userData.put("hashedPassword", this.hashedPassword);
        userData.put("shop", this.shop.jsonify());

        return userData;
    }
}
