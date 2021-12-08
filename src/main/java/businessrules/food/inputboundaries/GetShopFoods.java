package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for GetShopFoodsInteractor
 */
public interface GetShopFoods {

    /**
     * Method for getting the foods of a shop
     * @param shopId the shop id
     * @return a response object
     */
    ResponseObject getShopFoods(String shopId);
}
