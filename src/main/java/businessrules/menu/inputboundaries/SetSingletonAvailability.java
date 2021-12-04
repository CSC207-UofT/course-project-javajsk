package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Singleton;

public interface SetSingletonAvailability {
    ResponseObject setSingletonAvailability(String vendorToken, Singleton singleton, boolean newAvailability);
}
