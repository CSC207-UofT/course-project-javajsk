package businessrules.addon.inputboundaries;

import org.json.JSONObject;

/**
 * Input Boundary for Create Addon
 *
 * indicates the methods and parameters that the CreateAddonUsecase must implement
 */
public interface CreateAddonInputBoundary {
    /**
     * A method that creates an Addon entity and returns its JSONObject representation
     * @param vendorToken token of current vendor
     * @param data information to create the addon with
     * @return JSONObject representing the addon object or error message
     */
    JSONObject createAddon(String vendorToken, JSONObject data);
}
