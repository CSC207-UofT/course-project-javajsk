package Entities.Regular;

import Entities.Interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Public class that stores a user's cart of selected foods and corresponding addons.
 */
public class RegularCart implements ICart {

    /**
     * Use a hashmap to store the foods and the corresponding information about the food ordered.
     * contents maps each food item to a list of a list of selections where
     * each lists of selections corresponds with the selections for each singleton item in the food
     * item and the list of list of selections corresponds to one instance of a food item in the order
     *
     * each food item is mapped to a list with length equal to the quantity of that food in the cart
     * ex. if a person wants to order 2 food items, the food is mapped to a list of length 2
     *
     * shopId is the id of the shop that this cart is ordering from
     * id is a unique id for this cart
     */
    private HashMap<IFood,  List<List<ISelection>>> contents;
    private String shopId;
    private String id;

    /**
     * Constructor for regularCart object
     */
    public RegularCart(String newShopId, HashMap<IFood,  List<List<ISelection>>> contents, String cartId){
        this.shopId = newShopId;
        this.contents = contents;
        this.id = cartId;
    }

    /**
     * A method that returns the id of the cart
     * @return id of cart
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * A method that returns the shop id of the cart
     * @return shop id
     */
    @Override
    public String getShopId(){ return this.shopId; }

    /**
     * Get the quantity of a given item in the cart.
     * @param item the item being looked for
     * @return how many of type "item" are in the cart.
     */
    @Override
    public int getQuantity(IFood item) {
        if(contents.containsKey(item)){
            return contents.get(item).size();
        }
        throw new IllegalArgumentException("No such item");
    }

    /**
     * Get an array of unique food types that have been ordered into the cart.
     * @return the array of food types.
     */
    @Override
    public IFood[] getFoods() {
        IFood[] retArr = new IFood[contents.size()];
        int i =0;
        for(IFood food: contents.keySet()){
            retArr[i++] = food;
        }
        return retArr;
    }

    /**
     * Get the addons of a food at the given position.
     * @param item the food for which to get the addons set
     * @param index the index at which to get the addons set.
     * @return list of selections
     */
    @Override
    public List<ISelection> getAddons(IFood item, int index) {
        if(contents.containsKey(item)){
            return contents.get(item).get(index);
        }
        throw new IllegalArgumentException("No such item");
    }

    /**
     * Sets the addons of a given item at a given index. E.g. if there are 3 burgers in the cart of the same food type
     * if you want to change the addons of the second burger, this method would be used.
     * @param item the item for which to modify addons
     * @param index the position at which to modify addons
     * @param addonInfo the new addon information
     * @return if the operation succeeded
     */
    @Override
    public boolean setAddons(IFood item, int index, List<ISelection> addonInfo) {
        if(contents.containsKey(item)){
            List<List<ISelection>> order = contents.get(item);
            if(order.size() <= index){
                throw new IllegalArgumentException("Index greater than size of List.");
            }
            order.set(index, addonInfo);
            return true;
        }
        return false;
    }

    /**
     * Set the number of one specific food in a cart. e.g. if there is 1 burger, but you want 3. Use this method.
     * @param item the item for which to change the quantity
     * @param quantity the new expected quantity (can be greater, equal to
     *                or less than the current quantity, but not less than 0)
     * @return if the setting succeeded or not
     */
    @Override
    public boolean setItemQuantity(IFood item, int quantity) {
        if(quantity < 1){
            // THIS SHOULD NEVER HAPPEN.
            throw new IllegalArgumentException("Quantities cannot be less than 1.");
        }

        if(contents.containsKey(item)){
            List<List<ISelection>> foodData = contents.get(item);
            if(foodData.size() == quantity){
                return true;
            }
            else if(foodData.size() < quantity){
                List<ISelection> last_item = foodData.get(foodData.size()-1);
                for(int i = 0; i < quantity - foodData.size(); i++) {
                    foodData.add(last_item);
                }
                assert foodData.size() == quantity;
                return true;
            }else{
                for(int i = 0; i < foodData.size()-quantity; i++) {
                    //keep removing last item
                    foodData.remove(foodData.size()-1);
                }
                assert foodData.size() == quantity;
                return true;
            }
        }
        return false;
    }


    /**
     * A method to remove an item at the given index.
     * @param item Which food item to remove
     * @param index The index at which to remove the order.
     * @return whether the removal was successful
     */
    @Override
    public boolean removeItem(IFood item, int index) {
        if(contents.containsKey(item)){
            int qty = contents.get(item).size();
            if (index <= qty -1) {
                contents.get(item).remove(index);
                return true;
            }else{
                throw new IllegalArgumentException("Index is out of bounds. Please check orderInfo size.");
            }
        }
        return false;
    }

    /**
     * A method to remove the last entry of a given item.
     * NOTE: This is the same as calling removeItem(item, -1) where -1 == contents.get(item).size()
     * @param item Which food item to remove.
     * @return if the removal succeeded or not.
     */
    public boolean removeItem(IFood item) {
        //simply removes the last entry of the item.
        if(contents.containsKey(item)){
            int qty = contents.get(item).size();
            contents.get(item).remove(qty-1);
            return true;
        }
        return false;
    }


    /**
     * Add an item to the order
     * @param item the food item to be added
     * @param addonInfo the list of addon information for the given food
     *               i.e. if food has 3 components, the first element of the addons corresponds to the first
     *               singleton of item.
     * @return if adding the item succeeded
     *
     * Note: This function will simply add to the info of the cart if the item is already present.
     */
    @Override
    public boolean addItem(IFood item, List<ISelection> addonInfo) {
        if(contents.containsKey(item)){
            contents.get(item).add(addonInfo);
        }
        else {
            List<List<ISelection>> order = new ArrayList<>();
            order.add(addonInfo);
            contents.put(item, order);
        }
        return true;
    }

    /**
     * A method that returns whether there are any items in the cart (in contents)
     * @return whether contents is empty
     */
    public boolean isEmpty(){
        return contents.isEmpty();
    }
}
