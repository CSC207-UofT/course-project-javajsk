package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Food;

public interface RemoveFoodFromMenu {
    ResponseObject removeFood(String vendorToken, Food food);
}
