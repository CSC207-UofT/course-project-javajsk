package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

public interface RemoveAddonFromMenu {
    ResponseObject removeAddon(String vendorToken, Addon addon);

}
