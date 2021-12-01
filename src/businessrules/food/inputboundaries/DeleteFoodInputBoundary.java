package businessrules.food.inputboundaries;

public interface DeleteFoodInputBoundary {
    boolean deleteFood(String vendorToken, String id);
}
