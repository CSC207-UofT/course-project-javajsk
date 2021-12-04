package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

public interface CreateSingleton {
    ResponseObject createSingleton(String vendorToken, Singleton singleton);
}
