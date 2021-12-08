package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Cart;
import entities.Food;
import entities.Addon;
import entities.Selection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of a repository with type cart
 */
public class CartDB implements Repository<Cart> {
    DBGateway databaseConnector;
    final String tableName = "Cart";

    /**
     * Instantiates a cart database
     * @param databaseConnector the connector to the database
     */
    public CartDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Method for getting a cart entity with an id from the database
     * @param id the id of the cart
     * @return the cart entity
     */
    @Override
    public Cart read(String id) {
        return loadCartFromJSON(databaseConnector.read(tableName, id));
    }

    /**
     * Method for updating a cart in the database
     * @param id the id of the cart being updated
     * @param item the updated cart information
     * @return whether the update was successful or not
     */
    @Override
    public boolean update(String id, Cart item) {
        return databaseConnector.update(tableName, id, loadJSONFromCart(item));
    }


    /**
     * Method for creating a new cart entry in the database
     * @param item the new cart information
     * @return the id of the new cart
     */
    @Override
    public String create(Cart item) {
        return databaseConnector.create(tableName, loadJSONFromCart(item));
    }

    /**
     * Method for reading multiple cart entries from the database
     * @param parameter the parameter to look up in the database
     * @param needle the value of the parameter to find in the database
     * @return a list of cart entities that match the requirements
     */
    @Override
    public List<Cart> readMultiple(String parameter, String needle) {
        List<Cart> cartList = new ArrayList<>();
        List<JSONObject> rawCarts = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawCart: rawCarts){
            cartList.add(loadCartFromJSON(rawCart));
        }
        return cartList;
    }


    /**
     * Method for finding a cart entry in the database
     * @param fieldName the field to search by
     * @param needle the value of the field to search
     * @return the first cart entity that matches the requirements
     */
    @Override
    public Cart findOneByFieldName(String fieldName, String needle) {
        return loadCartFromJSON(databaseConnector.readOne(tableName,fieldName,needle));
    }

    /**
     * Method for converting a cart entity to a JSON object
     * @param cart the cart to convert
     * @return the JSON object
     */
    public static JSONObject loadJSONFromCart(Cart cart){
        return new JSONObject(cart.toString());
    }
  


  /**
     * Method for converting a selection entity to a JSON object
     * @param selection the selection entity
     * @return the JSON object
     */
    public static JSONObject loadJSONfromSelection(Selection selection){
        return new JSONObject(selection.toString());
    }

    /**
     * Method for creating a cart entity from a JSON object
     * @param object the JSON object
     * @return the corresponding cart entity
     */
    public Cart loadCartFromJSON(JSONObject object){
        if(!object.has("id") || !object.has("shopId") || !object.has("contents") ){
            return null;
        }
        try{
            String id = object.getString("id");
            String shopId = object.getString("shopId");
            JSONObject contentsRaw = object.getJSONObject("contents");
            HashMap<Food, List<Selection[]>> contents = loadContents(contentsRaw);

            return new Cart(id, shopId,contents);
        }catch (JSONException e){
            return null;
        }
    }

    /**
     * Method for loading cart content from a JSON object
     * @param rawCont the cart content data as a JSON object
     * @return the cart contents as a hash map
     * @throws JSONException if there is an error with retrieving selections
     */
    private HashMap<Food, List<Selection[]>> loadContents(JSONObject rawCont) throws JSONException {
        Iterator<String> str = rawCont.keys();
        FoodDB foodLoader = new FoodDB(databaseConnector);
        HashMap<Food, List<Selection[]>> finalStore = new HashMap<>();
        while (str.hasNext()) {
            String foodId = str.next();
            Food food = foodLoader.read(foodId);
            JSONArray rawSelections = rawCont.getJSONArray(foodId);
            finalStore.put(food, loadSelections(rawSelections));
        }
        return finalStore;
    }

    /**
     * Method for loading the selections for a cart
     * @param data the selections in a JSON array
     * @return the data converted to a nested list of selection entities
     */
    private List<Selection[]> loadSelections(JSONArray data){
        List<Selection[]> selectionData = new ArrayList<>();
        for(int i =0; i< data.length();i++){
            JSONArray current = data.getJSONArray(i);
            Selection[] temporary = new Selection[current.length()];
            for(int x =0;x<current.length();x++){
                temporary[i] = parseSelection(current.getJSONObject(x));
            }
            selectionData.add(temporary);
        }
        return selectionData;
    }

    /**
     * Method for creating selection entities from a JSON object
     * @param rawSelection selection data in JSON object
     * @return the corresponding selection entity
     * @throws JSONException if there's an error getting data from the JSON object
     */
    public Selection parseSelection(JSONObject rawSelection) throws JSONException{
        Iterator<String> str = rawSelection.keys();
        AddonDB addonLoader = new AddonDB(databaseConnector);
        HashMap<Addon, Integer> finalValue = new HashMap<>();
        while (str.hasNext()) {
            String addonId = str.next();
            Addon addon = addonLoader.read(addonId);
            int quantity = rawSelection.getInt(addonId);
            finalValue.put(addon, quantity);
        }
        return new Selection(finalValue);
    }
}
