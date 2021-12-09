package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

/**
 * Input Boundary for RemoveFoodFromMenuInteractor
 */
public interface RemoveFoodFromMenu {
    /**
     * Method for removing food entities from a menu
     *
     * @param vendorToken vendor token
     * @param food        the food entity
     * @return a response object
     */
    ResponseObject removeFood(String vendorToken, Food food);
}
