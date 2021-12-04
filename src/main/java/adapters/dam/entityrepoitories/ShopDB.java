package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import EntitiesUnitTest.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopDB implements Repository<Shop> {
    DBGateway dbGateway;
    final String tableName = "Shop";
    @Override
    public Shop read(String id) {
        return loadShopFromJSON(dbGateway.read(tableName, id));
    }

    @Override
    public boolean update(String id, Shop item) {
        return dbGateway.update(tableName, id, loadJSONFromShop(item));

    }


    @Override
    public String create(Shop item) {
        return dbGateway.create(tableName, loadJSONFromShop(item));
    }

    @Override
    public List<Shop> readMultiple(String parameter, String needle) {
        List<Shop> shopList = new ArrayList<>();
        List<JSONObject> rawShops = dbGateway.readMultiple(tableName, parameter, needle);
        for(JSONObject rawShop: rawShops){
            shopList.add(loadShopFromJSON(rawShop));
        }
        return shopList;
    }


    @Override
    public Shop findOneByFieldName(String fieldName, String needle) {
        return loadShopFromJSON(dbGateway.readOne(tableName,fieldName,needle));
    }


    public JSONObject loadJSONFromShop(Shop shop){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", shop.getId());
        jsonObject.put("name", shop.getName());
        jsonObject.put("location", shop.getLocation());
        jsonObject.put("menu", loadJSONfromMenu(shop.getMenu()));
        jsonObject.put("isOpen", shop.isOpen());
        return jsonObject;
    }

    public Shop loadShopFromJSON(JSONObject rawShop){
        OrderDB orderLoader = new OrderDB(dbGateway);
        try{
            String id = rawShop.getString("id");
            String name = rawShop.getString("name");
            String location = rawShop.getString("location");
            boolean isOpen = rawShop.getBoolean("isOpen");
            JSONObject rawMenu = rawShop.getJSONObject("menu");
            List<Order> orderList = orderLoader.readMultiple("shopId", id);
            OrderBook orderBook = new OrderBook(orderList);
            Menu menu = loadMenuFromJSON(rawMenu);
            return new Shop(id, name, location, isOpen, menu, orderBook);
        }catch(JSONException e){
            return null;
        }
    }

    public Menu loadMenuFromJSON(JSONObject rawMenu){
        FoodDB foodLoader = new FoodDB(dbGateway);
        AddonDB addonLoader = new AddonDB(dbGateway);
        try{
            JSONArray rawFoods = rawMenu.getJSONArray("foods");
            JSONArray rawAddons = rawMenu.getJSONArray("addons");
            List<Food> foods = new ArrayList<>();
            for (int i = 0; i < rawFoods.length(); i++) {
                foods.add(foodLoader.read(rawFoods.getString(i)));
            }
            List<Addon> addons = new ArrayList<>();
            for (int i = 0; i < rawFoods.length(); i++) {
                addons.add(addonLoader.read(rawFoods.getString(i)));
            }
            return new Menu(foods, addons);
        }catch (JSONException e){
            return null;
        }
    }

    public static JSONObject loadJSONfromMenu(Menu menu){
        JSONObject jsonObject = new JSONObject();
        JSONArray foods = new JSONArray();
        JSONArray addons = new JSONArray();
        for(Food food: menu.getFoods()){
            foods.put(food.getId());
        }
        for(Addon addon: menu.getAddons()){
            addons.put(addon.getId());
        }
        jsonObject.put("foods", foods);
        jsonObject.put("addons", addons);
        return jsonObject;
    }

}
