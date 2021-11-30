package UseCases.Food;

import Entities.Interfaces.ISingleton;

import java.util.List;

/**
 * Input boundary for modifying food use case
 */
public interface ChangeFoodInputBoundary {
    /**
     *  A method that changes a food item with the given parameters and returned
     *  whether the item was successfully created
     * @param token token of current user that is logged in
     * @param shopId id of shop to add food item to
     * @param foodId if of food that user wants to modify
     * @param name name of food item
     * @param desc description of food item
     * @param price price of food item
     * @param singletons list of singletons
     * @return whether food object was successfully created and saved
     */
    boolean setFood(String token, String shopId, String foodId, String name,
                    String desc, float price, List<ISingleton> singletons);

}
