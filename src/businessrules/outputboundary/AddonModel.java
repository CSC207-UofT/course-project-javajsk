package businessrules.outputboundary;

import org.json.JSONObject;

public interface AddonModel {
    JSONObject displayAddon(JSONObject addon);

    JSONObject displayError(String errorMessage);
}
