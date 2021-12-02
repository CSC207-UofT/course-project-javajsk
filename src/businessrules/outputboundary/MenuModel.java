package businessrules.outputboundary;

import org.json.JSONObject;

public interface MenuModel {
    public JSONObject displayMenu(JSONObject data);

    public JSONObject displayError(String error);
}
