package businessrules.loaders;

import businessrules.dai.AddonRepository;
import businessrules.outputboundary.AddonModel;
import entities.Addon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddonLoader {
    AddonRepository addonRepository;
    AddonModel addonModel;

    public AddonLoader(AddonRepository ar, AddonModel am){
        this.addonRepository = ar;
        this.addonModel = am;
    }

    public Addon loadAddon(JSONObject data) throws JSONException {
        // Possibly use a factory

        // These are all assumed to exist (controller's job to check and ensure json is of correct format)
        String id = data.getString("id");
        String name = data.getString("name");
        float price = data.getFloat("price");

        boolean isAvailable = data.getBoolean("isAvailable");

        // TODO: see how this works based on Addon's jsonify command.
        List<Integer> types = getListFromJSONArray(data.getJSONArray("types"));

        // As long as the datatypes are correct, this should work.
        return new Addon(id, name, price, types, isAvailable);
    }

    public Addon loadAddonFromId(String id){
        JSONObject addonRaw = addonRepository.readAddon(id);
        if(addonRaw == null){
            addonModel.displayError("Unable to find addon with id: " + id);
            return null;
        }

        try {
            return loadAddon(addonRaw);
        }catch (JSONException e){
            addonModel.displayError(e.getMessage());
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

}
