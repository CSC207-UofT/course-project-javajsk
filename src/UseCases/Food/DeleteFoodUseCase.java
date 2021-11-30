package UseCases.Food;

import Entities.Interfaces.IShop;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodModel;

/**
 * A use case for deleting food items
 */
public class DeleteFoodUseCase implements DeleteFoodInputBoundary{
    FoodRepository foodRepository;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    ErrorPopup errorDisplay;
    FoodModel foodModel;

    /**
     * Class constructor with all attributes as required parameters
     */
    public DeleteFoodUseCase(FoodRepository foodRepo, VendorRepository vendorRepo,
                             ShopRepository shopRepo, ErrorPopup error, FoodModel foodM){
        this.foodRepository = foodRepo;
        this.vendorRepository = vendorRepo;
        this.shopRepository = shopRepo;
        this.errorDisplay = error;
        this.foodModel = foodM;
    }

    /**
     * A method to delete food item from higher level data structure
     * @param token token of current user
     * @param shopId id of shop that user wants to delete food from
     * @param foodId id of food item to delete
     * @return whether food was successfully deleted
     */
    @Override
    public boolean deleteFood(String token, String shopId, String foodId) {
        IVendor vendor = (IVendor) vendorRepository.getUserFromToken(token);
        if(vendor == null){
            errorDisplay.displayError("Error. Vendor must be logged in to delete.");
            return false;
        }

        IShop shop = vendor.getShop(shopId);
        if(shop == null) {
            errorDisplay.displayError("Error. Could not locate shop.");
            return false;
        }

        boolean success = foodRepository.deleteFood(foodId);
        if(!success){
            errorDisplay.displayError("Error. Unable to delete food item.");
            return false;
        }

        Menu menu = shop.getMenu();
        menu.removeFood(menu.getFood(foodId));
        shop.setMenu(menu);
        vendor.updateShop(shopId, shop);

        success = shopRepository.save(shop);
        if(!success){
            errorDisplay.displayError("Error. Unable to save new food item.");
            return false;
        }

        success = vendorRepository.save(vendor);
        if(!success){
            errorDisplay.displayError("Error. Unable to save new food item.");
            return false;
        }

        return true;
    }
}
