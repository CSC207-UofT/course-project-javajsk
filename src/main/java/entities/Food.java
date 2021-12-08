package entities;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Food entity class
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
    /**
     * The Shop id.
     */
    protected String shopId;

    /**
     * Instantiates a new Food.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param components  the components
     * @param shopId      the shop id
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

    /**
     * Gets shop id.
     *
     * @return the shop id
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Sets shop id.
     *
     * @param shopId the shop id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * Is valid selections boolean.
     *
     * @param selections the selections
     * @return the boolean
     */
    public boolean isValidSelections(Selection[] selections){
        for(int i =0; i<this.components.length; i++){
            if(!components[i].isValidSelection(selections[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Add singleton.
     *
     * @param singleton the singleton
     */
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

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        for(Singleton singleton: this.components){
            if(!singleton.getAvailable()){
                return false;
            }
        }
        return true;
    }

    /**
     * Method overrides the equals method, returns whether a given object is considered
     * to be equal to this food object
     * @param object object to check if it is equal
     * @return wther object is equal to this instance of food
     */
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Food)){
            return false;
        }
        return ((Food) object).getId().equals(this.id);
    }

    /**
     * Method returns food as a string representation
     *
     * @return string representation of food
     */
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
