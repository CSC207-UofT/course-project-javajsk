package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

public interface SetSingletonAvailability {
    ResponseObject setSingletonAvailability(String vendorToken, Singleton singleton, boolean newAvailability);
}
