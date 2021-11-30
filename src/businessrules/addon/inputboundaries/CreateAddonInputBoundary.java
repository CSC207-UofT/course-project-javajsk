package businessrules.addon.inputboundaries;

import org.json.JSONObject;

public interface CreateAddonInputBoundary {
    boolean createAddon(String vendorToken, JSONObject data);
}
