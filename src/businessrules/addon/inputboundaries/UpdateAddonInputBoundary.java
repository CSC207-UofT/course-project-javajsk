package businessrules.addon.inputboundaries;

import org.json.JSONObject;

public interface UpdateAddonInputBoundary {
    JSONObject updateAddon(String vendorToken, String addonId, JSONObject object);
}
