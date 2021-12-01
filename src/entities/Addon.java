package entities;

import org.json.JSONObject;

import java.util.List;

/**
 * The type Addon.
 */
public class Addon implements JSONable{
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
     * Instantiates a new Addon.
     *
     * @param id          the id
     * @param name        the name
     * @param price       the price
     * @param addonTypes  the addon types
     * @param isAvailable the is available
     */
    public Addon(String id, String name, float price, List<Integer> addonTypes, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addonTypes = addonTypes;
        this.isAvailable = isAvailable;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets addon types.
     *
     * @return the addon types
     */
    public List<Integer> getAddonTypes() {
        return addonTypes;
    }

    /**
     * Sets addon types.
     *
     * @param addonTypes the addon types
     */
    public void setAddonTypes(List<Integer> addonTypes) {
        this.addonTypes = addonTypes;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public JSONObject jsonify() {
        JSONObject final_data = new JSONObject();;
        final_data.put("id", this.id);
        final_data.put("name", this.name);
        final_data.put("price", this.price);

        // TODO: SEE how this works out into the JSON.
        final_data.put("types", this.addonTypes);

        final_data.put("isAvailable", this.isAvailable);
        return final_data;
    }
}