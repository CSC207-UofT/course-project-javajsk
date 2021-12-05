package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

public interface CreateSingleton {
    /**
     * This method creates a Singleton ResponseObject. Must be used by a vendor.
     *
     * @param vendorToken   the token of the vendor creating the singleton
     * @param singleton     the Singleton object being created
     */
    ResponseObject createSingleton(String vendorToken, Singleton singleton);
}
