package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.AddSingleton;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Food;
import entities.Singleton;
import entities.Vendor;

/**
 * Use case for adding a singleton to a repository
 */
public class AddSingletonInteractor implements AddSingleton {
    VendorRepository vendorRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Food> foodObjectBoundary;
    VendorBoundary vendorBoundary;


    /**
     * Instantiates a use case for adding singleton entities to a repository
     * @param vR the vendor repository
     * @param fR the food repository
     * @param rB the repository boundary
     * @param fOB the food object boundary
     * @param vB the vendor boundary
     */
    public AddSingletonInteractor(VendorRepository vR, Repository<Food> fR, RepositoryBoundary rB,
                                  ObjectBoundary<Food> fOB, VendorBoundary vB) {
        this.vendorRepository = vR;
        this.foodRepository = fR;
        this.repositoryBoundary = rB;
        this.foodObjectBoundary = fOB;
        this.vendorBoundary = vB;
    }

    /**
     * Method for adding a new singleton
     * @param vendorToken the token of the vendor
     * @param foodId the food id
     * @param singleton the singleton entity
     * @return a response object
     */
    @Override
    public ResponseObject addSingleton(String vendorToken, String foodId, Singleton singleton) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found.");
        }

        Food food = foodRepository.read(foodId);

        if(food == null){
            return repositoryBoundary.queryNotFound("No such food found.");
        }

        if(!food.getShopId().equals(vendor.getShop().getId())){
            return vendorBoundary.unauthorizedAccess("You do not have authority to modify this food.");
        }

        if(!food.getShopId().equals(singleton.getShopId())){
            return vendorBoundary.error("The singleton does not belong to your shop.");
        }

        food.addSingleton(singleton);

        if(!foodRepository.update(food.getId(), food)){
            return repositoryBoundary.modificationFailed("Failed to update food.");
        }

        return foodObjectBoundary.showObject(food);

    }
}
