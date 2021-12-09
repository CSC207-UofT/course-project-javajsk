package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

/**
 * Input Boundary for ModifyFoodInteractor
 */
public interface ModifyFood {
    /**
     * Method for modifying a food entry
     *
     * @param vendorToken the vendor token
     * @param foodId      the food id
     * @param food        the food entity
     * @return a response object
     */
    ResponseObject modifyFood(String vendorToken, String foodId, Food food);
}
