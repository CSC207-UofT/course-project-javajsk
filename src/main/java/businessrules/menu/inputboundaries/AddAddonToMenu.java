package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

/**
 * Input Boundary for  AddAddonToMenuInteractor
 */
public interface AddAddonToMenu {
    /**
     * Method for adding an addon to a menu
     * @param vendorToken the vendor token
     * @param addon the addon entity
     * @return a response object
     */
    ResponseObject addAddon(String vendorToken, Addon addon);
}
