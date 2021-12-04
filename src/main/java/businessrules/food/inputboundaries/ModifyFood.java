package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

public interface ModifyFood {
    ResponseObject modifyFood(String vendorToken, String foodId, Food food);
}
