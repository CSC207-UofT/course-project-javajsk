package UseCases.FoodTruck;

import Entities.Menu;

public interface GetMenuInputBoundary {

    Menu getMenu(String userToken, String shopid);
}
