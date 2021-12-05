package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

/**
 * Input Boundary for SetSingletonAvailabilityInteractor
 */
public interface SetSingletonAvailability {
    /**
     * Method for setting the availability of a singleton
     * @param vendorToken the vendor token
     * @param singleton the singleton entity
     * @param newAvailability the new availability
     * @return a response object
     */
    ResponseObject setSingletonAvailability(String vendorToken, Singleton singleton, boolean newAvailability);
}
