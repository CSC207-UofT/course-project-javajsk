package businessrules.food.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

public interface CreateFood {
    ResponseObject createFood(String vendorToken, Food food);
}
