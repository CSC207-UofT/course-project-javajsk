package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

public interface AddAddonToMenu {
    ResponseObject addAddon(String vendorToken, Addon addon);
}
