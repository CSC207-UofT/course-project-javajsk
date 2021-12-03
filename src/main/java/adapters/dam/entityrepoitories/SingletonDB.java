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
    static final String[] keys = {"id","price","name","description",
            "allowedAddonTypes","defaultSelection",
            "availability","shopId"};
    @Override
    public Singleton read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Singleton item) {
        return false;
    }

    @Override
    public String create(Singleton item) {
        return null;
    }

    @Override
    public List<Singleton> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Singleton findOneByFieldName(String fieldName, String needle) {
        return null;
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

            boolean availability = rawSelection.getBoolean("availability");

            String shopId = rawSelection.getString("shopId");

            return new Singleton(id, price, name, description, allowedAddonTypes, defaultSel, availability,
                    shopId);

        }catch (JSONException e){
            return null;
        }
    }
}
