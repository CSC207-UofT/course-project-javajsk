package entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * The type Cart.
 */
public class Cart implements JSONable{

    /**
    * The Id.
     */
    public String id;
    /**
     * The Shop id.
     */
    protected String shopId;

    /**
     * The Contents.
     */
    protected HashMap<Food, List<Selection[]>> contents;

    /**
     * Instantiates a new Cart.
     *
     * @param id       the id
     * @param shopId   the shop id
     * @param contents the contents
     */
    public Cart(String id, String shopId, HashMap<Food, List<Selection[]>> contents) {
        this.id = id;
        this.shopId = shopId;
        this.contents = contents;
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
     * Gets contents.
     *
     * @return the contents
     */
    public HashMap<Food, List<Selection[]>> getContents() {
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     */
    public void setContents(HashMap<Food, List<Selection[]>> contents) {
        this.contents = contents;
    }

    @Override
    public JSONObject jsonify() {
        JSONObject final_data = new JSONObject();
        final_data.put("id", this.id);
        final_data.put("shopId", this.shopId);
        JSONObject cartContents = new JSONObject();
        for(Food key: this.contents.keySet()){
            JSONArray foodSelection = new JSONArray();
            for(Selection[] selections: this.contents.get(key)){
                JSONArray singletonSelection = new JSONArray();
                for(Selection selection: selections){
                    singletonSelection.put(selection.jsonify());

                }
                foodSelection.put(singletonSelection);
            }

            cartContents.put(key.getId(), foodSelection);
        }
        final_data.put("contents", cartContents);
        return final_data;
    }
}
