package UseCases.Food;

public interface DeleteFoodInputBoundary{
    /**
     * Method returns whether a food item was successfully deleted
     * @param token tok of current user
     * @param foodId id of food item to delete
     * @return whether food item was deleted
     */
    boolean deleteFood(String token, String foodId);
}
