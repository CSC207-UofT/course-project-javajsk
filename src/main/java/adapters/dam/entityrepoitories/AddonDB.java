package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Addon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddonDB implements Repository<Addon> {
    DBGateway databaseConnector;

    public AddonDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Addon read(String id) {
        return null;
    }

    @Override
    public boolean update(String id, Addon item) {
        return false;
    }

    @Override
    public String create(Addon item) {
        return null;
    }

    @Override
    public List<Addon> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Addon findOneByFieldName(String fieldName, String needle) {
        return null;
    }

    public Addon loadAddonFromJSON(JSONObject addonObj){
        if(!addonObj.has("id") || !addonObj.has("name") || !addonObj.has("price") ||
                !addonObj.has("addonTypes") || !addonObj.has("isAvailable") || !addonObj.has("shopId")  ){
            return null;
        }
        try {
            String id = addonObj.getString("id");
            String name = addonObj.getString("name");
            float price = addonObj.getFloat("price");
            JSONArray addonTypesRaw = addonObj.getJSONArray("addonTypes");
            List<Integer> addonTypes = new ArrayList<>();
            for(int i =0;i<addonTypesRaw.length(); i++){
                addonTypes.add(addonTypesRaw.getInt(i));
            }
            boolean isAvailable = addonObj.getBoolean("isAvailable");
            String shopId = addonObj.getString("shopId");
            return new Addon(id, name,price,addonTypes,isAvailable,shopId);
        }catch(JSONException e){
            return null;
        }
    }
}
