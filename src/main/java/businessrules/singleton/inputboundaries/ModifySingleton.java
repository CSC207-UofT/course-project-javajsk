package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Singleton;

public interface ModifySingleton {
    ResponseObject modifySingleton(String vendorToken, String singletonId, Singleton newSingleton);
}
