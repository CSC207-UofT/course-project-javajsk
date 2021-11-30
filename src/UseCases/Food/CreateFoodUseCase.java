package UseCases.Food;

import Entities.Interfaces.IFood;
import Entities.Interfaces.IShop;
import Entities.Interfaces.ISingleton;
import Entities.Interfaces.IVendor;
import Entities.Menu;
import Entities.Regular.RegularFood;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.DataAccessInterfaces.ShopRepository;
import UseCases.DataAccessInterfaces.VendorRepository;
import UseCases.OutputBoundary.ErrorPopup;
import UseCases.OutputBoundary.FoodModel;

import java.util.List;

/**
 * A use case for handling the creation of a new food object
 */
public class CreateFoodUseCase implements CreateFoodInputBoundary {
    FoodRepository foodRepository;
    VendorRepository vendorRepository;
    ShopRepository shopRepository;
    ErrorPopup errorDisplay;
    FoodModel foodModel;

    /**
     * Class constructor with all attributes as required parameters
     */
    public CreateFoodUseCase(FoodRepository foodRepo, VendorRepository vendorRepo,
                             ShopRepository shopRepo, ErrorPopup error, FoodModel foodM){
        this.foodRepository = foodRepo;
        this.vendorRepository = vendorRepo;
        this.shopRepository = shopRepo;
        this.errorDisplay = error;
        this.foodModel = foodM;
    }

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
    @Override
    public boolean createFood(String token, String shopId, String name, String desc,
                              float price, List<ISingleton> singletons){

        IVendor vendor = (IVendor)vendorRepository.getUserFromToken(token);
        if(vendor == null) {
            errorDisplay.displayError("Vendor must be logged in.");
            return false;
        }

        IShop shop = vendor.getShop(shopId);
        if(shop == null) {
            errorDisplay.displayError("Error. Could not locate shop.");
            return false;
        }

        Menu menu = shop.getMenu();
        IFood food = new RegularFood(null, name, price, false, singletons);


        // Check if food is already on the menu
        if(menu.hasFood(food)){
            errorDisplay.displayError("Error. This food item already exists on your menu.");
            return false;
        }

        boolean success = foodRepository.save(food);
        if(!success){
            errorDisplay.displayError("Error. Unable to save new food item.");
            return false;
        }

        menu.addFood(food);
        shop.setMenu(menu);
        vendor.updateShop(shopId, shop); //no need to check if true as already checked that shopId exists in vendor

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

        foodModel.displayFood(food);
        return true;
    }
}

