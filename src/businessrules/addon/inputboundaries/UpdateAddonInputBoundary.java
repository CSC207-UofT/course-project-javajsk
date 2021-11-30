package businessrules.addon.inputboundaries;

import org.json.JSONObject;

public interface UpdateAddonInputBoundary {
    boolean updateAddon(String id, JSONObject object);
}
