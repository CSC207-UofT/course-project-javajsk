package businessrules.addon.usecases;

import businessrules.addon.Factory;
import entities.Addon;
import org.json.JSONObject;

public class AddonFactory implements Factory<Addon> {

    @Override
    public Addon get(JSONObject data) {
        /*String type = data.getString("type");
        switch(type){
            // NOT NEEDED
            case "regular":
                return new Addon(data.getString("id"), data.getString("name"), data.getFloat("price"),
                        getListFromJSONArray(data.getJSONArray("addonTypes")), data.getBoolean("isAvailable"));
        }
         */
        return null;
    }


}


