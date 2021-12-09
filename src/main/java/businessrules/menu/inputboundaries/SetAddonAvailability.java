package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

/**
 * Input Boundary for SetAddonAvailabilityInteractor
 */
public interface SetAddonAvailability {
    /**
     * Method for setting add on availability
     *
     * @param vendorToken     the vendor token
     * @param addon           the addon entity
     * @param newAvailability the new availability
     * @return a response object
     */
    ResponseObject setAddonAvailability(String vendorToken, Addon addon, boolean newAvailability);
}
