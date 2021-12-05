package entities;

import org.json.JSONObject;

import java.util.List;

/**
 * The type Singleton.
 */
public class Singleton{
    /**
     * The Id.
     */
    public String id;
    /**
     * The Price.
     */
    protected float price;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Allowed addon types.
     */
    protected List<Integer> allowedAddonTypes;
    /**
     * The Default selection.
     */
    protected Selection defaultSelection;
    /**
     * The Availability.
     */
    protected boolean availability;

    protected String shopId;

    /**
     * Instantiates a new Singleton.
     *
     * @param id                the id
     * @param price             the price
     * @param name              the name
     * @param description       the description
     * @param allowedAddonTypes the allowed addon types
     * @param defaultSelection  the default selection
     * @param availability      the availability
     */
    public Singleton(String id, float price, String name, String description, List<Integer> allowedAddonTypes,
                     Selection defaultSelection, boolean availability, String shopId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.allowedAddonTypes = allowedAddonTypes;
        this.defaultSelection = defaultSelection;
        this.availability = availability;
        this.shopId = shopId;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets allowed addon types.
     *
     * @return the allowed addon types
     */
    public List<Integer> getAllowedAddonTypes() {
        return allowedAddonTypes;
    }

    /**
     * Sets allowed addon types.
     *
     * @param allowedAddonTypes the allowed addon types
     */
    public void setAllowedAddonTypes(List<Integer> allowedAddonTypes) {
        this.allowedAddonTypes = allowedAddonTypes;
    }

    /**
     * Gets default selection.
     *
     * @return the default selection
     */
    public Selection getDefaultSelection() {
        return defaultSelection;
    }

    /**
     * Sets default selection.
     *
     * @param defaultSelection the default selection
     */
    public void setDefaultSelection(Selection defaultSelection) {
        this.defaultSelection = defaultSelection;
    }

    /**
     * Is availability boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return availability;
    }

    /**
     * Sets availability.
     *
     * @param availability the availability
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Replace a singleton with another
     *
     * @param newSingleton  the replacement singleton
     */
    public void replace(Singleton newSingleton) {
        this.id = newSingleton.getId();
        this.price = newSingleton.getPrice();
        this.name = newSingleton.getName();
        this.description = newSingleton.getDescription();
        this.allowedAddonTypes = newSingleton.getAllowedAddonTypes();
        this.defaultSelection = newSingleton.getDefaultSelection();
        this.availability = newSingleton.isAvailable();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public boolean isValidSelection(Selection selection){
        for(Addon addon: selection.getSelectedAddons()){
            List<Integer> addonTypes = addon.getAddonTypes();
            for(int i =0;i<addonTypes.size();i++){
                if(!this.allowedAddonTypes.contains(addonTypes.get(i))){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("price", this.price);
        jsonObject.put("name", this.name);
        jsonObject.put("description", this.description);
        jsonObject.put("allowedAddonTypes", this.allowedAddonTypes);
        jsonObject.put("defaultSelection", this.defaultSelection.toString());
        jsonObject.put("availability", this.availability);
        jsonObject.put("shopId", this.shopId);
        return jsonObject.toString();
    }



}
