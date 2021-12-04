package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Addon;

public interface RemoveAddonFromMenu {
    ResponseObject removeAddon(String vendorToken, Addon addon);

}
