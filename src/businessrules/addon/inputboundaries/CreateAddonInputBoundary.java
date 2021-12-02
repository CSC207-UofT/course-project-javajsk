package businessrules.addon.inputboundaries;

import org.json.JSONObject;

public interface CreateAddonInputBoundary {
    JSONObject createAddon(String vendorToken, JSONObject data);
}
