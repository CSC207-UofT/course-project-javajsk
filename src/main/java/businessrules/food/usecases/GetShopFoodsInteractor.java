package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.food.inputboundaries.GetShopFoods;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Food;

import java.util.List;

public class GetShopFoodsInteractor implements GetShopFoods {
    Repository<Food> foodRepository;
    ObjectBoundary<Food> foodObjectBoundary;

    @Override
    public ResponseObject getShopFoods(String shopId) {
        List<Food> foodList = foodRepository.readMultiple("shopId", shopId);
        return foodObjectBoundary.showObjectList(foodList);
    }
}
