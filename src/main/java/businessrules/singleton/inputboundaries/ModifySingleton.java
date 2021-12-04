package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

public interface ModifySingleton {
    ResponseObject modifySingleton(String vendorToken, String singletonId, Singleton newSingleton);
}
