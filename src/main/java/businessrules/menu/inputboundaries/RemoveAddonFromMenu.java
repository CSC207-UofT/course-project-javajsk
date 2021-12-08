package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

/**
 * Input Boundary for RemoveAddonFromMenuInteractor
 */
public interface RemoveAddonFromMenu {
    /**
     * Methof for removing an addon entity
     * @param vendorToken vendor token
     * @param addon addon entity
     * @return a response object
     */
    ResponseObject removeAddon(String vendorToken, Addon addon);

}
