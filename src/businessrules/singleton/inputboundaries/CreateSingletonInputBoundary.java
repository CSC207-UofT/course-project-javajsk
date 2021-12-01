package businessrules.singleton.inputboundaries;

import org.json.JSONObject;

public interface CreateSingletonInputBoundary {
    boolean createSingleton(String vendorToken, JSONObject data);
}
