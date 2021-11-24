package UseCases.Food;

import Entities.Interfaces.IVendor;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodModel;

/**
 * A use case for deleting food items
 */
public class DeleteFoodUseCase implements DeleteFoodInputBoundary{
    FoodRepository foodRepository;
    VendorRepository vendorRepository;
    ErrorPopup errorDisplay;
    FoodModel foodModel;

    /**
     * Class constructor with all attributes as required parameters
     */
    public DeleteFoodUseCase(FoodRepository foodRepo, VendorRepository vendorRepo,
                             ErrorPopup error, FoodModel foodM){
        this.foodRepository = foodRepo;
        this.vendorRepository = vendorRepo;
        this.errorDisplay = error;
        this.foodModel = foodM;
    }

    /**
     * A method to delete food item from higher level data structure
     * @param token token of current user
     * @param foodId id of food item to delete
     * @return whether food was successfully deleted
     */
    @Override
    public boolean deleteFood(String token, String foodId) {
        IVendor vendor = vendorRepository.getUserFromToken(token);
        if(vendor != null){
            return foodRepository.deleteFood(foodId);
        }
        return false;
    }
}
