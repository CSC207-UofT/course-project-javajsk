package entities;

import adapters.dam.entityrepoitories.SingletonDB;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The type Food.
 */
public class Food {
    /**
     * The Id.
     */
    public String id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Price.
     */
    protected float price;
    /**
     * The Components.
     */
    protected Singleton[] components;
    protected String shopId;

    /**
     * Instantiates a new Food.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param components  the components
     */
    public Food(String id, String name, String description, float price, Singleton[] components, String shopId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.components = components;
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
     * Get components singleton [ ].
     *
     * @return the singleton [ ]
     */
    public Singleton[] getComponents() {
        return components;
    }

    /**
     * Sets components.
     *
     * @param components the components
     */
    public void setComponents(Singleton[] components) {
        this.components = components;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public boolean isValidSelections(Selection[] selections){
        for(int i =0; i<this.components.length; i++){
            if(!components[i].isValidSelection(selections[i])){
                return false;
            }
        }
        return true;
    }

    public void addSingleton(Singleton singleton){
        Singleton[] newComponents = new Singleton[this.components.length+1];
        int index =0;
        for(int i =0; i< this.components.length;i++){
            if(this.components[i].equals(singleton)){
                return;
            }
            newComponents[i] = this.components[i];
            index = i;
        }

        newComponents[index + 1] = singleton;
        this.setComponents(newComponents);
    }

    public boolean isAvailable() {
        for(Singleton singleton: this.components){
            if(!singleton.getAvailable()){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("name", this.name);
        jsonObject.put("description", this.description);
        jsonObject.put("price", this.price);
        jsonObject.put("shopId", this.shopId);
        JSONArray arr = new JSONArray();
        for(Singleton sel: this.components){
            arr.put(sel.getId());
        }
        jsonObject.put("components", arr);
        return jsonObject.toString();
    }
}
