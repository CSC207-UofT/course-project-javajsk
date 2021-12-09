package adapters.dam;

import adapters.DBGateway;
import businessrules.dai.Repository;
import entities.Selection;
import entities.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SingletonDB implements Repository<Singleton> {
    DBGateway dbConnector;
    final String tableName = "Singleton";

    static final String[] keys = {"id","price","name","description",
            "allowedAddonTypes","defaultSelection",
            "isAvailable","shopId"};

    public SingletonDB(DBGateway dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * @param id the id of the entry to get from the database
     * @return the constructed object of the given id from the database
     */
    @Override
    public Singleton read(String id) {
        return loadSingletonFromJSON(dbConnector.read(tableName, id));
    }

    /**
     * Method for updating a singleton entry in the database
     * @param id the id of the singleton entry
     * @param item the updated singleton information
     * @return whether the update was successful or not
     */
    @Override
    public boolean update(String id, Singleton item) {
        return dbConnector.update(tableName, id, loadJSONFromSingleton(item));

    }
    /**
     * Method for creating a new shop singleton in the database
     * @param item the new singleton information
     * @return the id of the new singleton entry
     */
    @Override
    public String create(Singleton item) {
        return dbConnector.create(tableName, loadJSONFromSingleton(item));
    }


    /**
     * Method for retrieving multiple singletons from the database
     * @param parameter the parameter to search by
     * @param needle the value of the parameter to find
     * @return a list of singleton entities that match the requirements
     */
    @Override
    public List<Singleton> readMultiple(String parameter, String needle) {
        List<Singleton> singletonList = new ArrayList<>();
        List<JSONObject> rawSingletons = dbConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawSingleton: rawSingletons){
            singletonList.add(loadSingletonFromJSON(rawSingleton));
        }
        return singletonList;
    }

    /**
     * Method for retrieving a singleton from the database
     * @param fieldName the field to search by
     * @param needle the value of the field to find
     * @return a singleton entity that matches the requirements
     */
    @Override
    public Singleton findOneByFieldName(String fieldName, String needle) {
        return loadSingletonFromJSON(dbConnector.readOne(tableName,fieldName,needle));
    }
    /**
     * Method for converting a singleton entity to a JSON object
     * @param singleton the singleton entity
     * @return the corresponding JSON object
     */
    public static JSONObject loadJSONFromSingleton(Singleton singleton){
        return new JSONObject(singleton.toString());
    }

    /**
     * Method for converting a JSON object to a singleton entity
     * @param rawSingleton the JSON data
     * @return the corresponding singleton entity
     */
    public Singleton loadSingletonFromJSON(JSONObject rawSingleton){
        CartDB selectionLoader = new CartDB(dbConnector);
        for(String key: keys){
            if(!rawSingleton.has(key)){
                return null;
            }
        }

        try{
            String id = rawSingleton.getString("id");
            float price = rawSingleton.getFloat("price");
            String name = rawSingleton.getString("name");
            String description = rawSingleton.getString("description");
            JSONArray allowedAddonTypesRaw = rawSingleton.getJSONArray("allowedAddonTypes");
            List<Integer> allowedAddonTypes = new ArrayList<>();
            for(int i =0;i< allowedAddonTypesRaw.length(); i++){
                allowedAddonTypes.add(allowedAddonTypesRaw.getInt(i));
            }
            JSONObject rawSelection = rawSingleton.getJSONObject("defaultSelection");
            Selection defaultSel = selectionLoader.parseSelection(rawSelection);

            boolean availability = rawSingleton.getBoolean("isAvailable");

            String shopId = rawSingleton.getString("shopId");

            return new Singleton(id, price, name, description, allowedAddonTypes, defaultSel, availability,
                    shopId);

        }catch (JSONException e){
            return null;
        }
    }
}
