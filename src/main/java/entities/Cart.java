package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Cart.
 */
public class Cart{

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

    public Cart(){
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


    public boolean addItem(Food food, Selection[] selections){
        if(this.shopId.equals("N/A")){
            this.setShopId(food.getShopId());
        }

        if(!food.getShopId().equals(this.getShopId())){
            return false;
        }

        if(this.contents.containsKey(food)){
            this.contents.get(food).add(selections);
        }
        else{
            List<Selection[]> store = new ArrayList<>();
            store.add(selections);
            this.contents.put(food, store);
        }
        return true;
    }

    public void removeItem(Food food, Selection[] selections){
        if(this.contents.containsKey(food)){
            this.contents.get(food).remove(selections);
        }
    }

    public boolean isEmpty(){return this.contents.isEmpty();}

    public void empty(){
        this.contents = new HashMap<>();
    }

    public void modifySelection(Food food, Selection[] oldSel, Selection[] newSel){
        if(!this.contents.containsKey(food)){
            return;
        }
        List<Selection[]> selections = this.contents.get(food);
        int index = selections.indexOf(oldSel);
        if(index == -1){
            return;
        }
        selections.remove(oldSel);
        selections.add(index, newSel);
    }

}
