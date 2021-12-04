package businessrules.menu.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;

public interface RemoveFoodFromMenu {
    ResponseObject removeFood(String vendorToken, Food food);
}
