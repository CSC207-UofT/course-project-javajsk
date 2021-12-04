package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Singleton;

public interface CreateSingleton {
    ResponseObject createSingleton(String vendorToken, Singleton singleton);
}
