package businessrules.dai;

import org.json.JSONObject;

public interface SingletonRepository {
    String createSingleton(JSONObject data);

    boolean updateSingleton(String id, JSONObject data);

    boolean deleteSingleton(String id);

    JSONObject readSingleton(String id);
}
