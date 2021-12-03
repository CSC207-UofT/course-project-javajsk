package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopDB implements Repository<Shop> {
    DBGateway dbGateway;
    @Override
    public Shop read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Shop item) {
        return false;
    }


    @Override
    public String create(Shop item) {
        return null;
    }

    @Override
    public List<Shop> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Shop findOneByFieldName(String fieldName, String needle) {
        return null;
    }

    public Shop loadShopFromJSON(JSONObject rawShop){
        try{
            String id = rawShop.getString("id");
            String name = rawShop.getString("name");
            String location = rawShop.getString("location");
            Boolean isOpen = rawShop.getBoolean("isOpen");
            JSONObject rawMenu = rawShop.getJSONObject("menu");
            // TODO: Change to load from db raw list of orders.
            JSONArray rawOrderBook = rawShop.getJSONArray("orders");
            OrderBook orderBook = loadOrderBookFromJSON(rawOrderBook);
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
                foods.add(foodLoader.loadFoodFromJSON(rawFoods.getJSONObject(i)));
            }
            List<Addon> addons = new ArrayList<>();
            for (int i = 0; i < rawFoods.length(); i++) {
                addons.add(addonLoader.loadAddonFromJSON(rawFoods.getJSONObject(i)));
            }
            return new Menu(foods, addons);
        }catch (JSONException e){
            return null;
        }
    }

    public OrderBook loadOrderBookFromJSON(JSONArray rawOrderBook){
        OrderDB orderLoader = new OrderDB(dbGateway);
        List<Order> orderList = new ArrayList<>();
        for(int i=0;i<rawOrderBook.length();i++){
            orderList.add(orderLoader.loadOrderFromJSON(rawOrderBook.getJSONObject(i)));
        }
        return new OrderBook(orderList);
    }
}
