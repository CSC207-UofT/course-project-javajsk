package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

/**
 * Input Boundary for AddFoodToMenuInteractor
 */
public interface AddFoodToMenu {
    /**
     * Method for adding a food entity to the menu
     *
     * @param vendorToken the vendor token
     * @param food        the food entity
     * @return a response object
     */
    ResponseObject addFood(String vendorToken, Food food);
}
