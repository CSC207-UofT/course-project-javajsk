package businessrules.outputboundary;

import org.json.JSONObject;

public interface AddonModel {
    void displayAddon(JSONObject addon);

    void updateAddon(String id, JSONObject addon);

    void deleteAddon(String id);
}
