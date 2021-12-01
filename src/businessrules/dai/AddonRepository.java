package businessrules.dai;

import org.json.JSONObject;

public interface AddonRepository {
    // This function returns a string.
    String createAddon(JSONObject data);

    boolean updateAddon(String id, JSONObject data);

    boolean deleteAddon(String id);

    JSONObject readAddon(String id);
}
