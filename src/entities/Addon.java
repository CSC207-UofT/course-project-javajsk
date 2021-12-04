package entities;

import org.json.JSONObject;

import java.util.List;

/**
 * The Addon Entity - represents toppings/ingredients that can be added to a food
 */
public class Addon implements JSONable{
    /**
     * Attributes:
     *
     * id - id of addon
     * name - name of addon
     * price - price addon for 1 quantity
     * addonTypes - list of integers that represent the types of addon this is
     * isAvailable - whether addon is available for order
     *
     */
    public String id;
    protected String name;
    protected float price;
    protected List<Integer> addonTypes;
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
    public boolean getAvailability() {
        return isAvailable;
    }

    /**
     * A method that sets the availability of the addon.
     *
     * @param available the price
     */
    public void setAvailability(boolean available) {
        isAvailable = available;
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
     * A method that returns the corresponding JSON object of the addon
     * @return addon as a JSONObject
     */
    @Override
    public JSONObject jsonify() {
        JSONObject final_data = new JSONObject();
        final_data.put("id", this.id);
        final_data.put("name", this.name);
        final_data.put("price", this.price);

        // TODO: SEE how this works out into the JSON.
        final_data.put("types", this.addonTypes);

        final_data.put("isAvailable", this.isAvailable);
        return final_data;
    }
}
