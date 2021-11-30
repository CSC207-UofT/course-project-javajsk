package UseCases.Food;

import Entities.Interfaces.ISingleton;

import java.util.List;

/**
 * Input boundary for CreateFoodUseCase indicates necessary parameters
 */
public interface CreateFoodInputBoundary {
    /**
     *  A method that creates a new food item with the given parameters and returned
     *  whether the item was successfully created
     * @param token token of current user that is logged in
     * @param shopId id of shop to add food item to
     * @param name name of food item
     * @param desc description of food item
     * @param price price of food item
     * @param singletons list of singletons
     * @return whether food object was successfully created and saved
     */
    boolean createFood(String token, String shopId, String name, String desc, float price, List<ISingleton> singletons);
}