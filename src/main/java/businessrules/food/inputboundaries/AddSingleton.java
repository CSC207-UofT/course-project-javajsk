package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

public interface AddSingleton {
    ResponseObject addSingleton(String vendorToken, String foodId, Singleton singleton);
}
