package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.ModifyFood;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Food;
import entities.Vendor;

/**
 * Use case for modifying a food entry in a repository
 */
public class ModifyFoodInteractor implements ModifyFood {
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Food object boundary.
     */
    ObjectBoundary<Food> foodObjectBoundary;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;

    /**
     * Instantiates a use case for modifying a food entry in a repository
     *
     * @param vR  the vendor repository
     * @param fR  the food repository
     * @param rB  the repository boundary
     * @param fOB the food object boundary
     * @param vB  the vendor boundary
     */
    public ModifyFoodInteractor(VendorRepository vR, Repository<Food> fR, RepositoryBoundary rB,
                                ObjectBoundary<Food> fOB, VendorBoundary vB) {
        this.vendorRepository = vR;
        this.foodRepository = fR;
        this.repositoryBoundary = rB;
        this.foodObjectBoundary = fOB;
        this.vendorBoundary = vB;
    }

    /**
     * Method for modifying a food entry
     *
     * @param vendorToken the vendor token
     * @param foodId      the food id
     * @param food        the food entity
     * @return a response object
     */
    @Override
    public ResponseObject modifyFood(String vendorToken, String foodId, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return repositoryBoundary.queryNotFound("No such vendor found");
        }

        Food oldFood = foodRepository.read(foodId);
        if (oldFood == null) {
            return repositoryBoundary.queryNotFound("No such food found");
        }

        if (!oldFood.getShopId().equals(vendor.getShop().getId())) {
            return vendorBoundary.unauthorizedAccess("You do not have access to modify this food.");
        }

        if (!food.getId().equals(oldFood.getId())) {
            return vendorBoundary.error("Food ids cannot be altered.");
        }

        if (!food.getShopId().equals(oldFood.getShopId())) {
            return vendorBoundary.error("ShopId cannot be altered.");
        }

        if (!foodRepository.update(food.getId(), food)) {
            return repositoryBoundary.modificationFailed("Failed to update food.");
        }
        return foodObjectBoundary.showObject(food);
    }
}
