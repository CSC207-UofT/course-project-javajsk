package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.CreateFood;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Food;
import entities.Vendor;

import java.util.List;

/**
 * Use case for creating a food entry in a repository
 */
public class CreateFoodInteractor implements CreateFood {
    VendorRepository vendorRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Food> foodObjectBoundary;

    /**
     * Instantiates a use case for creating food entries in a repository
     * @param vR the vendor repository
     * @param fR the food repository
     * @param rB the repository boundary
     * @param fOB the food object boundary
     */
    public CreateFoodInteractor(VendorRepository vR, Repository<Food> fR,
                                RepositoryBoundary rB, ObjectBoundary<Food> fOB) {
        this.vendorRepository = vR;
        this.foodRepository = fR;
        this.repositoryBoundary = rB;
        this.foodObjectBoundary = fOB;
    }

    /**
     * Method for creating a new food
     * @param vendorToken the vendor token
     * @param food the food entity
     * @return a response object
     */
    @Override
    public ResponseObject createFood(String vendorToken, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);

        if(vendor == null){
            return repositoryBoundary.queryNotFound("Unable to find such a vendor.");
        }

        String foodId = foodRepository.create(food);
        if(foodId == null){
            return repositoryBoundary.creationFailed("Failed to create food.");
        }

        food.setId(foodId);
        String shopId = vendor.getShop().getId();
        List<Food> foods = foodRepository.readMultiple("shopId", shopId);

        return foodObjectBoundary.showObjectList(foods);


    }
}
