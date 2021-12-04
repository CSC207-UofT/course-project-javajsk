package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

public interface SetAddonAvailability {
    ResponseObject setAddonAvailability(String vendorToken, Addon addon, boolean newAvailability);
}
