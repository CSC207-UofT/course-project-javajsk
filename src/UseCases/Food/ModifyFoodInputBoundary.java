package UseCases.Food;

import Entities.Interfaces.ISingleton;

import java.util.List;

/**
 * Input boundary for modifying food use case
 */
public interface ModifyFoodInputBoundary {
    /**
     * A method that sets the name
     * @param token token of current user
     * @param id id of food object to modify
     * @param name name of food object
     * @return whether name was successfully set
     */
    boolean setFoodName(String token, String id, String name);

    /**
     * A method that sets the description of the food object
     * @param token token of current user
     * @param id id of food object to modify
     * @param description description of food object
     * @return whether description was successfully set
     */
    boolean setFoodDescription(String token, String id, String description);

    /**
     * A method that sets the price of the food object
     * @param token token of current user
     * @param id id of food object to modify
     * @param price price of food object
     * @return whether price was successfully set
     */
    boolean setFoodPrice(String token, String id, float price);

    /**
     * A method that sets the description of the food object
     * @param token token of current user
     * @param id id of food object to modify
     * @param singletons list of singletons of food object
     * @return whether singletons was successfully set
     */
    boolean setFoodComponents(String token, String id, List<ISingleton> singletons);
}
