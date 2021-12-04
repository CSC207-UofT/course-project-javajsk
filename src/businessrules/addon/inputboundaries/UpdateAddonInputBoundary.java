package businessrules.addon.inputboundaries;

import org.json.JSONObject;

/**
 * Input boundary for UpdateAddonUseCase
 */
public interface UpdateAddonInputBoundary {
    /**
     * A method that updates an addon with given id and data
     * @param vendorToken token of current vendor
     * @param addonId id of addon to update
     * @param object data to update addon
     * @return data containing error message or updated addon data
     */
    JSONObject updateAddon(String vendorToken, String addonId, JSONObject object);
}
