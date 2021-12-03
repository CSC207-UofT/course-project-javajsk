package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Addon;
import entities.Cart;
import entities.Food;
import entities.Selection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CartDB implements Repository<Cart> {
    DBGateway databaseConnector;

    public CartDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Cart read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Cart item) {
        return false;
    }


    @Override
    public String create(Cart item) {
        return null;
    }

    @Override
    public List<Cart> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Cart findOneByFieldName(String fieldName, String needle) {
        return null;
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
        for (; str.hasNext(); ) {
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
        for (; str.hasNext(); ) {
            String addonId = str.next();
            Addon addon = addonLoader.read(addonId);
            int quantity = rawSelection.getInt(addonId);
            finalValue.put(addon, quantity);
        }
        return new Selection(finalValue);
    }
}
