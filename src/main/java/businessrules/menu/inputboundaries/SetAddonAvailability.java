package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Addon;

public interface SetAddonAvailability {
    ResponseObject setAddonAvailability(String vendorToken, Addon addon, boolean newAvailability);
}
