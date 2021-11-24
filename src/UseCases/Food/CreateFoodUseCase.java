package UseCases.Food;

import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;
import Entities.Interfaces.IVendor;
import Entities.Regular.RegularFood;
import UseCases.DataAccessInterfaces.FoodRepository;
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
    ErrorPopup errorDisplay;
    FoodModel foodModel;

    /**
     * Class constructor with all attributes as required parameters
     */
    public CreateFoodUseCase(FoodRepository foodRepo, VendorRepository vendorRepo,
                             ErrorPopup error, FoodModel foodM){
        this.foodRepository = foodRepo;
        this.vendorRepository = vendorRepo;
        this.errorDisplay = error;
        this.foodModel = foodM;
    }

    /**
     * Creates food object sends update to output boundary
     *
     * @param userToken current user's token
     * @param id id of food object
     * @param name name of food object
     * @param desc description of food object
     * @param price price of food object
     * @param singletons list of singletons
     * @return whether food object was successfully created and saved
     */
    @Override
    public boolean createFood(String userToken, String id, String name, String desc,
                              float price, List<ISingleton> singletons){
        IVendor vendor = vendorRepository.getUserFromToken(userToken);
        if(vendor != null) {
            IFood food = new RegularFood(id, name, desc, price, singletons);
            return foodRepository.save(food);
        }

        errorDisplay.displayError("Vendor must be logged in.");
        return false;
    }
}

