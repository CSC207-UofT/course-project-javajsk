package entities;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The type Food.
 */
public class Food implements JSONable {
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
     * Instantiates a new Food.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param components  the components
     */
    public Food(String id, String name, String description, float price, Singleton[] components) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.components = components;
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

    @Override
    public JSONObject jsonify() {
        JSONObject finalData = new JSONObject();
        finalData.put("id", this.id);
        finalData.put("name", this.name);
        finalData.put("description", this.description);

        JSONArray components = new JSONArray();
        for(Singleton item : this.components){
            components.put(item.jsonify());
        }

        finalData.put("components", components);
        return finalData;
    }
}
