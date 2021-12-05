package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

public interface ModifySingleton {
    /**
     * Replaces the Singleton with the specified id
     * owned by the specified vendor with a new Singleton.
     * Must be used by the vendor that owns the specified Singleton.
     *
     * @param vendorToken  the token of the vendor modifying the Singleton
     * @param singletonId  the id of the Singleton to be modified
     * @param newSingleton the Singleton replacing the original
     */
    ResponseObject modifySingleton(String vendorToken, String singletonId, Singleton newSingleton);
}
