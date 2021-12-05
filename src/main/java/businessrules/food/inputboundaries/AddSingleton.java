package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Singleton;

/**
 * Input Boundary for AddSingletonInteractor
 */
public interface AddSingleton {
    /**
     * Method for adding a new singleton
     * @param vendorToken the token of the vendor
     * @param foodId the food id
     * @param singleton the singleton entity
     * @return a response object
     */
    ResponseObject addSingleton(String vendorToken, String foodId, Singleton singleton);
}
