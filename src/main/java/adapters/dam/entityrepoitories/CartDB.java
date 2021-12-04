package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import EntitiesUnitTest.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CartDB implements Repository<Cart> {
    DBGateway databaseConnector;
    final String tableName = "Cart";

    public CartDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Cart read(String id) {
        return loadCartFromJSON(databaseConnector.read(tableName, id));
    }

    @Override
    public boolean update(String id, Cart item) {
        return databaseConnector.update(tableName, id, loadJSONFromCart(item));

    }


    @Override
    public String create(Cart item) {
        return databaseConnector.create(tableName, loadJSONFromCart(item));
    }

    @Override
    public List<Cart> readMultiple(String parameter, String needle) {
        List<Cart> cartList = new ArrayList<>();
        List<JSONObject> rawCarts = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawCart: rawCarts){
            cartList.add(loadCartFromJSON(rawCart));
        }
        return cartList;
    }


    @Override
    public Cart findOneByFieldName(String fieldName, String needle) {
        return loadCartFromJSON(databaseConnector.readOne(tableName,fieldName,needle));
    }

    public static JSONObject loadJSONFromCart(Cart cart){
        JSONObject finalValue = new JSONObject();
        assert !cart.getId().equals("N/A");
        finalValue.put("id", cart.getId());
        finalValue.put("shopId", cart.getShopId());
        JSONObject contentsJson = new JSONObject();
        HashMap<Food, List<Selection[]>> contents = cart.getContents();
        for(Food food: contents.keySet()){
            List<Selection[]> selections = contents.get(food);
            JSONArray selectionsJson = new JSONArray();
            for(Selection[] selectionArr: selections){
                selectionsJson.put(loadJSONfromSelectionLst(selectionArr));
            }
            contentsJson.put(food.getId(), selectionsJson);
        }
        finalValue.put("contents", contentsJson);
        return finalValue;
    }

    public static JSONArray loadJSONfromSelectionLst(Selection[] input){
        JSONArray jsonSelectionList = new JSONArray();
        for(Selection sel: input){
            jsonSelectionList.put(loadJSONfromSelection(sel));
        }
        return jsonSelectionList;
    }


    public static JSONObject loadJSONfromSelection(Selection selection){
        JSONObject jsonObject = new JSONObject();
        HashMap<Addon, Integer> singletonSelection = selection.getSingletonSelection();
        for(Addon addon: selection.getSelectedAddons()){
            jsonObject.put(addon.getId(), singletonSelection.get(addon));
        }
        return jsonObject;
    }

    public Cart loadCartFromJSON(JSONObject object){
        if(!object.has("id") || !object.has("shopId") || !object.has("contents") ){
            return null;
        }
        try{
            String id = object.getString("id");
            String shopId = object.getString("shopId");
            JSONObject contentsRaw = object.getJSONObject("contents");
            return new Cart(id, shopId, loadContents(contentsRaw));
        }catch (JSONException e){
            return null;
        }
    }

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
