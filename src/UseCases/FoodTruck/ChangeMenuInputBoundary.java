package UseCases.FoodTruck;

import Entities.Menu;

public interface ChangeMenuInputBoundary {

    boolean changeMenu(String userToken, String shopID, Menu menu);
}
