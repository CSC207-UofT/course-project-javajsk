package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.ModifyFood;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Vendor;

public class ModifyFoodInteractor implements ModifyFood {
    VendorRepository vendorRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Food> foodObjectBoundary;
    VendorBoundary vendorBoundary;
    @Override
    public ResponseObject modifyFood(String vendorToken, String foodId, Food food) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if(vendor == null){
            return repositoryBoundary.queryNotFound("No such vendor found");
        }

        Food oldFood = foodRepository.read(foodId);
        if(oldFood == null){
            return repositoryBoundary.queryNotFound("No such food found");
        }

        if(!oldFood.getShopId().equals(vendor.getShop().getId())){
            return vendorBoundary.unauthorizedAccess("You do not have access to modify this food.");
        }

        if(!food.getId().equals(oldFood.getId())){
            return vendorBoundary.error("Food ids cannot be altered.");
        }

        if(!food.getShopId().equals(oldFood.getShopId())){
            return vendorBoundary.error("ShopId cannot be altered.");
        }

        if(!foodRepository.update(food.getId(), food)){
            return repositoryBoundary.modificationFailed("Failed to update food.");
        }
        return foodObjectBoundary.showObject(food);
    }
}
