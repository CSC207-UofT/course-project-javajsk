package businessrules.outputboundary;

import org.json.JSONObject;

public interface MenuModel {
    void displayMenu(JSONObject menu);

    void clearMenu(String id, JSONObject menu);
}
