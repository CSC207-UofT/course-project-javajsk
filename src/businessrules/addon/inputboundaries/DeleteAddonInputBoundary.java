package businessrules.addon.inputboundaries;

import org.json.JSONObject;

/**
 * Input boundary for the DeleteAddonUseCase - indicates the methods and parameters that the use case must implement
 */
public interface DeleteAddonInputBoundary {
    /**
     * A method that deletes an instance/entry of an addon
     * @param vendorToken token of current vendor
     * @param addonId id of addon
     * @return JSONObject containing empty data or error message for display
     */
    JSONObject deleteAddon(String vendorToken, String addonId);
}
