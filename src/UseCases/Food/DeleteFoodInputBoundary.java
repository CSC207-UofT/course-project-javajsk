package UseCases.Food;

/**
 * Input boundary for DeleteFoodUseCase that indicates necessary parameters
 */
public interface DeleteFoodInputBoundary{
    /**
     * Method returns whether a food item was successfully deleted
     * @param token token of current user
     * @param shopId id of shop that user wants to delete food from
     * @param foodId id of food item to delete
     * @return whether food item was deleted
     */
    boolean deleteFood(String token, String shopId, String foodId);
}
