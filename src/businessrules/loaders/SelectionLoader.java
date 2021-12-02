package businessrules.loaders;

import businessrules.dai.AddonRepository;
import entities.Addon;
import entities.Selection;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SelectionLoader {

    AddonLoader addonLoader;

    public SelectionLoader(AddonRepository aR) {
        this.addonLoader = new AddonLoader(aR);

    }

    public Selection loadSelection(JSONObject data) throws JSONException {
        HashMap<Addon, Integer> selection = new HashMap<>();

        for(String key: data.keySet()){
            selection.put(addonLoader.loadAddonFromId(key), data.getInt(key));
        }
        return new Selection(selection);
    }

}
