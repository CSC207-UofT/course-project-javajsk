package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Food;

public interface AddFoodToMenu {
    ResponseObject addFood(String vendorToken, Food food);
}
