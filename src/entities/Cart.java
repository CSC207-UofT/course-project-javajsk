package entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * The Cart entity to represent what a customer wants to order.
 */
public class Cart implements JSONable{

    public String id;
    protected String shopId;
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
     * A method that returns the id of the cart
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * A method that sets the id of the cart
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A method that returns the shop id of the cart
     *
     * @return the id
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * A method that sets the shop id of the cart
     *
     * @param id the id
     */
    public void setShopId(String id) {
        this.shopId = id;
    }

    /**
     * A method that returns the contents of the cart
     *
     * @return the contents
     */
    public HashMap<Food, List<Selection[]>> getContents() {
        return contents;
    }

    /**
     * A method that sets the contents of the cart.
     *
     * @param contents the contents of the cart
     */
    public void setContents(HashMap<Food, List<Selection[]>> contents) {
        this.contents = contents;
    }

    /**
     * A method that returns the corresponding JSON object of the addon
     * @return addon as a JSONObject
     */
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
