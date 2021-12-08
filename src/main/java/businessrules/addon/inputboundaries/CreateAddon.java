package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

/**
 * Input boundary for CreateAddonInteractor
 */
public interface CreateAddon {
    /**
     * A method that creates an Addon entity and returns a response object containing
     * message/content for user interface
     * @param vendorToken token of current vendor
     * @param addon information to create the addon with
     * @return response object containing the addon object or error message
     */
    ResponseObject createAddon(String vendorToken, Addon addon);
}
