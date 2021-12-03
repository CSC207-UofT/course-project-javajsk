package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Food;
import org.json.JSONObject;

import java.util.List;

public class FoodDB implements Repository<Food> {
    DBGateway databaseConnector;

    public FoodDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Food read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Food item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String create(Food item) {
        return null;
    }

    @Override
    public List<Food> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Food findOneByFieldName(String fieldName, String needle) {
        return null;
    }

    public Food loadFoodFromJSON(JSONObject rawData){

    }
}
