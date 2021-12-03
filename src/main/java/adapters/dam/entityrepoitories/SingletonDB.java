package adapters.dam.entityrepoitories;

import businessrules.dai.Repository;
import entities.Singleton;
import org.json.JSONObject;

import java.util.List;

public class SingletonDB implements Repository<Singleton> {
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
    public boolean delete(String id) {
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

        }
    }
}
