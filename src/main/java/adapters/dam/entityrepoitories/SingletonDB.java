package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
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

    @Override
    public Singleton read(String id) {
        return loadSingletonFromJSON(dbConnector.read(tableName, id));
    }

    @Override
    public boolean update(String id, Singleton item) {
        return dbConnector.update(tableName, id, loadJSONFromSingleton(item));

    }

    @Override
    public String create(Singleton item) {
        return dbConnector.create(tableName, loadJSONFromSingleton(item));
    }

    @Override
    public List<Singleton> readMultiple(String parameter, String needle) {
        List<Singleton> singletonList = new ArrayList<>();
        List<JSONObject> rawSingletons = dbConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawSingleton: rawSingletons){
            singletonList.add(loadSingletonFromJSON(rawSingleton));
        }
        return singletonList;
    }

    @Override
    public Singleton findOneByFieldName(String fieldName, String needle) {
        return loadSingletonFromJSON(dbConnector.readOne(tableName,fieldName,needle));
    }

    public static JSONObject loadJSONFromSingleton(Singleton singleton){
        return new JSONObject(singleton.toString());
    }

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
            e.printStackTrace();
            return null;
        }
    }
}
