package UseCases.FoodTruck;

import Entities.Menu;

public interface GetMenuInputBoundary {

    public Menu getMenu(String userToken, String shopid);
}
