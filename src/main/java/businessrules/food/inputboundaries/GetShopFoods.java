package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetShopFoods {
    ResponseObject getShopFoods(String shopId);
}
