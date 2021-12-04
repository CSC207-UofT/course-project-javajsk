package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Food;

public interface CreateFood {
    ResponseObject createFood(String vendorToken, Food food);
}
