package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.AddSingleton;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Singleton;
import EntitiesUnitTest.Vendor;

public class AddSingletonInteractor implements AddSingleton {
    VendorRepository vendorRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Food> foodObjectBoundary;
    VendorBoundary vendorBoundary;

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
