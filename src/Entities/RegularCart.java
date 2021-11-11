package Entities;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Public class that stores a user's cart of selected foods and corresponding addons.
 */
public class RegularCart implements ICart {

    /**
     * A class that makes storage of addon information easier.
     */
    public static class foodInformation{
        List<HashMap<IAddon, Integer>> singleFood;

        public foodInformation(List<HashMap<IAddon, Integer>> data){
            this.singleFood = data;
        }

        public float getPrice(){
            float sum = 0;
            for(HashMap<IAddon, Integer> singletonData : this.singleFood){
                for(IAddon addon : singletonData.keySet()){
                    sum += addon.getPrice() * singletonData.get(addon);
                }
            }
            return sum;
        }

    }
    /**
     * Use a hashmap to store the foods and the corresponding information about the food ordered.
     * HashMap<IAddon, Integer>[] is the information about the addons
     */
    private HashMap<IFood,  List<foodInformation>> contents;

    /**
     * Constructor for regularCart object
     */
    public RegularCart(HashMap<IFood,  List<foodInformation>> contents){
        this.contents = contents;
    }

    /**
     * A method to get the total price of the given cart.
     * @return a float representing the total price of the cart.
     */
    @Override
    public float getTotalPrice() {
        float sum = 0;
        for (IFood food: contents.keySet()) {
            List<foodInformation> orders = contents.get(food);
            sum += food.getPrice() * orders.size();
            for(foodInformation items : orders){
                sum += items.getPrice();
            }
        }
        return sum;
    }

    /**
     * A method to remove an item at the given index.
     * @param item Which food item to remove
     * @param index The index at which to remove the order.
     * @return if the removal succeeded or not.
     */
    public boolean removeItem(IFood item, int index) {
        // removes item at given index.
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
    @Override
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
            List<foodInformation> foodData = contents.get(item);
            if(foodData.size() == quantity){
                return true;
            }
            if(foodData.size() < quantity){
                foodInformation last_item = foodData.get(foodData.size()-1);
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
    public boolean addItem(IFood item, List<HashMap<IAddon, Integer>> addonInfo) {
        if(contents.containsKey(item)){
            contents.get(item).add(new foodInformation(addonInfo));
            return true;
        }
        else {
            List<foodInformation> order = new ArrayList<>();
            order.add(new foodInformation(addonInfo));
            contents.put(item, order);
            return true;
        }
    }

    /**
     * Identical to the function addItem, except it will also take in a quantity to set the total number of foods to.
     * @param item the food item to be added
     * @param addonInfo the list of list of addons for the given food
     *               i.e. if food has 3 components, the first element of the addons corresponds to the first
     *               singleton of item.
     * @param quantity the new target quantity (this will simply just call the setQuantity function).
     * @return if adding the item succeeded
     *
     * Note: This function will simply add to the info of the cart if the item is already present.
     */
    public boolean addItem(IFood item, int quantity, List<HashMap<IAddon, Integer>> addonInfo) {
        foodInformation info = new foodInformation(addonInfo);
        if(contents.containsKey(item)){
            contents.get(item).add(info);
            return this.setItemQuantity(item, quantity);
        }
        else {
            List<foodInformation> order = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                order.add(info);
            }
            contents.put(item, order);
            return true;
        }
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
    public boolean setAddons(IFood item, int index,  List<HashMap<IAddon, Integer>> addonInfo) {
        if(contents.containsKey(item)){
            List<foodInformation> order = contents.get(item);
            if(order.size() <= index){
                throw new IllegalArgumentException("Index greater than size of List.");
            }
            order.get(index).singleFood = addonInfo;
            return true;
        }
        return false;
    }

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
     * Get the addons of a food at the given position.
     * @param item the food for which to get the addons set
     * @param index the index at which to get the addons set.
     * @return
     */
    @Override
    public  List<HashMap<IAddon, Integer>> getAddons(IFood item, int index) {
        if(contents.containsKey(item)){
            return contents.get(item).get(index).singleFood;
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

}
