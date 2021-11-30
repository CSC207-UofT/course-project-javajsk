package businessrules.addon.inputboundaries;

import org.json.JSONObject;

public interface UpdateAddonInputBoundary {
    boolean updateAddon(String vendorToken, JSONObject object);
}
