package businessrules.addon.inputboundaries;

import org.json.JSONObject;

public interface DeleteAddonInputBoundary {
    JSONObject deleteAddon(String vendorToken, String addonId);
}
