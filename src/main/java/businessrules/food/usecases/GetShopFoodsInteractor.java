package businessrules.food.usecases;

import businessrules.dai.Repository;
import businessrules.food.inputboundaries.GetShopFoods;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Food;

import java.util.List;

/**
 * Use case for getting a shops foods from a repository
 */
public class GetShopFoodsInteractor implements GetShopFoods {
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository;
    /**
     * The Food object boundary.
     */
    ObjectBoundary<Food> foodObjectBoundary;

    /**
     * Instantiates a use case for getting shop foods
     *
     * @param fR  the food repository
     * @param fOB the food object boundary
     */
    public GetShopFoodsInteractor(Repository<Food> fR, ObjectBoundary<Food> fOB) {
        this.foodRepository = fR;
        this.foodObjectBoundary = fOB;
    }

    /**
     * Method for getting the foods of a shop
     * @param shopId the shop id
     * @return a response object
     */
    @Override
    public ResponseObject getShopFoods(String shopId) {
        List<Food> foodList = foodRepository.readMultiple("shopId", shopId);
        return foodObjectBoundary.showObjectList(foodList);
    }
}
