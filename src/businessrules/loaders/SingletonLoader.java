package businessrules.loaders;

import businessrules.dai.SingletonRepository;
import businessrules.outputboundary.ErrorModel;
import entities.Addon;
import entities.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SingletonLoader {
    ErrorModel errorHandler;
    SingletonRepository SingletonRepository;

    public SingletonLoader(SingletonRepository ar, ErrorModel er){
        this.SingletonRepository = ar;
        this.errorHandler = er;
    }

    public static Singleton loadSingleton(JSONObject data) throws JSONException {
        // These are all assumed to exist (controller's job to check and ensure json is of correct format)
        String id = data.getString("id");
        float price = data.getFloat("price");
        String name = data.getString("name");
        String description = data.getString("description");
        List<Integer> allowedAddonTypes = getListFromJSONArray(data.getJSONArray("allowedAddonTypes"));
        List<Addon> defaultSelection = getAddonListFromJSONArray(data.getJSONArray("allowedAddonTypes"));
        boolean availability = data.getBoolean("availability");

        // TODO: see how this works based on Singleton's jsonify command.
        List<Integer> types = getListFromJSONArray(data.getJSONArray("types"));

        // As long as the datatypes are correct, this should work.
        return new Singleton(id, price, name, description, allowedAddonTypes, defaultSelection, availability);
    }

    public Singleton loadSingletonFromId(String id){
        JSONObject SingletonRaw = SingletonRepository.readSingleton(id);
        if(SingletonRaw == null){
            errorHandler.displayError("Unable to find Singleton with id: " + id);
            return null;
        }

        try {
            return SingletonLoader.loadSingleton(SingletonRaw);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
        }
        return null;
    }

    public static List<Integer> getListFromJSONArray(JSONArray array){
        ArrayList<Integer> arr = new ArrayList<>();
        for(Object value : array){
            arr.add((int) value);
        }
        return arr;
    }

    public static List<Addon> getAddonListFromJSONArray(JSONArray array){
        ArrayList<Addon> arr = new ArrayList<>();
        // TODO: implement this method
        return arr;
    }
}
