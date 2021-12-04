package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

public interface AddFoodToMenu {
    ResponseObject addFood(String vendorToken, Food food);
}
