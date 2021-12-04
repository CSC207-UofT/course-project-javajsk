package businessrules.addon.inputboundaries;

import org.json.JSONObject;

/**
 * Input boundary for read addon use case - indicates the method and parameters that must be
 * implemented in the readAddonUseCase
 */
public interface ReadAddonInputBoundary {
    /**
     * A method that gets the data for an addon from the given id
     * @param id id of an addon
     * @return JSONObject containing error messages or addon information
     */
    JSONObject readAddon(String id);
}
