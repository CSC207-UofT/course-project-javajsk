package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.food.inputboundaries.CreateFood;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Vendor;

import java.util.List;

public class CreateFoodInteractor implements CreateFood {
    VendorRepository vendorRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Food> foodObjectBoundary;

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
