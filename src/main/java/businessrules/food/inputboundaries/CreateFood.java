package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

/**
 * Input Boundary for CreateFoodInteractor
 */
public interface CreateFood {
    /**
     * Method for creating a new food
     * @param vendorToken the vendor token
     * @param food the food entity
     * @return a response object
     */
    ResponseObject createFood(String vendorToken, Food food);
}
