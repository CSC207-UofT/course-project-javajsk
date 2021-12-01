package businessrules.outputboundary;

import org.json.JSONObject;

public interface SingletonModel {
    void displaySingleton(JSONObject addon);

    void updateSingleton(String id, JSONObject addon);

    void deleteSingleton(String id);
}
