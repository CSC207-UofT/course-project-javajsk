package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

/**
 * Input boundary for ModifyAddonInteractor
 */
public interface ModifyAddon {
    /**
     * Method replaces addon with given id with attributes from given addon and
     * returns a response object containing the modified addon or error message
     *
     * vendor must be logged in and own the addon they want to modify
     * @param vendorToken token of current vendor
     * @param id id of addon to modify
     * @param addon new addon
     * @return response object containing addon or error message
     */
    ResponseObject modifyAddon(String vendorToken, String id, Addon addon);
}
