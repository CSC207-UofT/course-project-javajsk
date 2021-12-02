package businessrules.singleton.inputboundaries;

import org.json.JSONObject;

public interface UpdateSingletonInputBoundary {
    boolean updateSingleton(String vendorToken, String id, JSONObject object);
}
