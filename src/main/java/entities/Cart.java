package entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The Cart entity class
 */
public class Cart {

    /**
     * The cart id.
     */
    public String id;
    /**
     * The Shop id.
     */
    protected String shopId;

    /**
     * the cart contents.
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
     * Instantiates a new empty cart
     */
    public Cart() {
        this.id = "";
        this.shopId = "N/A";
        this.contents = new HashMap<>();
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
     * Gets cart contents.
     *
     * @return the contents
     */
    public HashMap<Food, List<Selection[]>> getContents() {
        return contents;
    }

    /**
     * Sets cart contents.
     *
     * @param contents the contents
     */
    public void setContents(HashMap<Food, List<Selection[]>> contents) {
        this.contents = contents;
    }

    /**
     * Method for adding a new item to cart
     *
     * @param food       the food item
     * @param selections the list of selection chosen
     * @return whether the new item was added successfully
     */
    public boolean addItem(Food food, Selection[] selections) {
        if (this.shopId.equals("N/A")) {
            this.setShopId(food.getShopId());
        }

        if (!food.getShopId().equals(this.getShopId())) {
            return false;
        }

        if (this.contents.containsKey(food)) {
            this.contents.get(food).add(selections);
        } else {
            List<Selection[]> store = new ArrayList<>();
            store.add(selections);
            this.contents.put(food, store);
        }
        return true;
    }

    /**
     * Method for removing an item from the cart
     *
     * @param food       the food item
     * @param selections the list of selections chosen
     */
    public void removeItem(Food food, Selection[] selections) {
        if (this.contents.containsKey(food)) {
            this.contents.get(food).remove(selections);
        }
    }

    /**
     * Method for checking if cart is empty
     *
     * @return whether the cart is empty
     */
    public boolean isEmpty() {
        return this.contents.isEmpty();
    }

    /**
     * Method for clearing the contents of a cart
     */
    public void empty() {
        this.contents = new HashMap<>();
    }

    /**
     * Method for changing the selections of a food
     *
     * @param food   the food receiving the selection change
     * @param oldSel the list of old selections
     * @param newSel the list of new selections
     */
    public void modifySelection(Food food, Selection[] oldSel, Selection[] newSel) {
        if (!this.contents.containsKey(food)) {
            return;
        }
        List<Selection[]> selections = this.contents.get(food);
        int index = selections.indexOf(oldSel);
        if (index == -1) {
            return;
        }
        selections.remove(oldSel);
        selections.add(index, newSel);
    }


    /**
     * Method returns cart as a string representation
     *
     * @return string representation of cart
     */
    @Override
    public String toString() {
        JSONObject finalValue = new JSONObject();
        assert !this.id.equals("N/A");
        finalValue.put("id", this.id);
        finalValue.put("shopId", this.shopId);
        JSONObject contentsJson = new JSONObject();
        HashMap<Food, List<Selection[]>> contents = this.contents;
        for (Food food : contents.keySet()) {
            List<Selection[]> selections = contents.get(food);
            JSONArray selectionsJson = new JSONArray();
            for (Selection[] selectionArr : selections) {
                selectionsJson.put(loadJSONfromSelectionLst(selectionArr));
            }
            contentsJson.put(food.getId(), selectionsJson);
        }
        finalValue.put("contents", contentsJson);
        return finalValue.toString();
    }

    /**
     * Method that returns list of selections objects represented as a JSONArray
     *
     * @param input list of selections to convert
     * @return JSONArray representation of list
     */
    public static JSONArray loadJSONfromSelectionLst(Selection[] input) {
        JSONArray jsonSelectionList = new JSONArray();
        for (Selection sel : input) {
            jsonSelectionList.put(new JSONObject(sel.toString()));


        }
        return jsonSelectionList;
    }
}
