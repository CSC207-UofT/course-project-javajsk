package entities;

import org.json.JSONObject;

import java.util.List;

/**
 * The Addon entity class
 */
public class Addon {

    /**
     * The Id.
     */
    public String id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Price.
     */
    protected float price;
    /**
     * The Addon types.
     */
    protected List<Integer> addonTypes;
    /**
     * The Is available.
     */
    protected boolean isAvailable;
    /**
     * The Shop id.
     */
    protected String shopId;

    /**
     * Instantiates a new Addon.
     *
     * @param id          the id
     * @param name        the name
     * @param price       the price
     * @param addonTypes  the addon types
     * @param isAvailable the is available
     * @param shopId      the shop id
     */
    public Addon(String id, String name, float price, List<Integer> addonTypes, boolean isAvailable, String shopId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addonTypes = addonTypes;
        this.isAvailable = isAvailable;
        this.shopId = shopId;
    }

    /**
     * A method that returns the name of the addon.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * A method that sets the name of the addon.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method that returns the price of the addon.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * A method that sets the price of the addon.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * A method that returns the addon types of the addon.
     *
     * @return the addonTypes
     */
    public List<Integer> getAddonTypes() {
        return addonTypes;
    }

    /**
     * A method that sets the addon types of the addon.
     *
     * @param addonTypes the price
     */
    public void setAddonTypes(List<Integer> addonTypes) {
        this.addonTypes = addonTypes;
    }

    /**
     * A method that returns whether the addon is available
     *
     * @return the availability of the addon
     */
    public boolean getAvailable() {
        return this.isAvailable;
    }

    /**
     * A method that sets the availability of the addon.
     *
     * @param available the price
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    /**
     * A method that returns the id of the addon.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * A method that sets the id of the addon.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A method that returns the shop id of the addon
     *
     * @return shop id of the addon
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * A method that sets the shop id of the addon
     *
     * @param shopId new shop id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Addon)) {
            return false;
        }
        return ((Addon) object).getId().equals(this.id);
    }

    /**
     * A method that returns the entity represented as a string
     *
     * @return string representation of the addon
     */
    @Override
    public String toString() {
        JSONObject finalObject = new JSONObject();
        assert !this.id.equals("N/A");
        finalObject.put("id", this.id);
        finalObject.put("price", this.price);
        finalObject.put("name", this.name);
        finalObject.put("addonTypes", this.addonTypes);
        finalObject.put("isAvailable", this.isAvailable);
        finalObject.put("shopId", this.shopId);
        return finalObject.toString();
    }
}
