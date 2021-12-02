package entities;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * The type Cart.
 */
public class Cart implements JSONable{
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
     * The Id.
     */
    public String id;
    /**
     * The Shop id.
     */
    protected String shopId;

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

    /**
     * The Contents.
     */
    protected HashMap<Food, List<Selection[]>> contents;

    @Override
    public JSONObject jsonify() {
        JSONObject final_data = new JSONObject();
        final_data.put("id", this.id);
        final_data.put("shopId", this.shopId);
        final_data.put("contents", this.contents);

        return final_data;
    }
}
