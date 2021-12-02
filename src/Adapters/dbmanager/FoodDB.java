package Adapters.dbmanager;

import Adapters.DatabaseGateway;
import businessrules.dai.FoodRepository;
import org.json.JSONObject;

public class FoodDB implements FoodRepository{
    DatabaseGateway databaseGateway;
    String TABLE = "food";
    @Override
    public String createFood(JSONObject data) {
        return databaseGateway.createEntry(TABLE, data);
    }

    @Override
    public boolean updateFood(String id, JSONObject data) {
        return databaseGateway.updateEntry(TABLE, id, data);
    }

    @Override
    public boolean deleteFood(String id) {
        return databaseGateway.deleteEntry(TABLE, id);
    }

    @Override
    public JSONObject readFood(String id) {
        return databaseGateway.readEntry(TABLE, id);
    }
}
