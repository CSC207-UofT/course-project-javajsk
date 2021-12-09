package adapters.dam;

import adapters.DBGateway;
import businessrules.dai.Repository;
import entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The data access manager for Shop database.
 */
public class ShopDB implements Repository<Shop> {
    /**
     * The database gateway.
     */
    DBGateway dbGateway;
    /**
     * The Table name.
     */
    final String tableName = "Shop";

    /**
     * Instantiates a new Shop db.
     *
     * @param db the db
     */
    public ShopDB(DBGateway db) {
        this.dbGateway = db;
    }

    /**
     * @param id the id of the entry to get from the database
     * @return the constructed object of the given id from the database
     */
    @Override
    public Shop read(String id) {
        return loadShopFromJSON(dbGateway.read(tableName, id));
    }

    /**
     * Method for updating a shop entry in the database
     *
     * @param id   the id of the shop entry
     * @param item the updated shop information
     * @return whether the update was successful or not
     */
    @Override
    public boolean update(String id, Shop item) {
        return dbGateway.update(tableName, id, loadJSONFromShop(item));

    }

    /**
     * View all shops json object.
     *
     * @return Gets all items from the collection "Shop" in the database in a JSONObject
     */
    public JSONObject viewAllShops() {
        return dbGateway.getCollection("Shop");


    }

    /**
     * Method for creating a new shop entry in the database
     *
     * @param item the new shop information
     * @return the id of the new shop entry
     */
    @Override
    public String create(Shop item) {
        return dbGateway.create(tableName, loadJSONFromShop(item));
    }

    /**
     * Method for retrieving multiple shops from the database
     *
     * @param parameter the parameter to search by
     * @param needle    the value of the parameter to find
     * @return a list of shop entities that match the requirements
     */
    @Override
    public List<Shop> readMultiple(String parameter, String needle) {
        List<Shop> shopList = new ArrayList<>();
        List<JSONObject> rawShops = dbGateway.readMultiple(tableName, parameter, needle);
        for (JSONObject rawShop : rawShops) {
            shopList.add(loadShopFromJSON(rawShop));
        }
        return shopList;
    }


    /**
     * Method for retrieving a shop from the database
     *
     * @param fieldName the field to search by
     * @param needle    the value of the field to find
     * @return a shop entity that matches the requirements
     */
    @Override
    public Shop findOneByFieldName(String fieldName, String needle) {
        return loadShopFromJSON(dbGateway.readOne(tableName, fieldName, needle));
    }

    /**
     * Method for converting a shop entity to a JSON object
     *
     * @param shop the shop entity
     * @return the corresponding JSON object
     */
    public JSONObject loadJSONFromShop(Shop shop) {
        return new JSONObject(shop.toString());
    }


    /**
     * Method for converting a JSON object to a shop entity
     *
     * @param rawShop the JSON data
     * @return the corresponding shop entity
     */
    public Shop loadShopFromJSON(JSONObject rawShop) {
        if (rawShop == null) {
            return null;
        }
        OrderDB orderLoader = new OrderDB(dbGateway);
        try {
            String id = rawShop.getString("id");
            String name = rawShop.getString("name");
            String location = rawShop.getString("location");
            boolean isOpen = rawShop.getBoolean("isOpen");
            JSONObject rawMenu = rawShop.getJSONObject("menu");
            List<Order> orderList = orderLoader.readMultiple("shopId", id);
            OrderBook orderBook = new OrderBook(orderList);
            Menu menu = loadMenuFromJSON(rawMenu);
            return new Shop(id, name, location, isOpen, menu, orderBook);
        } catch (NullPointerException | JSONException err) {
            return null;
        }
    }

    /**
     * Method for converting a JSON object to a menu entity
     *
     * @param rawMenu the JSON data
     * @return the corresponding menu entity
     */
    public Menu loadMenuFromJSON(JSONObject rawMenu) {
        FoodDB foodLoader = new FoodDB(dbGateway);
        AddonDB addonLoader = new AddonDB(dbGateway);
        try {
            JSONArray rawFoods = rawMenu.getJSONArray("foods");
            JSONArray rawAddons = rawMenu.getJSONArray("addons");
            List<Food> foods = new ArrayList<>();
            for (int i = 0; i < rawFoods.length(); i++) {
                foods.add(foodLoader.read(rawFoods.getString(i)));
            }
            List<Addon> addons = new ArrayList<>();
            for (int i = 0; i < rawAddons.length(); i++) {
                addons.add(addonLoader.read(rawAddons.getString(i)));
            }
            return new Menu(foods, addons);
        } catch (JSONException e) {
            return null;
        }
    }

}
